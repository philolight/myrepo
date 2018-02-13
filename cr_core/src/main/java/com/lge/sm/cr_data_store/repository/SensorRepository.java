package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ASensorRepository;
import com.lge.sm.cr_data_store.dao.SensorDao;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.entity.SensorEntity;

@Repository
public class SensorRepository extends ASensorRepository{
    public SensorRepository(SensorDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
