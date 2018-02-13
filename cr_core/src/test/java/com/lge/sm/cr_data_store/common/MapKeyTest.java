package com.lge.sm.cr_data_store.common;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lge.framework.ceasar.entity.MapKey;

public class MapKeyTest {
	private MapKey sut;
	private String keyName = "key";
	
	@Before
	public void setUp() {
		sut = new MapKey(keyName);
	}
	
	@Test
	public void testEquals() {
		assertThat(sut, not(equalTo(keyName)));
		assertThat(sut, equalTo(new MapKey(keyName)));
		assertThat(sut, not(equalTo(new MapKey("differentKeyName"))));
	}
	
	@Test
	public void testHashCode() {
		assertThat(sut.hashCode(), equalTo(new MapKey(keyName).hashCode()));
	}
}
