package was.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtils {
	
	public static Map<String, Object> parseQueryString(String queryString) {
		if (Strings.isNullOrEmpty(queryString)) {
			return Maps.newHashMap();
		}
		
		String[] tokens = queryString.split("&");
		return Arrays.stream(tokens)
					.map(t -> getKeyValue(t, "="))
					.filter(p -> p != null)
					.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
	}
	
	static Pair getKeyValue(String keyValue, String regex) {
		if (Strings.isNullOrEmpty(keyValue)) {
			return null;
		}
		
		String[] tokens = keyValue.split(regex);
		if (tokens.length != 2) {
			return null;
		}
		
		return new Pair(tokens[0], tokens[1]);
	}
	
	public static Pair parseHeader(String header) {
		return getKeyValue(header, ": ");
	}
	
	@Data
	@AllArgsConstructor
	public static class Pair {
		String key;
		String value;
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			
			if (obj == null) {
				return false;
			}
			
			if (getClass() != obj.getClass()) {
				return false;
			}
			
			Pair other = (Pair) obj;
			
			if (key == null) {
				if (other.key != null) {
					return false;
				}
			} else if (!key.equals(other.key)) {
				return false;
			}
			
			if (value == null) {
				if (other.value != null) {
					return false;
				}
			} else if (!value.equals(other.value)) {
				return false;
			}
			
			return true;
		}
	}
	
	public static String readData(BufferedReader br, int contentLength) throws IOException {
		char[] body = new char[contentLength];
		br.read(body, 0, contentLength);
		
		return String.copyValueOf(body);
	}
	
	public static String parseData(String resource) throws Exception {
		if (resource == null) {
			return null;
		}
		
		final StringBuilder sb = new StringBuilder("");
		
		try (InputStream is = HttpUtils.class.getResourceAsStream(resource);
				InputStreamReader isr = new InputStreamReader(is);
    			BufferedReader in = new BufferedReader(isr)) {
    		
    		String strLine = "";
    		while ((strLine = in.readLine()) != null) {
    			sb.append(strLine);
    		}

    	} catch (Exception e) {
    		log.error("page resource is null", e);
    		throw e;
    	};
		
		return sb.toString();
	}
}
