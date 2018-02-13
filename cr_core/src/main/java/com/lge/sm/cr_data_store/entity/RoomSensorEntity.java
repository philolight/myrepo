package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ARoomSensorEntity;
import com.lge.sm.cr_data_store.dto.RoomSensorDto;
import com.lge.sm.cr_data_store.repository.RoomSensorRepository;
import com.lge.framework.ceasar.repository.Repos;

public class RoomSensorEntity extends ARoomSensorEntity implements Cloneable{
    private static final String TAG = RoomSensorEntity.class.getSimpleName();
  
    protected RoomSensorEntity() {} // for Serialize
  
    public RoomSensorEntity(RoomSensorDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(RoomSensorRepository.class).update(this);
    }
    
    @Override
    protected RoomSensorEntity clone() throws CloneNotSupportedException {
        return Repos.repo(RoomSensorRepository.class).cloneOf(this);
    }
}
