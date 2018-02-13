package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AAuthorityEntity;
import com.lge.sm.cr_data_store.dto.AuthorityDto;
import com.lge.sm.cr_data_store.repository.AuthorityRepository;
import com.lge.framework.ceasar.repository.Repos;

public class AuthorityEntity extends AAuthorityEntity implements Cloneable{
    private static final String TAG = AuthorityEntity.class.getSimpleName();
  
    protected AuthorityEntity() {} // for Serialize
  
    public AuthorityEntity(AuthorityDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(AuthorityRepository.class).update(this);
    }
    
    @Override
    protected AuthorityEntity clone() throws CloneNotSupportedException {
        return Repos.repo(AuthorityRepository.class).cloneOf(this);
    }
}
