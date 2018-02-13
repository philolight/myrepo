package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AUserAuthorityEntity;
import com.lge.sm.cr_data_store.dto.UserAuthorityDto;
import com.lge.sm.cr_data_store.repository.UserAuthorityRepository;
import com.lge.framework.ceasar.repository.Repos;

public class UserAuthorityEntity extends AUserAuthorityEntity implements Cloneable{
    private static final String TAG = UserAuthorityEntity.class.getSimpleName();
  
    protected UserAuthorityEntity() {} // for Serialize
  
    public UserAuthorityEntity(UserAuthorityDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(UserAuthorityRepository.class).update(this);
    }
    
    @Override
    protected UserAuthorityEntity clone() throws CloneNotSupportedException {
        return Repos.repo(UserAuthorityRepository.class).cloneOf(this);
    }
}
