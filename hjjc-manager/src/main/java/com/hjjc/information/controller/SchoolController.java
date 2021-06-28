package com.hjjc.information.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hjjc.common.utils.PageUtils;
import com.hjjc.common.utils.Query;
import com.hjjc.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjjc.information.domain.SchoolDO;
import com.hjjc.information.service.SchoolService;


/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-24 11:09:56
 */
 
@Controller
@RequestMapping("/information/school")
public class SchoolController {
	@Autowired
	private SchoolService schoolService;
	
	@GetMapping()
	@RequiresPermissions("information:school:school")
	String School(){
	    return "information/school/school";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:school:school")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolDO> schoolList = schoolService.list(query);
		int total = schoolService.count(query);
		PageUtils pageUtils = new PageUtils(schoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:school:add")
	String add(){
	    return "information/school/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:school:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolDO school = schoolService.get(id);
		model.addAttribute("school", school);
	    return "information/school/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:school:add")
	public R save( SchoolDO school){
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("province",school.getProvince());
		paramsMap.put("city",school.getCity());
		paramsMap.put("area",school.getArea());
		paramsMap.put("school",school.getSchool().trim());
		if(schoolService.list(paramsMap).size()>0){
			return R.error(-1,"学校名称已经存在");
		}
		school.setRegisterTime(new Date());
		school.setSchool(school.getSchool().trim());
		if(schoolService.save(school)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:school:edit")
	public R update( SchoolDO school){
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("province",school.getProvince());
		paramsMap.put("city",school.getCity());
		paramsMap.put("area",school.getArea());
		paramsMap.put("school",school.getSchool().trim());
		List<SchoolDO> list = schoolService.list(paramsMap);
		for(SchoolDO schoolDO :list){
			if(schoolDO.getId().longValue()!=school.getId().longValue())
				return R.error(-1,"学校名称已经存在");
		}
		school.setSchool(school.getSchool().trim());
		schoolService.update(school);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:school:remove")
	public R remove( Long id){
		if(schoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:school:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolService.batchRemove(ids);
		return R.ok();
	}
	
}
