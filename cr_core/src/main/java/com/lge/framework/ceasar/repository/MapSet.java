package com.lge.framework.ceasar.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.lge.framework.ceasar.entity.MapKey;

/**
 * Map의 element로 Set을 가지고 있는 컨테이너.
 * Thread Safe하게 구현됨.
 */
public class MapSet<V> {
	private Map<MapKey, Set<V>> mapSet = new ConcurrentHashMap<>();
	
	/**
	 * key에 대응되는 Set에 value를 삽입하는 메소드.
	 */
	public void put(MapKey key, V value) {
		Set<V> set = mapSet.get(key);
		if(set == null) {
			set = Collections.synchronizedSet(new HashSet<>());
			mapSet.put(key, set);
		}
		set.add(value);
	}

	/**
	 * key에 대응되는 Set을 삭제하는 메소드.
	 */
	public void remove(MapKey key) {
		mapSet.remove(key);
	}
	
	/**
	 * 하나의 value를 삭제하는 메소드. 
	 */
	public void remove(MapKey key, V value) {
		Set<V> set = mapSet.get(key);
		if(set == null) return;
		set.remove(value);
		if(set.isEmpty()) mapSet.remove(key);
	}
	
	/**
	 * key에 해당하는 Set을 "카피"하여 리턴하는 메소드. 
	 */
	public List<V> get(MapKey key){
		Set<V> element = mapSet.get(key);
		if(element == null) return Collections.emptyList();
		List<V> ret = new ArrayList<>();
		element.forEach(each -> ret.add(each));
		return ret;
	}
}
