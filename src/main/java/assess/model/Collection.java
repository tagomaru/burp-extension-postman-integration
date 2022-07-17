package assess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Collection {
	
	class Info {
		private String _postman_id;
		private String name;
		private String schema = "https://schema.getpostman.com/json/collection/v2.0.0/collection.json";
		public Info(String name) {
			super();
			this._postman_id = UUID.randomUUID().toString();
			this.name = name;
		}
	}

	public String getId() {
		return this.info._postman_id;
	}

	public void setId(String id) {
		this.info._postman_id = id;
	}

	// public String getName() {
	// 	return name;
	// }

	// public void setName(String name) {
	// 	this.name = name;
	// }

	// public String getDescription() {
	// 	return description;
	// }

	// public void setDescription(String description) {
	// 	this.description = description;
	// }

	// public List<String> getOrder() {
	// 	return order;
	// }

	// public void setOrder(List<String> order) {
	// 	this.order = order;
	// }

	// public List<FolderItem> getFolders() {
	// 	return folders;
	// }

	// public void setFolders(List<FolderItem> folders) {
	// 	this.folders = folders;
	// }

	// public int getTimestamp() {
	// 	return timestamp;
	// }

	// public void setTimestamp(int timestamp) {
	// 	this.timestamp = timestamp;
	// }

	// public int getOwner() {
	// 	return owner;
	// }

	// public void setOwner(int owner) {
	// 	this.owner = owner;
	// }

	// public List<RequestItem> getRequests() {
	// 	return requests;
	// }

	// public void setRequests(List<RequestItem> requests) {
	// 	this.requests = requests;
	// }

	public List<Item> getItem() {
		return this.item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	private Info info;
	// private String id;	// Collection Id. Auto generated UUID.
	// private String name;	// This is set on GUI.
	// private String description = "";	// Description
	private List<Item> item = new ArrayList<>();
	// private List<String> order = new ArrayList<>();
	// private transient List<FolderItem> folders = new ArrayList<>();
	// private int timestamp = 0;
	// private int owner = 0;
	//private boolean public;
	// private transient List<RequestItem> requests = new ArrayList<>();

	public Collection(String name) {
		super();
		this.info = new Info(name);
		// this.id = UUID.randomUUID().toString();
	}
}