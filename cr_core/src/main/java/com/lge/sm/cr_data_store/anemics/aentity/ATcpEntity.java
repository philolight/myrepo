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

import com.lge.sm.cr_data_store.entity.TcpEntity;
import com.lge.sm.cr_data_store.dto.TcpDto;

abstract public class ATcpEntity extends Entity<TcpDto>{


	public DriverEntity getDriverEntity() { return Repos.repo(DriverRepository.class).getByMapKey(DriverEntity.newMapKey(dto.getDriverId())); }

    protected ATcpEntity() {} // for Serialize
  
    public ATcpEntity(TcpDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long tcpId) { return new MapKey(tcpId); }
    public static MapKey newMapKey(TcpDto dto)   { return newMapKey(dto.getTcpId() ); }  

	public String getIp() 	{ return dto.getIp();}
	public Integer getPort() 	{ return dto.getPort();}
	public Long getTcpId() 	{ return dto.getTcpId();}
	public String getCdate() 	{ return dto.getCdate();}
	public Long getDriverId() 	{ return dto.getDriverId();}

	public ATcpEntity setIp(String ip){
		dto.setIp(ip);
		return this;
	}
	public ATcpEntity setPort(Integer port){
		dto.setPort(port);
		return this;
	}
	public ATcpEntity setTcpId(Long tcpId){
		dto.setTcpId(tcpId);
		return this;
	}
	public ATcpEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public ATcpEntity setDriverId(Long driverId){
		dto.setDriverId(driverId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TcpEntity && 
            this.mapKey().equals(((TcpEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Tcp";
    } 
}
