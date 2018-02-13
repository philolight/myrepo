package com.lge.framework.ceasar.auto_generation.anemics;

import java.util.Date;

class Field {
	public String type;
	public String smallName;
	public String bigName;
	public String dbName;
	public int length = 0;
	public boolean nillable;
	public boolean primaryKey;
	public boolean foreignKey;
	public boolean isAutoincrement;
	public String fkTable; 
	
	public Field(String type, String name) {
		this.type = type;
		this.smallName = name;
	}
	
	public Field(String typeString, String dbName, boolean nillable, boolean primaryKey, boolean foreignKey, boolean isAutoincrement) {
		this.type = getType(typeString);
		this.dbName = dbName;
		this.smallName = dbNameToSmallName(dbName);
		this.bigName = bigName();
		this.nillable = nillable;
		this.primaryKey = primaryKey;
		this.foreignKey = foreignKey;
		this.isAutoincrement = isAutoincrement;
	}
	
	private String getType(String typeString) {
		if(typeString.endsWith(",")) typeString = typeString.substring(0, typeString.length()-1);
		if(typeString.startsWith("Varchar(")) {
			String intString = typeString.substring(8, typeString.length()-1);
			length = Integer.parseInt(intString);
			return "String";
		}
		else if(typeString.startsWith("Char(")) {
			String intString = typeString.substring(5, typeString.length()-1);
			length = Integer.parseInt(intString);
			return "String";
		}
		else if(typeString.equals("Bigint")) return "Long";
		else if(typeString.equals("Double")) return "Double";
		else if(typeString.equals("Float")) return "Float";
		else if(typeString.equals("Int")) return "Integer";
		else if(typeString.equals("MediumInt")) return "Integer";
		else if(typeString.equals("Timestamp")) return "Date";
		else if(typeString.equals("Text")) return "String";
	    
//		if(typeString.startsWith("varchar(")) {
//			String intString = typeString.substring(8, typeString.length()-1);
//			length = Integer.parseInt(intString);
//			return "String";
//		}
//		else if(typeString.equals("INTEGER")) return "Integer";
//		else if(typeString.equals("datetime")) return "String";
//		else if(typeString.equals("int")) return "Integer";
//		else if(typeString.equals("NUMERIC")) return "Double";
//		else if(typeString.equals("boolean")) return "Integer";
//		else if(typeString.equals("smallint")) return "Integer";
//		else if(typeString.equals("float")) return "Double";
//		else if(typeString.equals("bigint")) return "Integer";
//		else if(typeString.startsWith("char(")) return "String";
		System.err.println("No right Type : Field.getType(" + typeString + ")");
		return null;
	}
	
	public String bigName() {
		return smallName.substring(0, 1).toUpperCase() + smallName.substring(1);
	}
	
	public String smallName() {
		return smallName.substring(0, 1).toLowerCase() + smallName.substring(1);
	}
	
	public static String dbNameToSmallName(String dbName) {
		String ret = "" + dbName.charAt(0);
		
		for(int i = 1; i < dbName.length(); i++) {
			if(dbName.charAt(i) == '_') ret += ("" + dbName.charAt(++i)).toUpperCase();
			else ret += dbName.charAt(i);
		}
		
		return ret;
	}
	
	public static String dbNameToBigName(String dbName) {
		String ret = ("" + dbName.charAt(0)).toUpperCase();
		
		for(int i = 1; i < dbName.length(); i++) {
			if(dbName.charAt(i) == '_') ret += ("" + dbName.charAt(++i)).toUpperCase();
			else ret += dbName.charAt(i);
		}
		
		return ret;
	}
	
	@Override
	public String toString() {
		String nillableStr = nillable? "nillable" : "        ";
		String pkStr = primaryKey? "pk" : "  ";
		String fkStr = foreignKey? "fk" : "  ";
		String autoIncStr = isAutoincrement? "Autoincrement" : "  ";
		String fkTableStr = foreignKey? "fkt:" + fkTable : "  ";
		return "(" + type + ", " + smallName + ", " + length + ", " + nillableStr + ", " + pkStr + ", " + fkStr + ", " + autoIncStr +", " + fkTableStr + ")";
	}
}
