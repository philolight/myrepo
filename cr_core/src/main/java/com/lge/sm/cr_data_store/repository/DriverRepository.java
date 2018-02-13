package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ADriverRepository;
import com.lge.sm.cr_data_store.dao.DriverDao;
import com.lge.sm.cr_data_store.dto.DriverDto;
import com.lge.sm.cr_data_store.entity.DriverEntity;

@Repository
public class DriverRepository extends ADriverRepository{
    public DriverRepository(DriverDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
