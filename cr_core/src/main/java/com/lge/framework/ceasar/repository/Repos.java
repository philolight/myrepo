package com.lge.framework.ceasar.repository;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Repos {
	private static Map<Class<? extends PermanenceRepository>, PermanenceRepository> map = new HashMap<>();
	
	public static void add(PermanenceRepository repo) {
		map.put(repo.getClass(), repo);
	}
	
	public static <T extends PermanenceRepository> T repo(Class<T> clazz) {
		return (T)map.get(clazz);
	}
}