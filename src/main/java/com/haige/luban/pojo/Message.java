package com.haige.luban.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//消息标题
    private String title;

    //消息内容
    private String content;

    //消息创建时间
    private Date createTime=new Date();
    
    //消息创建者
    @ManyToOne
    private User createUser;
    
    //消息类型：1通知消息，2任务
    private Integer type;
    
    //消息接收者：空为全体人员，否则为具体人
    @ManyToOne
    private User receiver;
    
    //消息状态：0.未读，1.为已读或接收，2.拒绝
    private Integer status=0;
    
}
