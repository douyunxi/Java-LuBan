package com.haige.luban.wechat;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.haige.luban.bo.WechatMessage;
import com.haige.luban.pojo.Message;
import com.haige.luban.pojo.Task;
import com.haige.luban.pojo.User;
import com.haige.luban.service.MessageService;
import com.haige.luban.service.TaskService;
import com.haige.luban.service.UserService;

@Controller
@RequestMapping("/wechat")
public class MyController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private TaskService taskService;
	
	/**
	 * 获取小程序“我的”页面的消息状态
	 * @param session
	 * @param code
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("/getMyStatus")
	@ResponseBody
	Object getMyStatus(HttpSession session){
		User user=(User)session.getAttribute("user");
		return userService.getMyStatus(user);
	}

	/**
	 * 获取消息列表(包括通知和派单)
	 * @param session
	 * @return
	 */
	@RequestMapping("/getMessages")
	@ResponseBody
	Object getMessages(HttpSession session){
		User user=(User)session.getAttribute("user");
		List<WechatMessage> wechatMessages=new LinkedList<WechatMessage>();
		List<Message> messages=messageService.getMessagesByReceiver(user);
		List<Task> tasks=taskService.getDispatchedTasks(user);
		for(Message message:messages) {
			WechatMessage wechatMessage=new WechatMessage();
			wechatMessage.setId(message.getId());
			wechatMessage.setType("message");
			wechatMessage.setTitle(message.getTitle());
			wechatMessage.setContent(message.getContent());
			wechatMessage.setTime(message.getPublishTime());
			wechatMessages.add(wechatMessage);
		}
		for(Task task:tasks) {
			WechatMessage wechatMessage=new WechatMessage();
			wechatMessage.setId(task.getId());
			wechatMessage.setType("task");
			wechatMessage.setTitle(task.getTitle());
			wechatMessage.setContent(task.getContent());
			wechatMessage.setTime(task.getCreateTime());
			wechatMessages.add(wechatMessage);
		}
		return wechatMessages;
	}
	
	@RequestMapping("/task/reject")
	@ResponseBody
	boolean reject(Task task){
		taskService.reject(task);
		return true;
	}
	
	@RequestMapping("/task/receipt")
	@ResponseBody
	boolean receipt(Task task){
		taskService.receipt(task);
		return true;
	}
	

	@RequestMapping("/message/read")
	@ResponseBody
	boolean read(HttpSession session,Message message){
		User user=(User)session.getAttribute("user");
		messageService.readMessage(user,message);;
		return true;
	}
}
