package com.haige.luban.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haige.luban.enums.EnumMessagePublishStatus;

import lombok.Data;

@Entity
@Data
public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//消息标题
	@Column(nullable=false)
	@NotBlank
    private String title;

    //消息内容
	@Column(nullable=false)
	@NotBlank
    private String content;

    //消息创建时间
	@Column(nullable=false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    
    //消息创建者
    @ManyToOne
    private User createUser;
    
    //消息状态：0未发布，1已发布
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    @NotNull
    private EnumMessagePublishStatus publishStatus;
    
    //消息发布时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publishTime;
    
    //消息接收者,为了保存消息是否被接收等状态，选择用中间表保存
    @JsonIgnore
    @OneToMany(mappedBy="message",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<UserMessageRelation> userMessageRelation=new HashSet<UserMessageRelation>();
    
    //@ManyToMany
    //private Set<User> receivers= new HashSet<User>();
    
}
