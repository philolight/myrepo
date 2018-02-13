package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AWatchableHistoryRepository;
import com.lge.sm.cr_data_store.dao.WatchableHistoryDao;
import com.lge.sm.cr_data_store.dto.WatchableHistoryDto;
import com.lge.sm.cr_data_store.entity.WatchableHistoryEntity;

@Repository
public class WatchableHistoryRepository extends AWatchableHistoryRepository{
    public WatchableHistoryRepository(WatchableHistoryDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
