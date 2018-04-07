package com.haige.luban.enums;

/**
 * 任务状态枚举
 * @author linwei
 *
 */
public enum EnumTaskStatus {
	
	NO_START(0,"未开始"),DISPATCHED(1,"已派单"),RECEIPT_PART(2,"部分接单"),REJECT_PART(3,"需重派单"),RECEIPT_ALL(4,"全部接单"),PROCESSING(5,"进行中"),CHECKING(6,"验收中"),FINISHED(7,"已完成");
	
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
