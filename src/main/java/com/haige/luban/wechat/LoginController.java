package com.haige.luban.wechat;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haige.luban.bo.LoginInfo;
import com.haige.luban.bo.WechatLoginBo;
import com.haige.luban.pojo.User;
import com.haige.luban.service.LoginService;
import com.haige.luban.service.UserService;

@Controller
@RequestMapping("/wechat")
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Value("${web.upload-path}")
	//private String webUploadPath;

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;

	/**
	 * 用户登录小程序，获取微信登录信息；开发者服务器使用登录凭证 code 获取 session_key 和 openid
	 * @param session
	 * @param code
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("/login")
	@ResponseBody
	LoginInfo login(HttpSession session,String code) throws JsonParseException, JsonMappingException, IOException {
		String response=loginService.login(code);
		ObjectMapper mapper = new ObjectMapper();  
        WechatLoginBo loginBo = mapper.readValue(response, WechatLoginBo.class);
        session.setAttribute("loginBo", loginBo);//保存用户密钥等登录信息
        String openId=loginBo.getOpenId();
        User user=userService.getUserByOpenId(openId);
        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setSessionId(session.getId());
        if(user!=null) {//用户已注册
        	//将用户user存入session为以后使用
        	session.setAttribute("user", user);
        	if(user.getType()!=null) {
        		loginInfo.setUserType(user.getType());//用户类型
        	}
        }
		return loginInfo;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	boolean register(HttpSession session,User user) {
		logger.info("sessionId:"+session.getId());
		boolean success=false;
		WechatLoginBo loginBo =(WechatLoginBo)session.getAttribute("loginBo");
		if(loginBo!=null) {
			String openId=loginBo.getOpenId();
			user.setOpenId(openId);
		}
		else {
			
		}
		User createdUser=userService.addUser(user);
		if(createdUser!=null) {
			session.setAttribute("user",createdUser);
			success=true;
		}
		return success;
	}
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	User query(HttpSession session){
		User user=(User)session.getAttribute("user");
		return user;
	}
	
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	boolean update(HttpSession session,User user) throws IllegalAccessException, InvocationTargetException{
		Long userId=((User)session.getAttribute("user")).getId();
		user.setId(userId);
		User newUser=userService.updateUser(user);
		session.setAttribute("user",newUser);
		return true;
	}

	/**
	 * 用户上传身份证
	 * @param idCardFront 图片
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping(value = "/uploadIdCard", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean fileUpload(HttpSession session,@RequestParam("idCardFront") MultipartFile file,String fileName) throws IllegalStateException, IOException {
		User user=(User)session.getAttribute("user");
		logger.info(user.getRealName()+"上传身份证照片");
		if (!file.isEmpty()) {
			if (file.getContentType().contains("image")) {
				String temp = "images" + File.separator + "upload" + File.separator;
				// 获取图片的文件名
				String originalFilename = file.getOriginalFilename();
				// 获取图片的扩展名
				String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
				String filePath=user.getId()+"_"+user.getRealName()+"_"+user.getMobile();
				String newName=System.currentTimeMillis()+"_1"+suffix;
				File destFile=new File("d:/鲁班来啦"+File.separator+filePath+File.separator+newName);
				if(!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}
				file.transferTo(destFile);
			}
		}
		return true;
	}
	  
}
