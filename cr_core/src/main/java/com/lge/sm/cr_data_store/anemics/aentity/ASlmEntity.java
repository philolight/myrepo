package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.SensorEntity;

import com.lge.sm.cr_data_store.repository.SensorRepository;

import com.lge.sm.cr_data_store.entity.SlmEntity;
import com.lge.sm.cr_data_store.dto.SlmDto;

abstract public class ASlmEntity extends Entity<SlmDto>{

	public List<SensorEntity> getSensorEntityList(){ return Repos.repo(SensorRepository.class).getBySlmId(dto.getSlmId()); }


    protected ASlmEntity() {} // for Serialize
  
    public ASlmEntity(SlmDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String slmId) { return new MapKey(slmId); }
    public static MapKey newMapKey(SlmDto dto)   { return newMapKey(dto.getSlmId() ); }  

	public String getSlmId() 	{ return dto.getSlmId();}
	public String getProtocol() 	{ return dto.getProtocol();}
	public String getIp() 	{ return dto.getIp();}
	public Integer getPort() 	{ return dto.getPort();}
	public Integer getUseAuth() 	{ return dto.getUseAuth();}
	public String getUserId() 	{ return dto.getUserId();}
	public String getUserPw() 	{ return dto.getUserPw();}
	public String getCdate() 	{ return dto.getCdate();}

	public ASlmEntity setSlmId(String slmId){
		dto.setSlmId(slmId);
		return this;
	}
	public ASlmEntity setProtocol(String protocol){
		dto.setProtocol(protocol);
		return this;
	}
	public ASlmEntity setIp(String ip){
		dto.setIp(ip);
		return this;
	}
	public ASlmEntity setPort(Integer port){
		dto.setPort(port);
		return this;
	}
	public ASlmEntity setUseAuth(Integer useAuth){
		dto.setUseAuth(useAuth);
		return this;
	}
	public ASlmEntity setUserId(String userId){
		dto.setUserId(userId);
		return this;
	}
	public ASlmEntity setUserPw(String userPw){
		dto.setUserPw(userPw);
		return this;
	}
	public ASlmEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SlmEntity && 
            this.mapKey().equals(((SlmEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Slm";
    } 
}
