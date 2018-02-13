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

import com.lge.framework.ceasar.repository.Repos;
import com.lge.sm.cr_data_store.LocationDtos;
import com.lge.sm.cr_data_store.RoomDtos;
import com.lge.sm.cr_data_store.ScheduleDtos;
import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.repository.LocationRepository;
import com.lge.sm.cr_data_store.repository.RoomRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class ScheduleDaoTest {
	private LocationDtos locationDtos = new LocationDtos();
	private RoomDtos roomDtos = new RoomDtos();
	private ScheduleDtos vos = new ScheduleDtos();
	
	@Autowired private ScheduleDao sut = null;
		
	@Before
	public void setUp(){
		assertNotNull(sut);
		
		sut.deleteAll();
		Repos.repo(RoomRepository.class).init();
		Repos.repo(RoomRepository.class).deleteAll();
		
		Repos.repo(LocationRepository.class).init();
		Repos.repo(LocationRepository.class).deleteAll();
		
		Repos.repo(LocationRepository.class).create(locationDtos.get(0));
		Repos.repo(RoomRepository.class).create(roomDtos.get(0));
	}
	
	@After
	public void tearDown(){
		sut.deleteAll();		
		Repos.repo(RoomRepository.class).deleteAll();
		Repos.repo(LocationRepository.class).deleteAll();
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
		sut.insert(vos.getList());

		ScheduleDto ret = sut.select(vos.get(0).getScheduleId());
		assertThat(vos.get(0).getScheduleId(), equalTo(ret.getScheduleId()));
		
		ret = sut.select(vos.get(1).getScheduleId());
		assertThat(vos.get(1).getScheduleId(), equalTo(ret.getScheduleId()));
		
		ret = sut.select(vos.get(2).getScheduleId());
		assertThat(vos.get(2).getScheduleId(), equalTo(ret.getScheduleId()));
	}
	
	@Test
	public void testUpdate(){
		sut.insert(vos.getList());
		vos.get(0).setName(vos.get(0).getName() + "difference");
		vos.get(1).setName(vos.get(1).getName() + "difference");
		vos.get(2).setName(vos.get(2).getName() + "difference");
		
		sut.update(vos.get(0));
		sut.update(Arrays.asList(vos.get(1), vos.get(2)));
		
		ScheduleDto record = sut.select(vos.get(0).getScheduleId());		
		assertThat(vos.get(0).getName(), equalTo(record.getName()));
		
		record = sut.select(vos.get(1).getScheduleId());		
		assertThat(vos.get(1).getName(), equalTo(record.getName()));
		
		record = sut.select(vos.get(2).getScheduleId());		
		assertThat(vos.get(2).getName(), equalTo(record.getName()));
	}
	
	@Test
	public void testDelete(){
		sut.insert(vos.getList());
		assertThat(sut.selectAll(), hasSize(3));
		
		sut.delete(vos.get(0));
		assertThat(sut.selectAll(), hasSize(2));
		
		sut.delete(Arrays.asList(vos.get(1), vos.get(2)));
		assertThat(sut.selectAll(), hasSize(0));
	}
	
	@Test
	public void testTransactionRollback(){
		vos.get(1).setScheduleId(vos.get(0).getScheduleId()); // make primary key conflict
		assertThat(sut.insert(Arrays.asList(vos.get(0), vos.get(1), vos.get(2))), equalTo(Boolean.FALSE));
		assertThat("assert list.size() == 0 by rollback", sut.selectAll(), hasSize(0));
	}
}
