package com.hjjc.information.service;

import com.hjjc.information.domain.DeviceDO;

import java.util.List;
import java.util.Map;

/**
 * 用户设备表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-11 14:01:32
 */
public interface DeviceService {
	
	DeviceDO get(Integer id);
	
	List<DeviceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeviceDO device);
	
	int update(DeviceDO device);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
