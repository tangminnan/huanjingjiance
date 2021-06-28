package com.hjjc.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hjjc.information.dao.DataDao;
import com.hjjc.information.domain.DataDO;
import com.hjjc.information.service.DataService;



@Service
public class DataServiceImpl implements DataService {
	@Autowired
	private DataDao dataDao;
	
	@Override
	public DataDO get(Integer id){
		return dataDao.get(id);
	}
	
	@Override
	public List<DataDO> list(Map<String, Object> map){
		return dataDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dataDao.count(map);
	}
	
	@Override
	public int save(DataDO data){
		return dataDao.save(data);
	}
	
	@Override
	public int update(DataDO data){
		return dataDao.update(data);
	}
	
	@Override
	public int remove(Integer id){
		return dataDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return dataDao.batchRemove(ids);
	}
	
}
