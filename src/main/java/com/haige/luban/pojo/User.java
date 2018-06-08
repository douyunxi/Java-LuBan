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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haige.luban.enums.EnumCertificationStatus;
import com.haige.luban.enums.EnumGenderType;
import com.haige.luban.enums.EnumUserType;

import lombok.Data;

@Entity
@DynamicInsert(true)  
@DynamicUpdate(true)
@Data
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//微信的用户唯一标识
	@JsonIgnore
	//@Column(unique=true,nullable=false)
	//@NotNull
	private String openId;

	@JsonProperty("nickName")
	@Column(nullable=false)
	@NotBlank
    private String nickname;
    
	@Column(nullable=false)
	@NotBlank
    private String realName;
    
    //微信头像地址
    private String avatarUrl;
    
    //性别 0：未知、1：男、2：女
    @Enumerated(EnumType.ORDINAL)
    private EnumGenderType gender;
    
    @ManyToOne
    private Area country;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Area province ;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Area city;
    
    @ManyToOne
    private Area district;
    
    //地址门牌号
    private String address;

    @JsonIgnore
    private String password;
    
    //身份证号码
    private String idCardNum;
    
    //用户类型:管理员、工人、雇主
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private EnumUserType type;

    @Column(nullable=true)
    @NotBlank
    private String mobile;

    @Email
    private String email;
    
    //邮政编码
    private String postalcode;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    
    //是否通过实名认证
    private EnumCertificationStatus certification;
    
    //身份证正面图片保存地址
    @JsonIgnore
    private String idCardFrontPath;
    
    //身份证反面图片保存地址
    @JsonIgnore
    private String idCardBackPath;
    
    //手持身份证图片保存地址
    @JsonIgnore
    private String idCardWithPersonPath;
    
    @JsonIgnore
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<UserMessageRelation> userMessageRelation=new HashSet<UserMessageRelation>();
    
    @JsonIgnore
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<UserTaskRelation> userTaskRelation=new HashSet<UserTaskRelation>();
    
    //购物车-用户映射
    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Cart cart;
}
