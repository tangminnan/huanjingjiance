package com.hjjc.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-24 11:09:56
 */
public class SchoolDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//注册时间
	private Date registerTime;
	//国家
	private String country;
	//所在省
	private String province;
	//城市
	private String city;
	//学校
	private String school;
	//所在区域
	private String area;

	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：注册时间
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getRegisterTime() {
		return registerTime;
	}
	/**
	 * 设置：国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 获取：国家
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * 设置：所在省
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：所在省
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：城市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：城市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：学校
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * 获取：学校
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * 设置：所在区域
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取：所在区域
	 */
	public String getArea() {
		return area;
	}
}
