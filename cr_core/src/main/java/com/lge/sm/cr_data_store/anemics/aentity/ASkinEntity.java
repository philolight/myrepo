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

import com.lge.sm.cr_data_store.entity.SkinEntity;
import com.lge.sm.cr_data_store.dto.SkinDto;

abstract public class ASkinEntity extends Entity<SkinDto>{

	public List<FieldSkinEntity> getFieldSkinEntityList(){ return Repos.repo(FieldSkinRepository.class).getBySkinId(dto.getSkinId()); }


    protected ASkinEntity() {} // for Serialize
  
    public ASkinEntity(SkinDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String skinId) { return new MapKey(skinId); }
    public static MapKey newMapKey(SkinDto dto)   { return newMapKey(dto.getSkinId() ); }  

	public String getCdate() 	{ return dto.getCdate();}
	public String getSkinId() 	{ return dto.getSkinId();}

	public ASkinEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public ASkinEntity setSkinId(String skinId){
		dto.setSkinId(skinId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SkinEntity && 
            this.mapKey().equals(((SkinEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Skin";
    } 
}
