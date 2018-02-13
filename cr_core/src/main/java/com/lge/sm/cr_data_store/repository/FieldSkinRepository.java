package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AFieldSkinRepository;
import com.lge.sm.cr_data_store.dao.FieldSkinDao;
import com.lge.sm.cr_data_store.dto.FieldSkinDto;
import com.lge.sm.cr_data_store.entity.FieldSkinEntity;

@Repository
public class FieldSkinRepository extends AFieldSkinRepository{
    public FieldSkinRepository(FieldSkinDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
