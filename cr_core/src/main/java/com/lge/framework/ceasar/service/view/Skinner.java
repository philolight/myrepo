package com.lge.framework.ceasar.service.view;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public interface Skinner {
	public String skinType();
	public Skin skin();
	public String skinized(JsonNode node);
	public String skinizedAll();
	public String create(JsonNode nodeList);
	public String read(Map<String, String> from, Map<String, String> to, Map<String, List<String>> maps, int limit);
	public String update(JsonNode nodeList);
	public String delete(JsonNode nodeList);
	public String getSkinizedKids(JsonNode node, String kidSkinType);
}
