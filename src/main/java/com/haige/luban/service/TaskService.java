package com.haige.luban.service;

import java.util.List;

import com.haige.luban.pojo.Task;
import com.haige.luban.pojo.User;

public interface TaskService {
	
	Task addTask(Task task);
	
	void deleteTask(Task task);
	
	Task updateTask(Task task);
	
	Task getTaskById(Long id);
	
	List<Task> searchTask(Task task);
	
	/**
	 * 获得未完成的任务
	 * @param user
	 * @return
	 */
	List<Task> getUnfinishiedTasks(User user);
	
	/**
	 * 获得未完成的任务数
	 * @param user
	 * @return
	 */
	Long getUnfinishiedTasksCount(User user);
	
	/**
	 * 获得已完成的任务
	 * @param user
	 * @return
	 */
	List<Task> getFinishiedTasks(User user);
	
	/**
	 * 获得已完成的任务数
	 * @param user
	 * @return
	 */
	Long getFinishiedTasksCount(User user);
	
}
