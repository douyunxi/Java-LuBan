package com.haige.luban.bo;

import lombok.Data;


/**
 * 记录“我的”页面中未处理的消息数量
 * @author linwei
 *
 */
@Data
public class MyStatus {
	
	//未读消息数
	private Long messages;
	
	//未完成任务数
	private Long tasks;
	
	//未到帐工资笔数
	private Long salaries;
}
