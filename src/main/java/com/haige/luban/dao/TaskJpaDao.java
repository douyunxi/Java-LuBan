package com.haige.luban.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.enums.EnumTaskStatus;
import com.haige.luban.pojo.Task;

public interface TaskJpaDao extends JpaRepository<Task, Long>,JpaSpecificationExecutor<Task> {
	
	List<Task> findByStatus(EnumTaskStatus status);
	
	Long countByStatus(EnumTaskStatus status);
}
