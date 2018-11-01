package was.http;

import java.io.UnsupportedEncodingException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import was.type.HttpStatus;

@Data
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class HttpHeader {
	
	String statusCode;
	String contentType;
	Integer length;
	String encoding;

	public byte[] getBytes(){
		StringBuilder headerString = new StringBuilder("");
		
		if(statusCode != null) {
			headerString.append("HTTP/1.1 " + statusCode + " "+ HttpStatus.getCodeOf(statusCode).getResult() +" \r\n");
		}
		
		if(contentType != null) {
			headerString.append("Content-Type: " + contentType + ";");
		}
		
		if(encoding != null) {
			headerString.append("charset="+ encoding +"\r\n");
		}
		
		if(length != null) {
			headerString.append("Content-Length: " + length + "\r\n");
		}
		
		headerString.append("\r\n");

		try {
			return headerString.toString().getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static HttpHeader of(String statusCode, String contentType, int bodyBytesLength, String encoding) {
		return HttpHeader.builder()
				.statusCode(statusCode)
				.contentType("text/html")
				.length(bodyBytesLength)
				.encoding("UTF-8")
				.build();
	}
}
