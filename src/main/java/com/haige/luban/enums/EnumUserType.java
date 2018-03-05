package com.haige.luban.enums;

/**
 * 用户类型枚举
 * @author linwei
 *
 */
public enum EnumUserType {
	
	ADMIN(1,"管理员"),EMPLOYER(2,"雇主"),WORKER(3,"工人");
	
	private Integer code;
	
	private String desc;
	
	private EnumUserType(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static String getDesc(Integer code){
		for(EnumUserType userType : EnumUserType.values()){
			if(userType.getCode().equals(code)){
				return userType.getDesc();
			}
		}
		return null;
	}
}
