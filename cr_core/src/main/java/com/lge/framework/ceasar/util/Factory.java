package com.lge.framework.ceasar.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.activation.UnsupportedDataTypeException;

import com.lge.framework.pacific.logger.Logger;

public class Factory{
	private static final String TAG = Factory.class.getSimpleName();
	private static Map<Class<?>, Object> defaults = new HashMap<>();
	
	static {
		defaults.put(String.class, 		"");
		defaults.put(Date.class, 		new Date(0));
		defaults.put(BigDecimal.class, 	BigDecimal.ZERO);
		defaults.put(Long.class, 		Long.valueOf(0));
		defaults.put(Integer.class, 	Integer.valueOf(0));
		defaults.put(Short.class, 		Short.valueOf((short)0));
		defaults.put(Byte.class, 		Byte.valueOf((byte)0));
		defaults.put(Character.class, 	Character.valueOf((char)0));
	}
		
	public static void setDefaultFields(Object obj){
		Field[] fields = getFirstSuperClass(obj).getDeclaredFields();
		
		for(Field field : fields){
			field.setAccessible(true);
			Object defaultObject = defaults.get(field.getType());
			if(defaultObject == null) continue;
			try {
				field.set(obj, defaultObject);
			} catch (Exception e) {
				if(e.getCause() != null) Logger.error(TAG, "setDefaultFields() failed : " + e.getCause().getMessage());
				else Logger.error(TAG, "setDefaultFields() failed");
			}
		}
	}
	
	private static Class<?> getFirstSuperClass(Object obj){ /** 인자로 들어온 obj의 가장 상위 클래스(Object 클래스를 제외하고)를 리턴함*/
		Class<?> klass = obj.getClass();
		while(klass.getSuperclass() != null && klass.getSuperclass() != Object.class){
			klass = klass.getSuperclass();
		}
		return klass;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T deepCopy(T src){
		T dest = null;
		try {
			dest = (T)src.getClass().newInstance();
			deepCopyFrom(src, dest);
		} catch (Exception e) {
			if(e.getCause() != null) Logger.error(TAG, "deepCopy() failed : " + e.getCause().getMessage());
			else Logger.error(TAG, "deepCopy() failed");
		}
		return dest;
	}
	
	public static <T> T deepCopyFrom(T src, T dest) throws Exception{
		Field[] fields = src.getClass().getDeclaredFields();
		Method[] destMethods = dest.getClass().getDeclaredMethods();
		Method setter = null;

		for ( Field field : fields ) {
			field.setAccessible(true);
			for(Method method : destMethods){
				if(method.getName().equalsIgnoreCase("set" + field.getName())){
					setter = method;
					break;
				}
			}

//			try {
			fieldCopy(src, dest, field, setter);
//			} catch (Exception e) {
//				if(e.getCause() != null) Logger.error(TAG, "deepCopyFrom() failed : " + e.getCause().getMessage());
//				else Logger.error(TAG, "deepCopyFrom() failed" + src.getClass().getSimpleName());
//				
//	        	StackTraceElement[] elements = e.getStackTrace();
//	        	StringBuffer buf = new StringBuffer();
//	        	for(int i = elements.length - 1; i >= 0; i--) {
//	        		StackTraceElement each = elements[i];
//	        		if(each.getFileName() == null) continue;
//	        		if(each.getFileName().equals("<generated>")) break;
//	        		buf.append(each.getFileName() + " " + each.getLineNumber() + " / ");
//	        	}
//	        	
//	        	Logger.error(TAG, "deepCopyFrom() failed : " + buf.toString());
//			}
		}
		return dest;
	}
		
	private static void fieldCopy(Object src, Object dest, Field field, Method setter) throws UnsupportedDataTypeException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Object item = field.get(src);
		
		if(item == null) return;
		if(Modifier.isStatic(field.getModifiers())) return;
		
		if(field.getType().isPrimitive()){
			setter.invoke(dest, item);
		}
		else if(item instanceof Number || item instanceof String || item instanceof Boolean || item instanceof BigDecimal){ // immutable
			setter.invoke(dest, item);
		}
		else if(field.getType().isArray()){
			Object array = Array.newInstance(field.getType().getComponentType(), Array.getLength(item));
			for(int i = 0; i < Array.getLength(item); i++){
				Array.set(array, i, Array.get(item, i));
			}
			
			setter.invoke(dest, array);
		}
		else throw new UnsupportedDataTypeException();
	}
}