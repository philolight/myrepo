package com.lge.framework.ceasar.auto_generation.anemics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.lge.framework.ceasar.repository.TableType;

public class SqlTableKeyCollector{
	private Map<String/*Table*/, List<Field>> 	tablePkMap 	= new HashMap<>();
	private Map<String/*Table*/, List<Field>> 	tableFkMap 	= new HashMap<>();
	private Map<String/*Table*/, List<Field>> 	fieldMap 	= new HashMap<>();
	private Map<String/*Table*/, List<String>/*Table*/> foreignUsedMap 	= new HashMap<>();
	private Map<String/*Table*/, TableType> 	tableTypeMap = new HashMap<>();
	
	public Map<String, List<Field>> tablePkMap() { return tablePkMap; }
	public Map<String, List<Field>> tableFkMap() { return tableFkMap; }
	public Map<String, List<Field>> fieldMap() { return fieldMap; }
	public Map<String, List<String>> foreignUsedMap() { return foreignUsedMap;}
	public Map<String, TableType> tableTypeMap() { return tableTypeMap; }
	
	public void collect(String sqlScriptPath) {
		File sqlScriptFolder = new File(sqlScriptPath);
		
		File[] files = sqlScriptFolder.listFiles();
		
		try {
			makeFieldMap(files);
			makeTablePkMap();
			makeTableFkMap();
			makeForeignUsedMap();
			makeTableTypeMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void makeTableTypeMap() {
		String configFileName = "./src/main/java/" + AbstractMaker.class.getPackage().getName().replaceAll("[.]", "/") + "/generation_config.txt";
		File f = new File(configFileName);
		try {
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner token = new Scanner(line);
				String TableName = Field.dbNameToBigName(token.next());
				token.next();
				String typeName = token.next();
				tableTypeMap.put(TableName, TableType.getType(typeName));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void makeForeignUsedMap() {
		for(String Table : fieldMap.keySet()) {
			foreignUsedMap.put(Table, new ArrayList<>());
//			System.out.println("Table = " + Table);
		}
		
		for(String ForeignTable : tableFkMap.keySet()) {
			List<Field> fkList = tableFkMap.get(ForeignTable);
			for(Field each : fkList) {
				List<String> tables = foreignUsedMap.get(each.fkTable);
				tables.add(ForeignTable);
			}
		}
	}
	
	private void makeTablePkMap(){
		for(String TableName : fieldMap.keySet()) {
			List<Field> fields = fieldMap.get(TableName);
			
			List<Field> pks = new ArrayList<>();
			for(Field each : fields) if(each.primaryKey) pks.add(each);
			tablePkMap.put(TableName, pks);
		}
	}
	
	private void makeTableFkMap(){
		for(String TableName : fieldMap.keySet()) {
			List<Field> fields = fieldMap.get(TableName);
			
			List<Field> pks = new ArrayList<>();
			for(Field each : fields) if(each.foreignKey) pks.add(each);
			tableFkMap.put(TableName, pks);
		}
	}
	
	private void makeFieldMap(File[] files) throws FileNotFoundException{
		for(File each : files) {
			if(each.isDirectory()) continue;
			if(each.getAbsolutePath().endsWith(".sql") == false) continue;

			Scanner scan = new Scanner(each);
			
			String TableName = "";
			
			while(scan.hasNextLine()) {
				String line = scan.nextLine().trim();
				
				if(line.startsWith("CREATE TABLE")) {
					Scanner token = new Scanner(line);
					token.next(); // "CREATE" 버림
					token.next(); // "TABLE" 버림
					String table_name = token.next();
					TableName = Field.dbNameToBigName(table_name);
					tablePkMap.put(TableName, new ArrayList<>());
					tableFkMap.put(TableName, new ArrayList<>());
					fieldMap.put(TableName, new ArrayList<>());
				}
				else if(line.startsWith("ALTER TABLE")){ // PK, FK 제약이 추가됨.
					
					Scanner token = new Scanner(line);
					token.next(); // ALTER 버림
					token.next(); // TABLE 버림
					String table_name = token.next();
					TableName = Field.dbNameToBigName(table_name);
					token.next(); // ADD 버림
					String key = token.next();
					if(key.equals("PRIMARY")) {
						token.next(); // KEY 버림
						addPks(TableName, token.next());
					}
					else { // CONSTRAINT
						token.next(); // relation 이름 버림
						token.next(); // FOREIGN 버림
						token.next(); // KEY 버림
						String fk = token.next();
						fk = fk.substring(1, fk.length() - 1);
						token.next();
						String fkTable = token.next();
						for(Field field : fieldMap.get(TableName)) {
							if(field.dbName.equals(fk)) {
								field.foreignKey = true;
								field.fkTable = Field.dbNameToBigName(fkTable);
								break;
							}
						}
					}
				}
				else if(line.startsWith("(")) handleFields(TableName, scan);
			}

			scan.close(); 
		}
	}
	
	private void handleFields(String TableName, Scanner scan) {
//		System.out.println("TableName = " + TableName);
		while(scan.hasNextLine()) {
			String line = scan.nextLine().trim();
			
			if(line.startsWith("INDEX")) continue;
			else if(line.startsWith("PRIMARY KEY")) {
				Scanner token = new Scanner(line);
				token.next(); // PRIMARY 버림
				token.next(); // KEY 버림
				addPks(TableName, token.next());
			}
			else if(line.startsWith(")")) break;
			else { // field
				Scanner token = new Scanner(line);
				String field_name = token.next();
				String typeName = token.next();
				if(typeName.endsWith(",")) typeName.substring(0, typeName.length()-1);
				
				Field newField = new Field(typeName, field_name, true, false, false, false);				
				
				if(token.hasNext()) {
					if(token.next().equals("NOT")) {
						String nullStr = token.next();
						if(nullStr.endsWith(",")) nullStr = nullStr.substring(0, nullStr.length()-1);
						
						if(nullStr.equals("NULL")) newField.nillable = false;
					}
				}
				
				List<Field> fields = fieldMap.get(TableName);
				fields.add(newField);
			}
		}
	}
	
	private void addPks(String TableName, String pks) {
		if(pks.endsWith(",")) pks = pks.substring(0, pks.length()-1);
		pks = pks.substring(1, pks.length() - 1);				
		while(pks.indexOf(',') != -1) {
			String pk = pks.substring(0, pks.indexOf(','));
			for(Field field : fieldMap.get(TableName)) {
				if(field.dbName.equals(pk)) {
					field.primaryKey = true;
					break;
				}
			}
			pks = pks.substring(pks.indexOf(',') + 1);
		}
		for(Field field : fieldMap.get(TableName)) {
			if(field.dbName.equals(pks)) {
				field.primaryKey = true;
				break;
			}
		}
	}
	
	private Field getFieldByName(String TableName, String fk) {
		for(Field each : fieldMap.get(TableName)) {
			if(each.dbName.equals(fk)) return each;
		}
		return null;
	}
	
	private Field getLastField(String TableName) {
		List<Field> fields = fieldMap.get(TableName);
		return fields.get(fields.size()-1);
	}
	
	public static void main(String[] args) {
		SqlTableKeyCollector c= new SqlTableKeyCollector();
		c.collect("C://Users/USER/git/smart_building_cr/smart_building/cr_core/Scripts/create_table/");
		for(String TableName : c.fieldMap.keySet()) {
			System.out.println("Table = " + TableName);
			for(Field each : c.fieldMap.get(TableName)) System.out.println(each);
		}
	}
}

