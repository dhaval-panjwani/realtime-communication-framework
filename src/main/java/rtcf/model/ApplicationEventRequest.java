package rtcf.model;

import java.util.Map;

public class ApplicationEventRequest {
	Map<String,String> headers;
	Object content;
		
	public ApplicationEventRequest(Map<String, String> headers, Object content) {
		super();
		this.headers = headers;
		this.content = content;
	}

	public ApplicationEventRequest() {
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ApplicationEventRequest [headers=" + headers + ", content=" + content + "]";
	}
	
	
}
