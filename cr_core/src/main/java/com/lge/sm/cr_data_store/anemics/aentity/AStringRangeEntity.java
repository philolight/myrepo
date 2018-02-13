package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.FieldSkinEntity;

import com.lge.sm.cr_data_store.repository.FieldSkinRepository;

import com.lge.sm.cr_data_store.entity.StringRangeEntity;
import com.lge.sm.cr_data_store.dto.StringRangeDto;

abstract public class AStringRangeEntity extends Entity<StringRangeDto>{


	public FieldSkinEntity getFieldSkinEntity() { return Repos.repo(FieldSkinRepository.class).getByMapKey(FieldSkinEntity.newMapKey(dto.getFieldSkinId())); }

    protected AStringRangeEntity() {} // for Serialize
  
    public AStringRangeEntity(StringRangeDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long stringRangeId) { return new MapKey(stringRangeId); }
    public static MapKey newMapKey(StringRangeDto dto)   { return newMapKey(dto.getStringRangeId() ); }  

	public Integer getLength() 	{ return dto.getLength();}
	public String getRegex() 	{ return dto.getRegex();}
	public String getCdate() 	{ return dto.getCdate();}
	public Long getStringRangeId() 	{ return dto.getStringRangeId();}
	public String getScriptName() 	{ return dto.getScriptName();}
	public String getFieldSkinId() 	{ return dto.getFieldSkinId();}

	public AStringRangeEntity setLength(Integer length){
		dto.setLength(length);
		return this;
	}
	public AStringRangeEntity setRegex(String regex){
		dto.setRegex(regex);
		return this;
	}
	public AStringRangeEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public AStringRangeEntity setStringRangeId(Long stringRangeId){
		dto.setStringRangeId(stringRangeId);
		return this;
	}
	public AStringRangeEntity setScriptName(String scriptName){
		dto.setScriptName(scriptName);
		return this;
	}
	public AStringRangeEntity setFieldSkinId(String fieldSkinId){
		dto.setFieldSkinId(fieldSkinId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringRangeEntity && 
            this.mapKey().equals(((StringRangeEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "StringRange";
    } 
}
