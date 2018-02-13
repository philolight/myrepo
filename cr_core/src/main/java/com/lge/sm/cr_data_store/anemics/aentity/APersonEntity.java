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

import com.lge.sm.cr_data_store.repository.UserRepository;

import com.lge.sm.cr_data_store.entity.PersonEntity;
import com.lge.sm.cr_data_store.dto.PersonDto;

abstract public class APersonEntity extends Entity<PersonDto>{


	public UserEntity getUserEntity() { return Repos.repo(UserRepository.class).getByMapKey(UserEntity.newMapKey(dto.getUserId())); }

    protected APersonEntity() {} // for Serialize
  
    public APersonEntity(PersonDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String userId) { return new MapKey(userId); }
    public static MapKey newMapKey(PersonDto dto)   { return newMapKey(dto.getUserId() ); }  

	public String getUserId() 	{ return dto.getUserId();}
	public String getFirstName() 	{ return dto.getFirstName();}
	public String getLastName() 	{ return dto.getLastName();}
	public String getEmail() 	{ return dto.getEmail();}
	public String getPhoneNumber() 	{ return dto.getPhoneNumber();}
	public String getAddress() 	{ return dto.getAddress();}
	public String getLanguage() 	{ return dto.getLanguage();}
	public String getCdate() 	{ return dto.getCdate();}

	public APersonEntity setUserId(String userId){
		dto.setUserId(userId);
		return this;
	}
	public APersonEntity setFirstName(String firstName){
		dto.setFirstName(firstName);
		return this;
	}
	public APersonEntity setLastName(String lastName){
		dto.setLastName(lastName);
		return this;
	}
	public APersonEntity setEmail(String email){
		dto.setEmail(email);
		return this;
	}
	public APersonEntity setPhoneNumber(String phoneNumber){
		dto.setPhoneNumber(phoneNumber);
		return this;
	}
	public APersonEntity setAddress(String address){
		dto.setAddress(address);
		return this;
	}
	public APersonEntity setLanguage(String language){
		dto.setLanguage(language);
		return this;
	}
	public APersonEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PersonEntity && 
            this.mapKey().equals(((PersonEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Person";
    } 
}
