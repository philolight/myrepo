package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ARoomSensorRepository;
import com.lge.sm.cr_data_store.dao.RoomSensorDao;
import com.lge.sm.cr_data_store.dto.RoomSensorDto;
import com.lge.sm.cr_data_store.entity.RoomSensorEntity;

@Repository
public class RoomSensorRepository extends ARoomSensorRepository{
    public RoomSensorRepository(RoomSensorDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
