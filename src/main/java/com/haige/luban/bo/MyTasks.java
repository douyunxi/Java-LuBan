package com.haige.luban.bo;

import java.util.List;

import com.haige.luban.pojo.Task;

import lombok.Data;


/**
 * 我的任务单
 * @author linwei
 *
 */
@Data
public class MyTasks {
	
	//未完成任务数
	private List<Task> unfinishedTask;
	
	//已完成任务数
	private List<Task> finishedTask;
	
}
