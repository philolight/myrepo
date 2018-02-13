package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ARoomRepository;
import com.lge.sm.cr_data_store.dao.RoomDao;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.entity.RoomEntity;

@Repository
public class RoomRepository extends ARoomRepository{
    public RoomRepository(RoomDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
