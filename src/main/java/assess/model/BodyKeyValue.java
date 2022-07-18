package assess.model;

public class BodyKeyValue {
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isDisabled() {
		return this.disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	private String key;
	private String value;
	private String type = "text";
	private boolean disabled = false;
}
