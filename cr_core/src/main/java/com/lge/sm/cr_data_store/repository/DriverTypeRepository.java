package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ADriverTypeRepository;
import com.lge.sm.cr_data_store.dao.DriverTypeDao;
import com.lge.sm.cr_data_store.dto.DriverTypeDto;
import com.lge.sm.cr_data_store.entity.DriverTypeEntity;

@Repository
public class DriverTypeRepository extends ADriverTypeRepository{
    public DriverTypeRepository(DriverTypeDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
