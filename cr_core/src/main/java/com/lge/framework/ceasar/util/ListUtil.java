package com.lge.framework.ceasar.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	public static <T> List<T> copy(List<T> list){
		return new ArrayList<>(list);
	}
}
