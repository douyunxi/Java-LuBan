package com.haige.luban.enums;

/**
 * 身份认证状态枚举
 * @author linwei
 *
 */
public enum EnumCertificationStatus {
	
	NO_START(0,"未完成"),CHECKING(1,"审核中"),NOT_PASS(2,"未通过"),FINISHED(3,"已完成");
	
	private Integer code;
	
	private String desc;
	
	private EnumCertificationStatus(Integer code, String desc) {
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
		for(EnumCertificationStatus status : EnumCertificationStatus.values()){
			if(status.getCode().equals(code)){
				return status.getDesc();
			}
		}
		return null;
	}
}
