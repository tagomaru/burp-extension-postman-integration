package assess.model;

public class RequestItem extends Item {
	private Request request;
	
	public void setRequest(Request req) {
		this.request = req;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RequestItem() {
		super();
	}
}
