package was.http;

public class HttpResponseFactory {
	
	public static HttpResponse getResponse(String statusCode, String body){
		byte[] bodyBytes = (body != null) ? body.getBytes() : "".getBytes();
		HttpHeader header = HttpHeader.of(statusCode, "text/html", bodyBytes.length, "UTF-8");
		return new HttpResponse(header, bodyBytes);
	}
	
}