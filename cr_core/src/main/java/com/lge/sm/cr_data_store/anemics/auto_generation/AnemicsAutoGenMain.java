package com.lge.sm.cr_data_store.anemics.auto_generation;

import com.lge.framework.ceasar.auto_generation.anemics.AnemicsGenerator;

public class AnemicsAutoGenMain {
	public static void main(String[] args) {
		String anemicsPackage = AnemicsAutoGenMain.class.getPackage().getName();		
		anemicsPackage = anemicsPackage.substring(0, anemicsPackage.lastIndexOf('.'));
		String concretePackage = anemicsPackage.substring(0, anemicsPackage.lastIndexOf('.'));
		AnemicsGenerator gen = new AnemicsGenerator();
		
		String dtoPath = "./src/main/java/" + concretePackage.replaceAll("[.]", "/") + "/dto/";
		gen.makeDtoAsSerializable(dtoPath);

		String createSqlPath = "./Scripts/create_table/";
		String skinSqlPath = "./Scripts/init_table/";
		gen.generate(anemicsPackage, concretePackage, createSqlPath, skinSqlPath);
	}
}
