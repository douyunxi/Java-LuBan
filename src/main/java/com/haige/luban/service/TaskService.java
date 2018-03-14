package com.haige.luban.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.haige.luban.pojo.Task;
import com.haige.luban.pojo.User;

public interface TaskService {
	
	Task addTask(Task task);
	
	void deleteTask(Task task);
	
	Task updateTask(Task task);
	
	Task dispatch(Task task);
	
	Task reject(Task task);
	
	Task receipt(Task task);
	
	Task getTaskById(Long id);
	
	List<Task> searchTask(Task task);
	
	/**
	 * 获得未开始的任务
	 * @param user
	 * @return
	 */
	List<Task> getNoStartTasks(User user);
	
	/**
	 * 分页获得未开始的任务
	 * @param user
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Task> getNoStartTasks(User user,int page,int size);
	
	/**
	 * 获得已向某人派发的任务
	 * @param user
	 * @return
	 */
	List<Task> getDispatchedTasks(User user);
	
	/**
	 * 获得已向某人派发的任务数
	 * @param user
	 * @return
	 */
	Long countDispatchedTask(User user);
	
	/**
	 * 分页获得所有未开始的任务
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Task> findAllNoStartTasks(int page,int size);
	
	/**
	 * 获得未开始的任务数
	 * @param user
	 * @return
	 */
	Long getNoStartTasksCount(User user);
	
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
	Page<Task> findAllTasks(int start, int size);
	
}
