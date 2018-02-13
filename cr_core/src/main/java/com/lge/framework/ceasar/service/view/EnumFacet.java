package com.lge.framework.ceasar.service.view;

import java.util.ArrayList;
import java.util.List;

import com.lge.sm.cr_data_store.dto.EnumFacetDto;

public class EnumFacet {
    private Long enumFacetId;
    private String value;
    private String fieldSkinId;
	public EnumFacet(EnumFacetDto dto) {
		enumFacetId = dto.getEnumFacetId();
		value = dto.getValue();
		fieldSkinId = dto.getFieldSkinId();
	}
	
	public List<Script> scripts = new ArrayList<>();
}
