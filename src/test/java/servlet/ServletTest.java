package servlet;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.xml.sax.SAXException;

import was.servlet.SimpleServletMapper;

public class ServletTest {
	@Test
	public void parseServletJson() throws JsonParseException, JsonMappingException, IOException, SAXException, ParserConfigurationException {
		String json = "{\n" + 
				"	\"www.was-path-1.com\" : {\n" + 
				"		\"package\" : \"was.path1\",\n" + 
				"		\"class\" : {\n" + 
				"			\"hello\" : \"Hello\",\n" + 
				"			\"sub-hello\" : \"sub.SubHello\",\n" + 
				"			\"error-test\" : \"InternalServerError\"\n" + 
				"		}\n" + 
				"	},\n" + 
				"	\"www.was-path-2.com\" : {\n" + 
				"		\"package\" : \"was.path2\",\n" + 
				"		\"class\" : {\n" + 
				"			\"hello\" : \"Hello\",\n" + 
				"			\"sub-hello\" : \"sub.SubHello\",\n" + 
				"			\"error-test\" : \"InternalServerError\"\n" + 
				"		}\n" + 
				"	}\n" + 
				"}";
		
		SimpleServletMapper.setMap(json);
		
		assertNotNull(SimpleServletMapper.getMap());
		assertNotNull(SimpleServletMapper.getMap().get("www.was-path-1.com"));
		assertNotNull(SimpleServletMapper.getMap().get("www.was-path-2.com"));
		
	}
}
