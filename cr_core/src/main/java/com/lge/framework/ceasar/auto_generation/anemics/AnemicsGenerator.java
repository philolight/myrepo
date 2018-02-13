package com.lge.framework.ceasar.auto_generation.anemics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;

/**
 * Repository, Entity, DAO 등 도메인 객체 및 Data Access Layer의 근간을 이루는 anemics 클래스들에 대한 코드 자동 생성을 담당하는 클래스.
 */
public class AnemicsGenerator {
	
	/**
	 * @param String targetPackage : 자동 생성된 anemics 클래스들이 들어갈 패키지. arepository, aentity, adao 패키지가 targetPackage 아래에 생성되고, 그 안에 각 anemic 클래스들이 들어간다.
	 * @param String dtoPath : anemics는 기본적으로 자동 생성된 DTO 소스 파일을 기반으로 만들어진다. 따라서 dto들이 들어 있는 패키지를 지정해 주어야 한다.
	 */
	public void makeDtoAsSerializable(String dtoPath) {
		File dtoPackage = new File(dtoPath);
		
		File[] files = dtoPackage.listFiles();
		
		for(File each : files) {
			if(each.isDirectory()) continue;
			if(each.getAbsolutePath().endsWith("Dto.java") == false) continue;

			Scanner scan;
			try {
				scan = new Scanner(each);
				
				String out = "";
				
				boolean modifyFile = true;
				
				while(scan.hasNextLine()) {
					String line = scan.nextLine();
					
					if(line.startsWith("import java.io.Serializable;")) { // already made
						modifyFile = false;
						break;
					}
					
					if(line.startsWith("public class")) {
						line = line.substring(0, line.length() - 1);
						line = "import java.io.Serializable;\n\n" + line;
						line = line + "implements Serializable {\n";
						line = line + "\tprivate static final long serialVersionUID = " + new Date().getTime() + "L;";
					}
					
					out = out + line + "\n";
				}
				
				scan.close();
				
				if(modifyFile) {
					BufferedWriter outFile;
					outFile = new BufferedWriter(new FileWriter(each.getAbsolutePath()));
					outFile.append(out);
					outFile.flush();
					outFile.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	public void generate(String anemicPackage, String concretePackage, String dtoPath, String skinFilePath) {
		SqlTableKeyCollector c = new SqlTableKeyCollector();
			
		// DTO들을 통해 DB 테이블 명, 필드, 테이블의 primaryKey, foreignKey를 알아낸다.
		c.collect(dtoPath);
		
		String anemicPath = "./src/main/java/" + anemicPackage.replaceAll("[.]", "/") + "/";
		String concretePath = "./src/main/java/" + concretePackage.replaceAll("[.]", "/") + "/";
		String springContextPath = "./resources/spring/";

		SpringContextMaker 	scMaker 	= new SpringContextMaker(springContextPath, c);
		SkinMaker 			skinMaker 	= new SkinMaker(skinFilePath, c);
		DaoMaker 			dao 		= new DaoMaker(anemicPath, anemicPackage, concretePath, concretePackage, c);
		RepositoryMaker 	repository 	= new RepositoryMaker(anemicPath, anemicPackage, concretePath, concretePackage,  c);
		EntityMaker 		entity 		= new EntityMaker(anemicPath, anemicPackage, concretePath, concretePackage, c);
		
		scMaker.make();
		skinMaker.make();
		dao.make();
		repository.make();
		entity.make();

	}
	
	/** Generation Example */
	public static void main(String[] args) {
		
		// target package 계산
		String anemicsPackage = AnemicsGenerator.class.getPackage().getName();
		anemicsPackage = anemicsPackage.substring(0, anemicsPackage.lastIndexOf('.'));	// com.lge.sm.cr_data_store.anemics
		
		String concretePackage = anemicsPackage.substring(0, anemicsPackage.lastIndexOf('.'));	// com.lge.sm.cr_data_store
				
		AnemicsGenerator gen = new AnemicsGenerator();

		String dtoPath = "./src/main/java/" + concretePackage.replaceAll("[.]", "/") + "/dto/";
		gen.makeDtoAsSerializable(dtoPath);
				
		String createSqlPath = "./Scripts/create_table/";
		String skinSqlPath = "./Scripts/init_table/";
		gen.generate(anemicsPackage, concretePackage, createSqlPath, skinSqlPath);
	}
}