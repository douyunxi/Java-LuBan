package com.haige.luban.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	
	private Long id;

    private String nickname;
    
    private String realName;

    private String password;
    
    //身份证号码
    private String idCardNum;
    
    //用户类型:管理员、工人、雇主
    private String type;

    private String phone;

    private String email;

    private Date created;

    private Date updated;
}
