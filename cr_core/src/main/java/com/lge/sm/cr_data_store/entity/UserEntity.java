package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AUserEntity;
import com.lge.sm.cr_data_store.dto.UserDto;
import com.lge.sm.cr_data_store.repository.UserRepository;
import com.lge.framework.ceasar.repository.Repos;

public class UserEntity extends AUserEntity implements Cloneable{
    private static final String TAG = UserEntity.class.getSimpleName();
  
    protected UserEntity() {} // for Serialize
  
    public UserEntity(UserDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(UserRepository.class).update(this);
    }
    
    @Override
    protected UserEntity clone() throws CloneNotSupportedException {
        return Repos.repo(UserRepository.class).cloneOf(this);
    }
}
