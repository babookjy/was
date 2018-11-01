package was;

import was.http.HttpRequest;
import was.http.HttpResponse;


public interface SimpleServlet {
	HttpResponse service(HttpRequest req, HttpResponse res) throws Exception;
}
