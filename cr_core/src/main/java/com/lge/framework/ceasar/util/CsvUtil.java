package com.lge.framework.ceasar.util;

public class CsvUtil {
	/** 
	 * CSV 파일에 들어갈 컬럼의 포멧을 변환하는 메소드.
	 * CSV 파일의 컬럼은 ','가 없어야 한다.
	 * */
	public static String asColumn(String str) {
		String ret = str.replaceAll(",", " ");
		return ret;
	}
}
