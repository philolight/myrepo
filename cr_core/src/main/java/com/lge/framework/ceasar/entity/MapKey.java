package com.lge.framework.ceasar.entity;

import java.io.Serializable;

/**
 * 특정 ET(Entity)를 지칭하는 키
 * DB의 Primary key나 일반적인 자료형을 그대로 사용하여 ET에 바로 접근하는 실수를 막기 위해 정의함.
 * (immutable)
 */
public class MapKey implements Serializable{
	private static final long serialVersionUID = 3782478068462173284L;

	private static final String SEPARATOR = "@|@";
	
	private String key = "";
	public MapKey(Object...objects) {
		for(Object obj : objects) key += obj.toString() + SEPARATOR;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof MapKey && key.equals(((MapKey)obj).key);
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
}
