package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AUrlRepository;
import com.lge.sm.cr_data_store.dao.UrlDao;
import com.lge.sm.cr_data_store.dto.UrlDto;
import com.lge.sm.cr_data_store.entity.UrlEntity;

@Repository
public class UrlRepository extends AUrlRepository{
    public UrlRepository(UrlDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
