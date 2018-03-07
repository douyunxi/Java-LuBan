package com.haige.luban.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haige.luban.enums.EnumGenderType;
import com.haige.luban.enums.EnumUserType;

import lombok.Data;

@Entity
@Data
public class User {
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
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
