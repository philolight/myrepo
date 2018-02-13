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
import com.lge.sm.cr_data_store.entity.PartyEntity;

import com.lge.sm.cr_data_store.repository.AuthorityRepository;
import com.lge.sm.cr_data_store.repository.PartyRepository;

import com.lge.sm.cr_data_store.entity.PartyAuthorityEntity;
import com.lge.sm.cr_data_store.dto.PartyAuthorityDto;

abstract public class APartyAuthorityEntity extends Entity<PartyAuthorityDto>{


	public AuthorityEntity getAuthorityEntity() { return Repos.repo(AuthorityRepository.class).getByMapKey(AuthorityEntity.newMapKey(dto.getAuthorityId())); }
	public PartyEntity getPartyEntity() { return Repos.repo(PartyRepository.class).getByMapKey(PartyEntity.newMapKey(dto.getPartyId())); }

    protected APartyAuthorityEntity() {} // for Serialize
  
    public APartyAuthorityEntity(PartyAuthorityDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long authorityId,String partyId) { return new MapKey(authorityId,partyId); }
    public static MapKey newMapKey(PartyAuthorityDto dto)   { return newMapKey(dto.getAuthorityId(),dto.getPartyId() ); }  

	public Long getAuthorityId() 	{ return dto.getAuthorityId();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getPartyId() 	{ return dto.getPartyId();}

	public APartyAuthorityEntity setAuthorityId(Long authorityId){
		dto.setAuthorityId(authorityId);
		return this;
	}
	public APartyAuthorityEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public APartyAuthorityEntity setPartyId(String partyId){
		dto.setPartyId(partyId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PartyAuthorityEntity && 
            this.mapKey().equals(((PartyAuthorityEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "PartyAuthority";
    } 
}
