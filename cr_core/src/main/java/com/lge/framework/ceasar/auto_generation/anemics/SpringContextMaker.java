package com.lge.framework.ceasar.auto_generation.anemics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class SpringContextMaker {
	private String target;
	private SqlTableKeyCollector c;
	private BufferedWriter outFile;
	public SpringContextMaker(String target, SqlTableKeyCollector c) {
		this.target = target + "mapperDaoContext.xml";
		this.c = c;
	}
	
	public void make(){	
		String text = "";
		
		String textFileName = "./src/main/java/" + AbstractMaker.class.getPackage().getName().replaceAll("[.]", "/") + "/mapperDaoContext.xml";
		File textFile = new File(textFileName);
		Scanner scan = null;
		try {
			scan = new Scanner(textFile);
			while(scan.hasNextLine()) text += scan.nextLine() + "\n";
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
				
		String mapperString = 
		"\n\n\t<bean id=\"" + AbstractMaker.tableTag + "DtoMapper\" class=\"org.mybatis.spring.mapper.MapperFactoryBean\">" + 
		"\n\t\t<property name=\"mapperInterface\" value=\"com.lge.sm.cr_data_store.mapper."+AbstractMaker.TableTag+"DtoMapper\" />" +
		"\n\t\t<property name=\"sqlSessionFactory\" ref=\"sqlSessionFactory\" />" +
		"\n\t</bean>";
		
		String daoString =
		"\n\n\t<bean id=\"" + AbstractMaker.tableTag + "DAO\" class=\"com.lge.sm.cr_data_store.dao."+AbstractMaker.TableTag+"Dao\">" +
		"\n\t\t<constructor-arg ref=\""+AbstractMaker.tableTag+"DtoMapper\" />" +
		"\n\t</bean>";

		try {
			outFile = new BufferedWriter(new FileWriter(target));
			
			for(String Table : c.fieldMap().keySet()) {
				text += mapperString + daoString;
				text = text.replaceAll(AbstractMaker.tableTag, AbstractMaker.smallName(Table));
				text = text.replaceAll(AbstractMaker.TableTag, Table);
			}
			
			outFile.append(text);
			outFile.append("</beans>\n");
			outFile.flush();
			outFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
