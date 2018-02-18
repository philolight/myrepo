package com.lge.sm.cr_data_store.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.repository.MapSet;

public class MapSetTest {
	private MapSet<String> sut;
	private MapKey key1 = new MapKey("key1");
	private MapKey key2 = new MapKey("key2");
	
	@Before
	public void setUp() {
		sut = new MapSet<>();
		sut.put(key1, "Ceasar");
		sut.put(key1, "Augustus");
		
		sut.put(key2, "Tiberius");
		sut.put(key2, "Claudius");
	}
	
	@Test
	public void testGet_get메소드를통해얻은Set은원본과같은원소를가져야한다() {
		assertThat(sut.get(key1), hasSize(2));
		assertThat(sut.get(key1), containsInAnyOrder("Ceasar", "Augustus"));
		assertThat(sut.get(key2), hasSize(2));
		assertThat(sut.get(key2), containsInAnyOrder("Tiberius", "Claudius"));
	}
	
	@Test
	public void testGet_get메소드를통해얻은Set은MapSet에서원소가삭제되어도처음과같은원소를가져야한다() {		
		List<String> set = sut.get(key1);

		sut.remove(key1, "Ceasar");
			
		assertThat(set, hasSize(2));
		assertThat(set, containsInAnyOrder("Ceasar", "Augustus"));
	}
	
	@Test
	public void testRemove_key를통해key에해당하는Set전부를지울수있어야한다() {		
		sut.removeSet(key1);		
		assertThat(sut.get(key1), hasSize(0));
		assertThat(sut.get(key2), hasSize(2));
	}
	
	@Test
	public void testThreadSafe() {
		for(int i = 0; i < 200; i++) {
			sut.put(key1, "value" + i);
		}
		new AddThread(sut, key1).start();
		new RemoveThread(sut, key1).start();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class AddThread extends Thread{
	private MapSet<String> sut;
	private MapKey key;
	AddThread(MapSet<String> sut, MapKey key){
		this.sut = sut;
		this.key = key;
	}
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			sut.put(key, "value" + i);
		}
	}
}

class RemoveThread extends Thread{
	private MapSet<String> sut;
	private MapKey key;
	RemoveThread(MapSet<String> sut, MapKey key){
		this.sut = sut;
		this.key = key;
	}
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			List<String> set = sut.get(key);
			if(set.size() != 0) {
				sut.remove(key, (String)set.toArray()[0]);
			}
		}
	}
}