package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.RoomEntity;
import com.lge.sm.cr_data_store.entity.SensorEntity;
import com.lge.sm.cr_data_store.entity.LocationEntity;

import com.lge.sm.cr_data_store.repository.RoomRepository;
import com.lge.sm.cr_data_store.repository.SensorRepository;
import com.lge.sm.cr_data_store.repository.LocationRepository;

import com.lge.sm.cr_data_store.entity.RoomSensorEntity;
import com.lge.sm.cr_data_store.dto.RoomSensorDto;

abstract public class ARoomSensorEntity extends Entity<RoomSensorDto>{


	public RoomEntity getRoomEntity() { return Repos.repo(RoomRepository.class).getByMapKey(RoomEntity.newMapKey(dto.getRoomId())); }
	public SensorEntity getSensorEntity() { return Repos.repo(SensorRepository.class).getByMapKey(SensorEntity.newMapKey(dto.getSensorId())); }
	public LocationEntity getLocationEntity() { return Repos.repo(LocationRepository.class).getByMapKey(LocationEntity.newMapKey(dto.getLocationId())); }

    protected ARoomSensorEntity() {} // for Serialize
  
    public ARoomSensorEntity(RoomSensorDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long roomSensorId) { return new MapKey(roomSensorId); }
    public static MapKey newMapKey(RoomSensorDto dto)   { return newMapKey(dto.getRoomSensorId() ); }  

	public Long getRoomSensorId() 	{ return dto.getRoomSensorId();}
	public String getRoomId() 	{ return dto.getRoomId();}
	public String getSensorId() 	{ return dto.getSensorId();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getUdate() 	{ return dto.getUdate();}
	public Integer getStatus() 	{ return dto.getStatus();}
	public String getLocationId() 	{ return dto.getLocationId();}

	public ARoomSensorEntity setRoomSensorId(Long roomSensorId){
		dto.setRoomSensorId(roomSensorId);
		return this;
	}
	public ARoomSensorEntity setRoomId(String roomId){
		dto.setRoomId(roomId);
		return this;
	}
	public ARoomSensorEntity setSensorId(String sensorId){
		dto.setSensorId(sensorId);
		return this;
	}
	public ARoomSensorEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public ARoomSensorEntity setUdate(String udate){
		dto.setUdate(udate);
		return this;
	}
	public ARoomSensorEntity setStatus(Integer status){
		dto.setStatus(status);
		return this;
	}
	public ARoomSensorEntity setLocationId(String locationId){
		dto.setLocationId(locationId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RoomSensorEntity && 
            this.mapKey().equals(((RoomSensorEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "RoomSensor";
    } 
}
