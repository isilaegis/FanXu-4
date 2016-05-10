package com.fan.filesystem;

public class File extends FileSystemItem{

	private String content;

	public File(String name){
		this.setIsFile(true);
		this.setName(name);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
