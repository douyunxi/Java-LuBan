package com.haige.luban.service;

import java.util.List;

import com.haige.luban.pojo.Area;

public interface AreaService {
	
	Area addArea(Area area);
	
	void deleteArea(Area area);
	
	Area updateArea(Area area);
	
	Area getAreaById(Long id);
	
	List<Area> findAreaByLevel(Integer level);
	
	List<Area> findAreaByParentId(Long parentId);
	
	List<Area> findAllProvince();
	
}
