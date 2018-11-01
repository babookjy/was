package was.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.xml.sax.SAXException;

import lombok.extern.slf4j.Slf4j;
import was.SimpleServlet;

@Slf4j
public class SimpleServletMapper {
	
	private final static Map<String, Map<String, SimpleServlet>> map = new HashMap<String, Map<String, SimpleServlet>>();
	private final static TypeReference<Map<String, Object>> type = new TypeReference<Map<String, Object>>() {};
	
	public static Map<String, Map<String, SimpleServlet>> getMap() {
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static void setMap(String hostJson) throws JsonParseException, JsonMappingException, IOException, SAXException, ParserConfigurationException {
		final Map<String, Map<String, Object>> hostMap = new ObjectMapper().readValue(hostJson, type);
		
		hostMap.forEach((hostName, hostInfo) -> {
			final String path = (String) hostInfo.get("package");
			final Map<String, Object> constructMap = (Map<String, Object>) hostInfo.get("class");
			
			final Map<String, SimpleServlet> servletMap = new HashMap<>();
			
			constructMap.forEach((servletName, className) -> {
				try {
					Class<SimpleServlet> servletClass =
							(Class<SimpleServlet>) Class.forName(String.format("%s.%s", path.toString(), className));
					
					servletMap.put(servletName, servletClass.newInstance());
					
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					log.error(e.getMessage(), e);
				}
					
				map.put(hostName, servletMap);
			});
			
		});
	}
			
}