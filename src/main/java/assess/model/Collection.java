package assess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Collection {
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<String> getOrder() {
		return order;
	}

	public void setOrder(List<String> order) {
		this.order = order;
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	private String id;	// Collection Id. Auto generated UUID.
	private String name;	// This is set on GUI.
	private String description = "";	// Description
	private List<String> order = new ArrayList<>();
	private List<Folder> folders = new ArrayList<>();
	private int timestamp = 0;
	private int owner = 0;
	//private boolean public;
	private List<Request> requests = new ArrayList<>();

	public Collection() {
		super();
		this.id = UUID.randomUUID().toString();
	}
}