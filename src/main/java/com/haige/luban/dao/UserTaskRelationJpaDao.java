package com.haige.luban.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.haige.luban.enums.EnumTaskReceiveStatus;
import com.haige.luban.pojo.Task;
import com.haige.luban.pojo.User;
import com.haige.luban.pojo.UserTaskRelation;

public interface UserTaskRelationJpaDao extends JpaRepository<UserTaskRelation, Long>,JpaSpecificationExecutor<UserTaskRelation> {
	
	/**
	 * 根据用户和任务查找唯一关系
	 * @param user
	 * @param task
	 * @return
	 */
	UserTaskRelation findByUserAndTask(User user,Task task);
	
	/**
	 * 根据任务查找所有关系
	 * @param task
	 * @return
	 */
	List<UserTaskRelation> findByTask(Task task);
	
	/**
	 * 根据任务id查找所有关系
	 * @param taskId
	 * @return
	 */
	List<UserTaskRelation> findByTask_Id(Long taskId);
	
	/**
	 * 根据用户和接单状态查找关系
	 * @param receiver
	 * @param status
	 * @return
	 */
	List<UserTaskRelation> findByUserAndStatus(User user,EnumTaskReceiveStatus taskReceiveStatus);
	
	/**
	 * 根据用户和接单状态统计数量
	 * @param user
	 * @param status
	 * @return
	 */
	Long countByUserAndStatus(User user,EnumTaskReceiveStatus status);
	
	
	/**
	 * 根据任务和接单状态查找关系
	 * @param task
	 * @param taskReceiveStatus
	 * @return
	 */
	List<UserTaskRelation> findByTaskAndStatus(Task task,EnumTaskReceiveStatus taskReceiveStatus);
	
	/**
	 * 根据任务和接单状态统计数量
	 * @param task
	 * @param status
	 * @return
	 */
	Long countByTaskAndStatus(Task task,EnumTaskReceiveStatus status);
	
	/**
	 * 根据任务Id删除所有关系
	 * @param taskId
	 */
	@Modifying
    @Query("delete from UserTaskRelation r where r.task.id = ?1")
	void deleteByTaskId(Long taskId);
	
	/**
	 * 根据用户Id删除所有关系
	 * @param userId
	 */
	@Modifying
	@Query("delete from UserTaskRelation r where r.user.id = ?1")
	void deleteByUserId(Long userId);
}
