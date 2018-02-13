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

import com.lge.sm.cr_data_store.entity.DecimalRangeEntity;
import com.lge.sm.cr_data_store.dto.DecimalRangeDto;

abstract public class ADecimalRangeEntity extends Entity<DecimalRangeDto>{


	public FieldSkinEntity getFieldSkinEntity() { return Repos.repo(FieldSkinRepository.class).getByMapKey(FieldSkinEntity.newMapKey(dto.getFieldSkinId())); }

    protected ADecimalRangeEntity() {} // for Serialize
  
    public ADecimalRangeEntity(DecimalRangeDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(Long decimalRangeId) { return new MapKey(decimalRangeId); }
    public static MapKey newMapKey(DecimalRangeDto dto)   { return newMapKey(dto.getDecimalRangeId() ); }  

	public Long getDecimalRangeId() 	{ return dto.getDecimalRangeId();}
	public Long getValueFrom() 	{ return dto.getValueFrom();}
	public Long getValueTo() 	{ return dto.getValueTo();}
	public String getCdate() 	{ return dto.getCdate();}
	public String getFieldSkinId() 	{ return dto.getFieldSkinId();}

	public ADecimalRangeEntity setDecimalRangeId(Long decimalRangeId){
		dto.setDecimalRangeId(decimalRangeId);
		return this;
	}
	public ADecimalRangeEntity setValueFrom(Long valueFrom){
		dto.setValueFrom(valueFrom);
		return this;
	}
	public ADecimalRangeEntity setValueTo(Long valueTo){
		dto.setValueTo(valueTo);
		return this;
	}
	public ADecimalRangeEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public ADecimalRangeEntity setFieldSkinId(String fieldSkinId){
		dto.setFieldSkinId(fieldSkinId);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DecimalRangeEntity && 
            this.mapKey().equals(((DecimalRangeEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "DecimalRange";
    } 
}
