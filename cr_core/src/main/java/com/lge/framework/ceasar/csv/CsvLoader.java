package com.lge.framework.ceasar.csv;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

abstract public class CsvLoader {
	private List<String> fieldNames = new ArrayList<String>();

	public void load(String fullFileName) throws Exception{
		File file = new File(fullFileName);

		if(file.exists() == false){
			System.out.println("File not Exists : " + fullFileName);
			return;
		}

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(file, "euc-kr");
		readFieldNames(scan.nextLine());

		while(scan.hasNext()){
			parseLine(scan.nextLine());
		}
	}

	private void readFieldNames(String line) throws Exception{
		CsvParser token = new CsvParser(line);
		while(token.hasNext()){
			String fieldName = token.next();
			fieldNames.add(fieldName);
		}
	}

	protected void parseLine(String line) throws Exception{
		Map<String, String> pairMap = new HashMap<String, String>();

		CsvParser token = new CsvParser(line);
		for(int i = 0; i < fieldNames.size(); i++){
			String field;
			if(token.hasNext()) field = token.next();
			else field = "";
			pairMap.put(fieldNames.get(i), field);
		}
		processFieldMap(pairMap);
	}
	
	protected int parseInt(String intString){
		// 16진수와 10진수로 된 integer 문자열을 동시에 파싱하는 메소드.
		if(intString.toUpperCase().startsWith("0X")) return Integer.parseUnsignedInt(intString.substring(2), 16);
		else return Integer.parseInt(intString);
	}

	abstract protected void processFieldMap(Map<String, String> pairMap) throws Exception;
}