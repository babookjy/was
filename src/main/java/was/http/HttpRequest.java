package was.http;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class HttpRequest {
	BufferedReader inputReader;
	int contentLength;
	String host;
	String urlHeader;
	String url;
	String method;
	String path;
	String fileName;
	String query;
	String accept;
	String body;
	Map<String, Object> params = new HashMap<String, Object>();
	Boolean valid;
	
	public Object getParameter(String name) {
		return this.params.get(name);
	}
	
}