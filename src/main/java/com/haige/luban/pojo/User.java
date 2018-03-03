package com.haige.luban.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//微信的用户唯一标识
	@JsonIgnore
	private String openId;

	@JsonProperty("nickName")
    private String nickname;
    
    private String realName;
    
    //微信头像地址
    private String avatarUrl;
    
    //性别 0：未知、1：男、2：女
    private String gender;
    
    @ManyToOne
    private Area country;
    @ManyToOne
    private Area province ;
    @ManyToOne
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
    private String type;

    private String mobile;

    private String email;
    
    //邮政编码
    private String postalcode;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
