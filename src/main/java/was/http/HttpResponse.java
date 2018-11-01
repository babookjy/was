package was.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import was.type.HttpStatus;

@Data
@AllArgsConstructor
public class HttpResponse {
	private HttpHeader header;
	private byte[] body;

	public byte[] getHeader() {
		return header.getBytes();
	}

	public byte[] getBody() {
		if(body == null) {
			return "".getBytes();
		}
		
		return body;
	}
	
	public HttpResponse write(String text) {
		return HttpResponseFactory.getResponse(HttpStatus.OK.getCode(), text);
	}
	
}
