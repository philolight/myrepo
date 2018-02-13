package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;



import com.lge.sm.cr_data_store.entity.EventHistoryEntity;
import com.lge.sm.cr_data_store.dto.EventHistoryDto;

abstract public class AEventHistoryEntity extends Entity<EventHistoryDto>{



    protected AEventHistoryEntity() {} // for Serialize
  
    public AEventHistoryEntity(EventHistoryDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String name,String cdate) { return new MapKey(name,cdate); }
    public static MapKey newMapKey(EventHistoryDto dto)   { return newMapKey(dto.getName(),dto.getCdate() ); }  

	public String getName() 	{ return dto.getName();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getType() 	{ return dto.getType();}
	public Long getTimes() 	{ return dto.getTimes();}
	public Float getFrequency() 	{ return dto.getFrequency();}
	public Long getLatency() 	{ return dto.getLatency();}

	public AEventHistoryEntity setName(String name){
		dto.setName(name);
		return this;
	}
	public AEventHistoryEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public AEventHistoryEntity setType(String type){
		dto.setType(type);
		return this;
	}
	public AEventHistoryEntity setTimes(Long times){
		dto.setTimes(times);
		return this;
	}
	public AEventHistoryEntity setFrequency(Float frequency){
		dto.setFrequency(frequency);
		return this;
	}
	public AEventHistoryEntity setLatency(Long latency){
		dto.setLatency(latency);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EventHistoryEntity && 
            this.mapKey().equals(((EventHistoryEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "EventHistory";
    } 
}
