package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ADecimalRangeRepository;
import com.lge.sm.cr_data_store.dao.DecimalRangeDao;
import com.lge.sm.cr_data_store.dto.DecimalRangeDto;
import com.lge.sm.cr_data_store.entity.DecimalRangeEntity;

@Repository
public class DecimalRangeRepository extends ADecimalRangeRepository{
    public DecimalRangeRepository(DecimalRangeDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
