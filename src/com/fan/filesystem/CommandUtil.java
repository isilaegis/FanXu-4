package com.fan.filesystem;

public class CommandUtil {
	//TODO commands can be objects for future improvement
	public static void run(String command,Folder root) throws Exception{
		String[] commands = command.split(" ");
		switch(commands[0]){
			case "mkdir":
				createNewFolder(commands,root);
				break;
			case "create":
				createNewFile(commands,root);
				break;
			case "write":
				writeFile(commands,root);
				break;
			case "cat":
				displayFileContent(commands,root);
				break;
			case "ls":
				listFolderContent(commands,root);
				break;
			case "cp":
				copy(commands,root);
				break;
			case "find":
				find(commands,root);
				break;
			case "exit":
				System.out.println("System Quit");
				System.exit(0);
			default: 
				throw new Exception("Command not found");		
		}
	}
	
	private static void createNewFolder (String[] args,Folder root)throws Exception{
		if(args.length<=1 ||args[1] == null || args[1].length() == 0||args[1].charAt(0)!='/'){
			throw new Exception("Illigal params for command : " + args[0]);
		}
		Folder cur = root;
		String[] path = args[1].split("/");
		for(int i = 1;i< path.length;i++){
			if(path[i] == null || path[i].length() == 0){
				throw new Exception("Illigal params for command : " + args[0]);
			}
			Folder buf = new Folder(path[i]);
			if(cur.getDirectory().containsKey(buf)){
				cur = (Folder) cur.getDirectory().get(buf);
				continue;
			}
			cur.getDirectory().put(buf, buf);
			cur = buf;
		}
		System.out.println("Folder created successfully");
	}
	
	private static void createNewFile (String[] args,Folder root)throws Exception{
		if(args.length<=1 ||args[1] == null || args[1].length() == 0||args[1].charAt(0)!='/'){
			throw new Exception("Illigal params for command : " + args[0]);
		}
		Folder cur = root;
		String[] path = args[1].split("/");
		for(int i = 1;i< path.length-1;i++){
			if(path[i] == null || path[i].length() == 0){
				throw new Exception("Illigal params for command : " + args[0]);
			}
			Folder buf = new Folder(path[i]);
			if(cur.getDirectory().containsKey(buf)){
				cur = (Folder) cur.getDirectory().get(buf);
				continue;
			}
			else{
				throw new Exception("Folder does not exist");
			}
		}
		String fileName = path[path.length-1];
		if(fileName == null || fileName.length() == 0){
			throw new Exception("Illigal params for command : " + args[0]);
		}else{
			FileSystemItem newFile = new File(fileName);
			cur.getDirectory().put(newFile, newFile);
		}
		System.out.println("File created successfully");
	}
	
	private static void writeFile (String[] args,Folder root)throws Exception{
		if(args.length<=2 ||args[1] == null || args[1].length() == 0||args[2] == null || args[2].length() == 0||args[2].charAt(0)!='/'){
			throw new Exception("Illigal params for command : " + args[0]);
		}
		Folder cur = root;
		String[] path = args[2].split("/");
		for(int i = 1;i< path.length-1;i++){
			if(path[i] == null || path[i].length() == 0){
				throw new Exception("Illigal params for command : " + args[0]);
			}
			Folder buf = new Folder(path[i]);
			if(cur.getDirectory().containsKey(buf)){
				cur = (Folder) cur.getDirectory().get(buf);
				continue;
			}
			else{
				throw new Exception("Folder does not exist");
			}
		}
		String fileName = path[path.length-1];
		if(fileName == null || fileName.length() == 0){
			throw new Exception("Illigal params for command : " + args[0]);
		}else{
			File buf = new File(fileName);
			if(cur.getDirectory().containsKey(buf)){
				File myFile = (File) cur.getDirectory().get(buf);
				String newContent = null;
				if(myFile.getContent()!= null)
					 newContent = myFile.getContent() + args[1];
				else
					 newContent = args[1];
				myFile.setContent(newContent);
			}
			else{
				throw new Exception("File does not exist");
			}

		}
		System.out.println("File wrote successfully");
	}
	
	private static void copy(String[] args,Folder root)throws Exception{
		if(args.length<=2 ||args[1] == null || args[1].length() == 0||args[2] == null || args[2].length() == 0||args[2].charAt(0)!='/'||args[1].charAt(0)!='/'){
			throw new Exception("Illigal params for command : " + args[0]);
		}
		Folder cur = root;
		String[] path = args[1].split("/");
		for(int i = 1;i< path.length-1;i++){
			if(path[i] == null || path[i].length() == 0){
				throw new Exception("Illigal params for command : " + args[0]);
			}
			Folder buf = new Folder(path[i]);
			if(cur.getDirectory().containsKey(buf)){
				cur = (Folder) cur.getDirectory().get(buf);
				continue;
			}
			else{
				throw new Exception("Folder does not exist");
			}
		}
		
		String targetName = path[path.length-1];
		int type = 0;
		if(targetName == null || targetName.length() == 0){
			throw new Exception("Illigal params for command : " + args[0]);
		}
		
		File bufFile = new File(targetName);
		Folder bufFolder = new Folder(targetName);
		if(cur.getDirectory().containsKey(bufFile)){
			type = 1;
		}else if(cur.getDirectory().containsKey(bufFolder)){
			type = 2;
		}else{
			throw new Exception("Can not find folder or file");
		}

		if(type ==1){
			String fileName = path[path.length-1];
			String fileContent = null;
		
			fileContent = ((File) cur.getDirectory().get(bufFile)).getContent();	
						
			cur = root;
			path = args[2].split("/");
			for(int i = 1;i< path.length-1;i++){
				if(path[i] == null || path[i].length() == 0){
					throw new Exception("Illigal params for command : " + args[0]);
				}
				Folder buf = new Folder(path[i]);
				if(cur.getDirectory().containsKey(buf)){
					cur = (Folder) cur.getDirectory().get(buf);
					continue;
				}
				else{
					throw new Exception("Folder does not exist");
				}
			}
			fileName = path[path.length-1];
			if(fileName == null || fileName.length() == 0){
				throw new Exception("Illigal params for command : " + args[0]);
			}else{
				File buf = new File(fileName);
				if(cur.getDirectory().containsKey(buf)){
					((File) cur.getDirectory().get(buf)).setContent(fileContent);	
				}
				else{
					File newFile = new File(fileName);
					newFile.setContent(fileContent);
					cur.getDirectory().put(newFile, newFile);
				}
	
			}
		}else if(type == 2){
			Folder newFolder = deepCopyFolder((Folder)cur.getDirectory().get(bufFolder));
			
			cur = root;
			path = args[2].split("/");
			for(int i = 1;i< path.length;i++){
				if(path[i] == null || path[i].length() == 0){
					throw new Exception("Illigal params for command : " + args[0]);
				}
				Folder buf = new Folder(path[i]);
				if(cur.getDirectory().containsKey(buf)){
					cur = (Folder) cur.getDirectory().get(buf);
					continue;
				}
				else{
					throw new Exception("Folder does not exist");
				}
			}
			
			cur.getDirectory().put(newFolder, newFolder);
		}
		
		System.out.println("File copied successfully");
	}
	
