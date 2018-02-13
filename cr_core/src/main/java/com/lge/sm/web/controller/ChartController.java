package com.lge.sm.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_data_store.spring_config.ChartDao;

@Controller
public class ChartController {
	private static final String TAG = ChartController.class.getSimpleName();
	
	@Autowired ChartDao dao;
	
	@RequestMapping("/chart")
	public @ResponseBody String getChart(@RequestBody ChartRequest request){
		System.out.println("skinType = " + request.getSkinType());
		System.out.println("limit = " + request.getLimit());
		System.out.println("axis = " + request.getAxis().toString());
		System.out.println("series = " + request.getSeries().toString());
		System.out.println("legend = " + request.getLegend().toString());

		if(request.prepare() == false) {
			Logger.error(TAG, "Failed to read chart.");
			return "";
		}
		
		return dao.getChartString(request.skinType, request.legend, request.axis, request.series, request.limit);
	}
}

class ChartRequest{
	String skinType;
	Map<String, List<String>> legend;
	Map<String, String> axis;
	List<String> series;
	int limit;
	
	public String getSkinType() { return skinType; }
	public Map<String, List<String>> getLegend() { return legend; }
	public Map<String, String> getAxis() { return axis; }
	public List<String> getSeries() { return series; }
	public int getLimit() { return limit; }
	public void setSkinType(String skinType) { this.skinType = skinType; }
	public void setLegend(Map<String, List<String>> legend) { this.legend = legend; }
	public void setAxis(Map<String, String> axis) { this.axis = axis; }
	public void setSeries(List<String> series) { this.series = series; }
	public void setLimit(int limit) { this.limit = limit;}
	
	public boolean prepare() {
		if(limit > 1000) limit = 1000;
		if(axis.size() != 3) return false;
		if(axis.get("skinType") == "") return false;
		return true;
	}
}