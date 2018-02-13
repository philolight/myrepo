package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AWatchableRepository;
import com.lge.sm.cr_data_store.dao.WatchableDao;
import com.lge.sm.cr_data_store.dto.WatchableDto;
import com.lge.sm.cr_data_store.entity.WatchableEntity;

@Repository
public class WatchableRepository extends AWatchableRepository{
    public WatchableRepository(WatchableDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
