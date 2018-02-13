package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.APartyRepository;
import com.lge.sm.cr_data_store.dao.PartyDao;
import com.lge.sm.cr_data_store.dto.PartyDto;
import com.lge.sm.cr_data_store.entity.PartyEntity;

@Repository
public class PartyRepository extends APartyRepository{
    public PartyRepository(PartyDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
