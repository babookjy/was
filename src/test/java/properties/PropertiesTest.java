package properties;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import was.util.HttpUtils;

public class PropertiesTest {
	
	private final static String PORT_KEY = "port";
	private final static String ERROR_403 = "403";
	private final static String ERROR_404 = "404";
	private final static String ERROR_500 = "500";
	
	@Test
	public void parseServletJson() throws Exception {
				
		Map<String, Object> parseMap =
				new ObjectMapper().readValue(HttpUtils.parseData("/properties.json"), new TypeReference<Map<String, Object>>() {});
		
		assertNotNull(parseMap.get(PORT_KEY));
		assertNotNull(parseMap.get(ERROR_403));
		assertNotNull(parseMap.get(ERROR_404));
		assertNotNull(parseMap.get(ERROR_500));
	}
}
