package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.APointRepository;
import com.lge.sm.cr_data_store.dao.PointDao;
import com.lge.sm.cr_data_store.dto.PointDto;
import com.lge.sm.cr_data_store.entity.PointEntity;

@Repository
public class PointRepository extends APointRepository{
    public PointRepository(PointDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
