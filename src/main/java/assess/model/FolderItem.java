package assess.model;

import java.util.List;
import java.util.ArrayList;

public class FolderItem extends Item {
	private List<RequestItem> item = new ArrayList<>();
	
	public void addRequest(RequestItem reqItem) {
		this.item.add(reqItem);
	}

	public List<RequestItem> getItem() {
		return this.item;
	}

	public FolderItem(String name) {
		super();
		this.name = name;
	}
}
