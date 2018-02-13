package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AScheduleEntity;
import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.repository.ScheduleRepository;
import com.lge.framework.ceasar.repository.Repos;

public class ScheduleEntity extends AScheduleEntity implements Cloneable{
    private static final String TAG = ScheduleEntity.class.getSimpleName();
  
    protected ScheduleEntity() {} // for Serialize
  
    public ScheduleEntity(ScheduleDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(ScheduleRepository.class).update(this);
    }
    
    @Override
    protected ScheduleEntity clone() throws CloneNotSupportedException {
        return Repos.repo(ScheduleRepository.class).cloneOf(this);
    }
}
