package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AServiceEntity;
import com.lge.sm.cr_data_store.dto.ServiceDto;
import com.lge.sm.cr_data_store.repository.ServiceRepository;
import com.lge.framework.ceasar.repository.Repos;

public class ServiceEntity extends AServiceEntity implements Cloneable{
    private static final String TAG = ServiceEntity.class.getSimpleName();
  
    protected ServiceEntity() {} // for Serialize
  
    public ServiceEntity(ServiceDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(ServiceRepository.class).update(this);
    }
    
    @Override
    protected ServiceEntity clone() throws CloneNotSupportedException {
        return Repos.repo(ServiceRepository.class).cloneOf(this);
    }
}
