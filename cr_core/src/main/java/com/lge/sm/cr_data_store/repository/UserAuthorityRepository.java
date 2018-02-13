package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AUserAuthorityRepository;
import com.lge.sm.cr_data_store.dao.UserAuthorityDao;
import com.lge.sm.cr_data_store.dto.UserAuthorityDto;
import com.lge.sm.cr_data_store.entity.UserAuthorityEntity;

@Repository
public class UserAuthorityRepository extends AUserAuthorityRepository{
    public UserAuthorityRepository(UserAuthorityDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
