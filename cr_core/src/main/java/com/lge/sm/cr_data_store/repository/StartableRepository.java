package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AStartableRepository;
import com.lge.sm.cr_data_store.dao.StartableDao;
import com.lge.sm.cr_data_store.dto.StartableDto;
import com.lge.sm.cr_data_store.entity.StartableEntity;

@Repository
public class StartableRepository extends AStartableRepository{
    public StartableRepository(StartableDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
