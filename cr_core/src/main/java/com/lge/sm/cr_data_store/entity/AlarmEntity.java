package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AAlarmEntity;
import com.lge.sm.cr_data_store.dto.AlarmDto;
import com.lge.sm.cr_data_store.repository.AlarmRepository;
import com.lge.framework.ceasar.repository.Repos;

public class AlarmEntity extends AAlarmEntity implements Cloneable{
    private static final String TAG = AlarmEntity.class.getSimpleName();
  
    protected AlarmEntity() {} // for Serialize
  
    public AlarmEntity(AlarmDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(AlarmRepository.class).update(this);
    }
    
    @Override
    protected AlarmEntity clone() throws CloneNotSupportedException {
        return Repos.repo(AlarmRepository.class).cloneOf(this);
    }
}
