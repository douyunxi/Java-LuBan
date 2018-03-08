package com.haige.luban.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.haige.luban.pojo.User;
import com.haige.luban.service.MessageService;
import com.haige.luban.service.UserService;

@Controller
public class MessageController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;

	
	/**
	 * 获取消息列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/getMessages")
	@ResponseBody
	Object getMessages(HttpSession session){
		User user=(User)session.getAttribute("user");
		return messageService.getMessagesByReceiver(user);
	}

}
