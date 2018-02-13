package com.lge.framework.ceasar.auto_generation.anemics;

import java.util.List;

class DaoMaker extends AbstractMaker{
	public DaoMaker(String packagePath, String packageName, String concretePackagePath, String concretePackageName, SqlTableKeyCollector c) {
		makerName = "dao";
		MakerName = "Dao";
		
		init(packagePath, packageName, concretePackagePath, concretePackageName, c);
	}
	
	String makeAnemicFile(String in) {
		String getLastIdTag = "\"GET_LAST_ID\\(\\)\"";
		
		String getLastIdTypeString =
				"\n\tpublic long getLastId() throws IllegalAccessError{" +
						"\n\t\tList<"+TableTag+"Dto> dtos = null;"+
						"\n\t\t\tsynchronized(example) {"+
						"\n\t\t\tcleanExample().setOrderByClause(\""+table_pk_tag+" desc limit 1\");"+
						"\n\t\t\texample.or();"+
						"\n\t\t\tdtos = mapper.selectByExample(example);"+
						"\n\t\t}"+
						"\n\t\tif(dtos == null) throw new IllegalAccessError(\"database error\");"+
						"\n\t\tif(dtos.size() == 1) return dtos.get(0).get"+TablePkTag+"();"+
						"\n\t\treturn -1;"+
						"\n\t}";
		
		String out = in;
		
		if(c.tablePkMap().get(Table).size() == 1 && c.tablePkMap().get(Table).get(0).type.equals("Long")) {
			out = out.replaceAll(getLastIdTag, getLastIdTypeString);
			out = out.replaceAll(table_pk_tag, c.tablePkMap().get(Table).get(0).dbName);
			out = out.replaceAll(TablePkTag, c.tablePkMap().get(Table).get(0).bigName);
		}
		else out = out.replaceAll(getLastIdTag, "");
		
		out = out.replaceAll(packageTag, anemicPackageDeclare);
		out = out.replaceAll(anemicRootPackageNameTag, anemicPackageName.substring(0, anemicPackageName.lastIndexOf(".")));
		out = out.replaceAll(concreteRootPackageNameTag, concretePackageName.substring(0, concretePackageName.lastIndexOf(".")));
		out = out.replaceAll(TableTag, Table);
		out = out.replaceAll(tableTag, table);
		
		out = replaceForPkTag(out);
		out = replaceForPkCommaTag(out);
		
		return out;
	}
	
	String makeConcreteFile(String in) {		
		String out = in;

		out = out.replaceAll(packageTag, concretePackageDeclare);
		out = out.replaceAll(anemicRootPackageNameTag, anemicPackageName.substring(0, anemicPackageName.lastIndexOf(".")));
		out = out.replaceAll(concreteRootPackageNameTag, concretePackageName.substring(0, concretePackageName.lastIndexOf(".")));
		out = out.replaceAll(TableTag, Table);
		out = out.replaceAll(tableTag, table);
		
		out = replaceForPkTag(out);
		out = replaceForPkCommaTag(out);
		
		return out;
	}
}