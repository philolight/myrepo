package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.RoomSensorEntity;
import com.lge.sm.cr_data_store.entity.AuthorityLocationEntity;
import com.lge.sm.cr_data_store.entity.RoomEntity;

import com.lge.sm.cr_data_store.repository.RoomSensorRepository;
import com.lge.sm.cr_data_store.repository.AuthorityLocationRepository;
import com.lge.sm.cr_data_store.repository.RoomRepository;

import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.dto.LocationDto;

abstract public class ALocationEntity extends Entity<LocationDto>{

	public List<RoomSensorEntity> getRoomSensorEntityList(){ return Repos.repo(RoomSensorRepository.class).getByLocationId(dto.getLocationId()); }
	public List<AuthorityLocationEntity> getAuthorityLocationEntityList(){ return Repos.repo(AuthorityLocationRepository.class).getByLocationId(dto.getLocationId()); }
	public List<RoomEntity> getRoomEntityList(){ return Repos.repo(RoomRepository.class).getByLocationId(dto.getLocationId()); }


    protected ALocationEntity() {} // for Serialize
  
    public ALocationEntity(LocationDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String locationId) { return new MapKey(locationId); }
    public static MapKey newMapKey(LocationDto dto)   { return newMapKey(dto.getLocationId() ); }  

	public String getName() 	{ return dto.getName();}
	public String getTimeZoneId() 	{ return dto.getTimeZoneId();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getLocationId() 	{ return dto.getLocationId();}

	public ALocationEntity setName(String name){
		dto.setName(name);
		return this;
	}
	public ALocationEntity setTimeZoneId(String timeZoneId){
		dto.setTimeZoneId(timeZoneId);
		return this;
	}
	public ALocationEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public ALocationEntity setLocationId(String locationId){
		dto.setLocationId(locationId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LocationEntity && 
            this.mapKey().equals(((LocationEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Location";
    } 
}
