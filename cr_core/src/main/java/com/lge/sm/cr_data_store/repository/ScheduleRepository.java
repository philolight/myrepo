package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AScheduleRepository;
import com.lge.sm.cr_data_store.dao.ScheduleDao;
import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.entity.ScheduleEntity;

@Repository
public class ScheduleRepository extends AScheduleRepository{
    public ScheduleRepository(ScheduleDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
