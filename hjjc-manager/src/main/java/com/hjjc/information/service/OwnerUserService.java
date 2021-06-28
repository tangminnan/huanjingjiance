package com.hjjc.information.service;

import com.hjjc.information.domain.OwnerUserDO;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-15 14:29:40
 */
public interface OwnerUserService {
	
	OwnerUserDO get(Long id);
	
	List<OwnerUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OwnerUserDO user);
	
	int update(OwnerUserDO user);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
