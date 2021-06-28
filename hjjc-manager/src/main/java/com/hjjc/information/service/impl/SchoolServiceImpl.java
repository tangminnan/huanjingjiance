package com.hjjc.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hjjc.information.dao.SchoolDao;
import com.hjjc.information.domain.SchoolDO;
import com.hjjc.information.service.SchoolService;



@Service
public class SchoolServiceImpl implements SchoolService {
	@Autowired
	private SchoolDao schoolDao;
	
	@Override
	public SchoolDO get(Long id){
		return schoolDao.get(id);
	}
	
	@Override
	public List<SchoolDO> list(Map<String, Object> map){
		return schoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolDao.count(map);
	}
	
	@Override
	public int save(SchoolDO school){
		return schoolDao.save(school);
	}
	
	@Override
	public int update(SchoolDO school){
		return schoolDao.update(school);
	}
	
	@Override
	public int remove(Long id){
		return schoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolDao.batchRemove(ids);
	}
	
}
