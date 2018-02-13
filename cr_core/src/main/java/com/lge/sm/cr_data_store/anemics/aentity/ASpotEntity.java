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

import com.lge.sm.cr_data_store.entity.SpotEntity;
import com.lge.sm.cr_data_store.dto.SpotDto;

abstract public class ASpotEntity extends Entity<SpotDto>{


	public PointEntity getPointEntity() { return Repos.repo(PointRepository.class).getByMapKey(PointEntity.newMapKey(dto.getPointId())); }

    protected ASpotEntity() {} // for Serialize
  
    public ASpotEntity(SpotDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long spotId) { return new MapKey(spotId); }
    public static MapKey newMapKey(SpotDto dto)   { return newMapKey(dto.getSpotId() ); }  

	public Double getX() 	{ return dto.getX();}
	public Double getY() 	{ return dto.getY();}
	public Double getZ() 	{ return dto.getZ();}
	public Long getSpotId() 	{ return dto.getSpotId();}
	public String getCdate() 	{ return dto.getCdate();}
	public Long getPointId() 	{ return dto.getPointId();}
	public String getName() 	{ return dto.getName();}

	public ASpotEntity setX(Double x){
		dto.setX(x);
		return this;
	}
	public ASpotEntity setY(Double y){
		dto.setY(y);
		return this;
	}
	public ASpotEntity setZ(Double z){
		dto.setZ(z);
		return this;
	}
	public ASpotEntity setSpotId(Long spotId){
		dto.setSpotId(spotId);
		return this;
	}
	public ASpotEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public ASpotEntity setPointId(Long pointId){
		dto.setPointId(pointId);
		return this;
	}
	public ASpotEntity setName(String name){
		dto.setName(name);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SpotEntity && 
            this.mapKey().equals(((SpotEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "Spot";
    } 
}
