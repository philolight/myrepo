package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.APartyUserRepository;
import com.lge.sm.cr_data_store.dao.PartyUserDao;
import com.lge.sm.cr_data_store.dto.PartyUserDto;
import com.lge.sm.cr_data_store.entity.PartyUserEntity;

@Repository
public class PartyUserRepository extends APartyUserRepository{
    public PartyUserRepository(PartyUserDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
