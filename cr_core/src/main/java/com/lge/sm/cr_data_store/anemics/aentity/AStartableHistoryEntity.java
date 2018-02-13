package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.StartableEntity;

import com.lge.sm.cr_data_store.repository.StartableRepository;

import com.lge.sm.cr_data_store.entity.StartableHistoryEntity;
import com.lge.sm.cr_data_store.dto.StartableHistoryDto;

abstract public class AStartableHistoryEntity extends Entity<StartableHistoryDto>{


	public StartableEntity getStartableEntity() { return Repos.repo(StartableRepository.class).getByMapKey(StartableEntity.newMapKey(dto.getStartableId())); }

    protected AStartableHistoryEntity() {} // for Serialize
  
    public AStartableHistoryEntity(StartableHistoryDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String startableId,String cdate) { return new MapKey(startableId,cdate); }
    public static MapKey newMapKey(StartableHistoryDto dto)   { return newMapKey(dto.getStartableId(),dto.getCdate() ); }  

	public String getStartableId() 	{ return dto.getStartableId();}
	public Integer getStartSucceed() 	{ return dto.getStartSucceed();}
	public String getStartDate() 	{ return dto.getStartDate();}
	public Long getLatency() 	{ return dto.getLatency();}
	public String getReport() 	{ return dto.getReport();}
	public String getCdate() 	{ return dto.getCdate();}

	public AStartableHistoryEntity setStartableId(String startableId){
		dto.setStartableId(startableId);
		return this;
	}
	public AStartableHistoryEntity setStartSucceed(Integer startSucceed){
		dto.setStartSucceed(startSucceed);
		return this;
	}
	public AStartableHistoryEntity setStartDate(String startDate){
		dto.setStartDate(startDate);
		return this;
	}
	public AStartableHistoryEntity setLatency(Long latency){
		dto.setLatency(latency);
		return this;
	}
	public AStartableHistoryEntity setReport(String report){
		dto.setReport(report);
		return this;
	}
	public AStartableHistoryEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StartableHistoryEntity && 
            this.mapKey().equals(((StartableHistoryEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "StartableHistory";
    } 
}
