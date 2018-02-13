package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AAlarmRepository;
import com.lge.sm.cr_data_store.dao.AlarmDao;
import com.lge.sm.cr_data_store.dto.AlarmDto;
import com.lge.sm.cr_data_store.entity.AlarmEntity;

@Repository
public class AlarmRepository extends AAlarmRepository{
    public AlarmRepository(AlarmDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
