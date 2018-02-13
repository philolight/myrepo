package com.lge.framework.ceasar.json;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lge.framework.ceasar.util.ModelUtil;
import com.lge.framework.ceasar.util.ToString;
import com.lge.sm.cr_data_store.LocationDtos;
import com.lge.sm.cr_data_store.dto.LocationDto;

public class JacksonTest {
	private LocationDtos dtos = new LocationDtos();
	
	@Test
	public void testAddNewNode() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		LocationDto dto = dtos.get(0);		
		JsonNode node = mapper.valueToTree(dto);

		((ObjectNode) node).put("objectType", LocationDto.class.getSimpleName()); // add new node ( type : LocationDto )
//		System.out.println(mapper.writeValueAsString(node));
		
		String jsonString = mapper.writeValueAsString(node);
		
		ObjectMapper nm = new ObjectMapper();
		JsonNode newNode = nm.readTree(jsonString);			
		((ObjectNode) newNode).remove("objectType");	// remove node node
		
		LocationDto newDto = nm.treeToValue(newNode, LocationDto.class);
		
		assertTrue(ModelUtil.isEqual(dto, newDto));
	}
	
	@Test
	public void testGet() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		LocationDto dto = dtos.get(0);		
		JsonNode node = mapper.valueToTree(dto);

		((ObjectNode) node).put("objectType", LocationDto.class.getSimpleName()); // add new node ( type : LocationDto )
		System.out.println(((ObjectNode) node).get("objectType").asText());
		assertEquals(LocationDto.class.getSimpleName(), ((ObjectNode) node).get("objectType").asText());
	}
	
	@Test
	public void testList() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		List<String> list = Arrays.asList("Ceasar", "Augustus", "Tiberius");
		JsonNode node = mapper.createArrayNode(); 
		ObjectNode outerObject = mapper.createObjectNode();
		outerObject.putPOJO("data", list.toArray());
		
		System.out.println(mapper.writeValueAsString(list));
		System.out.println(mapper.writeValueAsString(outerObject));
	}
	
	@Test
	public void testMap() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.createObjectNode();
		Map<String, String> map = new HashMap<>();
		map.put("1", "Ceasar");
		map.put("2", "Augustus");
		map.put("3", "Tiberius");
		
		synchronized(map) {
			Set<String> keySet = map.keySet();
			for(String key : keySet) {
				((ObjectNode) node).put(key, map.get(key));
			}
		}
		
		System.out.println(mapper.writeValueAsString(node));
	}
	
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		LocationDto dto = new LocationDto();
		dto.setCdate("A");
		dto.setLocationId("B");
		dto.setName("N");
		dto.setTimeZoneId("TZ");
		try {
			JsonNode node = mapper.valueToTree(dto);
//			((ObjectNode) node).set("type", mapper.readTree("{\"type\": \""+ LocationDto.class.getSimpleName() +"\"}"));
			((ObjectNode) node).put("objectType", LocationDto.class.getSimpleName());
			System.out.println(mapper.writeValueAsString(node));
			
			String jsonString = mapper.writeValueAsString(node);
			
			ObjectMapper nm = new ObjectMapper();
			JsonNode newNode = nm.readTree(jsonString);			
			((ObjectNode) newNode).remove("objectType");
			LocationDto newDto = nm.treeToValue(newNode, LocationDto.class);
			
			System.out.println(ToString.toLine(newDto));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
