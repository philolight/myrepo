package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.entity.RoomSensorEntity;

import com.lge.sm.cr_data_store.repository.LocationRepository;
import com.lge.sm.cr_data_store.repository.RoomSensorRepository;

import com.lge.sm.cr_data_store.entity.RoomEntity;
import com.lge.sm.cr_data_store.dto.RoomDto;

abstract public class ARoomEntity extends Entity<RoomDto>{

	public List<RoomSensorEntity> getRoomSensorEntityList(){ return Repos.repo(RoomSensorRepository.class).getByRoomId(dto.getRoomId()); }

	public LocationEntity getLocationEntity() { return Repos.repo(LocationRepository.class).getByMapKey(LocationEntity.newMapKey(dto.getLocationId())); }

    protected ARoomEntity() {} // for Serialize
  
    public ARoomEntity(RoomDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String roomId) { return new MapKey(roomId); }
    public static MapKey newMapKey(RoomDto dto)   { return newMapKey(dto.getRoomId() ); }  

	public String getRoomId() 	{ return dto.getRoomId();}
	public String getName() 	{ return dto.getName();}
	public Integer getEnable() 	{ return dto.getEnable();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getUdate() 	{ return dto.getUdate();}
	public String getLocationId() 	{ return dto.getLocationId();}

	public ARoomEntity setRoomId(String roomId){
		dto.setRoomId(roomId);
		return this;
	}
	public ARoomEntity setName(String name){
		dto.setName(name);
		return this;
	}
	public ARoomEntity setEnable(Integer enable){
		dto.setEnable(enable);
		return this;
	}
	public ARoomEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public ARoomEntity setUdate(String udate){
		dto.setUdate(udate);
		return this;
	}
	public ARoomEntity setLocationId(String locationId){
		dto.setLocationId(locationId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RoomEntity && 
            this.mapKey().equals(((RoomEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Room";
    } 
}
