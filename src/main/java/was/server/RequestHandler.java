package was.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import was.SimpleServlet;
import was.config.Properties;
import was.http.HttpHeader;
import was.http.HttpRequest;
import was.http.HttpRequestFactory;
import was.http.HttpResponse;
import was.servlet.SimpleServletMapper;
import was.type.HttpStatus;
import was.util.HttpUtils;

@Slf4j
public class RequestHandler extends Thread {
	
	private final static String WEB_ROOT = "webapp";
	private final static String ENCODING = "UTF-8";
	private final static String STR_EXE = ".exe";
	private final static String STR_PARENT = "..";
	
	private Socket connection;

	public RequestHandler(Socket connectionSocket) {
		this.connection = connectionSocket;
	}
	
	public void run() {
		log.info("Connected IP : {}, Port : {}", connection.getInetAddress(), connection.getPort());
		
		try (InputStream in = connection.getInputStream();
				OutputStream out = connection.getOutputStream()) {
			
			RequestMapper mapper = new RequestMapper(WEB_ROOT, ENCODING, SimpleServletMapper.getMap());
			
			HttpRequestFactory reqFactory = new HttpRequestFactory(in); 
			HttpRequest req = reqFactory.getFactory();
			
			if (req.getValid()) {
				response(out, this.getResponse(req, mapper, out));
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private HttpResponse getResponse(HttpRequest req, RequestMapper mapper, OutputStream out) throws Exception {
		
		if (req != null && req.getUrl() != null) {
			boolean isForbidden = this.isForbidden(req.getUrl());
			
			if (isForbidden) {
				return this.error(req, mapper, Properties.get403Html());
			}
		}
		
		SimpleServlet servlet;
		
		if(mapper.getRequestMapping() != null && mapper.getRequestMapping().get(req.getHost()) != null &&
				(servlet = mapper.getRequestMapping().get(req.getHost()).get(req.getFileName())) != null) {
			try {
				return servlet.service(req, new HttpResponse(new HttpHeader(), new byte[0]));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return this.error(req, mapper, Properties.get500Html());
			}
		} else {
			try {
				if ("".equals(req.getFileName())) {
					req.setFileName("index.html");
				}
				
				String resource = String.format("/%s/%s", mapper.getRoot(), req.getFileName());
				return this.move(HttpUtils.parseData(resource), req, mapper);
			} catch (Exception e) {
				return this.error(req, mapper, Properties.get404Html());
			}
		}
		
	}
	
	private boolean isForbidden(String url) {
		boolean isForbidden = false;
		List<String> regList = Arrays.asList(url.split("/"));
		
		if (regList.size() < 1) {
			return isForbidden;
		}
		
		if (url.indexOf("/" + STR_PARENT + "/") > -1 || url.indexOf(STR_EXE + "/") > -1 ||
				STR_EXE.equals(url.substring(url.length() - STR_EXE.length(), url.length())) ||
				regList.contains(STR_EXE) || regList.contains(STR_PARENT)) {
			
			isForbidden = true;
		}
		
		return isForbidden;
	}
	
	private void response(OutputStream out, HttpResponse res) {
		try {
			byte[] header = res.getHeader();
			byte[] body = res.getBody();
			
			out.write(header, 0, header.length);
			
			if (body != null) {
				out.write(body, 0, body.length);
			}
			
			out.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	
	private HttpResponse move(String errorString, HttpRequest req, RequestMapper mapper) throws Exception {
		try {
			byte[] body = errorString.getBytes();
			HttpHeader header = HttpHeader.of(HttpStatus.OK.getCode(), req.getAccept(), body.length, mapper.getEncoding());
			return new HttpResponse(header, body);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return this.error(req, mapper, Properties.get500Html());
		}
		
	}
	
	private HttpResponse error(HttpRequest req, RequestMapper mapper, String html) throws Exception {
		final String errorResource = String.format("/%s/%s", mapper.getRoot(), html);
		return this.move(HttpUtils.parseData(errorResource).toString(), req, mapper);
	}
	
}
