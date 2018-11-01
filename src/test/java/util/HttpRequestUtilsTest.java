package util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Map;

import org.junit.Test;

import was.util.HttpUtils;
import was.util.HttpUtils.Pair;

public class HttpRequestUtilsTest {
	@Test
	public void parseQueryString() {
		String queryString = "text=aaaa";
		Map<String, Object> parameters = HttpUtils.parseQueryString(queryString);
		assertThat(parameters.get("text"), is("aaaa"));
	}
	
	@Test
	public void parseHeader() throws Exception {
		String header = "Content-Length: 59";
		Pair pair = HttpUtils.parseHeader(header);
		assertThat(pair, is(new Pair("Content-Length", "59")));
	}
	
	@Test
	public void readData() throws Exception {
		String data = "aaaa";
		StringReader sr = new StringReader(data);
		BufferedReader br = new BufferedReader(sr);
		
		assertNotNull(HttpUtils.readData(br, data.length()));
	}
}
