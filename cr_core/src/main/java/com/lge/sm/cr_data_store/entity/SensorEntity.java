package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ASensorEntity;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.repository.SensorRepository;
import com.lge.framework.ceasar.repository.Repos;

public class SensorEntity extends ASensorEntity implements Cloneable{
    private static final String TAG = SensorEntity.class.getSimpleName();
  
    protected SensorEntity() {} // for Serialize
  
    public SensorEntity(SensorDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(SensorRepository.class).update(this);
    }
    
    @Override
    protected SensorEntity clone() throws CloneNotSupportedException {
        return Repos.repo(SensorRepository.class).cloneOf(this);
    }
}
