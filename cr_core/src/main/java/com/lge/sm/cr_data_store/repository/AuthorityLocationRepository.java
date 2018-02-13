package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AAuthorityLocationRepository;
import com.lge.sm.cr_data_store.dao.AuthorityLocationDao;
import com.lge.sm.cr_data_store.dto.AuthorityLocationDto;
import com.lge.sm.cr_data_store.entity.AuthorityLocationEntity;

@Repository
public class AuthorityLocationRepository extends AAuthorityLocationRepository{
    public AuthorityLocationRepository(AuthorityLocationDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