	private static Folder deepCopyFolder(Folder target){
		Folder newFolder = new Folder(target.getName());
		for(FileSystemItem f : target.getDirectory().values()){
			if(f.isFile()){
				File buf = new File(f.getName());
				buf.setContent(((File)f).getContent());
				newFolder.getDirectory().put(buf, buf);
			}
			else{
				Folder buf = deepCopyFolder((Folder)f);
				newFolder.getDirectory().put(buf, buf);
			}
		}
		return newFolder;
	}
	
	private static void find (String[] args,Folder root)throws Exception{
		if(args.length<=1||args.length>3  ){
			throw new Exception("Illigal params for command : " + args[0]);
		}
		Folder cur = root;
		String fileName = null;
		String currentPath = "";
		if(args.length == 3){
			if(args[1] == null ||args[2] == null|| args[1].length() == 0||args[2].length() == 0||args[1].charAt(0)!='/'){
				throw new Exception("Illigal params for command : " + args[0]);
			}
			currentPath = args[1];
			String[] path = args[1].split("/");
			for(int i = 1;i< path.length;i++){
				if(path[i] == null || path[i].length() == 0){
					throw new Exception("Illigal params for command : " + args[0]);
				}
				Folder buf = new Folder(path[i]);
				if(cur.getDirectory().containsKey(buf)){
					cur = (Folder) cur.getDirectory().get(buf);
					continue;
				}
				else{
					throw new Exception("Folder does not exist");
				}
			}
			fileName = args[2];
		}
		else{
			fileName = args[1]; 
		}
		
		File buf = new File(fileName);
		search(cur,buf,currentPath);
		System.out.println("Search complete");
	}
	
	private static void search(Folder root,File target,String path){
		if(root.getDirectory().containsKey(target)){
			System.out.println(path+"/" + target.getName());
		}
		
		for(FileSystemItem f : root.getDirectory().values()){
			if(!f.isFile()){
				String newPath = path + "/" + f.getName();
				search((Folder)f,target,newPath);
			}
		}
	}
	
	private static void displayFileContent (String[] args,Folder root)throws Exception{
		if(args.length<=1 ||args[1] == null || args[1].length() == 0||args[1].charAt(0)!='/'){
			throw new Exception("Illigal params for command : " + args[0]);
		}
		Folder cur = root;
		String[] path = args[1].split("/");
		for(int i = 1;i< path.length-1;i++){
			if(path[i] == null || path[i].length() == 0){
				throw new Exception("Illigal params for command : " + args[0]);
			}
			Folder buf = new Folder(path[i]);
			if(cur.getDirectory().containsKey(buf)){
				cur = (Folder) cur.getDirectory().get(buf);
				continue;
			}
			else{
				throw new Exception("Folder does not exist");
			}
		}
		String fileName = path[path.length-1];
		if(fileName == null || fileName.length() == 0){
			throw new Exception("Illigal params for command : " + args[0]);
		}else{
			File buf = new File(fileName);
			if(cur.getDirectory().containsKey(buf)){
				File myFile = (File) cur.getDirectory().get(buf);
				String content = myFile.getContent();
				System.out.println("File content :");
				System.out.println(content);
			}
			else{
				throw new Exception("File does not exist");
			}
		}
		System.out.println("File read successfully");
	}
	
	private static void listFolderContent(String[] args,Folder root)throws Exception{
		if(args.length<=1 ||args[1] == null || args[1].length() == 0||args[1].charAt(0)!='/'){
			throw new Exception("Illigal params for command : " + args[0]);
		}
		Folder cur = root;
		String[] path = args[1].split("/");
		for(int i = 1;i< path.length;i++){
			if(path[i] == null || path[i].length() == 0){
				throw new Exception("Illigal params for command : " + args[0]);
			}
			Folder buf = new Folder(path[i]);
			if(!cur.getDirectory().containsKey(buf)){
				throw new Exception("Folder does not exist");
			}
			cur = (Folder) cur.getDirectory().get(buf);
		}
		for(FileSystemItem f : cur.getDirectory().values()){
			String type = f.isFile()?"file":"folder";
			System.out.println(f.getName() +"/"+type);
		}
	}
	
	
}
