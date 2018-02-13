package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ALocationRepository;
import com.lge.sm.cr_data_store.dao.LocationDao;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.entity.LocationEntity;

@Repository
public class LocationRepository extends ALocationRepository{
    public LocationRepository(LocationDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
