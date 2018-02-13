package com.lge.sm.cr_data_store;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SynchronizedExceptionTest {
	
	@Test
	public void testSynchronizedException() {
		TestClass sut = new TestClass();
		assertThat(sut.exception(), equalTo(true));
		assertThat(sut.checkUnlock(), equalTo(true));
	}
}

class TestClass{
	private final Object lock = new Object();
	
	public boolean exception() {
		try {
			synchronized(lock) {
				@SuppressWarnings("unused") Integer a = 0 / 0;
				return false;
			}
		} catch(Exception e) {
			return true;
		}
	}
	
	public boolean checkUnlock() {
		try {
			synchronized(lock) {
				int a = 1 + 1;
				return true;
			}
		} catch(Exception e) {
			return false;
		}
	}
}