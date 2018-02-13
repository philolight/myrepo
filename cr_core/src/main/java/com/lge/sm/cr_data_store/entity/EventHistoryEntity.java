package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AEventHistoryEntity;
import com.lge.sm.cr_data_store.dto.EventHistoryDto;
import com.lge.sm.cr_data_store.repository.EventHistoryRepository;
import com.lge.framework.ceasar.repository.Repos;

public class EventHistoryEntity extends AEventHistoryEntity implements Cloneable{
    private static final String TAG = EventHistoryEntity.class.getSimpleName();
  
    protected EventHistoryEntity() {} // for Serialize
  
    public EventHistoryEntity(EventHistoryDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(EventHistoryRepository.class).update(this);
    }
    
    @Override
    protected EventHistoryEntity clone() throws CloneNotSupportedException {
        return Repos.repo(EventHistoryRepository.class).cloneOf(this);
    }
}
