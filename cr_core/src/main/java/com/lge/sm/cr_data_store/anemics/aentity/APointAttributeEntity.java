package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.PointEntity;

import com.lge.sm.cr_data_store.repository.PointRepository;

import com.lge.sm.cr_data_store.entity.PointAttributeEntity;
import com.lge.sm.cr_data_store.dto.PointAttributeDto;

abstract public class APointAttributeEntity extends Entity<PointAttributeDto>{


	public PointEntity getPointEntity() { return Repos.repo(PointRepository.class).getByMapKey(PointEntity.newMapKey(dto.getPointId())); }

    protected APointAttributeEntity() {} // for Serialize
  
    public APointAttributeEntity(PointAttributeDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long pointAttributeId) { return new MapKey(pointAttributeId); }
    public static MapKey newMapKey(PointAttributeDto dto)   { return newMapKey(dto.getPointAttributeId() ); }  

	public Long getPointAttributeId() 	{ return dto.getPointAttributeId();}
	public String getName() 	{ return dto.getName();}
	public String getValue() 	{ return dto.getValue();}
	public String getType() 	{ return dto.getType();}
	public Long getPointId() 	{ return dto.getPointId();}
	public String getCdate() 	{ return dto.getCdate();}

	public APointAttributeEntity setPointAttributeId(Long pointAttributeId){
		dto.setPointAttributeId(pointAttributeId);
		return this;
	}
	public APointAttributeEntity setName(String name){
		dto.setName(name);
		return this;
	}
	public APointAttributeEntity setValue(String value){
		dto.setValue(value);
		return this;
	}
	public APointAttributeEntity setType(String type){
		dto.setType(type);
		return this;
	}
	public APointAttributeEntity setPointId(Long pointId){
		dto.setPointId(pointId);
		return this;
	}
	public APointAttributeEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PointAttributeEntity && 
            this.mapKey().equals(((PointAttributeEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "PointAttribute";
    } 
}
