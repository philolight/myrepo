package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AAuthorityLocationEntity;
import com.lge.sm.cr_data_store.dto.AuthorityLocationDto;
import com.lge.sm.cr_data_store.repository.AuthorityLocationRepository;
import com.lge.framework.ceasar.repository.Repos;

public class AuthorityLocationEntity extends AAuthorityLocationEntity implements Cloneable{
    private static final String TAG = AuthorityLocationEntity.class.getSimpleName();
  
    protected AuthorityLocationEntity() {} // for Serialize
  
    public AuthorityLocationEntity(AuthorityLocationDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(AuthorityLocationRepository.class).update(this);
    }
    
    @Override
    protected AuthorityLocationEntity clone() throws CloneNotSupportedException {
        return Repos.repo(AuthorityLocationRepository.class).cloneOf(this);
    }
}
