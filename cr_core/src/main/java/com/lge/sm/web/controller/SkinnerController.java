package com.lge.sm.web.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lge.framework.ceasar.service.view.SkinnerManager;

@Controller
public class SkinnerController {
	@RequestMapping(value="/skinner/skinTypeMap", method = RequestMethod.POST)
	@ResponseBody
	public String skinMap(ModelAndView modelAndView){
		System.out.println("/skinner/skinTypeMap");
		return SkinnerManager.getSkinTypeMap();
	}
	
	@RequestMapping(value="/skinner/skinTypeList", method = RequestMethod.POST)
	@ResponseBody
	public String skinTypeList(ModelAndView modelAndView){
		System.out.println("/skinner/skinTypeList");
		return SkinnerManager.getSkinTypeList();
	}
	
	@RequestMapping(value="/skinner/skinizedAll", method = RequestMethod.POST)
	@ResponseBody
	String skinizedAll(HttpServletRequest request){
		System.out.println("/skinner/skinizedAll");
		String skinType = request.getParameter("skinType");
		return SkinnerManager.getSkinizedAll(skinType);
	}
}

