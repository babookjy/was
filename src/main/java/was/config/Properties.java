package was.config;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import lombok.extern.slf4j.Slf4j;
import was.util.HttpUtils;

@Slf4j
public class Properties {
	private final static String PORT_KEY = "port";
	private final static String ERROR_403 = "403";
	private final static String ERROR_404 = "404";
	private final static String ERROR_500 = "500";
	
	private static Map<String, Object> parseMap;
	
	static {
		try {
			parseMap = new ObjectMapper().readValue(HttpUtils.parseData("/properties.json"), new TypeReference<Map<String, Object>>() {});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	public static int getDefaultPort() {
		return (Integer) parseMap.get(PORT_KEY);
	}
	
	public static String get403Html() {
		return (String) parseMap.get(ERROR_403);
	}
	
	public static String get404Html() {
		return (String) parseMap.get(ERROR_404);
	}
	
	public static String get500Html() {
		return (String) parseMap.get(ERROR_500);
	}
	
}
