package com.hjjc.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 设备上传原始数据表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-10 18:06:06
 */
public class DataDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//用户ID
	private Long userId;
	//采集器ID
	private String gid;
	//设备类型
	private String type;
	//光照强度
	private String lumen;
	//环境温度
	private String temp;
	//环境湿度
	private String humidity;
	//经度
	private String longitude;
	//纬度
	private String latitude;
	//信号强度
	private String rssi;
	//发射序列号
	private String sn;
	//电池电压
	private String voltage;
	//采集时间间隔
	private String st;
	//采集时间
	private Date addTime;
	//传感器状态
	private String stat;
	//设备ID
	private String did;
	//初始化数据ID
	private Integer initId;
	//所在省
	private String province;
	//城市
	private String city;
	//学校
	private String school;
	//所在区域
	private String area;
	//年级
	private String grade;
	//班级
	private String clas;



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
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：采集器ID
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}
	/**
	 * 获取：采集器ID
	 */
	public String getGid() {
		return gid;
	}
	/**
	 * 设置：设备类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：设备类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：光照强度
	 */
	public void setLumen(String lumen) {
		this.lumen = lumen;
	}
	/**
	 * 获取：光照强度
	 */
	public String getLumen() {
		return lumen;
	}
	/**
	 * 设置：环境温度
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}
	/**
	 * 获取：环境温度
	 */
	public String getTemp() {
		return temp;
	}
	/**
	 * 设置：环境湿度
	 */
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	/**
	 * 获取：环境湿度
	 */
	public String getHumidity() {
		return humidity;
	}
	/**
	 * 设置：经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：经度
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * 设置：纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：纬度
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * 设置：信号强度
	 */
	public void setRssi(String rssi) {
		this.rssi = rssi;
	}
	/**
	 * 获取：信号强度
	 */
	public String getRssi() {
		return rssi;
	}
	/**
	 * 设置：发射序列号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * 获取：发射序列号
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * 设置：电池电压
	 */
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	/**
	 * 获取：电池电压
	 */
	public String getVoltage() {
		return voltage;
	}
	/**
	 * 设置：采集时间间隔
	 */
	public void setSt(String st) {
		this.st = st;
	}
	/**
	 * 获取：采集时间间隔
	 */
	public String getSt() {
		return st;
	}
	/**
	 * 设置：采集时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：采集时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public Integer getInitId() {
		return initId;
	}

	public void setInitId(Integer initId) {
		this.initId = initId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}
}
