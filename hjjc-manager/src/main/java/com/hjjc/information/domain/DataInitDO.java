package com.hjjc.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 设备上传原始数据表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-10 11:53:59
 */
public class DataInitDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//原始数据
	private String data;
	//上传时间
	private Date addTime;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：原始数据
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * 获取：原始数据
	 */
	public String getData() {
		return data;
	}
	/**
	 * 设置：上传时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：上传时间
	 */
	public Date getAddTime() {
		return addTime;
	}
}
