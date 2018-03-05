package com.haige.luban.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.pojo.Area;

public interface AreaJpaDao extends JpaRepository<Area, Long>,JpaSpecificationExecutor<Area> {

	List<Area> findAreaByLevel(Integer level);
	
	List<Area> findAreaByParentId(Long parentId);
}
