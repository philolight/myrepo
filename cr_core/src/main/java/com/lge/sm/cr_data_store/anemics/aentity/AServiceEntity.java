package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.StartableEntity;
import com.lge.sm.cr_data_store.entity.ServiceAuthorityEntity;

import com.lge.sm.cr_data_store.repository.StartableRepository;
import com.lge.sm.cr_data_store.repository.ServiceAuthorityRepository;

import com.lge.sm.cr_data_store.entity.ServiceEntity;
import com.lge.sm.cr_data_store.dto.ServiceDto;

abstract public class AServiceEntity extends Entity<ServiceDto>{

	public List<ServiceAuthorityEntity> getServiceAuthorityEntityList(){ return Repos.repo(ServiceAuthorityRepository.class).getByServiceId(dto.getServiceId()); }

	public StartableEntity getStartableEntity() { return Repos.repo(StartableRepository.class).getByMapKey(StartableEntity.newMapKey(dto.getStartableId())); }

    protected AServiceEntity() {} // for Serialize
  
    public AServiceEntity(ServiceDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String serviceId) { return new MapKey(serviceId); }
    public static MapKey newMapKey(ServiceDto dto)   { return newMapKey(dto.getServiceId() ); }  

	public String getServiceId() 	{ return dto.getServiceId();}
	public String getUpperServiceId() 	{ return dto.getUpperServiceId();}
	public String getScriptName() 	{ return dto.getScriptName();}
	public Integer getEnable() 	{ return dto.getEnable();}
	public String getStartableId() 	{ return dto.getStartableId();}
	public String getCdate() 	{ return dto.getCdate();}

	public AServiceEntity setServiceId(String serviceId){
		dto.setServiceId(serviceId);
		return this;
	}
	public AServiceEntity setUpperServiceId(String upperServiceId){
		dto.setUpperServiceId(upperServiceId);
		return this;
	}
	public AServiceEntity setScriptName(String scriptName){
		dto.setScriptName(scriptName);
		return this;
	}
	public AServiceEntity setEnable(Integer enable){
		dto.setEnable(enable);
		return this;
	}
	public AServiceEntity setStartableId(String startableId){
		dto.setStartableId(startableId);
		return this;
	}
	public AServiceEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ServiceEntity && 
            this.mapKey().equals(((ServiceEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Service";
    } 
}
