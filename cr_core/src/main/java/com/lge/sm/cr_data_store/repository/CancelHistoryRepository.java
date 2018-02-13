package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ACancelHistoryRepository;
import com.lge.sm.cr_data_store.dao.CancelHistoryDao;
import com.lge.sm.cr_data_store.dto.CancelHistoryDto;
import com.lge.sm.cr_data_store.entity.CancelHistoryEntity;

@Repository
public class CancelHistoryRepository extends ACancelHistoryRepository{
    public CancelHistoryRepository(CancelHistoryDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
