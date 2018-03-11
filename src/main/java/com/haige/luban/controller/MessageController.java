package com.haige.luban.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haige.luban.bo.DataTables;
import com.haige.luban.pojo.Message;
import com.haige.luban.pojo.User;
import com.haige.luban.service.MessageService;
import com.haige.luban.service.UserService;

@Controller
@RequestMapping("/admin")
public class MessageController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;

	
	/**
	 * 管理员登录后台管理系统--用户管理页面
	 */
	@RequestMapping("/message")
	String message() {
		return "message";
	}
	
	@RequestMapping("/message/query")
	@ResponseBody
	DataTables query(int start,int length,int draw){
		Page<Message> messagePage=messageService.findAllMessages(start, length);
		DataTables dataTables=new DataTables();
		dataTables.setDraw(draw);
		dataTables.setRecordsTotal(messagePage.getTotalElements());
		dataTables.setRecordsFiltered(messagePage.getTotalElements());
		dataTables.setData(messagePage.getContent());
		return dataTables;
	}
	
	@RequestMapping("/message/add")
	@ResponseBody
	boolean add(HttpSession session,Message message,String reciver){
		User user=(User)session.getAttribute("user");
		message.setCreateUser(user);
		messageService.addMessage(message,reciver);
		return true;
	}
	
	@RequestMapping("/message/update")
	@ResponseBody
	boolean update(Message message,String reciver){
		messageService.updateMessage(message,reciver);
		return true;
	}
	
	@RequestMapping("/message/publish")
	@ResponseBody
	boolean publish(Message message){
		messageService.publishMessage(message);;
		return true;
	}
	
	@RequestMapping("/message/delete")
	@ResponseBody
	boolean delete(Message message){
		messageService.deleteMessage(message);;
		return true;
	}

}
