package com.hjjc.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hjjc.information.dao.DeviceDao;
import com.hjjc.information.domain.DeviceDO;
import com.hjjc.information.service.DeviceService;



@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	public DeviceDO get(Integer id){
		return deviceDao.get(id);
	}
	
	@Override
	public List<DeviceDO> list(Map<String, Object> map){
		return deviceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deviceDao.count(map);
	}
	
	@Override
	public int save(DeviceDO device){
		return deviceDao.save(device);
	}
	
	@Override
	public int update(DeviceDO device){
		return deviceDao.update(device);
	}
	
	@Override
	public int remove(Integer id){
		return deviceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return deviceDao.batchRemove(ids);
	}
	
}
