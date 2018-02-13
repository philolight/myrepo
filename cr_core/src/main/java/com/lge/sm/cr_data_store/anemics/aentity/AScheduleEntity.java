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
import com.lge.sm.cr_data_store.entity.LocationEntity;

import com.lge.sm.cr_data_store.repository.RoomRepository;
import com.lge.sm.cr_data_store.repository.LocationRepository;

import com.lge.sm.cr_data_store.entity.ScheduleEntity;
import com.lge.sm.cr_data_store.dto.ScheduleDto;

abstract public class AScheduleEntity extends Entity<ScheduleDto>{


	public RoomEntity getRoomEntity() { return Repos.repo(RoomRepository.class).getByMapKey(RoomEntity.newMapKey(dto.getRoomId())); }
	public LocationEntity getLocationEntity() { return Repos.repo(LocationRepository.class).getByMapKey(LocationEntity.newMapKey(dto.getLocationId())); }

    protected AScheduleEntity() {} // for Serialize
  
    public AScheduleEntity(ScheduleDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String scheduleId) { return new MapKey(scheduleId); }
    public static MapKey newMapKey(ScheduleDto dto)   { return newMapKey(dto.getScheduleId() ); }  

	public String getScheduleId() 	{ return dto.getScheduleId();}
	public String getRoomId() 	{ return dto.getRoomId();}
	public String getName() 	{ return dto.getName();}
	public String getUserId() 	{ return dto.getUserId();}
	public String getUserName() 	{ return dto.getUserName();}
	public String getDeptName() 	{ return dto.getDeptName();}
	public String getSdate() 	{ return dto.getSdate();}
	public String getEdate() 	{ return dto.getEdate();}
	public Integer getLocalYear() 	{ return dto.getLocalYear();}
	public Integer getLocalMonth() 	{ return dto.getLocalMonth();}
	public Integer getLocalDay() 	{ return dto.getLocalDay();}
	public Integer getLocalDayOfWeek() 	{ return dto.getLocalDayOfWeek();}
	public String getLocalShhmm() 	{ return dto.getLocalShhmm();}
	public String getLocalEhhmm() 	{ return dto.getLocalEhhmm();}
	public Integer getLocalDuration() 	{ return dto.getLocalDuration();}
	public Integer getSensorCnt() 	{ return dto.getSensorCnt();}
	public Integer getTotalSensor() 	{ return dto.getTotalSensor();}
	public Integer getTotalDetect() 	{ return dto.getTotalDetect();}
	public Integer getChkDuration() 	{ return dto.getChkDuration();}
	public Integer getResult() 	{ return dto.getResult();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getLocationId() 	{ return dto.getLocationId();}
	public Integer getErrorCnt() 	{ return dto.getErrorCnt();}
	public String getResultDate() 	{ return dto.getResultDate();}

	public AScheduleEntity setScheduleId(String scheduleId){
		dto.setScheduleId(scheduleId);
		return this;
	}
	public AScheduleEntity setRoomId(String roomId){
		dto.setRoomId(roomId);
		return this;
	}
	public AScheduleEntity setName(String name){
		dto.setName(name);
		return this;
	}
	public AScheduleEntity setUserId(String userId){
		dto.setUserId(userId);
		return this;
	}
	public AScheduleEntity setUserName(String userName){
		dto.setUserName(userName);
		return this;
	}
	public AScheduleEntity setDeptName(String deptName){
		dto.setDeptName(deptName);
		return this;
	}
	public AScheduleEntity setSdate(String sdate){
		dto.setSdate(sdate);
		return this;
	}
	public AScheduleEntity setEdate(String edate){
		dto.setEdate(edate);
		return this;
	}
	public AScheduleEntity setLocalYear(Integer localYear){
		dto.setLocalYear(localYear);
		return this;
	}
	public AScheduleEntity setLocalMonth(Integer localMonth){
		dto.setLocalMonth(localMonth);
		return this;
	}
	public AScheduleEntity setLocalDay(Integer localDay){
		dto.setLocalDay(localDay);
		return this;
	}
	public AScheduleEntity setLocalDayOfWeek(Integer localDayOfWeek){
		dto.setLocalDayOfWeek(localDayOfWeek);
		return this;
	}
	public AScheduleEntity setLocalShhmm(String localShhmm){
		dto.setLocalShhmm(localShhmm);
		return this;
	}
	public AScheduleEntity setLocalEhhmm(String localEhhmm){
		dto.setLocalEhhmm(localEhhmm);
		return this;
	}
	public AScheduleEntity setLocalDuration(Integer localDuration){
		dto.setLocalDuration(localDuration);
		return this;
	}
	public AScheduleEntity setSensorCnt(Integer sensorCnt){
		dto.setSensorCnt(sensorCnt);
		return this;
	}
	public AScheduleEntity setTotalSensor(Integer totalSensor){
		dto.setTotalSensor(totalSensor);
		return this;
	}
	public AScheduleEntity setTotalDetect(Integer totalDetect){
		dto.setTotalDetect(totalDetect);
		return this;
	}
	public AScheduleEntity setChkDuration(Integer chkDuration){
		dto.setChkDuration(chkDuration);
		return this;
	}
	public AScheduleEntity setResult(Integer result){
		dto.setResult(result);
		return this;
	}
	public AScheduleEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public AScheduleEntity setLocationId(String locationId){
		dto.setLocationId(locationId);
		return this;
	}
	public AScheduleEntity setErrorCnt(Integer errorCnt){
		dto.setErrorCnt(errorCnt);
		return this;
	}
	public AScheduleEntity setResultDate(String resultDate){
		dto.setResultDate(resultDate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ScheduleEntity && 
            this.mapKey().equals(((ScheduleEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Schedule";
    } 
}
