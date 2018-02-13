package com.lge.sm.cr_data_store.entity;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.service.view.NumericRange;
import com.lge.sm.cr_data_store.anemics.aentity.ANumericRangeEntity;
import com.lge.sm.cr_data_store.dto.NumericRangeDto;
import com.lge.sm.cr_data_store.repository.NumericRangeRepository;

public class NumericRangeEntity extends ANumericRangeEntity implements Cloneable{
    private static final String TAG = NumericRangeEntity.class.getSimpleName();
  
    protected NumericRangeEntity() {} // for Serialize
  
    public NumericRangeEntity(NumericRangeDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(NumericRangeRepository.class).update(this);
    }
    
    @Override
    protected NumericRangeEntity clone() throws CloneNotSupportedException {
        return Repos.repo(NumericRangeRepository.class).cloneOf(this);
    }
    

	public boolean isThis(String name, JsonNode node) {
    	JsonNode valueNode = ((ObjectNode) node).get(name);
    	if(valueNode.isDouble() && valueNode.asDouble() >= getValueFrom() && valueNode.asDouble() <= getValueTo()) return true;
    	
    	return false;
	}

	public NumericRange skin() {
		return new NumericRange(dto);
	}
}
