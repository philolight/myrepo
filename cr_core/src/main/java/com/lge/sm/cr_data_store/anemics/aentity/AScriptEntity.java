package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;



import com.lge.sm.cr_data_store.entity.ScriptEntity;
import com.lge.sm.cr_data_store.dto.ScriptDto;

abstract public class AScriptEntity extends Entity<ScriptDto>{



    protected AScriptEntity() {} // for Serialize
  
    public AScriptEntity(ScriptDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long scriptId) { return new MapKey(scriptId); }
    public static MapKey newMapKey(ScriptDto dto)   { return newMapKey(dto.getScriptId() ); }  

	public Long getScriptId() 	{ return dto.getScriptId();}
	public String getText() 	{ return dto.getText();}
	public String getLanguage() 	{ return dto.getLanguage();}
	public String getScriptName() 	{ return dto.getScriptName();}
	public String getCdate() 	{ return dto.getCdate();}

	public AScriptEntity setScriptId(Long scriptId){
		dto.setScriptId(scriptId);
		return this;
	}
	public AScriptEntity setText(String text){
		dto.setText(text);
		return this;
	}
	public AScriptEntity setLanguage(String language){
		dto.setLanguage(language);
		return this;
	}
	public AScriptEntity setScriptName(String scriptName){
		dto.setScriptName(scriptName);
		return this;
	}
	public AScriptEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ScriptEntity && 
            this.mapKey().equals(((ScriptEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Script";
    } 
}
