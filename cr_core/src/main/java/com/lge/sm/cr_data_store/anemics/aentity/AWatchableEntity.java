package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;



import com.lge.sm.cr_data_store.entity.WatchableEntity;
import com.lge.sm.cr_data_store.dto.WatchableDto;

abstract public class AWatchableEntity extends Entity<WatchableDto>{



    protected AWatchableEntity() {} // for Serialize
  
    public AWatchableEntity(WatchableDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String watchableId) { return new MapKey(watchableId); }
    public static MapKey newMapKey(WatchableDto dto)   { return newMapKey(dto.getWatchableId() ); }  

	public String getWatchableId() 	{ return dto.getWatchableId();}
	public String getUpperWatchableId() 	{ return dto.getUpperWatchableId();}
	public String getTimeZoneId() 	{ return dto.getTimeZoneId();}
	public String getModifiedTime() 	{ return dto.getModifiedTime();}
	public String getType() 	{ return dto.getType();}
	public String getCdate() 	{ return dto.getCdate();}

	public AWatchableEntity setWatchableId(String watchableId){
		dto.setWatchableId(watchableId);
		return this;
	}
	public AWatchableEntity setUpperWatchableId(String upperWatchableId){
		dto.setUpperWatchableId(upperWatchableId);
		return this;
	}
	public AWatchableEntity setTimeZoneId(String timeZoneId){
		dto.setTimeZoneId(timeZoneId);
		return this;
	}
	public AWatchableEntity setModifiedTime(String modifiedTime){
		dto.setModifiedTime(modifiedTime);
		return this;
	}
	public AWatchableEntity setType(String type){
		dto.setType(type);
		return this;
	}
	public AWatchableEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WatchableEntity && 
            this.mapKey().equals(((WatchableEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Watchable";
    } 
}
