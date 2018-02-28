package com.haige.luban.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haige.luban.dao.AreaJpaDao;
import com.haige.luban.pojo.Area;
import com.haige.luban.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaJpaDao areaJpaDao;

	@Override
	public Area addArea(Area area) {
		return areaJpaDao.save(area);
	}

	@Override
	public void deleteArea(Area area) {
		areaJpaDao.delete(area);
	}

	@Override
	public Area updateArea(Area area) {
		return areaJpaDao.saveAndFlush(area);
	}

	@Override
	public Area getAreaById(Long id) {
		return areaJpaDao.getOne(id);
	}

	@Override
	public List<Area> searchArea(Area area) {
		// TODO Auto-generated method stub
		return null;
	}

}
