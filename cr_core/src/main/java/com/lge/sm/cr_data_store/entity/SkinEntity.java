package com.lge.sm.cr_data_store.entity;


import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.service.view.Skin;
import com.lge.sm.cr_data_store.anemics.aentity.ASkinEntity;
import com.lge.sm.cr_data_store.dto.SkinDto;
import com.lge.sm.cr_data_store.repository.SkinRepository;

public class SkinEntity extends ASkinEntity implements Cloneable{
    private static final String TAG = SkinEntity.class.getSimpleName();
  
    protected SkinEntity() {} // for Serialize
  
    public SkinEntity(SkinDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(SkinRepository.class).update(this);
    }
    
    @Override
    protected SkinEntity clone() throws CloneNotSupportedException {
        return Repos.repo(SkinRepository.class).cloneOf(this);
    }
       
    public void skinize(JsonNode node) {    
		List<FieldSkinEntity> fieldSkins = getFieldSkinEntityList();
		
		for(FieldSkinEntity each : fieldSkins) {
			each.skinize(node);
		}
    }

	public Skin skin(List<String> parents, List<String> kids) {
		Skin ret = new Skin(dto);
		ret.parentSkinTypes.addAll(parents);
		ret.kidSkinTypes.addAll(kids);
		
		
		
        for(FieldSkinEntity each : getFieldSkinEntityList()) {
        	ret.fields.add(each.skin());
        }
        
        return ret;
	}
}
