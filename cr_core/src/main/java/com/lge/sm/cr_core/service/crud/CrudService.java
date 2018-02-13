package com.lge.sm.cr_core.service.crud;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.framework.ceasar.service.AbstractService;
import com.lge.framework.ceasar.service.view.Skinner;
import com.lge.framework.ceasar.service.view.SkinnerManager;
import com.lge.framework.ceasar.util.JsonUtil;
import com.lge.framework.ceasar.util.ToString;
import com.lge.framework.pacific.logger.Logger;

@Service
public class CrudService extends AbstractService{
	private static final String TAG = CrudService.class.getSimpleName();
	@Override public String getStartableId() { return TAG; }
	@Override public String getServiceId() { return TAG; }
	
	public CrudService() { super(); }
	
	private JsonNode asJsonNode(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readTree(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Logger.error(TAG, "Wrong format of JSON : " + json);
		return null;
	}
	
	private Skinner getSkinnerByString(String json) {
		JsonNode node = asJsonNode(json);
		if(node == null) {
			Logger.error(TAG, "Wrong format of JSON : " + json);
			return null;
		}
		Skinner skinner = SkinnerManager.get(((ObjectNode) node).get("skinType").asText());
		return skinner;
	}
	
	public String skin(String json) {
		Skinner skinner = getSkinnerByString(json);
		if(skinner == null) return "";
		return JsonUtil.toJsonString(skinner.skin());
	}
	
	public String create(String json) {
		JsonNode node = asJsonNode(json);
		if(node == null) return "";
		Skinner skinner = SkinnerManager.get(((ObjectNode) node).get("skinType").asText());
		if(skinner == null) return "";
		((ObjectNode) node).remove("skinType");
		
		return skinner.create(node);
	}
	
	public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit) {
		Skinner skinner = SkinnerManager.get(from.get("skinType"));
		if(skinner == null) return "";
		return skinner.read(from, to, maps, limit);
	}
	
	public String update(String json) {
		JsonNode node = asJsonNode(json);
		if(node == null) return "";
		Skinner skinner = SkinnerManager.get(((ObjectNode) node).get("skinType").asText());
		if(skinner == null) return "";
		((ObjectNode) node).remove("skinType");	
		return skinner.update(node);
	}
	
	public boolean delete(String json) {
		JsonNode node = asJsonNode(json);
		if(node == null) return false;
		Skinner skinner = SkinnerManager.get(((ObjectNode) node).get("skinType").asText());
		if(skinner == null) return false;
		((ObjectNode) node).remove("skinType");
		
		return skinner.delete(node);
	}
	
	public String getKids(Map<String, String> parent, String kidSkinType) {
		ObjectMapper om = JsonUtil.objectMapper();		
		JsonNode node = om.valueToTree(parent);
		
		if(node == null) {
			return "";
		}
		
		Skinner skinner = SkinnerManager.get(((ObjectNode) node).get("skinType").asText());
		if(skinner == null) {
			return "";
		}
		((ObjectNode) node).remove("skinType");
		
		return skinner.getSkinizedKids(node, kidSkinType);
	}
		
	@Override
	public boolean init() {
		return true;
	}

	@Override
	public boolean start() {
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}
}