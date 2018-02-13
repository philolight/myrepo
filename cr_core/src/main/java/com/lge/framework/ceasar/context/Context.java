package com.lge.framework.ceasar.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Context {
	private Map<Class<?>, Object> map = new ConcurrentHashMap<>();
	private Map<ContextType, String> contextTypeMap = new ConcurrentHashMap<>();
	
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz) {
		return (T)map.get(clazz);
	}
	
	public String get(ContextType type) {
		return contextTypeMap.get(type);
	}
}
