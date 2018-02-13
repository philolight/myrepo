package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;



import com.lge.sm.cr_data_store.entity.AlarmEntity;
import com.lge.sm.cr_data_store.dto.AlarmDto;

abstract public class AAlarmEntity extends Entity<AlarmDto>{



    protected AAlarmEntity() {} // for Serialize
  
    public AAlarmEntity(AlarmDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long alarmId) { return new MapKey(alarmId); }
    public static MapKey newMapKey(AlarmDto dto)   { return newMapKey(dto.getAlarmId() ); }  

	public Long getAlarmId() 	{ return dto.getAlarmId();}
	public String getCdate() 	{ return dto.getCdate();}
	public Integer getEnable() 	{ return dto.getEnable();}

	public AAlarmEntity setAlarmId(Long alarmId){
		dto.setAlarmId(alarmId);
		return this;
	}
	public AAlarmEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public AAlarmEntity setEnable(Integer enable){
		dto.setEnable(enable);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AlarmEntity && 
            this.mapKey().equals(((AlarmEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Alarm";
    } 
}
