package com.haige.luban.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haige.luban.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	@ResponseBody
    String login(String code) {
		System.out.println(code);
		loginService.login(code);
        return "login";
    }
	
}
