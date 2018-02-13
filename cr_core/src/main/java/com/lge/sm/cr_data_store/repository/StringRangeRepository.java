package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AStringRangeRepository;
import com.lge.sm.cr_data_store.dao.StringRangeDao;
import com.lge.sm.cr_data_store.dto.StringRangeDto;
import com.lge.sm.cr_data_store.entity.StringRangeEntity;

@Repository
public class StringRangeRepository extends AStringRangeRepository{
    public StringRangeRepository(StringRangeDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
