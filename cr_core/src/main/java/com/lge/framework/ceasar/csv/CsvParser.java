package com.lge.framework.ceasar.csv;

import java.util.ArrayList;
import java.util.List;

public class CsvParser{
	private int index = -1;
	private List<String> tokens = new ArrayList<String>();
	public CsvParser(String line){
		int start = 0;
		while(true){
			int nextIndex = line.substring(start).indexOf(',');
			String token;
			if(nextIndex == 0) token = "";
			else if(nextIndex == -1) token = line.substring(start);
			else token = line.substring(start, start + nextIndex);

			tokens.add(token);
			if(nextIndex == -1) return;
			start = start + nextIndex + 1;
		}
	}

	public boolean hasNext(){
		return (index + 1 < tokens.size());
	}

	public String next(){
		return tokens.get(++index);
	}
}