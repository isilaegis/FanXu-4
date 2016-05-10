package com.fan.filesystem;

import java.util.Scanner;

public class Run {

	public static void main(String[] args) {
		Folder root = new Folder("root");
		while(true){
			Scanner scanner = new Scanner(System.in);
			System.out.print("FileSystem:> ");
			String arg = scanner.nextLine();
			try {
				CommandUtil.run(arg,root);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

}
