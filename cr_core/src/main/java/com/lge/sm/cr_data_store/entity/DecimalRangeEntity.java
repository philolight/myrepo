package com.lge.sm.cr_data_store.entity;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.service.view.DecimalRange;
import com.lge.sm.cr_data_store.anemics.aentity.ADecimalRangeEntity;
import com.lge.sm.cr_data_store.dto.DecimalRangeDto;
import com.lge.sm.cr_data_store.repository.DecimalRangeRepository;

public class DecimalRangeEntity extends ADecimalRangeEntity implements Cloneable{
    private static final String TAG = DecimalRangeEntity.class.getSimpleName();
  
    protected DecimalRangeEntity() {} // for Serialize
  
    public DecimalRangeEntity(DecimalRangeDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(DecimalRangeRepository.class).update(this);
    }
    
    @Override
    protected DecimalRangeEntity clone() throws CloneNotSupportedException {
        return Repos.repo(DecimalRangeRepository.class).cloneOf(this);
    }
    

	public boolean isThis(String name, JsonNode node) {
    	JsonNode valueNode = ((ObjectNode) node).get(name);
    	if(valueNode.canConvertToLong() && valueNode.asLong() >= getValueFrom() && valueNode.asLong() <= getValueTo()) return true;
    	
    	return false;
	}

	public DecimalRange skin() {
		return new DecimalRange(dto);
	}
}
