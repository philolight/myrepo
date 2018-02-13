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

import com.lge.sm.cr_data_store.repository.LocationRepository;

import com.lge.sm.cr_data_store.entity.CancelHistoryEntity;
import com.lge.sm.cr_data_store.dto.CancelHistoryDto;

abstract public class ACancelHistoryEntity extends Entity<CancelHistoryDto>{


	public LocationEntity getLocationEntity() { return Repos.repo(LocationRepository.class).getByMapKey(LocationEntity.newMapKey(dto.getLocationId())); }

    protected ACancelHistoryEntity() {} // for Serialize
  
    public ACancelHistoryEntity(CancelHistoryDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String locationId,String dateOf) { return new MapKey(locationId,dateOf); }
    public static MapKey newMapKey(CancelHistoryDto dto)   { return newMapKey(dto.getLocationId(),dto.getDateOf() ); }  

	public Integer getReservations() 	{ return dto.getReservations();}
	public Integer getCancels() 	{ return dto.getCancels();}
	public Integer getCancelMinutes() 	{ return dto.getCancelMinutes();}
	public Float getCancelRate() 	{ return dto.getCancelRate();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getLocationId() 	{ return dto.getLocationId();}
	public String getDateOf() 	{ return dto.getDateOf();}
	public Integer getReuses() 	{ return dto.getReuses();}

	public ACancelHistoryEntity setReservations(Integer reservations){
		dto.setReservations(reservations);
		return this;
	}
	public ACancelHistoryEntity setCancels(Integer cancels){
		dto.setCancels(cancels);
		return this;
	}
	public ACancelHistoryEntity setCancelMinutes(Integer cancelMinutes){
		dto.setCancelMinutes(cancelMinutes);
		return this;
	}
	public ACancelHistoryEntity setCancelRate(Float cancelRate){
		dto.setCancelRate(cancelRate);
		return this;
	}
	public ACancelHistoryEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public ACancelHistoryEntity setLocationId(String locationId){
		dto.setLocationId(locationId);
		return this;
	}
	public ACancelHistoryEntity setDateOf(String dateOf){
		dto.setDateOf(dateOf);
		return this;
	}
	public ACancelHistoryEntity setReuses(Integer reuses){
		dto.setReuses(reuses);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CancelHistoryEntity && 
            this.mapKey().equals(((CancelHistoryEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "CancelHistory";
    } 
}
