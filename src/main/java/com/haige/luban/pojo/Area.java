package com.haige.luban.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class Area implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@JsonProperty("text")
    private String name;

    private Long parentId;

    //地级市的等级
    private Integer level;
}
