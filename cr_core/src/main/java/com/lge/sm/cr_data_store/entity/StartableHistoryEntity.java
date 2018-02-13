package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AStartableHistoryEntity;
import com.lge.sm.cr_data_store.dto.StartableHistoryDto;
import com.lge.sm.cr_data_store.repository.StartableHistoryRepository;
import com.lge.framework.ceasar.repository.Repos;

public class StartableHistoryEntity extends AStartableHistoryEntity implements Cloneable{
    private static final String TAG = StartableHistoryEntity.class.getSimpleName();
  
    protected StartableHistoryEntity() {} // for Serialize
  
    public StartableHistoryEntity(StartableHistoryDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(StartableHistoryRepository.class).update(this);
    }
    
    @Override
    protected StartableHistoryEntity clone() throws CloneNotSupportedException {
        return Repos.repo(StartableHistoryRepository.class).cloneOf(this);
    }
}
