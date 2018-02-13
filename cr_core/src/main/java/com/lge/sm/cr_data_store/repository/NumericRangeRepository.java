package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ANumericRangeRepository;
import com.lge.sm.cr_data_store.dao.NumericRangeDao;
import com.lge.sm.cr_data_store.dto.NumericRangeDto;
import com.lge.sm.cr_data_store.entity.NumericRangeEntity;

@Repository
public class NumericRangeRepository extends ANumericRangeRepository{
    public NumericRangeRepository(NumericRangeDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
