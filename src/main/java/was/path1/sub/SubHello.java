package was.path1.sub;

import was.SimpleServlet;
import was.http.HttpRequest;
import was.http.HttpResponse;

public class SubHello implements SimpleServlet{

	@Override
	public HttpResponse service(HttpRequest req, HttpResponse res) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("- Host : " + req.getHost() + " <br/>");
		sb.append("- Package : was.path1.sub <br/>");
		sb.append("Sub package");
		sb.append("<br/>");
		sb.append("Name : SubHello.java");
		
		return res.write(sb.toString());
	}
}
