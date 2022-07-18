package assess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Collection {
	private Info info;
	private List<Item> item = new ArrayList<>();

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

	public List<Item> getItem() {
		return this.item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public Collection(String name) {
		super();
		this.info = new Info(name);
		// this.id = UUID.randomUUID().toString();
	}
}