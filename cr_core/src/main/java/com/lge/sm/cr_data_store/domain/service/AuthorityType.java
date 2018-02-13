package com.lge.sm.cr_data_store.domain.service;

public enum AuthorityType {
	ADMIN(0),
	NORMAL(1),
	DEFINED(2);
	
	private int value;
	private AuthorityType(int value) { this.value = value; }
	
	public int getValue() { return value; }
	
	public static AuthorityType getType(String name) {
		for(AuthorityType each : values()){
			if(each.toString().equals(name)) return each;
		}
		
		return null; // error
	}
}
