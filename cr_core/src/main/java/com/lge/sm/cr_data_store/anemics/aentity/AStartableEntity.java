package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.ServiceEntity;

import com.lge.sm.cr_data_store.repository.ServiceRepository;

import com.lge.sm.cr_data_store.entity.StartableEntity;
import com.lge.sm.cr_data_store.dto.StartableDto;

abstract public class AStartableEntity extends Entity<StartableDto>{

	public List<ServiceEntity> getServiceEntityList(){ return Repos.repo(ServiceRepository.class).getByStartableId(dto.getStartableId()); }


    protected AStartableEntity() {} // for Serialize
  
    public AStartableEntity(StartableDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String startableId) { return new MapKey(startableId); }
    public static MapKey newMapKey(StartableDto dto)   { return newMapKey(dto.getStartableId() ); }  

	public String getStartableId() 	{ return dto.getStartableId();}
	public String getType() 	{ return dto.getType();}
	public String getStatus() 	{ return dto.getStatus();}
	public String getCdate() 	{ return dto.getCdate();}

	public AStartableEntity setStartableId(String startableId){
		dto.setStartableId(startableId);
		return this;
	}
	public AStartableEntity setType(String type){
		dto.setType(type);
		return this;
	}
	public AStartableEntity setStatus(String status){
		dto.setStatus(status);
		return this;
	}
	public AStartableEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StartableEntity && 
            this.mapKey().equals(((StartableEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Startable";
    } 
}
