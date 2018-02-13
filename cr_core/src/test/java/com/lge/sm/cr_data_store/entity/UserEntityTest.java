package com.lge.sm.cr_data_store.entity;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.sm.cr_data_store.UserDtos;
import com.lge.sm.cr_data_store.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class UserEntityTest {
	UserDtos dtos = new UserDtos();
	
	@Autowired private UserRepository repo;
	private UserEntity sut;
	
	@Before
	public void setUp(){
		repo.init();
		repo.deleteAll();
	}

	@Test
	public void testMapKey() {
		repo.init();
		repo.create(dtos.get(0));
		sut = repo.get(dtos.get(0));
		
		MapKey key1 = sut.mapKey();
		MapKey key2 = UserEntity.newMapKey(sut.getDto());
		assertThat(key1, equalTo(key2));
	}
	
	@After
	public void tearDown() {
		repo.deleteAll();
	}
}
