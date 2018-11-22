package com.cwca.customer.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1768931144633277198L;
	private Integer id;
	private String unittax;
	private String unitname;
	private String username;
	private String password;
	private String email;
	private String mobile;
	private String chat;
	private String salt;  //盐值
	private Integer valid;
	private Date createdTime;
	private String createdUser;
	private String modifiedUser;
	private Date modifiedTime;
	private Integer isRegistered;
	private Integer isMember;
	private String qrCodePath;
	private String openid;
	private String partnername;
	private long qrcodetimestampend;


}
