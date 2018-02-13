package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.SpaceEntity;
import com.lge.sm.cr_data_store.entity.SpaceEntity;

import com.lge.sm.cr_data_store.repository.SpaceRepository;
import com.lge.sm.cr_data_store.repository.SpaceRepository;

import com.lge.sm.cr_data_store.entity.SpaceEntity;
import com.lge.sm.cr_data_store.dto.SpaceDto;

abstract public class ASpaceEntity extends Entity<SpaceDto>{

	public List<SpaceEntity> getSpaceEntityList(){ return Repos.repo(SpaceRepository.class).getBySpaceId(dto.getSpaceId()); }

	public SpaceEntity getSpaceEntity() { return Repos.repo(SpaceRepository.class).getByMapKey(SpaceEntity.newMapKey(dto.getParentSpaceId())); }

    protected ASpaceEntity() {} // for Serialize
  
    public ASpaceEntity(SpaceDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long spaceId) { return new MapKey(spaceId); }
    public static MapKey newMapKey(SpaceDto dto)   { return newMapKey(dto.getSpaceId() ); }  

	public Long getSpaceId() 	{ return dto.getSpaceId();}
	public Long getParentSpaceId() 	{ return dto.getParentSpaceId();}
	public String getName() 	{ return dto.getName();}
	public String getFloor() 	{ return dto.getFloor();}
	public String getType() 	{ return dto.getType();}
	public String getCdate() 	{ return dto.getCdate();}

	public ASpaceEntity setSpaceId(Long spaceId){
		dto.setSpaceId(spaceId);
		return this;
	}
	public ASpaceEntity setParentSpaceId(Long parentSpaceId){
		dto.setParentSpaceId(parentSpaceId);
		return this;
	}
	public ASpaceEntity setName(String name){
		dto.setName(name);
		return this;
	}
	public ASpaceEntity setFloor(String floor){
		dto.setFloor(floor);
		return this;
	}
	public ASpaceEntity setType(String type){
		dto.setType(type);
		return this;
	}
	public ASpaceEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SpaceEntity && 
            this.mapKey().equals(((SpaceEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Space";
    } 
}
