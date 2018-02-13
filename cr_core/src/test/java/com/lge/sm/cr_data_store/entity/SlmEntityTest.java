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
import com.lge.sm.cr_data_store.SlmDtos;
import com.lge.sm.cr_data_store.entity.SlmEntity;
import com.lge.sm.cr_data_store.repository.SlmRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class SlmEntityTest {
	SlmDtos dtos = new SlmDtos();

	@Autowired private SlmRepository repo;
	private SlmEntity sut;
	
	@Before
	public void setUp() {
		repo.init();
		repo.deleteAll();
		repo.create(dtos.get(0));
		sut = repo.get(dtos.get(0));
	}
	
	@Test
	public void testMapKey() {
		MapKey key1 = sut.mapKey();
		MapKey key2 = SlmEntity.newMapKey(sut.getDto());
		assertThat(key1, equalTo(key2));
	}
		
	@After
	public void tearDown() {
		repo.deleteAll();
	}
}
