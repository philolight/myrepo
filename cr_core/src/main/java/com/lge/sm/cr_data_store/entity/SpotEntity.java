package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ASpotEntity;
import com.lge.sm.cr_data_store.dto.SpotDto;
import com.lge.sm.cr_data_store.repository.SpotRepository;
import com.lge.framework.ceasar.repository.Repos;

public class SpotEntity extends ASpotEntity implements Cloneable{
    private static final String TAG = SpotEntity.class.getSimpleName();
  
    protected SpotEntity() {} // for Serialize
  
    public SpotEntity(SpotDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(SpotRepository.class).update(this);
    }
    
    @Override
    protected SpotEntity clone() throws CloneNotSupportedException {
        return Repos.repo(SpotRepository.class).cloneOf(this);
    }
}
