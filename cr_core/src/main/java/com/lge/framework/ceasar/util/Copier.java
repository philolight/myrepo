package com.lge.framework.ceasar.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

//import com.lge.framework.pacific.common.Utils;
import com.lge.sm.cr_data_store.DataStore;

public class Copier {
//	private boolean isPackageMode = false;
	
	public Copier() {
//		isPackageMode = Utils.isClassInPackageFile(DataStore.class);
	}
	
	public void copy(String fromString, String toString) {
		File from = new File(fromString);
		File to = new File(toString);
			
		if(from.isDirectory()) {
			System.out.println("directory = " + from.getAbsolutePath());
			copyDirectory(from, to);
		}
		else copyFile(from, to);
	}
	
	private void copyDirectory(File from, File toDir) {
		System.out.println("copyDirectory");
		System.out.println("from = " + from.getAbsolutePath());
		System.out.println("to = " + toDir.getAbsolutePath());

		String folderName = from.getAbsolutePath().substring(from.getAbsolutePath().lastIndexOf('\\'));
		
		System.out.println("folderName = " + toDir.getAbsolutePath());
		
		File to = new File(toDir.getAbsolutePath() + folderName);
		to.mkdir();
						
		File[] kids = from.listFiles();
		
		for(File kid : kids) {
			if(kid.isDirectory()) {
				copyDirectory(kid, to);
			}
			else { // file
				copyFile(kid, to);
			}
		}
	}
	
	private void copyFile(File from, File to) {
		System.out.println("copyFile");
		System.out.println("from = " + from.getAbsolutePath());
		System.out.println("to = " + to.getAbsolutePath());
		
		String fileName = from.getAbsolutePath().substring(from.getAbsolutePath().lastIndexOf("\\"));
		
		InputStream in = null;
		OutputStream out = null;
		
		try {
			in = new FileInputStream(from);
			out = new FileOutputStream(to.getAbsolutePath() + fileName);
			
			byte[] buffer = new byte[4096];
			int size = 0;
			while ((size = in.read(buffer)) > 0) {
				out.write(buffer, 0, size);
			}
			out.flush();
		} catch(Exception e) {
			
		}
		
	}
}