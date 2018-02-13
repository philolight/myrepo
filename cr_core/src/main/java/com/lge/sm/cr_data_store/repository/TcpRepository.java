package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.ATcpRepository;
import com.lge.sm.cr_data_store.dao.TcpDao;
import com.lge.sm.cr_data_store.dto.TcpDto;
import com.lge.sm.cr_data_store.entity.TcpEntity;

@Repository
public class TcpRepository extends ATcpRepository{
    public TcpRepository(TcpDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
