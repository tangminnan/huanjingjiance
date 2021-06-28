package com.hjjc.information.service;

import com.hjjc.information.domain.DataInitDO;

import java.util.List;
import java.util.Map;

/**
 * 设备上传原始数据表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-10 11:53:59
 */
public interface DataInitService {
	
	DataInitDO get(Integer id);
	
	List<DataInitDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DataInitDO dataInit);
	
	int update(DataInitDO dataInit);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
