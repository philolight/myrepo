package com.lge.framework.ceasar.auto_generation.anemics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lge.framework.ceasar.util.DateStringUtil;

/**
 * TableKeyCollector의 데이터를 기반으로 skin/field_skin/decimal_range/numeric_range/string_range에 집어 넣을 Query들을 생성하는 객체.
 */
public class SkinMaker {
	private String target;
	private SqlTableKeyCollector c;
	private BufferedWriter outFile;
	public SkinMaker(String target, SqlTableKeyCollector c) {
		this.target = target + "skin_init.sql";
		this.c = c;
	}
	
	public void make(){
		try {
			outFile = new BufferedWriter(new FileWriter(target));
			
			for(String Table : c.fieldMap().keySet()) {
				generateSkin(Table);
				generateFieldSkin(Table);
				generateStringRange(Table);
			}
			
			outFile.flush();
			outFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<String/**/, String> skinPair = new HashMap<>();
	private Map<String/**/, String> fieldSkinPair = new HashMap<>();
	private Map<String/**/, String> strRangePair = new HashMap<>();
	
	private String makeInsertQuery(String db_table, Map<String, String> pair) {
		String insertQuery = "insert into "+db_table+"(";
		for(String key : pair.keySet()) {
			insertQuery += key + ",";
		}
		insertQuery = insertQuery.substring(0, insertQuery.length()-1) + ") values(";
		
		for(String key : pair.keySet()) {
			insertQuery += "'" + pair.get(key) + "',";
		}
		insertQuery = insertQuery.substring(0, insertQuery.length()-1) + ");\n";
		
		return insertQuery;
	}
		
	private void generateSkin(String Table) throws Exception{
		String cdate = DateStringUtil.getCurrentDateString("GMT");
		String skin_id = Table;
		
		skinPair.put("cdate", cdate);
		skinPair.put("skin_id", skin_id);
		
		outFile.append(makeInsertQuery("skin", skinPair));
	}
	
	private void generateFieldSkin(String Table) throws Exception{
		String db_table = AbstractMaker.dbName(Table);
		String cdate = DateStringUtil.getCurrentDateString("GMT");
		String skin_id = Table;
		
		fieldSkinPair.put("cdate", cdate);
		fieldSkinPair.put("skin_id", skin_id);
			
		for(Field field : c.fieldMap().get(Table)) {
			fieldSkinPair.put("field_skin_id", Table + "." + field.smallName);
			fieldSkinPair.put("name", field.smallName);
			fieldSkinPair.put("type", field.type);
			if(field.foreignKey || (field.primaryKey && field.isAutoincrement)) {
				fieldSkinPair.put("visible", "0");
				fieldSkinPair.put("editable", "0");
			}
			else if(field.primaryKey) {
				fieldSkinPair.put("visible", "1");
				fieldSkinPair.put("editable", "0");
			}
			else if(field.dbName.equals("cdate")) {
				fieldSkinPair.put("visible", "1");
				fieldSkinPair.put("editable", "0");
			}
			else{
				fieldSkinPair.put("visible", "1");
				fieldSkinPair.put("editable", "1");
			}
			
			if(field.dbName.equals("password")) {
				fieldSkinPair.put("encryption", "1");
				fieldSkinPair.put("hide_typing", "1");
			}
			else {
				fieldSkinPair.put("encryption", "0");
				fieldSkinPair.put("hide_typing", "0");
			}
			
			if(field.nillable) fieldSkinPair.put("nillable", "1");
			else fieldSkinPair.put("nillable", "0");
			
			if(field.isAutoincrement || field.dbName.equals("cdate")) fieldSkinPair.put("auto_fill", "1");
			else fieldSkinPair.put("auto_fill", "0");
			
			if(field.primaryKey) fieldSkinPair.put("is_pk", "1");
			else fieldSkinPair.put("is_pk", "0");
			
			if(field.foreignKey) fieldSkinPair.put("is_fk", "1");
			else fieldSkinPair.put("is_fk", "0");
			
			outFile.append(makeInsertQuery("field_skin", fieldSkinPair));
		}
	}
	
	private void generateStringRange(String Table) throws Exception{
		String db_table = AbstractMaker.dbName(Table);
		String cdate = DateStringUtil.getCurrentDateString("GMT");
		
		strRangePair.put("cdate", cdate);
		
		for(Field field : c.fieldMap().get(Table)) {
			if(field.type.equals("String") == false) continue;

			String field_skin_id = Table + "." + field.smallName;
			
			strRangePair.put("length", "" + field.length);
			strRangePair.put("regex", "*");
			strRangePair.put("field_skin_id", ""+ field_skin_id);			
			
			outFile.append(makeInsertQuery("string_range", strRangePair));
		}
	}
}

