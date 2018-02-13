package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ARoomEntity;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.repository.RoomRepository;
import com.lge.framework.ceasar.repository.Repos;

public class RoomEntity extends ARoomEntity implements Cloneable{
    private static final String TAG = RoomEntity.class.getSimpleName();
  
    protected RoomEntity() {} // for Serialize
  
    public RoomEntity(RoomDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(RoomRepository.class).update(this);
    }
    
    @Override
    protected RoomEntity clone() throws CloneNotSupportedException {
        return Repos.repo(RoomRepository.class).cloneOf(this);
    }
}
