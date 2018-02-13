package com.lge.framework.ceasar.repository;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.sm.cr_data_store.dao.LocationDao;
import com.lge.sm.cr_data_store.repository.LocationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class CacheableRepositoryTest {	
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
	public void testAutowired() {
		assertNotNull(sut.dao);
	}
	
	@After
	public void tearDown() {
		sut.deleteAll();
	}
}