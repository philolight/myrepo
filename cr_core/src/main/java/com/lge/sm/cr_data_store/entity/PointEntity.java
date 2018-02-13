package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.APointEntity;
import com.lge.sm.cr_data_store.dto.PointDto;
import com.lge.sm.cr_data_store.repository.PointRepository;
import com.lge.framework.ceasar.repository.Repos;

public class PointEntity extends APointEntity implements Cloneable{
    private static final String TAG = PointEntity.class.getSimpleName();
  
    protected PointEntity() {} // for Serialize
  
    public PointEntity(PointDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(PointRepository.class).update(this);
    }
    
    @Override
    protected PointEntity clone() throws CloneNotSupportedException {
        return Repos.repo(PointRepository.class).cloneOf(this);
    }
}
