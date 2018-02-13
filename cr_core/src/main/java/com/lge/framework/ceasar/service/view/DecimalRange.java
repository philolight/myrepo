package com.lge.framework.ceasar.service.view;

import com.lge.sm.cr_data_store.dto.DecimalRangeDto;

public class DecimalRange {
    private Long decimalRangeId;
    private Long valueFrom;
    private Long valueTo;
    private String fieldSkinId;
    
	public DecimalRange(DecimalRangeDto dto) {
		decimalRangeId = dto.getDecimalRangeId();
		valueFrom = dto.getValueFrom();
		valueTo = dto.getValueTo();
		fieldSkinId = dto.getFieldSkinId();
	}
}
