package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.APointAttributeEntity;
import com.lge.sm.cr_data_store.dto.PointAttributeDto;
import com.lge.sm.cr_data_store.repository.PointAttributeRepository;
import com.lge.framework.ceasar.repository.Repos;

public class PointAttributeEntity extends APointAttributeEntity implements Cloneable{
    private static final String TAG = PointAttributeEntity.class.getSimpleName();
  
    protected PointAttributeEntity() {} // for Serialize
  
    public PointAttributeEntity(PointAttributeDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(PointAttributeRepository.class).update(this);
    }
    
    @Override
    protected PointAttributeEntity clone() throws CloneNotSupportedException {
        return Repos.repo(PointAttributeRepository.class).cloneOf(this);
    }
}
