package com.lge.framework.ceasar.auto_generation.anemics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.lge.framework.ceasar.repository.TableType;

abstract class AbstractMaker{
	StringBuffer anemicIn = new StringBuffer();
	StringBuffer normalIn = new StringBuffer();
	
	File anemicTextFile;
	String anemicRootPackagePath;
	String anemicPackagePath;
	String anemicPackageName;
	String anemicPackageDeclare;
	
	File concreteTextFile;
	String concreteRootPackagePath;
	String concretePackagePath;
	String concretePackageName;
	String concretePackageDeclare;
	
	String makerName;
	String MakerName;

	SqlTableKeyCollector c;

	String Table;
	String table;
	TableType tableType;

	static final String packageTag 					= "\"PACKAGE_DECLARE\"";
	static final String concreteRootPackageNameTag 	= "\"CONCRETE_ROOT_PACKAGE_NAME\"";
	static final String anemicRootPackageNameTag	= "\"ANEMIC_ROOT_PACKAGE_NAME\"";
	
	static final String TableTag 			= "\"Table\"";
	static final String tableTag 			= "\"table\"";
	static final String TablePkTag 			= "\"TablePk\"";
	static final String tablePkTag 			= "\"tablePk\"";
	static final String table_pk_tag		= "\"table_pk\"";
	static final String pkTypeTag 			= "\"PkType\"";
	static final String forPkCommaTag		= "\"ForPk,\"";
	static final String forPkTag			= "\"ForPk\"";
	
	static final String foreigntableTag 	= "\"foreigntable\"";
	static final String ForeignTableTag 	= "\"ForeignTable\"";
	static final String foreigntablePkTag 	= "\"foreigntablePk\"";
	static final String ForeignTablePkTag 	= "\"ForeignTablePk\"";
	static final String foreignPkTypeTag 	= "\"foreignPkType\"";
	
	static final String fieldTypeTag 		= "\"fieldType\"";
	static final String fieldnameTag 		= "\"fieldname\"";
	static final String FieldNameTag 		= "\"FieldName\"";

	public void init(String anemicRootPackagePath, String anemicRootPackageName, String concreteRootPackagePath, String concreteRootPackageName, SqlTableKeyCollector c) {
		String anemicTextFileName = "./src/main/java/" + AbstractMaker.class.getPackage().getName().replaceAll("[.]", "/") + "/a" + makerName + ".txt";	
		this.anemicTextFile = new File(anemicTextFileName);
		this.anemicRootPackagePath = anemicRootPackagePath;
		this.anemicPackagePath = anemicRootPackagePath + "/a" + makerName + "/";
		this.anemicPackageName = anemicRootPackageName + ".a" + makerName;
		
		File anemicDir = new File(this.anemicPackagePath);
		anemicDir.mkdir();
		
		String concreteTextFileName = "./src/main/java/" + AbstractMaker.class.getPackage().getName().replaceAll("[.]", "/") + "/" + makerName + ".txt";
		this.concreteTextFile = new File(concreteTextFileName);
		this.concreteRootPackagePath = concreteRootPackagePath;
		this.concretePackagePath = concreteRootPackagePath + "/" + makerName + "/";
		this.concretePackageName = concreteRootPackageName + "." + makerName;
		
		File concreteDir = new File(this.concretePackagePath);
		concreteDir.mkdir();
		
		this.c = c;
	}

	abstract String makeAnemicFile(String in);
	abstract String makeConcreteFile(String in);

	@SuppressWarnings("resource")
	public void make() {
		makeAnemic();
		makeConcrete();
	}
	
