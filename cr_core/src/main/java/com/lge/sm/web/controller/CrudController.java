package com.lge.sm.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lge.framework.ceasar.util.JsonUtil;
import com.lge.sm.cr_core.service.crud.CrudService;

@Controller
public class CrudController {
	@Autowired CrudService service;
	
	@RequestMapping(value = "/crud/create", method = RequestMethod.POST)
	public @ResponseBody String createRequest(@RequestBody String json) {
		String ret = service.create(json);
		
		return ret;
	}
	
	@RequestMapping(value = "/crud/update", method = RequestMethod.POST)
	public @ResponseBody String updateRequest(@RequestBody String json) {
		System.out.println(json);
		
		String ret = service.update(json);
		
		System.out.println("ret = " + ret);
		
		return ret;
	}
	
	@RequestMapping(value = "/crud/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteRequest(@RequestBody String json) {
		System.out.println(json);
		
		boolean ret = service.delete(json);
		
		System.out.println("ret = " + ret);
		
		return ret == true ? "{\"result\":\"success\"}" : "{\"result\":\"fail\"}";
	}
	
	@RequestMapping(value = "/crud/kids", method = RequestMethod.POST)
	public @ResponseBody String kidsRequest(@RequestBody KidsRequest request) {
		System.out.println("parent = " + request.parent.toString());
		System.out.println("kidSkinType = " + request.kidSkinType);
		
		String ret = service.getKids(request.parent, request.kidSkinType);
		System.out.println("ret = " + ret);
		
		return ret;
	}
}

class KidsRequest{
	Map<String, String> parent;
	String kidSkinType;
	
	public Map<String, String> getParent() { return parent; }
	public String getKidSkinType() { return kidSkinType; }
	public void setParent(Map<String, String> parent) { this.parent = parent; }
	public void setKidSkinType(String kidSkinType) { this.kidSkinType = kidSkinType; }
}