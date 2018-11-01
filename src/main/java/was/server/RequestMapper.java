package was.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import was.SimpleServlet;

@Data
@AllArgsConstructor
public class RequestMapper {
	private String root;
	private String encoding;
	private Map<String, Map<String, SimpleServlet>> requestMapping = new ConcurrentHashMap<>();
	
	public RequestMapper(String root, String encoding){
		this(root, encoding, null);
	}
	
}
