package assess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Folder {
	// private String id;
	private String name;
	private String description;
	// private List<String> order = new ArrayList<String>();
	private int owner = 0;
	private List<RequestItem> item = new ArrayList<>();
	
	public Folder(String name) {
		this.name = name;
		// this.id = UUID.randomUUID().toString();
	}
	
	// public String getId() {
	// 	return id;
	// }
	// public void setId(String id) {
	// 	this.id = id;
	// }
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
	// public List<String> getOrder() {
	// 	return order;
	// }
	// public void setOrder(List<String> order) {
	// 	this.order = order;
	// }
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}

	public void setRequestItem(RequestItem reqItem) {
		this.item.add(reqItem);
	}

}
