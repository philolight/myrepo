package com.lge.framework.ceasar.service.view;

import java.util.ArrayList;
import java.util.List;

import com.lge.sm.cr_data_store.dto.SkinDto;

public class Skin {
    public String skinType;
    public List<FieldSkin> fields = new ArrayList<>();
    public List<String> parentSkinTypes = new ArrayList<>();
    public List<String> kidSkinTypes = new ArrayList<>();
    
    public Skin(SkinDto dto) {
    	skinType = dto.getSkinId();
    }
}
