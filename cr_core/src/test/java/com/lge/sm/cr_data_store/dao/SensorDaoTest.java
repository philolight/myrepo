package com.lge.sm.cr_data_store.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.sm.cr_data_store.SensorDtos;
import com.lge.sm.cr_data_store.SlmDtos;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.dto.SlmDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class SensorDaoTest {
	private SensorDtos vos = new SensorDtos();
	private SlmDto slm = new SlmDtos().get(0);
	
	@Autowired private SensorDao sut = null;
	@Autowired private SlmDao slmDao = null;
	
	@Before
	public void setUp(){
		assertNotNull(sut);
		assertNotNull(slmDao);
		
		sut.deleteAll();
		slmDao.deleteAll();
				
		slmDao.insert(slm);
	}
	
	@After
	public void tearDown(){
		sut.deleteAll();
		slmDao.deleteAll();
	}
	
	@Test
	public void testEmpty(){
		assertThat(sut.selectAll(), hasSize(0));
	}
			
	@Test
	public void testInsert(){
		assertThat(sut.insert(vos.get(0)), equalTo(Boolean.TRUE));
		sut.insert(Arrays.asList(vos.get(1), vos.get(2)));
		assertThat(sut.selectAll(), hasSize(3));
	}
	
	@Test
	public void testSelect(){
		sut.insert(Arrays.asList(vos.get(0), vos.get(1), vos.get(2)));

		SensorDto ret = sut.select(vos.get(0).getSensorId());
		assertThat(vos.get(0).getSlmId(), equalTo(ret.getSlmId()));
		assertThat(vos.get(0).getSensorId(), equalTo(ret.getSensorId()));
		
		ret = sut.select(vos.get(1).getSensorId());
		assertThat(vos.get(1).getSlmId(), equalTo(ret.getSlmId()));
		assertThat(vos.get(1).getSensorId(), equalTo(ret.getSensorId()));
		
		ret = sut.select(vos.get(2).getSensorId());
		assertThat(vos.get(2).getSlmId(), equalTo(ret.getSlmId()));
		assertThat(vos.get(2).getSensorId(), equalTo(ret.getSensorId()));
	}
	
	@Test
	public void testUpdate(){
		sut.insert(Arrays.asList(vos.get(0), vos.get(1), vos.get(2)));
		vos.get(0).setCdate(vos.get(0).getCdate() + "difference");
		vos.get(1).setCdate(vos.get(1).getCdate() + "difference");
		vos.get(2).setCdate(vos.get(2).getCdate() + "difference");
		
		sut.update(vos.get(0));
		sut.update(Arrays.asList(vos.get(1), vos.get(2)));
		
		SensorDto record = sut.select(vos.get(0).getSensorId());		
		assertThat(vos.get(0).getCdate(), equalTo(record.getCdate()));
		
		record = sut.select(vos.get(1).getSensorId());		
		assertThat(vos.get(1).getCdate(), equalTo(record.getCdate()));
		
		record = sut.select(vos.get(2).getSensorId());		
		assertThat(vos.get(2).getCdate(), equalTo(record.getCdate()));
	}
	
	@Test
	public void testDelete(){
		sut.insert(Arrays.asList(vos.get(0), vos.get(1), vos.get(2)));
		assertThat(sut.selectAll(), hasSize(3));
		
		sut.delete(vos.get(0));
		assertThat(sut.selectAll(), hasSize(2));
		
		sut.delete(Arrays.asList(vos.get(1), vos.get(2)));
		assertThat(sut.selectAll(), hasSize(0));
	}
	
	@Test
	public void testTransactionRollback(){
		vos.get(1).setSensorId(vos.get(0).getSensorId()); 	// make primary key conflict
		vos.get(1).setSlmId(vos.get(0).getSlmId()); 		// make primary key conflict
		assertThat(sut.insert(Arrays.asList(vos.get(0), vos.get(1), vos.get(2))), equalTo(Boolean.FALSE));
		assertThat("assert list.size() == 0 by rollback", sut.selectAll(), hasSize(0));
	}
}
