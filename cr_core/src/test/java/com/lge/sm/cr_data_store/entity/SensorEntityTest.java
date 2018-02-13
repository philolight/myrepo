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
import com.lge.sm.cr_data_store.SensorDtos;
import com.lge.sm.cr_data_store.SlmDtos;
import com.lge.sm.cr_data_store.repository.SensorRepository;
import com.lge.sm.cr_data_store.repository.SlmRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class SensorEntityTest {
	SensorDtos dtos = new SensorDtos();
	SlmDtos slmDtos = new SlmDtos();

	@Autowired private SensorRepository repo;
	@Autowired private SlmRepository slmRepo;
	private SensorEntity sut;
	
	@Before
	public void setUp() {
		slmRepo.init();
		slmRepo.deleteAll();
		slmRepo.create(slmDtos.get(0));
		repo.init();
		repo.deleteAll();
		repo.create(dtos.get(0));
		sut = repo.get(dtos.get(0));
	}
	
	@After
	public void tearDown() {
		repo.deleteAll();
		slmRepo.deleteAll();
	}
	
	@Test
	public void testMapKey() {
		MapKey key1 = sut.mapKey();
		MapKey key2 = SensorEntity.newMapKey(sut.getDto());
		assertThat(key1, equalTo(key2));
	}
}
