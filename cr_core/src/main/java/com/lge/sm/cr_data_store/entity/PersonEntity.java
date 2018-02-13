package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.APersonEntity;
import com.lge.sm.cr_data_store.dto.PersonDto;
import com.lge.sm.cr_data_store.repository.PersonRepository;
import com.lge.framework.ceasar.repository.Repos;

public class PersonEntity extends APersonEntity implements Cloneable{
    private static final String TAG = PersonEntity.class.getSimpleName();
  
    protected PersonEntity() {} // for Serialize
  
    public PersonEntity(PersonDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(PersonRepository.class).update(this);
    }
    
    @Override
    protected PersonEntity clone() throws CloneNotSupportedException {
        return Repos.repo(PersonRepository.class).cloneOf(this);
    }
}
