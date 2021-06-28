package com.hjjc.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hjjc.common.domain.LogDO;
import com.hjjc.common.domain.PageDO;
import com.hjjc.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
