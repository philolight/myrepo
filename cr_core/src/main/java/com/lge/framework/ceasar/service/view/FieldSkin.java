package com.lge.framework.ceasar.service.view;

import java.util.ArrayList;
import java.util.List;

import com.lge.sm.cr_data_store.dto.FieldSkinDto;

public class FieldSkin {
    public String fieldSkinId;
    public String name;
    public String type;
    public Integer visible;
    public Integer editable;
    public Integer encryption;
    public Integer hideTyping;
    public String skinId;
    public Integer nillable;
    public Integer autoFill;
    public Integer isPk;
    public Integer isFk;
        
    public List<EnumFacet> enums = new ArrayList<>();
    public List<DecimalRange> decimalRanges = new ArrayList<>();
    public List<NumericRange> numericRanges = new ArrayList<>();
    public List<StringRange> stringRanges = new ArrayList<>();
    public List<Script> scripts = new ArrayList<>();
    
	public FieldSkin(FieldSkinDto dto){
	    fieldSkinId = dto.getFieldSkinId();
	    name = dto.getName();
	    type = dto.getType();
	    visible = dto.getVisible();
	    editable = dto.getEditable();
	    encryption = dto.getEncryption();
	    hideTyping = dto.getHideTyping();
	    skinId = dto.getSkinId();
	    nillable = dto.getNillable();
	    autoFill = dto.getAutoFill();
	    isPk = dto.getIsPk();
	    isFk = dto.getIsFk();
	}
}
