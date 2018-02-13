package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.DriverTypeEntity;
import com.lge.sm.cr_data_store.entity.TcpEntity;
import com.lge.sm.cr_data_store.entity.PointEntity;
import com.lge.sm.cr_data_store.entity.UrlEntity;

import com.lge.sm.cr_data_store.repository.DriverTypeRepository;
import com.lge.sm.cr_data_store.repository.TcpRepository;
import com.lge.sm.cr_data_store.repository.PointRepository;
import com.lge.sm.cr_data_store.repository.UrlRepository;

import com.lge.sm.cr_data_store.entity.DriverEntity;
import com.lge.sm.cr_data_store.dto.DriverDto;

abstract public class ADriverEntity extends Entity<DriverDto>{

	public List<TcpEntity> getTcpEntityList(){ return Repos.repo(TcpRepository.class).getByDriverId(dto.getDriverId()); }
	public List<PointEntity> getPointEntityList(){ return Repos.repo(PointRepository.class).getByDriverId(dto.getDriverId()); }
	public List<UrlEntity> getUrlEntityList(){ return Repos.repo(UrlRepository.class).getByDriverId(dto.getDriverId()); }

	public DriverTypeEntity getDriverTypeEntity() { return Repos.repo(DriverTypeRepository.class).getByMapKey(DriverTypeEntity.newMapKey(dto.getDriverTypeId())); }

    protected ADriverEntity() {} // for Serialize
  
    public ADriverEntity(DriverDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long driverId) { return new MapKey(driverId); }
    public static MapKey newMapKey(DriverDto dto)   { return newMapKey(dto.getDriverId() ); }  

	public Integer getEnable() 	{ return dto.getEnable();}
	public Long getDriverId() 	{ return dto.getDriverId();}
	public Integer getPingEnable() 	{ return dto.getPingEnable();}
	public Integer getAlarmEnable() 	{ return dto.getAlarmEnable();}
	public Long getPingFrequency() 	{ return dto.getPingFrequency();}
	public String getName() 	{ return dto.getName();}
	public String getDriverTypeId() 	{ return dto.getDriverTypeId();}
	public Integer getPollEnable() 	{ return dto.getPollEnable();}
	public String getCdate() 	{ return dto.getCdate();}

	public ADriverEntity setEnable(Integer enable){
		dto.setEnable(enable);
		return this;
	}
	public ADriverEntity setDriverId(Long driverId){
		dto.setDriverId(driverId);
		return this;
	}
	public ADriverEntity setPingEnable(Integer pingEnable){
		dto.setPingEnable(pingEnable);
		return this;
	}
	public ADriverEntity setAlarmEnable(Integer alarmEnable){
		dto.setAlarmEnable(alarmEnable);
		return this;
	}
	public ADriverEntity setPingFrequency(Long pingFrequency){
		dto.setPingFrequency(pingFrequency);
		return this;
	}
	public ADriverEntity setName(String name){
		dto.setName(name);
		return this;
	}
	public ADriverEntity setDriverTypeId(String driverTypeId){
		dto.setDriverTypeId(driverTypeId);
		return this;
	}
	public ADriverEntity setPollEnable(Integer pollEnable){
		dto.setPollEnable(pollEnable);
		return this;
	}
	public ADriverEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DriverEntity && 
            this.mapKey().equals(((DriverEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Driver";
    } 
}
