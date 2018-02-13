package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.APointAttributeRepository;
import com.lge.sm.cr_data_store.dao.PointAttributeDao;
import com.lge.sm.cr_data_store.dto.PointAttributeDto;
import com.lge.sm.cr_data_store.entity.PointAttributeEntity;

@Repository
public class PointAttributeRepository extends APointAttributeRepository{
    public PointAttributeRepository(PointAttributeDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
