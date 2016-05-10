package com.fan.filesystem;

import java.util.HashMap;
import java.util.Map;

public class Folder extends FileSystemItem{
	private Map<FileSystemItem,FileSystemItem> directory;
	
	public Folder(String name){
		this.setIsFile(false);
		this.setName(name);
		directory = new HashMap<FileSystemItem,FileSystemItem>();
	}

	public Map<FileSystemItem,FileSystemItem> getDirectory() {
		return directory;
	}

	public void setDirectory(Map<FileSystemItem,FileSystemItem> directory) {
		this.directory = directory;
	}
	
	
}
