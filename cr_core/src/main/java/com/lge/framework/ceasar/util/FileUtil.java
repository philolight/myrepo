package com.lge.framework.ceasar.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {
	/** 파일이 위치할 folder와 fileName을 입력하면 folder 및 file을 생성해주는 메소드. */
	public static boolean createFileIfNotExist(String folder, String fileName) {
		boolean created = false;
		
		try {
			File folderFile = new File(folder);
			if(folderFile.exists() == false) folderFile.mkdirs();
			
			File historyFile = new File(folder + "/" + fileName);
			
			if(historyFile.exists() == false) {
				historyFile.createNewFile();
				created = true;
			}
		} catch (Exception e) {
			// Do nothing
			// 파일이나 폴더의 생성에 실패했을 경우에는 false를 리턴한다. 
		}
		
		return created;
	}
	
	/** fileName에 해당하는 파일에 대한 BufferedWriter를 리턴하는 메소드. 파일이 존재하지 않으면 null을 리턴한다.*/
	public static BufferedWriter getCsvFileBufferedWriter(String fileName) {		
		try {
			File historyFile = new File(fileName);
			BufferedWriter bw;
			if(historyFile.exists() == false) return null;
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "euc-kr"));

			return bw;
		} catch (IOException e) {
			return null;
		}
	}
}
