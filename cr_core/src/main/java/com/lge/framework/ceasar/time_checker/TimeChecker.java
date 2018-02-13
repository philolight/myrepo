package com.lge.framework.ceasar.time_checker;

public class TimeChecker {
	public static <T> T check(TimeCheckable<T> checkable) {
		long start = System.currentTimeMillis();
		try {
			return checkable.check();
		}
		finally {
			long end = System.currentTimeMillis();
			System.out.println("time = " + (end - start));
		}
	}
}