package com.haige.luban.enums;

/**
 * 任务状态枚举
 * @author linwei
 *
 */
public enum EnumTaskStatus {
	
	UNFINISHED(1,"未完成"),PROCESSING(2,"处理中"),FINISHED(3,"已完成");
	
	private Integer code;
	
	private String desc;
	
	private EnumTaskStatus(Integer code, String desc) {
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
		for(EnumTaskStatus taskType : EnumTaskStatus.values()){
			if(taskType.getCode().equals(code)){
				return taskType.getDesc();
			}
		}
		return null;
	}
}
