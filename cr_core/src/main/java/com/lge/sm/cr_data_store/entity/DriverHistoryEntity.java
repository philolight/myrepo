package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ADriverHistoryEntity;
import com.lge.sm.cr_data_store.dto.DriverHistoryDto;
import com.lge.sm.cr_data_store.repository.DriverHistoryRepository;
import com.lge.framework.ceasar.repository.Repos;

public class DriverHistoryEntity extends ADriverHistoryEntity implements Cloneable{
    private static final String TAG = DriverHistoryEntity.class.getSimpleName();
  
    protected DriverHistoryEntity() {} // for Serialize
  
    public DriverHistoryEntity(DriverHistoryDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(DriverHistoryRepository.class).update(this);
    }
    
    @Override
    protected DriverHistoryEntity clone() throws CloneNotSupportedException {
        return Repos.repo(DriverHistoryRepository.class).cloneOf(this);
    }
}
