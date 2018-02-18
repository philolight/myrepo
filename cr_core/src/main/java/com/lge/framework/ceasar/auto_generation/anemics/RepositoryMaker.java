package com.lge.framework.ceasar.auto_generation.anemics;

import java.util.List;

import com.lge.framework.ceasar.repository.TableType;
import com.lge.sm.cr_data_store.entity.DriverEntity;
import com.lge.sm.cr_data_store.entity.RoomSensorEntity;

class RepositoryMaker extends AbstractMaker{
	String mapSetDeclareString = "\n\tprotected MapSet<" + TableTag + "Entity> " + foreigntableTag + "MapSet = new MapSet<>();";
	String mapSetPutString = "\n\t\t" + foreigntableTag + "MapSet.put("+ForeignTableTag+"Entity.newMapKey(entity.get"+ForeignTablePkTag+"()), entity);";
	String mapSetRemoveString = "\n\t\t"+foreigntableTag+"MapSet.remove("+ForeignTableTag+"Entity.newMapKey(entity.get"+ForeignTablePkTag+"()), entity);";
	String mapSetGetString = "\n\tpublic List<"+TableTag+"Entity> getBy"+ForeignTableTag+"Id("+foreignPkTypeTag+" "+foreigntablePkTag+") {\r\n" + 
							"\t\treturn "+foreigntableTag+"MapSet.get("+ForeignTableTag+"Entity.newMapKey("+foreigntablePkTag+"));\r\n" + 
							"\t}";	
	String foreignImportString = "import "+concreteRootPackageNameTag+".entity."+ForeignTableTag+"Entity;\n" +
								"import "+concreteRootPackageNameTag+".repository."+ForeignTableTag+"Repository;\n";

	public RepositoryMaker(String packagePath, String packageName, String concretePackagePath, String concretePackageName, SqlTableKeyCollector c) {
		makerName = "repository";
		MakerName = "Repository";
		
		init(packagePath, packageName, concretePackagePath, concretePackageName, c);
	}
	
