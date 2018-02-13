package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ASpotRepository;
import com.lge.sm.cr_data_store.dao.SpotDao;
import com.lge.sm.cr_data_store.dto.SpotDto;
import com.lge.sm.cr_data_store.entity.SpotEntity;

@Repository
public class SpotRepository extends ASpotRepository{
    public SpotRepository(SpotDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
