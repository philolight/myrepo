package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AEventHistoryRepository;
import com.lge.sm.cr_data_store.dao.EventHistoryDao;
import com.lge.sm.cr_data_store.dto.EventHistoryDto;
import com.lge.sm.cr_data_store.entity.EventHistoryEntity;

@Repository
public class EventHistoryRepository extends AEventHistoryRepository{
    public EventHistoryRepository(EventHistoryDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
