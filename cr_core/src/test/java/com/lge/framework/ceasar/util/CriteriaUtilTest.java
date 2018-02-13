package com.lge.framework.ceasar.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CriteriaUtilTest {
	@Test
	public void testCalculateCriteriaCombination() {
		Map<String, List<String>> maps = new HashMap<>();
		int[] idx = new int[3];
		maps.put("A", Arrays.asList("A","B","C")); // 3개
		maps.put("B", Arrays.asList("A","B")); // 2개
		maps.put("C", Arrays.asList("A","B")); // 2개
		Object[] keySet = {"A","B","C"};
		
		int r = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					System.out.println(idx[0] + " " + idx[1] + " " + idx[2]);
					assertThat(idx[0], equalTo(i));
					assertThat(idx[1], equalTo(j));
					assertThat(idx[2], equalTo(k));
					r = CriteriaUtil.calculateCriteriaCombination(idx, maps, keySet);
				}
			}
		}
		
		assertThat(r, equalTo(-1));
	}
	
	@Test
	public void testCalculateCriteriaCombination2() {
		Map<String, List<String>> maps = new HashMap<>();
		int[] idx = new int[5];
		maps.put("A", new ArrayList<>()); // 0개
		maps.put("B", Arrays.asList("A","B","C")); // 3개
		maps.put("C", new ArrayList<>()); // 0개
		maps.put("D", Arrays.asList("A","B")); // 2개
		maps.put("E", new ArrayList<>()); // 0개
		Object[] keySet = {"A","B","C","D","E"};
		
		int r = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				System.out.println(idx[0] + " " + idx[1] + " " + idx[2] + " " + idx[3] + " " + idx[4]);
				assertThat(idx[0], equalTo(0));
				assertThat(idx[1], equalTo(i));
				assertThat(idx[2], equalTo(0));
				assertThat(idx[3], equalTo(j));
				assertThat(idx[4], equalTo(0));
				r = CriteriaUtil.calculateCriteriaCombination(idx, maps, keySet);
			}
		}
		
		assertThat(r, equalTo(-1));
	}
}
