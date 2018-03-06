package com.haige.luban.enums;

/**
 * 用户类型枚举
 * @author linwei
 *
 */
public enum EnumGenderType {
	
	UNKNOW(0,"未知"),MAN(1,"男"),WOMAN(2,"女");
	
	private Integer code;
	
	private String desc;
	
	private EnumGenderType(Integer code, String desc) {
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
		for(EnumGenderType genderType : EnumGenderType.values()){
			if(genderType.getCode().equals(code)){
				return genderType.getDesc();
			}
		}
		return null;
	}
}
