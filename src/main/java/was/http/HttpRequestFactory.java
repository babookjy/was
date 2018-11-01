package was.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import was.util.HttpUtils;

@Slf4j
public class HttpRequestFactory {
	
	private InputStream inputStream; 
	private HttpRequest httpRequest;
	
	public HttpRequestFactory(InputStream inputStream) {
		this.inputStream = inputStream;
		this.httpRequest = new HttpRequest();
	}

	public HttpRequest getFactory() {
		try {
			httpRequest.setInputReader(new BufferedReader(new InputStreamReader(this.inputStream, "UTF-8")));
			httpRequest.setUrlHeader(httpRequest.getInputReader().readLine());
			log.debug("url {} requested", httpRequest.getUrlHeader());
			
			if (httpRequest.getUrlHeader().indexOf("favicon.ico") > -1) {
				httpRequest.setValid(false);
				return httpRequest;
			}
			
			parseUrl(); 
			parseHeader();
			getBody();
			
		} catch (IOException e) {
			log.debug("wrong header!");
			httpRequest.setValid(false);
		}
		
		httpRequest.setValid(true);
		
		return httpRequest;
	}
	
	private void parseUrl() throws IOException{
		if(httpRequest.getUrlHeader() == null) {
			throw new IOException();
		}
		
		String[] splited = httpRequest.getUrlHeader().split(" ");
		if(splited.length < 2)  throw new IOException();
		
		httpRequest.setMethod(splited[0]);
		httpRequest.setUrl(splited[1]);

		Pattern pattern = Pattern.compile("(.*\\/)([^\\/?]*)\\??(.*$)");
		Matcher matcher = pattern.matcher(httpRequest.getUrl());
		
		if(matcher.matches()) {
			httpRequest.setPath(matcher.group(1));
			httpRequest.setFileName(matcher.group(2));
			httpRequest.setQuery(matcher.group(3));
			
			if(httpRequest.getMethod().equals("GET") && httpRequest.getQuery().length() > 0){
				httpRequest.setParams(HttpUtils.parseQueryString(httpRequest.getQuery()));
			}
		} else {
			 throw new IOException();
		}
	}
	
	private void parseHeader() throws IOException{
		String line = null;
		
		while(!"".equals(line)){
			line = httpRequest.getInputReader().readLine();
			
			switch(line.split(": ")[0]){
				case "Host" :
					httpRequest.setHost(line.split(":")[1].replaceAll(" ", ""));
					break;
					
				case "Content-Length":
					httpRequest.setContentLength(Integer.parseInt(line.split(": ")[1]));
					break;
				
				case "Accept":
					httpRequest.setAccept(line.split(": ")[1]);
					break;
				default :
					break;
			}
		}
	}
	
	private void getBody() throws IOException {
		httpRequest.setBody(HttpUtils.readData(httpRequest.getInputReader(), httpRequest.getContentLength()));
		
		if(httpRequest.getMethod().equals("POST")){
			httpRequest.setParams(HttpUtils.parseQueryString(httpRequest.getBody()));	
		}
	}
	
}
