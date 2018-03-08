package com.haige.luban.bo;


import com.haige.luban.enums.EnumUserType;

import lombok.Data;

@Data
public class LoginInfo {
	
	private String sessionId;
	
	private EnumUserType userType;
}
