package com.haige.luban.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haige.luban.pojo.Area;
import com.haige.luban.service.AreaService;

@Controller
@RequestMapping("/admin")
public class AreaController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AreaService areaService;
	
	
	/**
	 * 管理员登录后台管理系统--用户管理页面
	 */
	@RequestMapping("/area")
	String user() {
		return "area";
	}
	
	@RequestMapping("/area/findAllProvince")
	@ResponseBody
	List<Area> findAllProvince(){
		List<Area> provinces=areaService.findAllProvince();
		return provinces;
	}
	
	@RequestMapping("/area/findCities")
	@ResponseBody
	List<Area> findCities(long provinceId){
		List<Area> cities=areaService.findAreaByParentId(provinceId);
		return cities;
	}
	
	@RequestMapping("/area/findDistrictes")
	@ResponseBody
	List<Area> findDistrictes(long cityId){
		List<Area> districtes=areaService.findAreaByParentId(cityId);
		return districtes;
	}
}
