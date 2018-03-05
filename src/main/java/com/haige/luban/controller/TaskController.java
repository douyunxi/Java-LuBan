package com.haige.luban.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haige.luban.bo.DataTables;
import com.haige.luban.pojo.Task;
import com.haige.luban.service.TaskService;

@Controller
@RequestMapping("/admin")
public class TaskController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TaskService taskService;
	
	
	/**
	 * 管理员登录后台管理系统--用户管理页面
	 */
	@RequestMapping("/task")
	String user() {
		return "task";
	}
	
	@RequestMapping("/task/query")
	@ResponseBody
	DataTables query(int start,int length,int draw){
		Page<Task> taskPage=taskService.findAllTasks(start, length);
		DataTables dataTables=new DataTables();
		dataTables.setDraw(draw);
		dataTables.setRecordsTotal(taskPage.getTotalElements());
		dataTables.setRecordsFiltered(taskPage.getTotalElements());
		dataTables.setData(taskPage.getContent());
		return dataTables;
	}
}
