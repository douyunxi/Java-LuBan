package com.haige.luban.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

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
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;

	/**
	 * 用户登录小程序，获取微信登录信息
	 * @param session
	 * @param code
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("/login")
	@ResponseBody
	Object login(HttpSession session,String code) throws JsonParseException, JsonMappingException, IOException {
		String response=loginService.login(code);
		ObjectMapper mapper = new ObjectMapper();  
        LoginBo loginBo = mapper.readValue(response, LoginBo.class);
        session.setAttribute("loginBo", loginBo);//保存用户密钥等登录信息
		return session.getId();
	}
	
	/**
	 * 判断是否注册用户
	 * @param session
	 * @return
	 */
	@RequestMapping("/isRegistered")
	@ResponseBody
	boolean isRegistered(HttpSession session) {
		boolean isRegistered=false;
		LoginBo loginBo =(LoginBo)session.getAttribute("loginBo");
		if(loginBo!=null) {
			String openId=loginBo.getOpenId();
			//根据openId查库，是否是注册用户，是则返回进入按钮，否则返回注册，逛一逛按钮
			User user=userService.getUserByOpenId(openId);
			if(user!=null) {
				//用户已注册
				isRegistered=true;
			}
			else {
				//用户未注册
				isRegistered=false;
			}
		}
		return isRegistered;
	}

}
