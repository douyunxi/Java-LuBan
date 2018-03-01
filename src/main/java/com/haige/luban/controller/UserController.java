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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haige.luban.bo.LoginBo;
import com.haige.luban.pojo.User;
import com.haige.luban.service.LoginService;
import com.haige.luban.service.UserService;

@Controller
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 管理员登录后台管理系统
	 */
	@RequestMapping("/admin")
	String gotoLogin() {
		return "login";
	}

	/**
	 * 管理员登录后台管理系统
	 */
	@RequestMapping("/admin/login")
	String login(String nickname,String password) {
		//userService.g
		return "main";
	}
}
