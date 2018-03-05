package com.haige.luban.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.enums.EnumTaskStatus;
import com.haige.luban.pojo.Task;
import com.haige.luban.pojo.User;

public interface TaskJpaDao extends JpaRepository<Task, Long>,JpaSpecificationExecutor<Task> {
	
	List<Task> findByWorkerAndStatus(User worker,EnumTaskStatus status);
	
	Long countByWorkerAndStatus(User worker,EnumTaskStatus status);
}
