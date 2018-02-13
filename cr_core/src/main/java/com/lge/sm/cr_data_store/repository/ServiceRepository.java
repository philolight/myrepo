package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AServiceRepository;
import com.lge.sm.cr_data_store.dao.ServiceDao;
import com.lge.sm.cr_data_store.dto.ServiceDto;
import com.lge.sm.cr_data_store.entity.ServiceEntity;

@Repository
public class ServiceRepository extends AServiceRepository{
    public ServiceRepository(ServiceDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
