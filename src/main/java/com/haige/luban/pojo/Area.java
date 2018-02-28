package com.haige.luban.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Area {
	
	@Id
	private Long id;

    private String name;

    private Long parentId;

    //地级市的等级
    private Integer level;
}
