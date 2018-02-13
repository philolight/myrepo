package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.APartyAuthorityRepository;
import com.lge.sm.cr_data_store.dao.PartyAuthorityDao;
import com.lge.sm.cr_data_store.dto.PartyAuthorityDto;
import com.lge.sm.cr_data_store.entity.PartyAuthorityEntity;

@Repository
public class PartyAuthorityRepository extends APartyAuthorityRepository{
    public PartyAuthorityRepository(PartyAuthorityDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
