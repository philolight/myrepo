package com.lge.framework.ceasar.service.view;

import java.util.ArrayList;
import java.util.List;

import com.lge.sm.cr_data_store.dto.StringRangeDto;

public class StringRange {
    private Long stringRangeId;
    private Integer length;
    private String regex;
    private String scriptName;
    private String fieldSkinId;
    public List<Script> scripts = new ArrayList<>();
	public StringRange(StringRangeDto dto) {
		stringRangeId = dto.getStringRangeId();
		length = dto.getLength();
		regex = dto.getRegex();
		fieldSkinId = dto.getFieldSkinId();
	}
}
