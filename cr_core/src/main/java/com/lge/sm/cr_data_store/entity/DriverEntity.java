package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ADriverEntity;
import com.lge.sm.cr_data_store.dto.DriverDto;
import com.lge.sm.cr_data_store.repository.DriverRepository;
import com.lge.framework.ceasar.repository.Repos;

public class DriverEntity extends ADriverEntity implements Cloneable{
    private static final String TAG = DriverEntity.class.getSimpleName();
  
    protected DriverEntity() {} // for Serialize
  
    public DriverEntity(DriverDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(DriverRepository.class).update(this);
    }
    
    @Override
    protected DriverEntity clone() throws CloneNotSupportedException {
        return Repos.repo(DriverRepository.class).cloneOf(this);
    }
}
