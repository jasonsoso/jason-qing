package com.jason.qing.domain.contact;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.jason.framework.domain.IdDomainObject;

public class Contact extends IdDomainObject{

	private static final long serialVersionUID = 1L;
	
	@Size(min = 2, max = 20,message="姓名限制2~20之间")
	private String name;		//姓名
	
	
	@Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message="手机号码格式不正确！")
	private String mobile;		//手机号码
	
	private Date createdAt;		//创建时间
	
	private Date updatedAt;		//最后更新时间
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
