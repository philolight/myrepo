package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.AUrlEntity;
import com.lge.sm.cr_data_store.dto.UrlDto;
import com.lge.sm.cr_data_store.repository.UrlRepository;
import com.lge.framework.ceasar.repository.Repos;

public class UrlEntity extends AUrlEntity implements Cloneable{
    private static final String TAG = UrlEntity.class.getSimpleName();
  
    protected UrlEntity() {} // for Serialize
  
    public UrlEntity(UrlDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(UrlRepository.class).update(this);
    }
    
    @Override
    protected UrlEntity clone() throws CloneNotSupportedException {
        return Repos.repo(UrlRepository.class).cloneOf(this);
    }
}
