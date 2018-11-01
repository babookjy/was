package was.type;

public enum HttpStatus {
	OK("200", "Ok", "");
	
	private String code;
	private String result;
	private String path;
	
	public String getCode() {
		return this.code;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public String getPath() {
		return this.path;
	}
	
	HttpStatus(String code, String result, String path) {
		this.code = code;
		this.result = result;
		this.path = path;
	}
	
	public static HttpStatus getCodeOf(String code) {
		for (HttpStatus status : HttpStatus.values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		
		throw new IllegalArgumentException();
	}
}
