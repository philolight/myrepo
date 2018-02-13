package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AAuthorityRepository;
import com.lge.sm.cr_data_store.dao.AuthorityDao;
import com.lge.sm.cr_data_store.dto.AuthorityDto;
import com.lge.sm.cr_data_store.entity.AuthorityEntity;

@Repository
public class AuthorityRepository extends AAuthorityRepository{
    public AuthorityRepository(AuthorityDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
