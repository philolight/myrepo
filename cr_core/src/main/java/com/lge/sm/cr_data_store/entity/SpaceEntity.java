package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ASpaceEntity;
import com.lge.sm.cr_data_store.dto.SpaceDto;
import com.lge.sm.cr_data_store.repository.SpaceRepository;
import com.lge.framework.ceasar.repository.Repos;

public class SpaceEntity extends ASpaceEntity implements Cloneable{
    private static final String TAG = SpaceEntity.class.getSimpleName();
  
    protected SpaceEntity() {} // for Serialize
  
    public SpaceEntity(SpaceDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(SpaceRepository.class).update(this);
    }
    
    @Override
    protected SpaceEntity clone() throws CloneNotSupportedException {
        return Repos.repo(SpaceRepository.class).cloneOf(this);
    }
}
