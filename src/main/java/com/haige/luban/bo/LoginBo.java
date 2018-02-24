package com.haige.luban.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginBo {
	
	@JsonProperty("session_key") 
	private String sessionKey;
	
	@JsonProperty("openid") 
	private String openId;
}
