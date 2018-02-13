package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AStartableHistoryRepository;
import com.lge.sm.cr_data_store.dao.StartableHistoryDao;
import com.lge.sm.cr_data_store.dto.StartableHistoryDto;
import com.lge.sm.cr_data_store.entity.StartableHistoryEntity;

@Repository
public class StartableHistoryRepository extends AStartableHistoryRepository{
    public StartableHistoryRepository(StartableHistoryDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
