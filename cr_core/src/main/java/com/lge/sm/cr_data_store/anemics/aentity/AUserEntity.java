package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.UserAuthorityEntity;
import com.lge.sm.cr_data_store.entity.PersonEntity;
import com.lge.sm.cr_data_store.entity.PartyUserEntity;

import com.lge.sm.cr_data_store.repository.UserAuthorityRepository;
import com.lge.sm.cr_data_store.repository.PersonRepository;
import com.lge.sm.cr_data_store.repository.PartyUserRepository;

import com.lge.sm.cr_data_store.entity.UserEntity;
import com.lge.sm.cr_data_store.dto.UserDto;

abstract public class AUserEntity extends Entity<UserDto>{

	public List<UserAuthorityEntity> getUserAuthorityEntityList(){ return Repos.repo(UserAuthorityRepository.class).getByUserId(dto.getUserId()); }
	public List<PersonEntity> getPersonEntityList(){ return Repos.repo(PersonRepository.class).getByUserId(dto.getUserId()); }
	public List<PartyUserEntity> getPartyUserEntityList(){ return Repos.repo(PartyUserRepository.class).getByUserId(dto.getUserId()); }


    protected AUserEntity() {} // for Serialize
  
    public AUserEntity(UserDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String userId) { return new MapKey(userId); }
    public static MapKey newMapKey(UserDto dto)   { return newMapKey(dto.getUserId() ); }  

	public String getUserId() 	{ return dto.getUserId();}
	public String getPassword() 	{ return dto.getPassword();}
	public Long getLoginCount() 	{ return dto.getLoginCount();}
	public String getLastLoginTime() 	{ return dto.getLastLoginTime();}
	public String getPwUdate() 	{ return dto.getPwUdate();}
	public String getCdate() 	{ return dto.getCdate();}

	public AUserEntity setUserId(String userId){
		dto.setUserId(userId);
		return this;
	}
	public AUserEntity setPassword(String password){
		dto.setPassword(password);
		return this;
	}
	public AUserEntity setLoginCount(Long loginCount){
		dto.setLoginCount(loginCount);
		return this;
	}
	public AUserEntity setLastLoginTime(String lastLoginTime){
		dto.setLastLoginTime(lastLoginTime);
		return this;
	}
	public AUserEntity setPwUdate(String pwUdate){
		dto.setPwUdate(pwUdate);
		return this;
	}
	public AUserEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UserEntity && 
            this.mapKey().equals(((UserEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "User";
    } 
}
