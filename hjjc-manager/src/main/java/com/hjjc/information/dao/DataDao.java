package com.hjjc.information.dao;

import com.hjjc.information.domain.DataDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 设备上传原始数据表
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-10 18:06:06
 */
@Mapper
public interface DataDao {

	DataDO get(Integer id);
	
	List<DataDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DataDO data);
	
	int update(DataDO data);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
