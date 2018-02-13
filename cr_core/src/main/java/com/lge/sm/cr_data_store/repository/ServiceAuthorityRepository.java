package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AServiceAuthorityRepository;
import com.lge.sm.cr_data_store.dao.ServiceAuthorityDao;
import com.lge.sm.cr_data_store.dto.ServiceAuthorityDto;
import com.lge.sm.cr_data_store.entity.ServiceAuthorityEntity;

@Repository
public class ServiceAuthorityRepository extends AServiceAuthorityRepository{
    public ServiceAuthorityRepository(ServiceAuthorityDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
