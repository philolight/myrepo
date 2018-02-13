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
import com.lge.sm.cr_data_store.entity.LocationEntity;

import com.lge.sm.cr_data_store.repository.AuthorityRepository;
import com.lge.sm.cr_data_store.repository.LocationRepository;

import com.lge.sm.cr_data_store.entity.AuthorityLocationEntity;
import com.lge.sm.cr_data_store.dto.AuthorityLocationDto;

abstract public class AAuthorityLocationEntity extends Entity<AuthorityLocationDto>{


	public AuthorityEntity getAuthorityEntity() { return Repos.repo(AuthorityRepository.class).getByMapKey(AuthorityEntity.newMapKey(dto.getAuthorityId())); }
	public LocationEntity getLocationEntity() { return Repos.repo(LocationRepository.class).getByMapKey(LocationEntity.newMapKey(dto.getLocationId())); }

    protected AAuthorityLocationEntity() {} // for Serialize
  
    public AAuthorityLocationEntity(AuthorityLocationDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long authorityId,String locationId) { return new MapKey(authorityId,locationId); }
    public static MapKey newMapKey(AuthorityLocationDto dto)   { return newMapKey(dto.getAuthorityId(),dto.getLocationId() ); }  

	public Long getAuthorityId() 	{ return dto.getAuthorityId();}
	public String getLocationId() 	{ return dto.getLocationId();}
	public String getCdate() 	{ return dto.getCdate();}

	public AAuthorityLocationEntity setAuthorityId(Long authorityId){
		dto.setAuthorityId(authorityId);
		return this;
	}
	public AAuthorityLocationEntity setLocationId(String locationId){
		dto.setLocationId(locationId);
		return this;
	}
	public AAuthorityLocationEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AuthorityLocationEntity && 
            this.mapKey().equals(((AuthorityLocationEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "AuthorityLocation";
    } 
}
