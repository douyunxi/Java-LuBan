package com.haige.luban.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * 雇主对工人的打分
 * @author linwei
 *
 */
@Entity
@Data
public class Score {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//分数
    private String value;

    //评论
    private String comment;

    //创建时间
    private Date createTime=new Date();
    
    //创建者（雇主、客户）
    @ManyToOne
    private User createUser;
    
    //接收者（工人）
    @ManyToOne
    private User receiver;
    
}
