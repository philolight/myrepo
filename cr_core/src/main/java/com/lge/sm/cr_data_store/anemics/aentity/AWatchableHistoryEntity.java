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

import com.lge.sm.cr_data_store.repository.WatchableRepository;

import com.lge.sm.cr_data_store.entity.WatchableHistoryEntity;
import com.lge.sm.cr_data_store.dto.WatchableHistoryDto;

abstract public class AWatchableHistoryEntity extends Entity<WatchableHistoryDto>{


	public WatchableEntity getWatchableEntity() { return Repos.repo(WatchableRepository.class).getByMapKey(WatchableEntity.newMapKey(dto.getWatchableId())); }

    protected AWatchableHistoryEntity() {} // for Serialize
  
    public AWatchableHistoryEntity(WatchableHistoryDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String watchableId,String cdate) { return new MapKey(watchableId,cdate); }
    public static MapKey newMapKey(WatchableHistoryDto dto)   { return newMapKey(dto.getWatchableId(),dto.getCdate() ); }  

	public String getWatchableId() 	{ return dto.getWatchableId();}
	public Long getRunCount() 	{ return dto.getRunCount();}
	public Long getLatency() 	{ return dto.getLatency();}
	public String getModifiedTime() 	{ return dto.getModifiedTime();}
	public String getCdate() 	{ return dto.getCdate();}

	public AWatchableHistoryEntity setWatchableId(String watchableId){
		dto.setWatchableId(watchableId);
		return this;
	}
	public AWatchableHistoryEntity setRunCount(Long runCount){
		dto.setRunCount(runCount);
		return this;
	}
	public AWatchableHistoryEntity setLatency(Long latency){
		dto.setLatency(latency);
		return this;
	}
	public AWatchableHistoryEntity setModifiedTime(String modifiedTime){
		dto.setModifiedTime(modifiedTime);
		return this;
	}
	public AWatchableHistoryEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WatchableHistoryEntity && 
            this.mapKey().equals(((WatchableHistoryEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "WatchableHistory";
    } 
}
