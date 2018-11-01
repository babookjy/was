package was.path1;

import was.SimpleServlet;
import was.http.HttpRequest;
import was.http.HttpResponse;

public class InternalServerError implements SimpleServlet {
	@Override
	public HttpResponse service(HttpRequest req, HttpResponse res) throws Exception {
		try {
			throw new Exception("Error-Test");
		} catch (Exception e) {
			throw e;
		}
	}
}
