package com.lge.framework.ceasar.repository;

public enum TableType {
	CACHEABLE,
	PERMANENCE;
	
	public static TableType getType(String name) {
		for(TableType each : values()){
			if(each.toString().equals(name)) return each;
		}
		
		return null;
	}
	
	public String TableTypeString() {
		return this.toString().charAt(0) + this.toString().toLowerCase().substring(1, this.toString().length());
	}
	
	public String tabletypeString() {
		return this.toString().charAt(0) + this.toString().toLowerCase().substring(1, this.toString().length());
	}
}
