package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ACancelHistoryEntity;
import com.lge.sm.cr_data_store.dto.CancelHistoryDto;
import com.lge.sm.cr_data_store.repository.CancelHistoryRepository;
import com.lge.framework.ceasar.repository.Repos;

public class CancelHistoryEntity extends ACancelHistoryEntity implements Cloneable{
    private static final String TAG = CancelHistoryEntity.class.getSimpleName();
  
    protected CancelHistoryEntity() {} // for Serialize
  
    public CancelHistoryEntity(CancelHistoryDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(CancelHistoryRepository.class).update(this);
    }
    
    @Override
    protected CancelHistoryEntity clone() throws CloneNotSupportedException {
        return Repos.repo(CancelHistoryRepository.class).cloneOf(this);
    }
}
