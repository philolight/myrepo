package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.PartyAuthorityEntity;
import com.lge.sm.cr_data_store.entity.UserAuthorityEntity;
import com.lge.sm.cr_data_store.entity.ServiceAuthorityEntity;
import com.lge.sm.cr_data_store.entity.AuthorityLocationEntity;

import com.lge.sm.cr_data_store.repository.PartyAuthorityRepository;
import com.lge.sm.cr_data_store.repository.UserAuthorityRepository;
import com.lge.sm.cr_data_store.repository.ServiceAuthorityRepository;
import com.lge.sm.cr_data_store.repository.AuthorityLocationRepository;

import com.lge.sm.cr_data_store.entity.AuthorityEntity;
import com.lge.sm.cr_data_store.dto.AuthorityDto;

abstract public class AAuthorityEntity extends Entity<AuthorityDto>{

	public List<PartyAuthorityEntity> getPartyAuthorityEntityList(){ return Repos.repo(PartyAuthorityRepository.class).getByAuthorityId(dto.getAuthorityId()); }
	public List<UserAuthorityEntity> getUserAuthorityEntityList(){ return Repos.repo(UserAuthorityRepository.class).getByAuthorityId(dto.getAuthorityId()); }
	public List<ServiceAuthorityEntity> getServiceAuthorityEntityList(){ return Repos.repo(ServiceAuthorityRepository.class).getByAuthorityId(dto.getAuthorityId()); }
	public List<AuthorityLocationEntity> getAuthorityLocationEntityList(){ return Repos.repo(AuthorityLocationRepository.class).getByAuthorityId(dto.getAuthorityId()); }


    protected AAuthorityEntity() {} // for Serialize
  
    public AAuthorityEntity(AuthorityDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long authorityId) { return new MapKey(authorityId); }
    public static MapKey newMapKey(AuthorityDto dto)   { return newMapKey(dto.getAuthorityId() ); }  

	public Long getAuthorityId() 	{ return dto.getAuthorityId();}
	public String getType() 	{ return dto.getType();}
	public String getCdate() 	{ return dto.getCdate();}

	public AAuthorityEntity setAuthorityId(Long authorityId){
		dto.setAuthorityId(authorityId);
		return this;
	}
	public AAuthorityEntity setType(String type){
		dto.setType(type);
		return this;
	}
	public AAuthorityEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AuthorityEntity && 
            this.mapKey().equals(((AuthorityEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Authority";
    } 
}
