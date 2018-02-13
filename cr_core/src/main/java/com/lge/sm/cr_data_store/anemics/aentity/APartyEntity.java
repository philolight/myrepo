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
import com.lge.sm.cr_data_store.entity.PartyUserEntity;

import com.lge.sm.cr_data_store.repository.PartyAuthorityRepository;
import com.lge.sm.cr_data_store.repository.PartyUserRepository;

import com.lge.sm.cr_data_store.entity.PartyEntity;
import com.lge.sm.cr_data_store.dto.PartyDto;

abstract public class APartyEntity extends Entity<PartyDto>{

	public List<PartyAuthorityEntity> getPartyAuthorityEntityList(){ return Repos.repo(PartyAuthorityRepository.class).getByPartyId(dto.getPartyId()); }
	public List<PartyUserEntity> getPartyUserEntityList(){ return Repos.repo(PartyUserRepository.class).getByPartyId(dto.getPartyId()); }


    protected APartyEntity() {} // for Serialize
  
    public APartyEntity(PartyDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String partyId) { return new MapKey(partyId); }
    public static MapKey newMapKey(PartyDto dto)   { return newMapKey(dto.getPartyId() ); }  

	public String getPartyId() 	{ return dto.getPartyId();}
	public String getDescription() 	{ return dto.getDescription();}
	public String getCdate() 	{ return dto.getCdate();}

	public APartyEntity setPartyId(String partyId){
		dto.setPartyId(partyId);
		return this;
	}
	public APartyEntity setDescription(String description){
		dto.setDescription(description);
		return this;
	}
	public APartyEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PartyEntity && 
            this.mapKey().equals(((PartyEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Party";
    } 
}
