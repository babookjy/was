package was.path1;

import was.SimpleServlet;
import was.http.HttpRequest;
import was.http.HttpResponse;

public class Hello implements SimpleServlet{

	@Override
	public HttpResponse service(HttpRequest req, HttpResponse res) {
		StringBuilder sb = new StringBuilder();
		sb.append("- Host : " + req.getHost() + " <br/>");
		sb.append("- Package : was.path1 <br/>");
		sb.append("- Parameters<br/>");
		sb.append(" param1 : " + req.getParameter("param1") + "<br/>");
		sb.append(" param2 : " + req.getParameter("param1") + "<br/>");
		
		return res.write(sb.toString());
	}
}
