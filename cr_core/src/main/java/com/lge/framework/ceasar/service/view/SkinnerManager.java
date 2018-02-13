package com.lge.framework.ceasar.service.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lge.framework.ceasar.util.JsonUtil;
import com.lge.framework.pacific.logger.Logger;

public class SkinnerManager {
	private static final String TAG = SkinnerManager.class.getSimpleName();
	
	private static Map<String/*skinType*/, Skinner> skinnerMap = new ConcurrentHashMap<>();
	public static void regist(Skinner skinner) {
		skinnerMap.put(skinner.skinType(), skinner);
	}
	
	public static Skinner get(String skinType) {
		return skinnerMap.get(skinType);
	}
	
	/** 시스템 내에서 다루고 있는 "Object"의 타입명 리스트를 반환하는 메소드. */
	public static String getSkinTypeList() {
		Object[] arr = null;
		synchronized(skinnerMap) {
			Set<String> keySet = skinnerMap.keySet();
			arr = keySet.toArray();
		}
		List<String> list = new ArrayList<>();
		for(Object each : arr) list.add((String) each);
		
		return JsonUtil.toJsonString(list);
	}
		
	/** 시스템 내에서 다루고 있는 "Object"의 타입명과 skin을 반환하는 메소드. */
	public static String getSkinTypeMap() {
		Map<String/*SkinType*/, Skin> map = new HashMap<>();
		synchronized(skinnerMap) {
			Set<String> keySet = skinnerMap.keySet();
			for(String key : keySet) {
				Skin skin = skinnerMap.get(key).skin();
				map.put(skin.skinType, skin);
			}
		}

		return JsonUtil.toJsonString(map);
	}
	
	public static String getSkinizedAll(String skinType) {
		return skinnerMap.get(skinType).skinizedAll();
	}
}
