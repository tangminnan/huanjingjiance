package com.hjjc.information.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hjjc.common.utils.PageUtils;
import com.hjjc.common.utils.Query;
import com.hjjc.common.utils.R;
import com.hjjc.information.domain.DataDO;
import com.hjjc.information.domain.OwnerUserDO;
import com.hjjc.information.service.DataService;
import com.hjjc.information.service.OwnerUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjjc.information.domain.DeviceDO;
import com.hjjc.information.service.DeviceService;


/**
 * 用户设备表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-11 14:01:32
 */
 
@Controller
@RequestMapping("/information/device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private OwnerUserService ownerUserService;
	@Autowired
	private DataService dataService;

	@GetMapping()
	@RequiresPermissions("information:device:device")
	String Device(){
	    return "information/device/device";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:device:device")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeviceDO> deviceList = deviceService.list(query);
		int total = deviceService.count(query);
		PageUtils pageUtils = new PageUtils(deviceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:device:add")
	String add(Model model){
		List<OwnerUserDO> list = ownerUserService.list(new HashMap<>());
		model.addAttribute("data",list);
	    return "information/device/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:device:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		DeviceDO device = deviceService.get(id);
		model.addAttribute("device", device);
		List<OwnerUserDO> list = ownerUserService.list(new HashMap<>());
		model.addAttribute("data",list);
	    return "information/device/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:device:add")
	public R save( DeviceDO device){
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("identity",device.getIdentity().trim());
		List<DeviceDO> deviceDOS=deviceService.list(paramsMap);
		if(deviceDOS.size()>0){
			return  R.error(-1,"设备ID已经存在");
		}
		device.setIdentity(device.getIdentity().trim());
		device.setName(device.getName().trim());
		device.setAddtime(new Date());
		if(!"WG".equals(device.getType())) {
			if (0 == device.getUserId())
				device.setUserName("");
			else {
				OwnerUserDO ownerUserDO = ownerUserService.get(device.getUserId());
				if (ownerUserDO != null) {
					device.setUserName(ownerUserDO.getSchool() + ownerUserDO.getGrade() + ownerUserDO.getClas());
				}
			}
		}else{
			device.setUserId(0L);
			device.setUserName("");
		}
		if(deviceService.save(device)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:device:edit")
	public R update( DeviceDO device){
		device.setName(device.getName().trim());
//		if(!"WG".equals(device.getType())){
//			Map<String,Object> paramsMap = new HashMap<>();
//			paramsMap.put("did",device.getIdentity());
//			List<DataDO> dataDOS = dataService.list(paramsMap);
		  if(!"WG".equals(device.getType())) {
				if (0 == device.getUserId())
					device.setUserName("");
				else {
					OwnerUserDO ownerUserDO = ownerUserService.get(device.getUserId());
					if (ownerUserDO != null) {
						device.setUserName(ownerUserDO.getSchool() + ownerUserDO.getGrade() + ownerUserDO.getClas());
					}
				}
			}else{
			  device.setUserId(0L);
			  device.setUserName("");
		  }
//		}
		deviceService.update(device);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:device:remove")
	public R remove( Integer id){
		DeviceDO deviceDO = deviceService.get(id);
		Map<String,Object> paramsMap = new HashMap<>();
		String type = deviceDO.getType();
		if("WG".equals(type)){
			paramsMap.put("gid",deviceDO.getIdentity());
		}else{
			paramsMap.put("did",deviceDO.getIdentity());
		}
		List<DataDO> dataDOS = dataService.list(paramsMap);
		if(dataDOS.size()>0)
			return R.error(-1,"设备已经在使用，无法删除");
		if(deviceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:device:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		deviceService.batchRemove(ids);
		return R.ok();
	}
	
}
