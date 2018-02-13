package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ASkinRepository;
import com.lge.sm.cr_data_store.dao.SkinDao;
import com.lge.sm.cr_data_store.dto.SkinDto;
import com.lge.sm.cr_data_store.entity.SkinEntity;

@Repository
public class SkinRepository extends ASkinRepository{
    public SkinRepository(SkinDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
