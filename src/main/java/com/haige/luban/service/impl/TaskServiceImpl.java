package com.haige.luban.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.haige.luban.dao.TaskJpaDao;
import com.haige.luban.enums.EnumTaskStatus;
import com.haige.luban.pojo.Task;
import com.haige.luban.pojo.User;
import com.haige.luban.service.TaskService;
import com.haige.luban.util.UpdateUtil;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskJpaDao taskJpaDao;

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
	public Task dispatch(Task task) {
		Task oldTask=taskJpaDao.getOne(task.getId());
		UpdateUtil.copyNonNullProperties(task, oldTask);
		oldTask.setStatus(EnumTaskStatus.DISPATCHED);
		return taskJpaDao.saveAndFlush(oldTask);
	}
	
	@Override
	public Task reject(Task task) {
		Task oldTask=taskJpaDao.getOne(task.getId());
		UpdateUtil.copyNonNullProperties(task, oldTask);
		oldTask.setStatus(EnumTaskStatus.REJECT);
		return taskJpaDao.saveAndFlush(oldTask);
	}
	
	@Override
	public Task receipt(Task task) {
		Task oldTask=taskJpaDao.getOne(task.getId());
		UpdateUtil.copyNonNullProperties(task, oldTask);
		oldTask.setStatus(EnumTaskStatus.RECEIPT);
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
	public List<Task> getNoStartTasks(User user) {
		return taskJpaDao.findByWorkerAndStatus(user,EnumTaskStatus.NO_START);
	}

	@Override
	public Long getNoStartTasksCount(User user) {
		return taskJpaDao.countByWorkerAndStatus(user,EnumTaskStatus.NO_START);
	}

	@Override
	public List<Task> getFinishiedTasks(User user) {
		return taskJpaDao.findByWorkerAndStatus(user,EnumTaskStatus.FINISHED);
	}

	@Override
	public Long getFinishiedTasksCount(User user) {
		return taskJpaDao.countByWorkerAndStatus(user,EnumTaskStatus.FINISHED);
	}

	@Override
	public Page<Task> getNoStartTasks(User user, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Task> findAllNoStartTasks(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Task> getFinishiedTasks(User user, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Task> findAllFinishiedTasks(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Task> findAllTasks(int start, int size) {
		return taskJpaDao.findAll(PageRequest.of(start/size, size));
	}

	@Override
	public List<Task> getDispatchedTasks(User user) {
		return taskJpaDao.findByWorkerAndStatus(user,EnumTaskStatus.DISPATCHED);
	}

	@Override
	public Long countDispatchedTask(User user) {
		return taskJpaDao.countByWorkerAndStatus(user,EnumTaskStatus.DISPATCHED);
	}

}
