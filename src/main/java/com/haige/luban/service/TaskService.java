package com.haige.luban.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.haige.luban.enums.EnumTaskReceiveStatus;
import com.haige.luban.enums.EnumTaskStatus;
import com.haige.luban.pojo.Task;
import com.haige.luban.pojo.User;
import com.haige.luban.pojo.UserTaskRelation;

public interface TaskService {
	
	Task addTask(Task task);
	
	void deleteTask(Task task);
	
	Task updateTask(Task task);
	
	Task dispatch(Task task,User[] workers);
	
	Task reject(Task task,User worker);
	
	Task receipt(Task task,User worker);
	
	Task getTaskById(Long id);
	
	List<Task> searchTask(Task task);
	
	/**
	 * 分页获得某个状态的任务
	 * @param user
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Task> getTasksByStatus(int page, int size,EnumTaskStatus status);
	
	/**
	 * 获得某个状态的任务集合
	 * @param user
	 * @param page
	 * @param size
	 * @return
	 */
	List<Task> getTasksByStatus(EnumTaskStatus status);
	
	
	/**
	 * 获得某个状态的任务数
	 * @param user
	 * @return
	 */
	Long countTasksByStatus(EnumTaskStatus status);
	
	/**
	 * 分页获得所有任务
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Task> findAllTasks(int start, int size);
	
	/**
	 * 获得已向某人派发的任务
	 * @param user
	 * @return
	 */
	List<Task> getDispatchedTasks(User worker);
	
	/**
	 * 获得已向某人派发的任务数
	 * @param user
	 * @return
	 */
	Long countDispatchedTask(User worker);
	
	/**
	 * 根据任务查找所有用户任务关系
	 * @return
	 */
	List<UserTaskRelation> findRelationsByTaskId(Long taskId);
	
	/**
	 * 根据工人和状态查找关系对应的任务
	 * @param worker
	 * @param status
	 * @return
	 */
	List<Task> findTaskByWorkerAndStauts(User worker,EnumTaskReceiveStatus status);
}
