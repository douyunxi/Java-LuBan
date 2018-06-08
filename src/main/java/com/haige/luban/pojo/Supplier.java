package com.haige.luban.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import lombok.Data;

/**
 * 商品供应商
 * @author linwei
 *
 */
@Entity
@DynamicInsert(true)  
@DynamicUpdate(true)
@Data
public class Supplier implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//供应商名称
	@Column(nullable=false)
	@NotBlank
	private String name;

    //供应商电话
    @Column(nullable=true)
    @NotBlank
    private String mobile;

    //供应商邮箱
    @Email
    private String email;
    
    //供应商邮政编码
    private String postalcode;
	
}
