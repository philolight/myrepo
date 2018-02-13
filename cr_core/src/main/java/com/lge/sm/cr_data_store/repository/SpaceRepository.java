package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ASpaceRepository;
import com.lge.sm.cr_data_store.dao.SpaceDao;
import com.lge.sm.cr_data_store.dto.SpaceDto;
import com.lge.sm.cr_data_store.entity.SpaceEntity;

@Repository
public class SpaceRepository extends ASpaceRepository{
    public SpaceRepository(SpaceDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
