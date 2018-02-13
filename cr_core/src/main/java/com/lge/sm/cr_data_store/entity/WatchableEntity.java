package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AWatchableEntity;
import com.lge.sm.cr_data_store.dto.WatchableDto;
import com.lge.sm.cr_data_store.repository.WatchableRepository;
import com.lge.framework.ceasar.repository.Repos;

public class WatchableEntity extends AWatchableEntity implements Cloneable{
    private static final String TAG = WatchableEntity.class.getSimpleName();
  
    protected WatchableEntity() {} // for Serialize
  
    public WatchableEntity(WatchableDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(WatchableRepository.class).update(this);
    }
    
    @Override
    protected WatchableEntity clone() throws CloneNotSupportedException {
        return Repos.repo(WatchableRepository.class).cloneOf(this);
    }
}
