package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AEnumFacetRepository;
import com.lge.sm.cr_data_store.dao.EnumFacetDao;
import com.lge.sm.cr_data_store.dto.EnumFacetDto;
import com.lge.sm.cr_data_store.entity.EnumFacetEntity;

@Repository
public class EnumFacetRepository extends AEnumFacetRepository{
    public EnumFacetRepository(EnumFacetDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
