package com.lge.sm.cr_data_store.anemics.aentity;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".entity" package.
  */

import java.util.List;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.entity.Entity;
import com.lge.framework.ceasar.repository.Repos;

import com.lge.sm.cr_data_store.entity.SkinEntity;
import com.lge.sm.cr_data_store.entity.NumericRangeEntity;
import com.lge.sm.cr_data_store.entity.EnumFacetEntity;
import com.lge.sm.cr_data_store.entity.DecimalRangeEntity;
import com.lge.sm.cr_data_store.entity.StringRangeEntity;

import com.lge.sm.cr_data_store.repository.SkinRepository;
import com.lge.sm.cr_data_store.repository.NumericRangeRepository;
import com.lge.sm.cr_data_store.repository.EnumFacetRepository;
import com.lge.sm.cr_data_store.repository.DecimalRangeRepository;
import com.lge.sm.cr_data_store.repository.StringRangeRepository;

import com.lge.sm.cr_data_store.entity.FieldSkinEntity;
import com.lge.sm.cr_data_store.dto.FieldSkinDto;

abstract public class AFieldSkinEntity extends Entity<FieldSkinDto>{

	public List<NumericRangeEntity> getNumericRangeEntityList(){ return Repos.repo(NumericRangeRepository.class).getByFieldSkinId(dto.getFieldSkinId()); }
	public List<EnumFacetEntity> getEnumFacetEntityList(){ return Repos.repo(EnumFacetRepository.class).getByFieldSkinId(dto.getFieldSkinId()); }
	public List<DecimalRangeEntity> getDecimalRangeEntityList(){ return Repos.repo(DecimalRangeRepository.class).getByFieldSkinId(dto.getFieldSkinId()); }
	public List<StringRangeEntity> getStringRangeEntityList(){ return Repos.repo(StringRangeRepository.class).getByFieldSkinId(dto.getFieldSkinId()); }

	public SkinEntity getSkinEntity() { return Repos.repo(SkinRepository.class).getByMapKey(SkinEntity.newMapKey(dto.getSkinId())); }

    protected AFieldSkinEntity() {} // for Serialize
  
    public AFieldSkinEntity(FieldSkinDto dto) {
        super(dto);
        mapKey = newMapKey(dto);
    }
  
    public static MapKey newMapKey(String fieldSkinId) { return new MapKey(fieldSkinId); }
    public static MapKey newMapKey(FieldSkinDto dto)   { return newMapKey(dto.getFieldSkinId() ); }  

	public String getFieldSkinId() 	{ return dto.getFieldSkinId();}
	public String getName() 	{ return dto.getName();}
	public String getType() 	{ return dto.getType();}
	public String getScriptName() 	{ return dto.getScriptName();}
	public String getCdate() 	{ return dto.getCdate();}
	public Integer getVisible() 	{ return dto.getVisible();}
	public Integer getEditable() 	{ return dto.getEditable();}
	public Integer getEncryption() 	{ return dto.getEncryption();}
	public Integer getHideTyping() 	{ return dto.getHideTyping();}
	public String getSkinId() 	{ return dto.getSkinId();}
	public Integer getNillable() 	{ return dto.getNillable();}
	public Integer getAutoFill() 	{ return dto.getAutoFill();}
	public Integer getIsPk() 	{ return dto.getIsPk();}
	public Integer getIsFk() 	{ return dto.getIsFk();}

	public AFieldSkinEntity setFieldSkinId(String fieldSkinId){
		dto.setFieldSkinId(fieldSkinId);
		return this;
	}
	public AFieldSkinEntity setName(String name){
		dto.setName(name);
		return this;
	}
	public AFieldSkinEntity setType(String type){
		dto.setType(type);
		return this;
	}
	public AFieldSkinEntity setScriptName(String scriptName){
		dto.setScriptName(scriptName);
		return this;
	}
	public AFieldSkinEntity setCdate(String cdate){
		dto.setCdate(cdate);
		return this;
	}
	public AFieldSkinEntity setVisible(Integer visible){
		dto.setVisible(visible);
		return this;
	}
	public AFieldSkinEntity setEditable(Integer editable){
		dto.setEditable(editable);
		return this;
	}
	public AFieldSkinEntity setEncryption(Integer encryption){
		dto.setEncryption(encryption);
		return this;
	}
	public AFieldSkinEntity setHideTyping(Integer hideTyping){
		dto.setHideTyping(hideTyping);
		return this;
	}
	public AFieldSkinEntity setSkinId(String skinId){
		dto.setSkinId(skinId);
		return this;
	}
	public AFieldSkinEntity setNillable(Integer nillable){
		dto.setNillable(nillable);
		return this;
	}
	public AFieldSkinEntity setAutoFill(Integer autoFill){
		dto.setAutoFill(autoFill);
		return this;
	}
	public AFieldSkinEntity setIsPk(Integer isPk){
		dto.setIsPk(isPk);
		return this;
	}
	public AFieldSkinEntity setIsFk(Integer isFk){
		dto.setIsFk(isFk);
		return this;
	}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FieldSkinEntity && 
            this.mapKey().equals(((FieldSkinEntity) obj).mapKey());
    }  
    @Override public int hashCode() { return mapKey().hashCode(); }
  
    abstract public boolean flush();
    
    public static String skinType(){
        return "FieldSkin";
    } 
}
