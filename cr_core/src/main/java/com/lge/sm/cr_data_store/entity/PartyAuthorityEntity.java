package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.APartyAuthorityEntity;
import com.lge.sm.cr_data_store.dto.PartyAuthorityDto;
import com.lge.sm.cr_data_store.repository.PartyAuthorityRepository;
import com.lge.framework.ceasar.repository.Repos;

public class PartyAuthorityEntity extends APartyAuthorityEntity implements Cloneable{
    private static final String TAG = PartyAuthorityEntity.class.getSimpleName();
  
    protected PartyAuthorityEntity() {} // for Serialize
  
    public PartyAuthorityEntity(PartyAuthorityDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(PartyAuthorityRepository.class).update(this);
    }
    
    @Override
    protected PartyAuthorityEntity clone() throws CloneNotSupportedException {
        return Repos.repo(PartyAuthorityRepository.class).cloneOf(this);
    }
}
