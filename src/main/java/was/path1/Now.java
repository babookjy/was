package was.path1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import was.SimpleServlet;
import was.http.HttpRequest;
import was.http.HttpResponse;

public class Now implements SimpleServlet {

	@Override
	public HttpResponse service(HttpRequest req, HttpResponse res) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("- Host : " + req.getHost() + " <br/>");
		sb.append("- Package : was.path1 <br/>");
		sb.append("- 현재시간 : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
		
		return res.write(sb.toString());
	}

	
}
