package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.AuthorityEntity;
import com.lge.sm.cr_data_store.entity.ServiceEntity;

import com.lge.sm.cr_data_store.repository.AuthorityRepository;
import com.lge.sm.cr_data_store.repository.ServiceRepository;

import com.lge.sm.cr_data_store.entity.ServiceAuthorityEntity;
import com.lge.sm.cr_data_store.dto.ServiceAuthorityDto;

abstract public class AServiceAuthorityEntity extends Entity<ServiceAuthorityDto>{


	public AuthorityEntity getAuthorityEntity() { return Repos.repo(AuthorityRepository.class).getByMapKey(AuthorityEntity.newMapKey(dto.getAuthorityId())); }
	public ServiceEntity getServiceEntity() { return Repos.repo(ServiceRepository.class).getByMapKey(ServiceEntity.newMapKey(dto.getServiceId())); }

    protected AServiceAuthorityEntity() {} // for Serialize
  
    public AServiceAuthorityEntity(ServiceAuthorityDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long authorityId,String serviceId) { return new MapKey(authorityId,serviceId); }
    public static MapKey newMapKey(ServiceAuthorityDto dto)   { return newMapKey(dto.getAuthorityId(),dto.getServiceId() ); }  

	public Long getAuthorityId() 	{ return dto.getAuthorityId();}
	public String getServiceId() 	{ return dto.getServiceId();}
	public String getCdate() 	{ return dto.getCdate();}

	public AServiceAuthorityEntity setAuthorityId(Long authorityId){
		dto.setAuthorityId(authorityId);
		return this;
	}
	public AServiceAuthorityEntity setServiceId(String serviceId){
		dto.setServiceId(serviceId);
		return this;
	}
	public AServiceAuthorityEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ServiceAuthorityEntity && 
            this.mapKey().equals(((ServiceAuthorityEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "ServiceAuthority";
    } 
}
