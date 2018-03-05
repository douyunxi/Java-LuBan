package com.haige.luban.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haige.luban.bo.DataTables;
import com.haige.luban.pojo.User;
import com.haige.luban.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	/**
	 * 管理员登录后台管理系统
	 */
	@RequestMapping("")
	String gotoLogin() {
		return "login";
	}

	/**
	 * 管理员登录后台管理系统
	 */
	@RequestMapping("/login")
	String login(String openId,String password) {//nickname不能保证唯一
		//userService.g
		return "main";
	}
	
	/**
	 * 管理员登录后台管理系统--用户管理页面
	 */
	@RequestMapping("/user")
	String user() {
		return "user";
	}
	
	@RequestMapping("/user/query")
	@ResponseBody
	DataTables query(int start,int length,int draw){
		Page<User> userPage=userService.findAllUser(start, length);
		DataTables dataTables=new DataTables();
		dataTables.setDraw(draw);
		dataTables.setRecordsTotal(userPage.getTotalElements());
		dataTables.setRecordsFiltered(userPage.getTotalElements());
		dataTables.setData(userPage.getContent());
		return dataTables;
	}
	
	@RequestMapping("/user/add")
	String query(User user){
		User newUser=userService.addUser(user);
		if(newUser!=null && newUser.getId()!=null) {
			
		}
		return "redirect:/user/query";
	}
	
	@RequestMapping("/user/findAllEmployer")
	@ResponseBody
	List<User> query(){
		List<User> employers=userService.findAllEmployer();
		return employers;
	}
}
