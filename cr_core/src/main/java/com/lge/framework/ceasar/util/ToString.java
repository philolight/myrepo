package com.lge.framework.ceasar.util;

import java.lang.reflect.Field;

public class ToString {	
	/** 모든 객체에 대한 ToString 함수 
	 * @param obj : 대상 클래스
	 * @param forSuperClass : Super Class에 대한 연산인 경우 true, 아니면 false. DB query의 경우 model class가 모두 base 클래스이므로 이 파라메터를 true로 함.
	 * */
	public static String toLine(Object obj){
		String str = "";
			
		String className = obj.getClass().toString();
		className = className.substring(className.lastIndexOf('.') + 1);
		str += className + "{";	
		
		Field[] fields = null;
		fields = obj.getClass().getDeclaredFields();

		for ( Field field : fields ) {
			field.setAccessible(true);
			try {
				if(field.getType().toString().startsWith("class")){
					str += field.getName();
					str += ":";
					if(field.get(obj) != null){
						str +=  field.get(obj).toString();
					}
				}
				else{
					str +=  field.getName();
					str += ": ";
					str +=  field.get(obj);
				}
				str += ", ";
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		str += "}";
		return str;
	}
	
	public static String toString(Object obj){
		String str = "";
			
		String className = obj.getClass().toString();
		className = className.substring(className.lastIndexOf('.') + 1);
		str += className + "{\n";	
		
		Field[] fields = null;
		fields = obj.getClass().getDeclaredFields();

		for ( Field field : fields ) {
			field.setAccessible(true);
			try {
				if(field.getType().toString().startsWith("class")){
					str += "   " + field.getName();
					str += ":";
					if(field.get(obj) != null){
						str +=  field.get(obj).toString();
					}
				}
				else{
					str += "   " + field.getName();
					str += ": ";
					str += field.get(obj);
				}
				str += ",\n";
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		str += "}";
		return str;
	}
}

