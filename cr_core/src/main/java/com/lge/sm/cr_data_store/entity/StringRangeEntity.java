package com.lge.sm.cr_data_store.entity;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.service.view.StringRange;
import com.lge.sm.cr_data_store.anemics.aentity.AStringRangeEntity;
import com.lge.sm.cr_data_store.dto.StringRangeDto;
import com.lge.sm.cr_data_store.repository.ScriptRepository;
import com.lge.sm.cr_data_store.repository.StringRangeRepository;

public class StringRangeEntity extends AStringRangeEntity implements Cloneable{
    private static final String TAG = StringRangeEntity.class.getSimpleName();
  
    protected StringRangeEntity() {} // for Serialize
  
    public StringRangeEntity(StringRangeDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(StringRangeRepository.class).update(this);
    }
    
    @Override
    protected StringRangeEntity clone() throws CloneNotSupportedException {
        return Repos.repo(StringRangeRepository.class).cloneOf(this);
    }
    

	public boolean isThis(String name, JsonNode node) {
    	JsonNode valueNode = ((ObjectNode) node).get(name);
    	String value = valueNode.asText();
    	
    	return value.matches(getRegex());
	}

	public StringRange skin() {
		StringRange ret = new StringRange(dto);
		
        if(getScriptName() != null && getScriptName() != "") {
			List<ScriptEntity> scripts = Repos.repo(ScriptRepository.class).getByScriptName(getScriptName());
			
			for(ScriptEntity each : scripts) ret.scripts.add(each.skin());
        }
		
        return ret;
	}
}
