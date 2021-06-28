package com.hjjc.information.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hjjc.common.utils.PageUtils;
import com.hjjc.common.utils.Query;
import com.hjjc.common.utils.R;
import com.hjjc.information.domain.DataDO;
import com.hjjc.information.domain.SchoolDO;
import com.hjjc.information.service.DataService;
import com.hjjc.information.service.SchoolService;
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

import com.hjjc.information.domain.OwnerUserDO;
import com.hjjc.information.service.OwnerUserService;


/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-15 14:29:40
 */
 
@Controller
@RequestMapping("/information/user")
public class OwnerUserController {
	@Autowired
	private OwnerUserService userService;
	@Autowired
	private DataService dataService;
	@Autowired
	private SchoolService schoolService;
	
	@GetMapping()
	@RequiresPermissions("information:user:user")
	String User(){
	    return "information/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OwnerUserDO> userList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:user:add")
	String add(Model model){
		List<SchoolDO> schoolDOS = schoolService.list(new HashMap<>());
		model.addAttribute("schoolDOS",schoolDOS);
	    return "information/user/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:user:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OwnerUserDO user = userService.get(id);
		model.addAttribute("user", user);
		List<SchoolDO> schoolDOS = schoolService.list(new HashMap<>());
		model.addAttribute("schoolDOS",schoolDOS);
	    return "information/user/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:user:add")
	public R save( OwnerUserDO user){
		user.setRegisterTime(new Date());
		user.setSchool(user.getSchool().trim());
		user.setGrade(user.getGrade().trim());
		user.setClas(user.getClas().trim());
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:user:edit")
	public R update( OwnerUserDO user){
		user.setSchool(user.getSchool().trim());
		user.setGrade(user.getGrade().trim());
		user.setClas(user.getClas().trim());
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:user:remove")
	public R remove( Long id){
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("userId",id);
		List<DataDO> dataDOS = dataService.list(paramsMap);
		if(dataDOS.size()>0){
			return R.error(-1,"用户已经有上传数据，不可删除");
		}
		if(userService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:user:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		userService.batchRemove(ids);
		return R.ok();
	}
	
}
