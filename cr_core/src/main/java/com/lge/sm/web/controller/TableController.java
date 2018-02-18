package com.lge.sm.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TableController {
	@RequestMapping("/table")
	public String onMouseMove(ModelAndView modelAndView){
		return "/table/table";
	}
}

class ReadRequest{
	Map<String, String> from; 
	Map<String, String> to;
	Map<String, List<String>> maps;
	int limit;
	public int getLimit() { return limit; }
	public Map<String, String> getFrom() { return from; }
	public Map<String, String> getTo() { return to; }
	public Map<String, List<String>> getMaps() { return maps; }
	public void setLimit(int limit) { this.limit = limit; }
	public void setFrom(Map<String, String> from) { this.from = from; }
	public void setTo(Map<String, String> to) { this.to = to; }
	public void setMaps(Map<String, List<String>> maps) { this.maps = maps;}
	
	public void prepare() {
		if(limit > 1000) limit = 1000;
		for(String key : from.keySet()) {
			String fieldFrom = from.get(key);
			String fieldTo = to.get(key);
			if(fieldFrom == "") to.replace(key, "");
			else if(fieldTo == "") to.replace(key, fieldFrom);
		}
	}
}
