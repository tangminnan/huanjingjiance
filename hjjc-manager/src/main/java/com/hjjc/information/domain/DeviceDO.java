package com.hjjc.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户设备表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-11 14:01:32
 */
public class DeviceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//设备ID
	private String identity;
	//设备名称
	private String name;
	//设备类型 T&H 温湿度传感器 LU光照度传感器  WG 网关
	private String type;
	//添加时间
	private Date addtime;
	//所属用户
	private Long userId;
	//用户名称
	private String userName;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：设备ID
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/**
	 * 获取：设备ID
	 */
	public String getIdentity() {
		return identity;
	}
	/**
	 * 设置：设备名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：设备名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：设备类型 CG 传感器  WG 网关
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：设备类型 CG 传感器  WG 网关
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：添加时间
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getAddtime() {
		return addtime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
