package com.haige.luban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
	@RequestMapping("/gotoRegister")
    String gotoRegister() {
        return "register";
    }
	
	@RequestMapping("/register")
	@ResponseBody
    String register() {
        return "index";
    }
}
