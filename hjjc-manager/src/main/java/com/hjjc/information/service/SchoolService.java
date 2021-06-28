package com.hjjc.information.service;

import com.hjjc.information.domain.SchoolDO;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-24 11:09:56
 */
public interface SchoolService {
	
	SchoolDO get(Long id);
	
	List<SchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolDO school);
	
	int update(SchoolDO school);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
