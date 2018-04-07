package com.haige.luban.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haige.luban.dao.TaskJpaDao;
import com.haige.luban.dao.UserTaskRelationJpaDao;
import com.haige.luban.enums.EnumTaskReceiveStatus;
import com.haige.luban.enums.EnumTaskStatus;
import com.haige.luban.pojo.Task;
import com.haige.luban.pojo.User;
import com.haige.luban.pojo.UserTaskRelation;
import com.haige.luban.service.TaskService;
import com.haige.luban.util.UpdateUtil;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskJpaDao taskJpaDao;
	
	@Autowired
	private UserTaskRelationJpaDao userTaskRelationJpaDao;

	@Override
	public Task addTask(Task task) {
		task.setStatus(EnumTaskStatus.NO_START);
		task.setCreateTime(new Date());
		return taskJpaDao.save(task);
	}

	@Override
	public void deleteTask(Task task) {
		taskJpaDao.delete(task);
	}

	@Override
	public Task updateTask(Task task) {
		Task oldTask=taskJpaDao.getOne(task.getId());
		UpdateUtil.copyNonNullProperties(task, oldTask);
		return taskJpaDao.saveAndFlush(oldTask);
	}
	
	
	@Override
	@Transactional
	public Task dispatch(Task task,User[] workers) {
		//建立工人与任务关系
		for(User worker:workers) {
			UserTaskRelation userTaskRelation=new UserTaskRelation();
			userTaskRelation.setTask(task);
			userTaskRelation.setUser(worker);
			userTaskRelation.setStatus(EnumTaskReceiveStatus.UNTREATED);
			userTaskRelationJpaDao.save(userTaskRelation);
		}
		//更新任务状态
		Task oldTask=taskJpaDao.getOne(task.getId());
		UpdateUtil.copyNonNullProperties(task, oldTask);
		oldTask.setStatus(EnumTaskStatus.DISPATCHED);
		return taskJpaDao.saveAndFlush(oldTask);
	}
	
	@Override
	@Transactional
	public Task reject(Task task,User worker) {
		//更新关系状态
		UserTaskRelation relation=userTaskRelationJpaDao.findByUserAndTask(worker, task);
		relation.setStatus(EnumTaskReceiveStatus.REJECT);
		userTaskRelationJpaDao.save(relation);
		//更新任务状态		
		Task oldTask=taskJpaDao.getOne(task.getId());
		UpdateUtil.copyNonNullProperties(task, oldTask);
		oldTask.setStatus(EnumTaskStatus.REJECT_PART);//需重派单
		return taskJpaDao.saveAndFlush(oldTask);
	}
	
	@Override
	@Transactional
	public Task receipt(Task task,User worker) {
		//更新关系状态
		UserTaskRelation relation=userTaskRelationJpaDao.findByUserAndTask(worker, task);
		relation.setStatus(EnumTaskReceiveStatus.RECEIPT);
		userTaskRelationJpaDao.save(relation);
		//查询总体接单状态
		Long reject=userTaskRelationJpaDao.countByTaskAndStatus(task, EnumTaskReceiveStatus.REJECT);
		Long untreated=userTaskRelationJpaDao.countByTaskAndStatus(task, EnumTaskReceiveStatus.UNTREATED);
		//更新任务状态
		Task oldTask=taskJpaDao.getOne(task.getId());
		UpdateUtil.copyNonNullProperties(task, oldTask);
		if(reject+untreated>0) {//拒单+未处理数量大于0
			oldTask.setStatus(EnumTaskStatus.RECEIPT_PART);//部分接单
		}
		else {
			oldTask.setStatus(EnumTaskStatus.RECEIPT_ALL);//全部接单（接单完成）
		}
		return taskJpaDao.saveAndFlush(oldTask);
	}

	@Override
	public Task getTaskById(Long id) {
		return taskJpaDao.getOne(id);
	}

	@Override
	public List<Task> searchTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Task> getTasksByStatus(EnumTaskStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Task> getTasksByStatus(int page, int size, EnumTaskStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countTasksByStatus(EnumTaskStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Task> findAllTasks(int start, int size) {
		return taskJpaDao.findAll(PageRequest.of(start/size, size));
	}

	@Override
	public List<Task> getDispatchedTasks(User worker) {
		List<Task> tasks=new LinkedList<Task>();
		List<UserTaskRelation> userTaskRelations=userTaskRelationJpaDao.findByUserAndStatus(worker, EnumTaskReceiveStatus.UNTREATED);
		for(UserTaskRelation relation:userTaskRelations) {
			tasks.add(relation.getTask());
		}
		return tasks;
	}

	@Override
	public Long countDispatchedTask(User worker) {
		return userTaskRelationJpaDao.countByUserAndStatus(worker, EnumTaskReceiveStatus.UNTREATED);
	}

	@Override
	public List<UserTaskRelation> findRelationsByTaskId(Long taskId) {
		return userTaskRelationJpaDao.findByTask_Id(taskId);
	}

	@Override
	public List<Task> findTaskByWorkerAndStauts(User worker, EnumTaskReceiveStatus status) {
		List<Task> tasks=new LinkedList<Task>();
		List<UserTaskRelation> relations=userTaskRelationJpaDao.findByUserAndStatus(worker, status);
		for(UserTaskRelation relation:relations) {
			tasks.add(relation.getTask());
		}
		return tasks;
	}
	
}
