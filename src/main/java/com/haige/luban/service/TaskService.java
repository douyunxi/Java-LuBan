package com.haige.luban.service;

import java.util.List;

import org.springframework.data.domain.Page;

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
	 * 分页获得未完成的任务
	 * @param user
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Task> getUnfinishiedTasks(User user,int page,int size);
	
	/**
	 * 分页获得所有未完成的任务
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Task> findAllUnfinishiedTasks(int page,int size);
	
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
	 * 分页获得已完成的任务
	 * @param user
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Task> getFinishiedTasks(User user,int page,int size);
	
	/**
	 * 分页获得所有已完成的任务
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Task> findAllFinishiedTasks(int page,int size);
	
	/**
	 * 获得已完成的任务数
	 * @param user
	 * @return
	 */
	Long getFinishiedTasksCount(User user);
	
	/**
	 * 分页获得所有任务
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Task> findAllTasks(int page,int size);
	
}
