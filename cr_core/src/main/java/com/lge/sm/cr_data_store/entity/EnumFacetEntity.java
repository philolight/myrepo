package com.lge.sm.cr_data_store.entity;


import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.service.view.EnumFacet;
import com.lge.sm.cr_data_store.anemics.aentity.AEnumFacetEntity;
import com.lge.sm.cr_data_store.dto.EnumFacetDto;
import com.lge.sm.cr_data_store.repository.EnumFacetRepository;
import com.lge.sm.cr_data_store.repository.ScriptRepository;

public class EnumFacetEntity extends AEnumFacetEntity implements Cloneable{
    private static final String TAG = EnumFacetEntity.class.getSimpleName();
  
    protected EnumFacetEntity() {} // for Serialize
  
    public EnumFacetEntity(EnumFacetDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(EnumFacetRepository.class).update(this);
    }
    
    @Override
    protected EnumFacetEntity clone() throws CloneNotSupportedException {
        return Repos.repo(EnumFacetRepository.class).cloneOf(this);
    }
    

    public boolean isThis(String name, JsonNode node) {
    	JsonNode valueNode = ((ObjectNode) node).get(name);
    	String value = valueNode.asText();
    	if(value.equals(getValue())) return true;
    	
    	List<ScriptEntity> scripts = Repos.repo(ScriptRepository.class).getByScriptName(getScriptName());
   		for(ScriptEntity each : scripts) if(value.equals(each.getText())) return true;

    	return false;
    }

	public EnumFacet skin() {
		EnumFacet ret = new EnumFacet(dto);
		
        if(getScriptName() != null && getScriptName() != "") {
			List<ScriptEntity> scripts = Repos.repo(ScriptRepository.class).getByScriptName(getScriptName());			
			for(ScriptEntity each : scripts) ret.scripts.add(each.skin());
        }

        return ret;
	}
}
