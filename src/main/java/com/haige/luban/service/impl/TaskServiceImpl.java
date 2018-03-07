package com.haige.luban.service.impl;

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

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskJpaDao taskJpaDao;

	@Override
	public Task addTask(Task task) {
		task.setStatus(EnumTaskStatus.NO_START);
		return taskJpaDao.save(task);
	}

	@Override
	public void deleteTask(Task task) {
		taskJpaDao.delete(task);
	}

	@Override
	public Task updateTask(Task task) {
		return taskJpaDao.saveAndFlush(task);
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

}
