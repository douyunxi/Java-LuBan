package com.haige.luban.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.haige.luban.enums.EnumMessageStatus;

import lombok.Data;

@Entity
@Table(name="user_message")
@Data
public class UserMessageRelation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonBackReference
	@ManyToOne
	private User user;
	
	@JsonBackReference
	@ManyToOne
	private Message message;
	
	//消息状态：0.未读，1.为已读
	@Enumerated(EnumType.ORDINAL)
	@NotNull
    private EnumMessageStatus status;
    
	//阅读时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date readTime;
}
