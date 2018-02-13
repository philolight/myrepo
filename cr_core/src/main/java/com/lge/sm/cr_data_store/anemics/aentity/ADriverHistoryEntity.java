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

import com.lge.sm.cr_data_store.entity.DriverHistoryEntity;
import com.lge.sm.cr_data_store.dto.DriverHistoryDto;

abstract public class ADriverHistoryEntity extends Entity<DriverHistoryDto>{


	public DriverEntity getDriverEntity() { return Repos.repo(DriverRepository.class).getByMapKey(DriverEntity.newMapKey(dto.getDriverId())); }

    protected ADriverHistoryEntity() {} // for Serialize
  
    public ADriverHistoryEntity(DriverHistoryDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String cdate,Long driverId) { return new MapKey(cdate,driverId); }
    public static MapKey newMapKey(DriverHistoryDto dto)   { return newMapKey(dto.getCdate(),dto.getDriverId() ); }  

	public String getCdate() 	{ return dto.getCdate();}
	public Long getDriverId() 	{ return dto.getDriverId();}
	public String getStatus() 	{ return dto.getStatus();}
	public String getFaultCause() 	{ return dto.getFaultCause();}
	public Integer getEnable() 	{ return dto.getEnable();}
	public Integer getPingEnable() 	{ return dto.getPingEnable();}
	public Long getPingFrequency() 	{ return dto.getPingFrequency();}
	public Integer getPollEnable() 	{ return dto.getPollEnable();}
	public Long getPollFrequency() 	{ return dto.getPollFrequency();}

	public ADriverHistoryEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public ADriverHistoryEntity setDriverId(Long driverId){
		dto.setDriverId(driverId);
		return this;
	}
	public ADriverHistoryEntity setStatus(String status){
		dto.setStatus(status);
		return this;
	}
	public ADriverHistoryEntity setFaultCause(String faultCause){
		dto.setFaultCause(faultCause);
		return this;
	}
	public ADriverHistoryEntity setEnable(Integer enable){
		dto.setEnable(enable);
		return this;
	}
	public ADriverHistoryEntity setPingEnable(Integer pingEnable){
		dto.setPingEnable(pingEnable);
		return this;
	}
	public ADriverHistoryEntity setPingFrequency(Long pingFrequency){
		dto.setPingFrequency(pingFrequency);
		return this;
	}
	public ADriverHistoryEntity setPollEnable(Integer pollEnable){
		dto.setPollEnable(pollEnable);
		return this;
	}
	public ADriverHistoryEntity setPollFrequency(Long pollFrequency){
		dto.setPollFrequency(pollFrequency);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DriverHistoryEntity && 
            this.mapKey().equals(((DriverHistoryEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "DriverHistory";
    } 
}
