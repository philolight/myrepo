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
	@RequestMapping(value="/skinner/skinTypeMap", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String skinMap(ModelAndView modelAndView){
		return SkinnerManager.getSkinTypeMap();
	}
	
	@RequestMapping(value="/skinner/skinTypeList", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String skinTypeList(ModelAndView modelAndView){
		return SkinnerManager.getSkinTypeList();
	}
	
	@RequestMapping(value="/skinner/skinizedAll", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	String skinizedAll(HttpServletRequest request){
		String skinType = request.getParameter("skinType");
		String ret = SkinnerManager.getSkinizedAll(skinType);
		System.out.println("ret = " + ret);
		return ret;
	}
}