	private void makeAnemic() {
		Scanner scan = null;
		try {
			scan = new Scanner(anemicTextFile);
			while(scan.hasNextLine()) anemicIn.append(scan.nextLine() + "\n");
			
			anemicPackageDeclare 	= "package " + anemicPackageName + ";";

			for(String key : c.tablePkMap().keySet()) {
				Table 			= key;
				table 			= smallName(Table);
				tableType = c.tableTypeMap().get(Table);
				
				String out = makeAnemicFile(anemicIn.toString());

				BufferedWriter outFile = new BufferedWriter(new FileWriter(anemicPackagePath + "A" + Table + MakerName + ".java"));
				outFile.append(out);
				outFile.flush();
				outFile.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(scan != null) scan.close();
		}
	}
	
	private void makeConcrete() {
		Scanner scan = null;
		try {
			scan = new Scanner(concreteTextFile);
			while(scan.hasNextLine()) normalIn.append(scan.nextLine() + "\n");
			
			concretePackageDeclare 			= "package " + concretePackageName + ";";

			for(String key : c.tablePkMap().keySet()) {
				Table 			= key;
				table 			= smallName(Table);
				
				String targetFileName = concretePackagePath + Table + MakerName + ".java";
				
				File f = new File(targetFileName);
				if(f.exists()) {
					System.out.println("구체 클래스가 이미 존재합니다 : " + targetFileName);
					System.out.println("구체 클래스들은 이미 선언되어 있을 경우 생성하지 않습니다." );
					continue; 
				}
				
				String out = makeConcreteFile(normalIn.toString());
			
				BufferedWriter outFile = new BufferedWriter(new FileWriter(targetFileName));
				outFile.append(out);
				outFile.flush();
				outFile.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(scan != null) scan.close();
		}
	}

	public static String bigName(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static String smallName(String name) {
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}
	
	public static String dbName(String name) {
		String smallName = smallName(name);
		String lowerCaseName = smallName.toLowerCase();
		
		String ret = "";
		
		for(int i = 0; i < smallName.length(); i++) {
			if(smallName.charAt(i) == lowerCaseName.charAt(i)) ret += lowerCaseName.charAt(i);
			else ret += "_" + lowerCaseName.charAt(i);
		}
		
		return ret;
	}
	
	protected String replaceForPkTag(String out) {
		int start = out.indexOf(forPkTag, 0);
		if(start == -1) return out;
				
		int idx = start;
		while(idx != -1) {
			int braceStart = out.indexOf("<", start + 1);
			int end = out.indexOf(">", start + 1);		
			String fromString = out.substring(braceStart + 1, end);
			
//			System.out.println(fromString);
			
			List<Field> pkList = c.tablePkMap().get(Table);
			String replacement = "";
			for(int i = 0; i < pkList.size(); i++) {
				Field each = pkList.get(i);
				replacement += fromString;
				replacement = replacement.replaceAll(tablePkTag, each.smallName);
				replacement = replacement.replaceAll(TablePkTag, each.bigName);
				replacement = replacement.replaceAll(table_pk_tag, each.dbName);
				replacement = replacement.replaceAll(pkTypeTag, each.type);
				replacement = replacement.replaceAll("\\\\t", "\t");
				if(i != pkList.size()-1) replacement += "\n";
			}
			
			String replaceFrom = out.substring(start, end + 1);
//System.out.println("replaceFrom1 = " + replaceFrom + " to " + replacement);
			
			out = out.replace(replaceFrom, replacement);
			
			start = out.indexOf(forPkTag, start + 1);
			idx = start;
		}
		
		return out;
	}
	
	protected String replaceForPkCommaTag(String out){
		int start = out.indexOf(forPkCommaTag, 0);
		if(start == -1) return out;
			
		int idx = start;
		while(idx != -1) {
			int braceStart = out.indexOf("<", start + 1);
			int end = out.indexOf(">", start + 1);		
			String fromString = out.substring(braceStart + 1, end);
			
			List<Field> pkList = c.tablePkMap().get(Table);
			String replacement = "";
			for(int i = 0; i < pkList.size(); i++) {
				Field each = pkList.get(i);
				replacement += fromString;
				replacement = replacement.replaceAll(tablePkTag, each.smallName);
				replacement = replacement.replaceAll(TablePkTag, each.bigName);
				replacement = replacement.replaceAll(table_pk_tag, each.dbName);
				replacement = replacement.replaceAll(pkTypeTag, each.type);
				replacement = replacement.replaceAll("\\\\t", "\t");
				if(i != pkList.size()-1) replacement += ",";
			}
			
			String replaceFrom = out.substring(start, end + 1);
			
			out = out.replace(replaceFrom, replacement);
			
			start = out.indexOf(forPkCommaTag, start + 1);
			idx = start;
		}
		
		return out;
	}
}