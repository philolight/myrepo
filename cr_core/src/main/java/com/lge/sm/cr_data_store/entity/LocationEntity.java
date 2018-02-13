package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ALocationEntity;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.repository.LocationRepository;
import com.lge.framework.ceasar.repository.Repos;

public class LocationEntity extends ALocationEntity implements Cloneable{
    private static final String TAG = LocationEntity.class.getSimpleName();
  
    protected LocationEntity() {} // for Serialize
  
    public LocationEntity(LocationDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(LocationRepository.class).update(this);
    }
    
    @Override
    protected LocationEntity clone() throws CloneNotSupportedException {
        return Repos.repo(LocationRepository.class).cloneOf(this);
    }
}
