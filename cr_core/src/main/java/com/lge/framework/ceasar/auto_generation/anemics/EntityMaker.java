package com.lge.framework.ceasar.auto_generation.anemics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lge.framework.ceasar.repository.TableType;

class EntityMaker extends AbstractMaker{
	String foreignUsedGetString = "\n\tpublic List<"+ForeignTableTag+"Entity> get"+ForeignTableTag+"EntityList(){ return Repos.repo("+ForeignTableTag+"Repository.class).getBy"+
									TablePkTag+"(dto.get"+TablePkTag+"()); }";
	String foreignEntityGetString = "\n\tpublic "+ForeignTableTag+"Entity get"+ForeignTableTag+"Entity() { return Repos.repo("+ForeignTableTag+"Repository.class).getByMapKey("+ForeignTableTag+"Entity.newMapKey(dto.get"+ForeignTablePkTag+"())); }";
	
	String foreignRepositoryImportString = "import " + concreteRootPackageNameTag + ".repository."+ForeignTableTag+"Repository;\n";
	String foreignEntityImportString = "import " +concreteRootPackageNameTag+".entity."+ForeignTableTag+"Entity;\n";
	
	String getterString = "\n\tpublic "+fieldTypeTag+" get"+FieldNameTag+"() 	{ return dto.get"+FieldNameTag+"();}";
	String setterString = "\n\tpublic A"+TableTag+"Entity set"+FieldNameTag+"("+fieldTypeTag+" "+fieldnameTag+"){" + 
			"\n\t\tdto.set"+FieldNameTag+"("+fieldnameTag+");" + 
			"\n\t\treturn this;" + 
			"\n\t}";
	
	public EntityMaker(String packagePath, String packageName, String concretePackagePath, String concretePackageName, SqlTableKeyCollector c) {
		makerName = "entity";
		MakerName = "Entity";
		
		init(packagePath, packageName, concretePackagePath, concretePackageName, c);
	}
	
	String makeAnemicFile(String in) {
		String out = in;
		out = out.replaceAll(packageTag, anemicPackageDeclare);
		out = out.replaceAll(TableTag, Table);
		out = out.replaceAll(tableTag, table);
		out = replaceForPkTag(out);
		out = replaceForPkCommaTag(out);
		
		List<Field> foreignIds = c.tableFkMap().get(Table);
		
		String foreignUsedRepo = "";
		String foreignUsedGet = "";
		String foreignEntityRepo = "";
		String foreignEntityGet = "";
		String foreignEntityImport = "";
		String foreignRepositoryImport = "";
			
		for(Field each : foreignIds) {
			String ForeignTable = each.fkTable;
			String foreigntable = smallName(each.fkTable);
			String ForeignTableId = bigName(each.smallName);
			String foreigntableId = smallName(each.smallName);
			
			foreignEntityGet += foreignEntityGetString;
			foreignEntityGet = foreignEntityGet.replaceAll(ForeignTableTag, ForeignTable);
			foreignEntityGet = foreignEntityGet.replaceAll(foreigntableTag, foreigntable);
			foreignEntityGet = foreignEntityGet.replaceAll(ForeignTablePkTag, ForeignTableId);
			foreignEntityGet = foreignEntityGet.replaceAll(foreigntablePkTag, foreigntableId);
			
			foreignRepositoryImport += foreignRepositoryImportString;
			foreignRepositoryImport = foreignRepositoryImport.replaceAll(ForeignTableTag, ForeignTable);
			foreignEntityImport += foreignEntityImportString;
			foreignEntityImport = foreignEntityImport.replaceAll(ForeignTableTag, ForeignTable);
		}
		
		Map<String, String> foreignUsedMap = getForeignUseTable(Table);
		for(String ForeignUsedTable : foreignUsedMap.keySet()) {
			String foreignusedtable = smallName(ForeignUsedTable);
			String foreignusedtableId = foreignUsedMap.get(ForeignUsedTable);
			String ForeignUsedTableId = bigName(foreignusedtableId);
						
			foreignUsedGet += foreignUsedGetString;
			foreignUsedGet = foreignUsedGet.replaceAll(ForeignTableTag, ForeignUsedTable);
			foreignUsedGet = foreignUsedGet.replaceAll(foreigntableTag, foreignusedtable);
			foreignUsedGet = foreignUsedGet.replaceAll(ForeignTablePkTag, ForeignUsedTableId);
			foreignUsedGet = foreignUsedGet.replaceAll(foreigntablePkTag, foreignusedtableId);
			
			// TODO : 키가 한 개라고 가정하고 만든 코드.
			foreignUsedGet = foreignUsedGet.replaceAll(TablePkTag, c.tablePkMap().get(Table).get(0).bigName);
			foreignUsedGet = foreignUsedGet.replaceAll(tablePkTag, c.tablePkMap().get(Table).get(0).smallName);
			
			foreignRepositoryImport += foreignRepositoryImportString;
			foreignRepositoryImport = foreignRepositoryImport.replaceAll(ForeignTableTag, ForeignUsedTable);
			foreignEntityImport += foreignEntityImportString;
			foreignEntityImport = foreignEntityImport.replaceAll(ForeignTableTag, ForeignUsedTable);
		}
		
		String getters = "";
		String setters = "";
		for(Field each : c.fieldMap().get(Table)) {
			getters += getterString;
			getters = getters.replaceAll(fieldTypeTag, each.type);
			getters = getters.replaceAll(fieldnameTag, each.smallName);
			getters = getters.replaceAll(FieldNameTag, bigName(each.smallName));
			getters = getters.replaceAll(TableTag, Table);

			setters += setterString;
			setters = setters.replaceAll(fieldTypeTag, each.type);
			setters = setters.replaceAll(fieldnameTag, each.smallName);
			setters = setters.replaceAll(FieldNameTag, bigName(each.smallName));
			setters = setters.replaceAll(TableTag, Table);
		}
			
		out = out.replaceAll("\"FOREIGN_ET_IMPORT\"", foreignEntityImport);
		out = out.replaceAll("\"FOREIGN_REPOSITORY_IMPORT\"", foreignRepositoryImport);
		out = out.replaceAll("\"FOREIGN_USED_REPO\"", foreignUsedRepo);
		out = out.replaceAll("\"FOREIGN_USED_GET\"", foreignUsedGet);
		out = out.replaceAll("\"FOREIGN_ET_REPO\"", foreignEntityRepo);
		out = out.replaceAll("\"FOREIGN_ET_GET\"", foreignEntityGet);
		out = out.replaceAll("\"GETTERS\"", getters);
		out = out.replaceAll("\"SETTERS\"", setters);

		out = out.replaceAll(anemicRootPackageNameTag, anemicPackageName.substring(0, anemicPackageName.lastIndexOf(".")));
		out = out.replaceAll(concreteRootPackageNameTag, concretePackageName.substring(0, concretePackageName.lastIndexOf(".")));
		
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
	
	private Map<String, String> getForeignUseTable(String Table){
		Map<String, String> tableUsed = new HashMap<>();
		
		for(String foreignTable : c.tableFkMap().keySet()) {
			 if(c.tableTypeMap().get(foreignTable) == TableType.PERMANENCE) continue;
			List<Field> fields = c.tableFkMap().get(foreignTable);
			for(Field each : fields) {
				if(each.fkTable.equals(Table)) {
					tableUsed.put(foreignTable, each.smallName);
					break;
				}
			}
		}
		
		return tableUsed;
	}
}