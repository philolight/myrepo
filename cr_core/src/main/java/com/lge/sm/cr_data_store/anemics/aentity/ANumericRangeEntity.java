package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.FieldSkinEntity;

import com.lge.sm.cr_data_store.repository.FieldSkinRepository;

import com.lge.sm.cr_data_store.entity.NumericRangeEntity;
import com.lge.sm.cr_data_store.dto.NumericRangeDto;

abstract public class ANumericRangeEntity extends Entity<NumericRangeDto>{


	public FieldSkinEntity getFieldSkinEntity() { return Repos.repo(FieldSkinRepository.class).getByMapKey(FieldSkinEntity.newMapKey(dto.getFieldSkinId())); }

    protected ANumericRangeEntity() {} // for Serialize
  
    public ANumericRangeEntity(NumericRangeDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long numericRangeId) { return new MapKey(numericRangeId); }
    public static MapKey newMapKey(NumericRangeDto dto)   { return newMapKey(dto.getNumericRangeId() ); }  

	public Long getNumericRangeId() 	{ return dto.getNumericRangeId();}
	public Double getValueFrom() 	{ return dto.getValueFrom();}
	public Double getValueTo() 	{ return dto.getValueTo();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getFieldSkinId() 	{ return dto.getFieldSkinId();}

	public ANumericRangeEntity setNumericRangeId(Long numericRangeId){
		dto.setNumericRangeId(numericRangeId);
		return this;
	}
	public ANumericRangeEntity setValueFrom(Double valueFrom){
		dto.setValueFrom(valueFrom);
		return this;
	}
	public ANumericRangeEntity setValueTo(Double valueTo){
		dto.setValueTo(valueTo);
		return this;
	}
	public ANumericRangeEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public ANumericRangeEntity setFieldSkinId(String fieldSkinId){
		dto.setFieldSkinId(fieldSkinId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NumericRangeEntity && 
            this.mapKey().equals(((NumericRangeEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "NumericRange";
    } 
}
