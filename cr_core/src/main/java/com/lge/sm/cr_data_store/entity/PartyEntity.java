package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.APartyEntity;
import com.lge.sm.cr_data_store.dto.PartyDto;
import com.lge.sm.cr_data_store.repository.PartyRepository;
import com.lge.framework.ceasar.repository.Repos;

public class PartyEntity extends APartyEntity implements Cloneable{
    private static final String TAG = PartyEntity.class.getSimpleName();
  
    protected PartyEntity() {} // for Serialize
  
    public PartyEntity(PartyDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(PartyRepository.class).update(this);
    }
    
    @Override
    protected PartyEntity clone() throws CloneNotSupportedException {
        return Repos.repo(PartyRepository.class).cloneOf(this);
    }
}
