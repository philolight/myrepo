package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AServiceAuthorityEntity;
import com.lge.sm.cr_data_store.dto.ServiceAuthorityDto;
import com.lge.sm.cr_data_store.repository.ServiceAuthorityRepository;
import com.lge.framework.ceasar.repository.Repos;

public class ServiceAuthorityEntity extends AServiceAuthorityEntity implements Cloneable{
    private static final String TAG = ServiceAuthorityEntity.class.getSimpleName();
  
    protected ServiceAuthorityEntity() {} // for Serialize
  
    public ServiceAuthorityEntity(ServiceAuthorityDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(ServiceAuthorityRepository.class).update(this);
    }
    
    @Override
    protected ServiceAuthorityEntity clone() throws CloneNotSupportedException {
        return Repos.repo(ServiceAuthorityRepository.class).cloneOf(this);
    }
}
