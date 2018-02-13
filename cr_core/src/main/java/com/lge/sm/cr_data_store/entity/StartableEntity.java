package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AStartableEntity;
import com.lge.sm.cr_data_store.dto.StartableDto;
import com.lge.sm.cr_data_store.repository.StartableRepository;
import com.lge.framework.ceasar.repository.Repos;

public class StartableEntity extends AStartableEntity implements Cloneable{
    private static final String TAG = StartableEntity.class.getSimpleName();
  
    protected StartableEntity() {} // for Serialize
  
    public StartableEntity(StartableDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(StartableRepository.class).update(this);
    }
    
    @Override
    protected StartableEntity clone() throws CloneNotSupportedException {
        return Repos.repo(StartableRepository.class).cloneOf(this);
    }
}
