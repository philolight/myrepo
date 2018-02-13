package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.DriverEntity;
import com.lge.sm.cr_data_store.entity.PointEntity;
import com.lge.sm.cr_data_store.entity.SpotEntity;
import com.lge.sm.cr_data_store.entity.PointEntity;
import com.lge.sm.cr_data_store.entity.PointAttributeEntity;

import com.lge.sm.cr_data_store.repository.DriverRepository;
import com.lge.sm.cr_data_store.repository.PointRepository;
import com.lge.sm.cr_data_store.repository.SpotRepository;
import com.lge.sm.cr_data_store.repository.PointRepository;
import com.lge.sm.cr_data_store.repository.PointAttributeRepository;

import com.lge.sm.cr_data_store.entity.PointEntity;
import com.lge.sm.cr_data_store.dto.PointDto;

abstract public class APointEntity extends Entity<PointDto>{

	public List<SpotEntity> getSpotEntityList(){ return Repos.repo(SpotRepository.class).getByPointId(dto.getPointId()); }
	public List<PointEntity> getPointEntityList(){ return Repos.repo(PointRepository.class).getByPointId(dto.getPointId()); }
	public List<PointAttributeEntity> getPointAttributeEntityList(){ return Repos.repo(PointAttributeRepository.class).getByPointId(dto.getPointId()); }

	public DriverEntity getDriverEntity() { return Repos.repo(DriverRepository.class).getByMapKey(DriverEntity.newMapKey(dto.getDriverId())); }
	public PointEntity getPointEntity() { return Repos.repo(PointRepository.class).getByMapKey(PointEntity.newMapKey(dto.getParentPointId())); }

    protected APointEntity() {} // for Serialize
  
    public APointEntity(PointDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long pointId) { return new MapKey(pointId); }
    public static MapKey newMapKey(PointDto dto)   { return newMapKey(dto.getPointId() ); }  

	public Long getPointId() 	{ return dto.getPointId();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getName() 	{ return dto.getName();}
	public Long getDriverId() 	{ return dto.getDriverId();}
	public Long getParentPointId() 	{ return dto.getParentPointId();}

	public APointEntity setPointId(Long pointId){
		dto.setPointId(pointId);
		return this;
	}
	public APointEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public APointEntity setName(String name){
		dto.setName(name);
		return this;
	}
	public APointEntity setDriverId(Long driverId){
		dto.setDriverId(driverId);
		return this;
	}
	public APointEntity setParentPointId(Long parentPointId){
		dto.setParentPointId(parentPointId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PointEntity && 
            this.mapKey().equals(((PointEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Point";
    } 
}
