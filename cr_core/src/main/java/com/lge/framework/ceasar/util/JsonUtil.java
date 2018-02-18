package com.lge.framework.ceasar.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lge.framework.ceasar.logger.Logger;

public class JsonUtil {
	private static final String TAG = JsonUtil.class.getSimpleName();
	
	public static ObjectMapper objectMapper() {
		ObjectMapper om = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return om;
	}
	
	public static String toJsonString(Object object) {
		try {
			return objectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			Logger.error(TAG, "Failed to make json string of : " + object.getClass().getSimpleName());
			return "";
		}
	}
}
