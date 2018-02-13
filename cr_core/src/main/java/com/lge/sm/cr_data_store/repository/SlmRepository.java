package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ASlmRepository;
import com.lge.sm.cr_data_store.dao.SlmDao;
import com.lge.sm.cr_data_store.dto.SlmDto;
import com.lge.sm.cr_data_store.entity.SlmEntity;

@Repository
public class SlmRepository extends ASlmRepository{
    public SlmRepository(SlmDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
