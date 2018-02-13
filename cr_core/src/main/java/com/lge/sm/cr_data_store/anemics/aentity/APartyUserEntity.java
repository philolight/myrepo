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
import com.lge.sm.cr_data_store.entity.PartyEntity;

import com.lge.sm.cr_data_store.repository.UserRepository;
import com.lge.sm.cr_data_store.repository.PartyRepository;

import com.lge.sm.cr_data_store.entity.PartyUserEntity;
import com.lge.sm.cr_data_store.dto.PartyUserDto;

abstract public class APartyUserEntity extends Entity<PartyUserDto>{


	public UserEntity getUserEntity() { return Repos.repo(UserRepository.class).getByMapKey(UserEntity.newMapKey(dto.getUserId())); }
	public PartyEntity getPartyEntity() { return Repos.repo(PartyRepository.class).getByMapKey(PartyEntity.newMapKey(dto.getPartyId())); }

    protected APartyUserEntity() {} // for Serialize
  
    public APartyUserEntity(PartyUserDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String userId,String partyId) { return new MapKey(userId,partyId); }
    public static MapKey newMapKey(PartyUserDto dto)   { return newMapKey(dto.getUserId(),dto.getPartyId() ); }  

	public String getUserId() 	{ return dto.getUserId();}
	public String getPartyId() 	{ return dto.getPartyId();}
	public String getCdate() 	{ return dto.getCdate();}

	public APartyUserEntity setUserId(String userId){
		dto.setUserId(userId);
		return this;
	}
	public APartyUserEntity setPartyId(String partyId){
		dto.setPartyId(partyId);
		return this;
	}
	public APartyUserEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PartyUserEntity && 
            this.mapKey().equals(((PartyUserEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "PartyUser";
    } 
}
