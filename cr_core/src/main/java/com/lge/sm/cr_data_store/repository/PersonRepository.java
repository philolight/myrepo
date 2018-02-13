package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.APersonRepository;
import com.lge.sm.cr_data_store.dao.PersonDao;
import com.lge.sm.cr_data_store.dto.PersonDto;
import com.lge.sm.cr_data_store.entity.PersonEntity;

@Repository
public class PersonRepository extends APersonRepository{
    public PersonRepository(PersonDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
