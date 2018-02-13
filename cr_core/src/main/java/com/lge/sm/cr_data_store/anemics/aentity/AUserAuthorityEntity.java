package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.UserEntity;
import com.lge.sm.cr_data_store.entity.AuthorityEntity;

import com.lge.sm.cr_data_store.repository.UserRepository;
import com.lge.sm.cr_data_store.repository.AuthorityRepository;

import com.lge.sm.cr_data_store.entity.UserAuthorityEntity;
import com.lge.sm.cr_data_store.dto.UserAuthorityDto;

abstract public class AUserAuthorityEntity extends Entity<UserAuthorityDto>{


	public UserEntity getUserEntity() { return Repos.repo(UserRepository.class).getByMapKey(UserEntity.newMapKey(dto.getUserId())); }
	public AuthorityEntity getAuthorityEntity() { return Repos.repo(AuthorityRepository.class).getByMapKey(AuthorityEntity.newMapKey(dto.getAuthorityId())); }

    protected AUserAuthorityEntity() {} // for Serialize
  
    public AUserAuthorityEntity(UserAuthorityDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String userId,Long authorityId) { return new MapKey(userId,authorityId); }
    public static MapKey newMapKey(UserAuthorityDto dto)   { return newMapKey(dto.getUserId(),dto.getAuthorityId() ); }  

	public String getUserId() 	{ return dto.getUserId();}
	public Long getAuthorityId() 	{ return dto.getAuthorityId();}
	public String getCdate() 	{ return dto.getCdate();}

	public AUserAuthorityEntity setUserId(String userId){
		dto.setUserId(userId);
		return this;
	}
	public AUserAuthorityEntity setAuthorityId(Long authorityId){
		dto.setAuthorityId(authorityId);
		return this;
	}
	public AUserAuthorityEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UserAuthorityEntity && 
            this.mapKey().equals(((UserAuthorityEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "UserAuthority";
    } 
}
