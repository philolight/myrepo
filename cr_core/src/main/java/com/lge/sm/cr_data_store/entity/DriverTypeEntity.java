package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ADriverTypeEntity;
import com.lge.sm.cr_data_store.dto.DriverTypeDto;
import com.lge.sm.cr_data_store.repository.DriverTypeRepository;
import com.lge.framework.ceasar.repository.Repos;

public class DriverTypeEntity extends ADriverTypeEntity implements Cloneable{
    private static final String TAG = DriverTypeEntity.class.getSimpleName();
  
    protected DriverTypeEntity() {} // for Serialize
  
    public DriverTypeEntity(DriverTypeDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(DriverTypeRepository.class).update(this);
    }
    
    @Override
    protected DriverTypeEntity clone() throws CloneNotSupportedException {
        return Repos.repo(DriverTypeRepository.class).cloneOf(this);
    }
}
