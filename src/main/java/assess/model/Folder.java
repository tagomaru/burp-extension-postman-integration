package assess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Folder {
	private String name;
	private List<RequestItem> item = new ArrayList<>();
	
	public Folder(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setRequestItem(RequestItem reqItem) {
		this.item.add(reqItem);
	}
}
