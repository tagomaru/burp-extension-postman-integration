package assess.model;

public class FolderItem extends Item {
	private Folder item;
	
	public void setFolder(Folder folder) {
		this.item = folder;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FolderItem() {
		super();
	}
}
