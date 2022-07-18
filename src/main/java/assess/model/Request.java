package assess.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Request {
	private List<Map<String, String>> header = new ArrayList<>();
	private String url;
	private String method;
	private Body body;
	private transient String name;
	private transient String folder = null;
	private String dataMode;
	private String rawModeData = "";

	public List<Map<String, String>> getHeader() {
		return header;
	}

	public void setHeader(List<Map<String, String>> header) {
		this.header = header;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDataMode() {
		return this.dataMode;
	}

	public void setDataMode(String dataMode) {
		this.dataMode = dataMode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRawModeData() {
		return this.rawModeData;
	}

	public void setRawModeData(String rawModeData) {
		this.rawModeData = rawModeData;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Body getBody() {
		return this.body;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	public Request() {
		super();
	}
}
