package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ADriverHistoryRepository;
import com.lge.sm.cr_data_store.dao.DriverHistoryDao;
import com.lge.sm.cr_data_store.dto.DriverHistoryDto;
import com.lge.sm.cr_data_store.entity.DriverHistoryEntity;

@Repository
public class DriverHistoryRepository extends ADriverHistoryRepository{
    public DriverHistoryRepository(DriverHistoryDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
