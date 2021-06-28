package com.hjjc.information.service.impl;

import com.hjjc.information.domain.OwnerUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hjjc.information.dao.OwnerUserDao;
import com.hjjc.information.service.OwnerUserService;



@Service
public class OwnerUserServiceImpl implements OwnerUserService {
	@Autowired
	private OwnerUserDao userDao;
	
	@Override
	public OwnerUserDO get(Long id){
		return userDao.get(id);
	}
	
	@Override
	public List<OwnerUserDO> list(Map<String, Object> map){
		return userDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int save(OwnerUserDO user){
		return userDao.save(user);
	}
	
	@Override
	public int update(OwnerUserDO user){
		return userDao.update(user);
	}
	
	@Override
	public int remove(Long id){
		return userDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return userDao.batchRemove(ids);
	}
	
}
