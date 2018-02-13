package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AWatchableHistoryEntity;
import com.lge.sm.cr_data_store.dto.WatchableHistoryDto;
import com.lge.sm.cr_data_store.repository.WatchableHistoryRepository;
import com.lge.framework.ceasar.repository.Repos;

public class WatchableHistoryEntity extends AWatchableHistoryEntity implements Cloneable{
    private static final String TAG = WatchableHistoryEntity.class.getSimpleName();
  
    protected WatchableHistoryEntity() {} // for Serialize
  
    public WatchableHistoryEntity(WatchableHistoryDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(WatchableHistoryRepository.class).update(this);
    }
    
    @Override
    protected WatchableHistoryEntity clone() throws CloneNotSupportedException {
        return Repos.repo(WatchableHistoryRepository.class).cloneOf(this);
    }
}
