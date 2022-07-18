package assess.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Request {
	// public String getId() {
	// 	return id;
	// }

	// public void setId(String id) {
	// 	this.id = id;
	// }

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

	// public String getPreRequestScript() {
	// 	return preRequestScript;
	// }

	// public void setPreRequestScript(String preRequestScript) {
	// 	this.preRequestScript = preRequestScript;
	// }

	// public Map<String, Object> getPathVariables() {
	// 	return pathVariables;
	// }

	// public void setPathVariables(Map<String, Object> pathVariables) {
	// 	this.pathVariables = pathVariables;
	// }

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	// public List<BodyKeyValue> getData() {
	// 	return data;
	// }

	// public void setData(List<BodyKeyValue> data) {
	// 	this.data = data;
	// }

	// public String getDataMode() {
	// 	return dataMode;
	// }

	// public void setDataMode(String dataMode) {
	// 	this.dataMode = dataMode;
	// }

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tests) {
		this.tests = tests;
	}

	public String getCurrentHelper() {
		return currentHelper;
	}

	public void setCurrentHelper(String currentHelper) {
		this.currentHelper = currentHelper;
	}

	public Map<String, Object> getHelperAttributes() {
		return helperAttributes;
	}

	public void setHelperAttributes(Map<String, Object> helperAttributes) {
		this.helperAttributes = helperAttributes;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// public String getCollectionId() {
	// 	return collectionId;
	// }

	// public void setCollectionId(String collectionId) {
	// 	this.collectionId = collectionId;
	// }

	public List<Object> getResponses() {
		return responses;
	}

	public void setResponses(List<Object> responses) {
		this.responses = responses;
	}

	public String getRawModeData() {
		return rawModeData;
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

	// private String id;
	private List<Map<String, String>> header = new ArrayList<>();
	private String url;
	// private String preRequestScript = null;
	// private Map<String, Object> pathVariables = new HashMap<String, Object>();
	private String method;
	// private List<BodyKeyValue> data = new ArrayList<>();
	// private String dataMode;
	private Body body;
	private int version = 2;
	private String tests = null;
	private String currentHelper = "normal";
	private Map<String, Object> helperAttributes = new HashMap<String, Object>();
	private int time = 0;
	private transient String name;
	private String description = "";
	// private String collectionId;
	private List<Object> responses = new ArrayList<>();
	private transient String folder = null;
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	private String rawModeData = "";
	
	public Request() {
		super();
		// generate UUID
		// this.id = UUID.randomUUID().toString();
	}
}
