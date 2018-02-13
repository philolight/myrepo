package com.lge.sm.cr_data_store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lge.sm.cr_data_store.anemics.arepository.AUserRepository;
import com.lge.sm.cr_data_store.dao.UserDao;
import com.lge.sm.cr_data_store.dto.UserDto;
import com.lge.sm.cr_data_store.entity.UserEntity;

@Repository
public class UserRepository extends AUserRepository{
    public UserRepository(UserDao dao) {
        super(dao);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public String getStartableId() {
        return this.getClass().getSimpleName();
    }
  
}