	String makeAnemicFile(String in) {
		String permanenceTag = "\"PERMANENCE\"";
		String cacheableTag = "\"CACHEABLE\"";
		String TableTypeTag = "\"TableType\"";
		if(tableType == null) System.out.println("Table " + Table + " tableType is null.");
		switch(tableType) {
			case PERMANENCE :
				in = in.substring(0, in.indexOf(cacheableTag));
				in = in.replaceAll(permanenceTag, "");
				break;
			case CACHEABLE :
				in = in.substring(0, in.indexOf(permanenceTag)) + in.substring(in.indexOf(cacheableTag), in.length()-1);
				in = in.replaceAll(cacheableTag, "");
				break;
			default :
				System.out.println("Table Type Error");
				return null;
		}
		
		in = in.replaceAll(TableTypeTag, tableType.TableTypeString());
		
		String deleteForeignUsedRecordsTag = "\"DELETE_FOREIGN_USED_RECORDS\"";
		String deleteForeignUsedRecordsString = "\t\tList<"+ForeignTableTag+"Entity> "+foreigntableTag+"List = new ArrayList<>();\n" +
											"\t\tfor("+TableTag+"Entity each : entities) "+foreigntableTag+"List.addAll(Repos.repo("+ForeignTableTag+"Repository.class).getBy"+TableTag+"Id("+forPkCommaTag +"<each.get"+TablePkTag+"()>));\n" +
											"\t\tif("+foreigntableTag+"List.size() != 0) {\n" +
											"\t\t\tif(Repos.repo("+ForeignTableTag+"Repository.class).delete("+foreigntableTag+"List) == false) return false;\n" +
											"\t\t}\n";
		
		String deleteForeignUsedEntitiesTag = "\"DELETE_FOREIGN_USED_ENTITIES\"";
		String deleteForeignUsedEntitiesString = "\t\tfor("+TableTag+"Entity each : entities) "+foreigntableTag+"MapSet.remove("+ForeignTableTag+"Entity.newMapKey(each.get"+ForeignTableTag+"Id()), each);";
		
		String checkForeignKeyEntityTag = "\"CHECK_FOREIGN_KEY_ENTITY\"";
		String checkForeignKeyEntityTagString = "\t\tif(dto.get"+ ForeignTablePkTag +"() != null && "+
												"Repos.repo(" + ForeignTableTag + "Repository.class).getByMapKey(" + 
												ForeignTableTag + "Entity.newMapKey(dto.get" + ForeignTablePkTag + "())) == null) return false;\n";
		
		String getNextIdTag = "\"GET_NEXT_ID\"";
		String initLastIdTag = "\"INIT_LAST_ID\"";
		String getNextIdString = 	"\n\tprotected volatile long lastId = -1;" + 
									"\n\tpublic long getNextId() {" +
									"\n\t\treturn ++lastId;" +
									"\n\t}"; 
		String initLastIdString = "\n\t\tlastId = dao.getLastId();";
		
		String setNewIdTag = "\"SET_NEW_ID\"";
		String SingleTableIdTag = "\"SingleTableId\"";
		String setNewIdString = "\t\tdto.set"+SingleTableIdTag+"(getNextId());";
		
		String initParentSkinTypesTag = "\"INIT_PARENT_SKIN_TYPES\"";
		String initParentSkinTypesString = "\n\t\tparentSkinTypes.add("+ForeignTableTag+"Entity.skinType());";
		String initKidSkinTypesTag = "\"INIT_KID_SKIN_TYPES\"";
		String initKidSkinTypesString = "\n\t\tkidSkinTypes.add("+ForeignTableTag+"Entity.skinType());";
		
		String foreignUsedSkinizedTag = "\"FOREIGN_USED_SKINIZED\"";
		String foreignUsedSkinizedString = 
	        "\n\t\tif(kidSkinType.equals(\""+ForeignTableTag+"\")) {" +
	            "\n\t\t\tList<"+ForeignTableTag+"Entity> list = entity.get"+ForeignTableTag+"EntityList();" +
	            "\n\t\t\t"+ForeignTableTag+"Repository repo = Repos.repo("+ForeignTableTag+"Repository.class);" +
	            "\n\t\t\tStringBuffer ret = new StringBuffer();" +
	            "\n\t\t\tret.append(\"[\");" +
	            "\n\t\t\tfor(int i = 0; i < list.size(); i++) {" +
	            	"\n\t\t\t\t"+ForeignTableTag+"Entity each = list.get(i);" +
	                "\n\t\t\t\tret.append(repo.skinized(each));" +
	                "\n\t\t\t\tif(i != list.size()-1) ret.append(\",\");" +
	            "\n\t\t\t}" +
	            "\n\t\t\tret.append(\"]\");" +
	            "\n\t\t\treturn ret.toString();" +
	        "\n\t\t}";
		
		String out = in;
		out = out.replaceAll(packageTag, anemicPackageDeclare);
		out = out.replaceAll(TableTag, Table);
		out = out.replaceAll(tableTag, table);
		out = replaceForPkTag(out);
		out = replaceForPkCommaTag(out);

		List<Field> foreignIds = c.tableFkMap().get(Table);
		
		String mapSetDeclare = "";
		String mapSetPut = "";
		String mapSetRemove = "";
		String mapSetGet = "";
		String foreignImport = "";
		String deleteForeignUsedRecords = "";
		String deleteForeignUsedEntities = "";
		String checkForeignKeyEntity = "";
		
		String initKidSkinTypes = "";
		String initParentSkinTypes = "";
		
		String foreignUsedSkinized = "";
		
		for(Field each : foreignIds) {
			String ForeignTable = each.fkTable;
			String foreigntable = smallName(each.fkTable);
			String ForeignTableId = bigName(each.smallName);
			String foreigntableId = smallName(each.smallName);
			String foreigntableIdType = each.type;
			
			mapSetDeclare += mapSetDeclareString;
			mapSetDeclare = mapSetDeclare.replaceAll(foreigntableTag, foreigntable);
			mapSetDeclare = mapSetDeclare.replaceAll(TableTag, Table);
			
			mapSetPut += mapSetPutString;
			mapSetPut = mapSetPut.replaceAll(ForeignTableTag, ForeignTable);
			mapSetPut = mapSetPut.replaceAll(foreigntableTag, foreigntable);
			mapSetPut = mapSetPut.replaceAll(ForeignTablePkTag, ForeignTableId);
			mapSetPut = mapSetPut.replaceAll(foreigntablePkTag, foreigntableId);
			
			mapSetRemove += mapSetRemoveString;
			mapSetRemove = mapSetRemove.replaceAll(ForeignTableTag, ForeignTable);
			mapSetRemove = mapSetRemove.replaceAll(foreigntableTag, foreigntable);
			mapSetRemove = mapSetRemove.replaceAll(ForeignTablePkTag, ForeignTableId);
			mapSetRemove = mapSetRemove.replaceAll(foreigntablePkTag, foreigntableId);
			
			mapSetGet += mapSetGetString;
			mapSetGet = mapSetGet.replaceAll(TableTag, Table);
			mapSetGet = mapSetGet.replaceAll(ForeignTableTag, ForeignTable);
			mapSetGet = mapSetGet.replaceAll(foreigntableTag, foreigntable);
			mapSetGet = mapSetGet.replaceAll(ForeignTablePkTag, ForeignTableId);
			mapSetGet = mapSetGet.replaceAll(foreigntablePkTag, foreigntableId);
//			System.out.println(ForeignTable + " " + ForeignTableId + " " + foreignPkTypeTag + " " + foreigntableIdType);
			mapSetGet = mapSetGet.replaceAll(foreignPkTypeTag, foreigntableIdType);
						
			checkForeignKeyEntity += checkForeignKeyEntityTagString;
			checkForeignKeyEntity = checkForeignKeyEntity.replaceAll(ForeignTableTag, ForeignTable);
			checkForeignKeyEntity = checkForeignKeyEntity.replaceAll(ForeignTablePkTag, ForeignTableId);
			
			foreignImport += foreignImportString;
			foreignImport = foreignImport.replaceAll(ForeignTableTag, ForeignTable);
			
			initParentSkinTypes += initParentSkinTypesString;
			initParentSkinTypes = initParentSkinTypes.replaceAll(ForeignTableTag, ForeignTable);
					
			deleteForeignUsedEntities += deleteForeignUsedEntitiesString;
			deleteForeignUsedEntities = deleteForeignUsedEntities.replaceAll(TableTag, Table);
			deleteForeignUsedEntities = deleteForeignUsedEntities.replaceAll(ForeignTableTag, ForeignTable);
			deleteForeignUsedEntities = deleteForeignUsedEntities.replaceAll(foreigntableTag, smallName(ForeignTable));
			deleteForeignUsedEntities = replaceForPkCommaTag(deleteForeignUsedEntities);
		}
		
		for(String each : c.foreignUsedMap().get(Table)) {
			if(c.tableTypeMap().get(each) == TableType.PERMANENCE) continue;
			deleteForeignUsedRecords += deleteForeignUsedRecordsString;
			deleteForeignUsedRecords = deleteForeignUsedRecords.replaceAll(TableTag, Table);
			deleteForeignUsedRecords = deleteForeignUsedRecords.replaceAll(ForeignTableTag, each);
			deleteForeignUsedRecords = deleteForeignUsedRecords.replaceAll(foreigntableTag, smallName(each));
			deleteForeignUsedRecords = replaceForPkCommaTag(deleteForeignUsedRecords);
			
			foreignImport += foreignImportString;
			foreignImport = foreignImport.replaceAll(ForeignTableTag, each);

			initKidSkinTypes += initKidSkinTypesString;
			initKidSkinTypes = initKidSkinTypes.replaceAll(ForeignTableTag, each);
			
			foreignUsedSkinized += foreignUsedSkinizedString;
			foreignUsedSkinized = foreignUsedSkinized.replaceAll(ForeignTableTag, each);
		}
		
		out = out.replaceAll("\"FOREIGN_IMPORT\"", foreignImport);
		out = out.replaceAll("\"MAPSET_DECLARE\"", mapSetDeclare);
		out = out.replaceAll("\"MAPSET_PUT\"", mapSetPut);
		out = out.replaceAll("\"MAPSET_REMOVE\"", mapSetRemove);
		out = out.replaceAll("\"MAPSET_GET\"", mapSetGet);
		out = out.replaceAll(deleteForeignUsedRecordsTag, deleteForeignUsedRecords);
		out = out.replaceAll(deleteForeignUsedEntitiesTag, deleteForeignUsedEntities);
		out = out.replaceAll(checkForeignKeyEntityTag, checkForeignKeyEntity);
		
		out = out.replaceAll(initParentSkinTypesTag, initParentSkinTypes);
		out = out.replaceAll(initKidSkinTypesTag, initKidSkinTypes);
		out = out.replaceAll(foreignUsedSkinizedTag, foreignUsedSkinized);		
		
		if(c.tablePkMap().get(Table).size() == 1 && c.tablePkMap().get(Table).get(0).type.equals("Long")) {
			out = out.replaceAll(getNextIdTag, getNextIdString);
			out = out.replaceAll(initLastIdTag, initLastIdString);
			out = out.replaceAll(setNewIdTag, setNewIdString);
			out = out.replaceAll(SingleTableIdTag, c.tablePkMap().get(Table).get(0).bigName);
		}
		else {
			out = out.replaceAll(getNextIdTag, "");
			out = out.replaceAll(initLastIdTag, "");
			out = out.replaceAll(setNewIdTag, "");
		}
		
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
}