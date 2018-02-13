package com.lge.framework.ceasar.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.lge.sm.cr_core.common.Constants;

public class CriteriaUtil {
	public static boolean setEqualCriteria(Object example, String name, String strValue){
		try {
			Method method = example.getClass().getDeclaredMethod("and" + bigName(name)+"EqualTo");
			Class<?>[] params = method.getParameterTypes();
			
			if(params[0].equals(String.class)) {
				method.invoke(example, strValue);
			}
			else if(params[0].equals(Short.class)) {
				Short value = Short.parseShort(strValue);
				method.invoke(example, value);
			}
			else if(params[0].equals(Integer.class)) {
				Integer value = Integer.parseInt(strValue);
				method.invoke(example, value);
			}
			else if(params[0].equals(Long.class)) {
				Long value = Long.parseLong(strValue);
				method.invoke(example, value);
			}
			else if(params[0].equals(Float.class)) {
				Float value = Float.parseFloat(strValue);
				method.invoke(example, value);
			}
			else if(params[0].equals(Double.class)) {
				Double value = Double.parseDouble(strValue);
				method.invoke(example, value);
			}
			else if(params[0].equals(Date.class)) {
				Date value = DateUtil.stringToDate(strValue, Constants.DEFAULT_DATE_FORMAT);
				method.invoke(example, value);
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean setBetweenCriteria(Object example, String name, String from, String to) {
		try {
			Method method = example.getClass().getDeclaredMethod("and" + bigName(name)+"Between");
			Class<?>[] params = method.getParameterTypes();
			
			if(params[0].equals(String.class)) {
				method.invoke(example, from, to);
			}
			else if(params[0].equals(Short.class)) {				
				Short fromValue = Short.parseShort(from);
				Short toValue = Short.parseShort(to);
				method.invoke(example, fromValue, toValue);
			}
			else if(params[0].equals(Integer.class)) {
				Integer fromValue = Integer.parseInt(from);
				Integer toValue = Integer.parseInt(to);
				method.invoke(example, fromValue, toValue);
			}
			else if(params[0].equals(Long.class)) {
				Long fromValue = Long.parseLong(from);
				Long toValue = Long.parseLong(to);
				method.invoke(example, fromValue, toValue);
			}
			else if(params[0].equals(Float.class)) {
				Float fromValue = Float.parseFloat(from);
				Float toValue = Float.parseFloat(to);
				method.invoke(example, fromValue, toValue);
			}
			else if(params[0].equals(Double.class)) {
				Double fromValue = Double.parseDouble(from);
				Double toValue = Double.parseDouble(to);
				method.invoke(example, fromValue, toValue);
			}
			else if(params[0].equals(Date.class)) {
				Date fromValue = DateUtil.stringToDate(from, Constants.DEFAULT_DATE_FORMAT);
				Date toValue = DateUtil.stringToDate(to, Constants.DEFAULT_DATE_FORMAT);
				method.invoke(example, fromValue, toValue);
			}			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	// Criteria 조합의 다음번 조합을 계산하는 메소드.
	// Criteria의 개수가 3개 이고, 각각 3, 2, 2개의 세부 Criteria가 존재한다면,
	// (0,0,0) (0,0,1) (0,1,0) (0,1,1) (1,0,0) (1,0,1) (1,1,0) (1,1,1) (2,0,0) (2,0,1) (2,1,0) (2,1,1) 순서의 조합이 생성되어야 한다.
	// Criteria의 세부 개수가 0개인 경우를 포함하여 모든 조합을 계산하여 idx 배열에 넣어 주고,
	// 만약 모든 조합의 Criteria를 수행하였을 경우 -1을 리턴해 준다.
    public static int calculateCriteriaCombination(int[] idx, Map<String, List<String>> maps, Object[] keySet) {
		int r = idx.length - 1;
		while(r >= 0 && ++idx[r] >= maps.get(keySet[r]).size()) {
			idx[r] = 0;
			r--;
		}
		return r;
    }
	
	private static String bigName(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
	}
}
