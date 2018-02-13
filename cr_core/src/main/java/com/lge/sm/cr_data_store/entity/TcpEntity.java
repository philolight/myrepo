package com.lge.sm.cr_data_store.entity;


import com.lge.sm.cr_data_store.anemics.aentity.ATcpEntity;
import com.lge.sm.cr_data_store.dto.TcpDto;
import com.lge.sm.cr_data_store.repository.TcpRepository;
import com.lge.framework.ceasar.repository.Repos;

public class TcpEntity extends ATcpEntity implements Cloneable{
    private static final String TAG = TcpEntity.class.getSimpleName();
  
    protected TcpEntity() {} // for Serialize
  
    public TcpEntity(TcpDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(TcpRepository.class).update(this);
    }
    
    @Override
    protected TcpEntity clone() throws CloneNotSupportedException {
        return Repos.repo(TcpRepository.class).cloneOf(this);
    }
}
