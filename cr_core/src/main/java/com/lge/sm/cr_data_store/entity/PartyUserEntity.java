package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.APartyUserEntity;
import com.lge.sm.cr_data_store.dto.PartyUserDto;
import com.lge.sm.cr_data_store.repository.PartyUserRepository;
import com.lge.framework.ceasar.repository.Repos;

public class PartyUserEntity extends APartyUserEntity implements Cloneable{
    private static final String TAG = PartyUserEntity.class.getSimpleName();
  
    protected PartyUserEntity() {} // for Serialize
  
    public PartyUserEntity(PartyUserDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(PartyUserRepository.class).update(this);
    }
    
    @Override
    protected PartyUserEntity clone() throws CloneNotSupportedException {
        return Repos.repo(PartyUserRepository.class).cloneOf(this);
    }
}
