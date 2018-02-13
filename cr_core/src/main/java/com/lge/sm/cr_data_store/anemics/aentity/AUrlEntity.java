package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.DriverEntity;

import com.lge.sm.cr_data_store.repository.DriverRepository;

import com.lge.sm.cr_data_store.entity.UrlEntity;
import com.lge.sm.cr_data_store.dto.UrlDto;

abstract public class AUrlEntity extends Entity<UrlDto>{


	public DriverEntity getDriverEntity() { return Repos.repo(DriverRepository.class).getByMapKey(DriverEntity.newMapKey(dto.getDriverId())); }

    protected AUrlEntity() {} // for Serialize
  
    public AUrlEntity(UrlDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long urlId,String url) { return new MapKey(urlId,url); }
    public static MapKey newMapKey(UrlDto dto)   { return newMapKey(dto.getUrlId(),dto.getUrl() ); }  

	public Long getUrlId() 	{ return dto.getUrlId();}
	public String getUrl() 	{ return dto.getUrl();}
	public Integer getAuthEnable() 	{ return dto.getAuthEnable();}
	public String getUserName() 	{ return dto.getUserName();}
	public String getPassword() 	{ return dto.getPassword();}
	public String getCdate() 	{ return dto.getCdate();}
	public Long getDriverId() 	{ return dto.getDriverId();}

	public AUrlEntity setUrlId(Long urlId){
		dto.setUrlId(urlId);
		return this;
	}
	public AUrlEntity setUrl(String url){
		dto.setUrl(url);
		return this;
	}
	public AUrlEntity setAuthEnable(Integer authEnable){
		dto.setAuthEnable(authEnable);
		return this;
	}
	public AUrlEntity setUserName(String userName){
		dto.setUserName(userName);
		return this;
	}
	public AUrlEntity setPassword(String password){
		dto.setPassword(password);
		return this;
	}
	public AUrlEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public AUrlEntity setDriverId(Long driverId){
		dto.setDriverId(driverId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UrlEntity && 
            this.mapKey().equals(((UrlEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Url";
    } 
}
