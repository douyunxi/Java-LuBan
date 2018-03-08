package com.haige.luban.wechat;

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
@RequestMapping("/wechat")
public class MyController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
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

}
