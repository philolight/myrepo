package com.lge.framework.ceasar.service.view;

import com.lge.sm.cr_data_store.dto.NumericRangeDto;

public class NumericRange {
    private Long numericRangeId;
    private Double valueFrom;
    private Double valueTo;
    private String fieldSkinId;
    
	public NumericRange(NumericRangeDto dto) {
		numericRangeId = dto.getNumericRangeId();
		valueFrom = dto.getValueFrom();
		valueTo = dto.getValueTo();
		fieldSkinId = dto.getFieldSkinId();
	}
}
