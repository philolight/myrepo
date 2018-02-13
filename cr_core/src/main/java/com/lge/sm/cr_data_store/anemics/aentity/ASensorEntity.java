package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.SlmEntity;
import com.lge.sm.cr_data_store.entity.RoomSensorEntity;

import com.lge.sm.cr_data_store.repository.SlmRepository;
import com.lge.sm.cr_data_store.repository.RoomSensorRepository;

import com.lge.sm.cr_data_store.entity.SensorEntity;
import com.lge.sm.cr_data_store.dto.SensorDto;

abstract public class ASensorEntity extends Entity<SensorDto>{

	public List<RoomSensorEntity> getRoomSensorEntityList(){ return Repos.repo(RoomSensorRepository.class).getBySensorId(dto.getSensorId()); }

	public SlmEntity getSlmEntity() { return Repos.repo(SlmRepository.class).getByMapKey(SlmEntity.newMapKey(dto.getSlmId())); }

    protected ASensorEntity() {} // for Serialize
  
    public ASensorEntity(SensorDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String sensorId) { return new MapKey(sensorId); }
    public static MapKey newMapKey(SensorDto dto)   { return newMapKey(dto.getSensorId() ); }  

	public String getSlmId() 	{ return dto.getSlmId();}
	public String getSensorId() 	{ return dto.getSensorId();}
	public String getName() 	{ return dto.getName();}
	public String getCdate() 	{ return dto.getCdate();}

	public ASensorEntity setSlmId(String slmId){
		dto.setSlmId(slmId);
		return this;
	}
	public ASensorEntity setSensorId(String sensorId){
		dto.setSensorId(sensorId);
		return this;
	}
	public ASensorEntity setName(String name){
		dto.setName(name);
		return this;
	}
	public ASensorEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SensorEntity && 
            this.mapKey().equals(((SensorEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Sensor";
    } 
}
