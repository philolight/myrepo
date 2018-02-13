package com.lge.sm.cr_data_store.entity;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.service.view.DecimalRange;
import com.lge.framework.ceasar.service.view.EnumFacet;
import com.lge.framework.ceasar.service.view.FieldSkin;
import com.lge.framework.ceasar.service.view.NumericRange;
import com.lge.framework.ceasar.service.view.Script;
import com.lge.framework.ceasar.service.view.StringRange;
import com.lge.framework.ceasar.util.JsonUtil;
import com.lge.sm.cr_data_store.anemics.aentity.AFieldSkinEntity;
import com.lge.sm.cr_data_store.dto.FieldSkinDto;
import com.lge.sm.cr_data_store.repository.FieldSkinRepository;
import com.lge.sm.cr_data_store.repository.ScriptRepository;

public class FieldSkinEntity extends AFieldSkinEntity implements Cloneable{
    private static final String TAG = FieldSkinEntity.class.getSimpleName();
  
    protected FieldSkinEntity() {} // for Serialize
  
    public FieldSkinEntity(FieldSkinDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(FieldSkinRepository.class).update(this);
    }
    
    @Override
    protected FieldSkinEntity clone() throws CloneNotSupportedException {
        return Repos.repo(FieldSkinRepository.class).cloneOf(this);
    }
    

    public void skinize(JsonNode node) {    
    	if(getVisible() == 0) { // 화면에 표시하지 않음.
    		((ObjectNode) node).remove(getName());
    	}
    }
    
    public boolean isValid(JsonNode node) {
    	boolean ret = false;
    	for(EnumFacetEntity each : getEnumFacetEntityList()) {
    		if(each.isThis(getName(), node) == true) {
    			ret = true;
    			break;
    		}
    	}
    	if(getEnumFacetEntityList().size() != 0 && ret == false) return false;

    	ret = false;
    	for(DecimalRangeEntity each : getDecimalRangeEntityList()) {
    		if(each.isThis(getName(), node) == true) {
    			ret = true;
    			break;
    		}
    	}
    	if(getDecimalRangeEntityList().size() != 0 && ret == false) return false;
    	
    	ret = false;
    	for(NumericRangeEntity each : getNumericRangeEntityList()) {
    		if(each.isThis(getName(), node) == true) {
    			ret = true;
    			break;
    		}
    	}
    	if(getNumericRangeEntityList().size() != 0 && ret == false) return false;
    	
    	ret = false;
    	for(StringRangeEntity each : getStringRangeEntityList()) {
    		if(each.isThis(getName(), node) == true) {
    			ret = true;
    			break;
    		}
    	}
    	if(getStringRangeEntityList().size() != 0 && ret == false) return false;
    	
    	return true;
    }
	
	public FieldSkin skin() {
		FieldSkin ret = new FieldSkin(dto);
				
		for(EnumFacetEntity each : getEnumFacetEntityList()) ret.enums.add(each.skin());		
		for(DecimalRangeEntity each : getDecimalRangeEntityList()) ret.decimalRanges.add(each.skin()); 
		for(NumericRangeEntity each : getNumericRangeEntityList()) ret.numericRanges.add(each.skin()); 
		for(StringRangeEntity each : getStringRangeEntityList()) ret.stringRanges.add(each.skin()); 		
        if(getScriptName() != null && getScriptName() != "") {
			List<ScriptEntity> scripts = Repos.repo(ScriptRepository.class).getByScriptName(getScriptName());
			
			for(ScriptEntity each : scripts) ret.scripts.add(each.skin());
        }
        
        return ret;
	}
}
