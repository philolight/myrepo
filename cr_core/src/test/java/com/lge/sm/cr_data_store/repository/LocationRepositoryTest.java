package com.lge.sm.cr_data_store.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.framework.ceasar.util.ModelUtil;
import com.lge.sm.cr_data_store.LocationDtos;
import com.lge.sm.cr_data_store.dao.LocationDao;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.entity.LocationEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class LocationRepositoryTest {
	private LocationDtos dtos = new LocationDtos();
	
	@Autowired
	private LocationDao dao;
	
	@Autowired
	private LocationRepository sut;
	
	@Before
	public void setUp(){
		assertNotNull(dao);
		assertNotNull(sut);		
				
		sut.deleteAll();
	}
		
	@Test
	public void testStart_시작시DB에있는데이터를읽어ET를생성하는지확인() {
		dao.insert(dtos.getList());
		
		sut.init();
		sut.start();
		
		assertThat(sut.getAll(), hasSize(3));
		assertThat(ModelUtil.isEqual(dtos.get(0), sut.get(dtos.get(0)).getDto()), equalTo(true));
		assertThat(ModelUtil.isEqual(dtos.get(1), sut.get(dtos.get(1)).getDto()), equalTo(true));
		assertThat(ModelUtil.isEqual(dtos.get(2), sut.get(dtos.get(2)).getDto()), equalTo(true));
	}
	
	@Test
	public void testCreate() {
		sut.create(dtos.get(0));
		sut.create(dtos.get(1));
		sut.create(dtos.get(2));
		
		assertThat(dao.selectAll(), hasSize(3));
		assertThat(sut.getAll(), hasSize(3));
		assertThat(ModelUtil.isEqual(sut.get(dtos.get(0)).getDto(), dtos.get(0)), equalTo(true)); 
		assertThat(ModelUtil.isEqual(sut.get(dtos.get(1)).getDto(), dtos.get(1)), equalTo(true));
		assertThat(ModelUtil.isEqual(sut.get(dtos.get(2)).getDto(), dtos.get(2)), equalTo(true));
	}
	
	@Test
	public void testGet_기본() {
		sut.create(dtos.get(0));
		
		assertThat(sut.get(dtos.get(0)).mapKey(), equalTo(LocationEntity.newMapKey(dtos.get(0))));
	}
	
	@Test
	public void testDelete_기본() {
		testCreate();
		
		sut.delete(sut.get(dtos.get(0)));
	
		assertNull(sut.get(dtos.get(0)));
		assertNotNull(sut.get(dtos.get(1)));
		assertNotNull(sut.get(dtos.get(2)));
	}
	
	@Test
	public void testUpdate() {
		testCreate();
		
		LocationEntity doo = sut.get(dtos.get(0));
				
		doo.setCdate("AAA");
		doo.flush();
		
		LocationDto dbDto = dao.select(dtos.get(0).getLocationId());
		
		assertThat(dbDto.getCdate(), equalTo("AAA"));
	}
	
	@Test
	public void testDeleteAll() {
		testCreate();
		
		sut.deleteAll();
	
		assertThat(sut.getAll(), hasSize(0));
	}
	
	@After
	public void tearDown() {
		sut.deleteAll();
	}
}
