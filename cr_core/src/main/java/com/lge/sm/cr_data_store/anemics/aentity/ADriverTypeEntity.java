package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.DriverEntity;

import com.lge.sm.cr_data_store.repository.DriverRepository;

import com.lge.sm.cr_data_store.entity.DriverTypeEntity;
import com.lge.sm.cr_data_store.dto.DriverTypeDto;

abstract public class ADriverTypeEntity extends Entity<DriverTypeDto>{

	public List<DriverEntity> getDriverEntityList(){ return Repos.repo(DriverRepository.class).getByDriverTypeId(dto.getDriverTypeId()); }


    protected ADriverTypeEntity() {} // for Serialize
  
    public ADriverTypeEntity(DriverTypeDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String driverTypeId) { return new MapKey(driverTypeId); }
    public static MapKey newMapKey(DriverTypeDto dto)   { return newMapKey(dto.getDriverTypeId() ); }  

	public String getDriverTypeId() 	{ return dto.getDriverTypeId();}
	public String getCdate() 	{ return dto.getCdate();}

	public ADriverTypeEntity setDriverTypeId(String driverTypeId){
		dto.setDriverTypeId(driverTypeId);
		return this;
	}
	public ADriverTypeEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DriverTypeEntity && 
            this.mapKey().equals(((DriverTypeEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "DriverType";
    } 
}
