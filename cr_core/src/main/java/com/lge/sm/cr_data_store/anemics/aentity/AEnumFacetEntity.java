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

import com.lge.sm.cr_data_store.entity.EnumFacetEntity;
import com.lge.sm.cr_data_store.dto.EnumFacetDto;

abstract public class AEnumFacetEntity extends Entity<EnumFacetDto>{


	public FieldSkinEntity getFieldSkinEntity() { return Repos.repo(FieldSkinRepository.class).getByMapKey(FieldSkinEntity.newMapKey(dto.getFieldSkinId())); }

    protected AEnumFacetEntity() {} // for Serialize
  
    public AEnumFacetEntity(EnumFacetDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long enumFacetId) { return new MapKey(enumFacetId); }
    public static MapKey newMapKey(EnumFacetDto dto)   { return newMapKey(dto.getEnumFacetId() ); }  

	public Long getEnumFacetId() 	{ return dto.getEnumFacetId();}
	public String getValue() 	{ return dto.getValue();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getScriptName() 	{ return dto.getScriptName();}
	public String getFieldSkinId() 	{ return dto.getFieldSkinId();}

	public AEnumFacetEntity setEnumFacetId(Long enumFacetId){
		dto.setEnumFacetId(enumFacetId);
		return this;
	}
	public AEnumFacetEntity setValue(String value){
		dto.setValue(value);
		return this;
	}
	public AEnumFacetEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public AEnumFacetEntity setScriptName(String scriptName){
		dto.setScriptName(scriptName);
		return this;
	}
	public AEnumFacetEntity setFieldSkinId(String fieldSkinId){
		dto.setFieldSkinId(fieldSkinId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EnumFacetEntity && 
            this.mapKey().equals(((EnumFacetEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "EnumFacet";
    } 
}
