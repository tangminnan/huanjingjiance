package com.hjjc.information.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.hjjc.common.utils.PageUtils;
import com.hjjc.common.utils.Query;
import com.hjjc.common.utils.R;
import com.hjjc.common.utils.StringUtils;
import com.hjjc.information.domain.DeviceDO;
import com.hjjc.information.domain.OwnerUserDO;
import com.hjjc.information.domain.SchoolDO;
import com.hjjc.information.service.DeviceService;
import com.hjjc.information.service.OwnerUserService;
import com.hjjc.information.service.SchoolService;
import com.hjjc.system.domain.DeptDO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import com.hjjc.information.domain.DataDO;
import com.hjjc.information.service.DataService;

import javax.servlet.http.HttpServletResponse;

/**
 * 设备上传原始数据表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-06-10 18:06:06
 */
 
@Controller
@RequestMapping("/information/data")
public class DataController {
	@Autowired
	private DataService dataService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private OwnerUserService ownerUserService;
	@Autowired
	private SchoolService schoolService;
	
	@GetMapping()
	String Data(Integer initId,
				String identity,
				Long userId,
				Date startTime,
				Date endTime,
				Model model){
		model.addAttribute("initId",initId==null?"":initId);
		model.addAttribute("identity",identity==null?"":identity);
		model.addAttribute("userId",userId==null?"":userId);
		model.addAttribute("startTime",startTime);
		model.addAttribute("endTime",endTime);
		List<SchoolDO> schoolDOS = schoolService.list(new HashMap<>());
		model.addAttribute("schoolDOS",schoolDOS);

		return "information/data/data";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Map<String,Object> daM = new HashMap<>();
		daM.put("identity",params.get("identity"));
		List<DeviceDO> deviceDOS = deviceService.list(daM);
		if(deviceDOS.size()>0) {
			String type = deviceDOS.get(0).getType();
			if ("T&H".equals(type) || "LU".equals("type"))
				params.put("did", params.get("identity"));
			else if ("WG".equals(type))
				params.put("gid", params.get("identity"));
		}
        Query query = new Query(params);
		List<DataDO> dataList = dataService.list(query);
//		dataList.forEach(a->{
//			if(a.getTemp()!=null)
//				a.setTemp(a.getTemp().substring(0,4)+"℃");
//		});
		int total = dataService.count(query);
		PageUtils pageUtils = new PageUtils(dataList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "information/data/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		DataDO data = dataService.get(id);
		model.addAttribute("data", data);
	    return "information/data/edit";
	}



	/**
	 *  班级监护仪数据excel导出
	 */
	@GetMapping("/daochu")
	public void exportClassData(HttpServletResponse response, String endTime,String startTime,String schoolID) throws IOException {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("检测数据导出模板.xls");
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("startTime",startTime);
		paramsMap.put("endTime",endTime);
		paramsMap.put("schoolID",schoolID);
		List<DataDO> list = dataService.list(paramsMap);
		HSSFCell cell=null;
		String name= null;
		for(int i=0;i<list.size(); i++){
			DataDO dataDO = list.get(i);
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataDO.getAddTime());
			name=date;
			String lumen= dataDO.getLumen()==null?"":dataDO.getLumen();
			String temp  =dataDO.getTemp()==null?"":dataDO.getTemp();
			String humidity = dataDO.getHumidity()==null?"":dataDO.getHumidity();
			String longitude = dataDO.getLongitude()==null?"":dataDO.getLongitude();
			String latitude =dataDO.getLatitude()==null?"":dataDO.getLatitude();
			String sn = dataDO.getSn()==null?"": dataDO.getSn();
			String st = dataDO.getSt()==null?"":dataDO.getSt();
			String type = dataDO.getType()==null?"":dataDO.getType();
			String did = dataDO.getDid()==null?"":dataDO.getDid();
			String voltage = dataDO.getVoltage()==null?"":dataDO.getVoltage();
			String grade = dataDO.getGrade()==null?"":dataDO.getGrade();
			String clas = dataDO.getClas()==null?"":dataDO.getClas();
			if(type!=null && "LU".equals(type))
				type="光照度传感器";
			if(type!=null && "T&H".equals(type))
				type="温湿度传感器";
			row = sheet.createRow(i+1);
			cell = row.createCell(0);
			cell.setCellValue(grade);
			cell = row.createCell(1);
			cell.setCellValue(clas);
			cell = row.createCell(2);
			cell.setCellValue(date);
			cell = row.createCell(3);
			cell.setCellValue(lumen);
			cell = row.createCell(4);
			cell.setCellValue(temp);
			cell = row.createCell(5);
			cell.setCellValue(humidity);
			cell = row.createCell(6);
			cell.setCellValue(longitude);
			cell = row.createCell(7);
			cell.setCellValue(latitude);
			cell = row.createCell(8);
			cell.setCellValue(st);
			cell = row.createCell(9);
			cell.setCellValue(type);
			cell = row.createCell(10);
			cell.setCellValue(did);
			cell = row.createCell(11);
			cell.setCellValue(voltage);
		}
		String fileName=name+"报告.xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader(
				"Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1"));

		workbook.write(response.getOutputStream());
	}





	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save(DataDO data){
		if(dataService.save(data)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( DataDO data){
		dataService.update(data);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(dataService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] ids){
		dataService.batchRemove(ids);
		return R.ok();
	}


	@GetMapping("/dis")
	public String dis(Long userId,Model model){
		OwnerUserDO ownerUserDO = ownerUserService.get(userId);
		model.addAttribute("ownerUserDO",ownerUserDO);
		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("userId",userId);
		List<DeviceDO> deviceDOS = deviceService.list(paramsMap);
		model.addAttribute("deviceDOS",deviceDOS);
		return "information/data/dis";
	}

	@GetMapping("/getEcharsData")
	@ResponseBody
	public Map<String,Object> getEcharsData(Long userId,Date startTime,Date endTime){
		Map<String,Object> resultMap = new HashMap<>();
		Calendar calendar = Calendar.getInstance();
		if(startTime==null) {
			calendar.set(Calendar.HOUR_OF_DAY,0);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			startTime = calendar.getTime();
		}
		if(endTime==null){
			endTime=new Date();
		}

		List<Date> xTime = new ArrayList<>();
        Date startTime_1 = startTime,endTime_1=endTime;
		do{
			calendar.setTime(startTime_1);
			calendar.add(Calendar.MINUTE,30);
			startTime_1=calendar.getTime();
		}while(startTime_1.compareTo(endTime_1)<=0 && xTime.add(startTime_1));
		if(xTime.size()==0){
			xTime.add(startTime_1);
			xTime.add(endTime_1);
		}


		Map<String,Object> params = new HashMap<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("userId",userId);
		params.put("startTime",simpleDateFormat.format(startTime));
		params.put("endTime",simpleDateFormat.format(endTime));
		List<DataDO> list = dataService.list(params);
		if(list.size()>0){
			Map<String,List<DataDO>> dataMap = list.stream().filter(a->a.getDid()!=null).collect(Collectors.groupingBy(DataDO::getDid));
			Set<String> ids = dataMap.keySet();
			Iterator<String> iterator = ids.iterator();
			Map<String,Object> subMap = new HashMap<>();
			while (iterator.hasNext()){
				String did = iterator.next();
				List<DataDO> dataDOS = dataMap.get(did);
				String type = dataDOS.get(0).getType();
				List<Double> lux_list  = new ArrayList<>();//保存光照
				List<Double> temp_list = new ArrayList<>();//保存温度
				List<Double> humidity_list = new ArrayList<>();//保存湿度
				xTime.forEach(a->{
					double  temp=0.0,//温度
					lux=0.0,//光照度
							humidity=0.0;//湿度
					int    temp_c=0,//温度点数
					       lux_c=0,//光照度点数
					       humidity_c=0;//湿度点数
					switch(type){
						case "LU":
							for(DataDO dataDO :dataDOS){
									if(type.equals(dataDO.getType()) && dataDO.getAddTime().compareTo(a)<=0 && StringUtils.isNotBlank(dataDO.getLumen())){
										lux+=Double.parseDouble(dataDO.getLumen().substring(0,dataDO.getLumen().indexOf("LX")));
										lux_c++;
									}
							}
							System.out.println(lux+"  >>>>>>>>>>>>>>>>>>  " +lux_c);
							if(lux_c==0) lux_list.add(0.0);
							else lux_list.add(lux/lux_c);
						break;
						case "T&H":
							for(DataDO dataDO :dataDOS){
								if(type.equals(dataDO.getType()) && dataDO.getAddTime().compareTo(a)<=0 && StringUtils.isNotBlank(dataDO.getHumidity())){
									humidity+=Double.parseDouble(dataDO.getHumidity().substring(0,dataDO.getHumidity().indexOf("%")));
									humidity_c++;
								}
								if(type.equals(dataDO.getType()) && dataDO.getAddTime().compareTo(a)<=0 && StringUtils.isNotBlank(dataDO.getTemp())){
//									temp+=Double.parseDouble(dataDO.getTemp().substring(0,dataDO.getTemp().indexOf("℃")));
									temp++;
									temp_c++;
								}
							}
							if(humidity_c==0) humidity_list.add(0.0);
							else humidity_list.add(humidity/humidity_c);
							if(temp_c==0) temp_list.add(0.0);
							else temp_list.add(temp/temp_c);
							break;
					}
				});
				subMap.put("lux"+did,lux_list);
				subMap.put("temp"+did,temp_list);
				subMap.put("humidity"+did,humidity_list);
			}

			resultMap.put("subMap",subMap);
		}else{
			resultMap.put("subMap",null);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		List<String> l = new ArrayList<>();
		for(int i=0,length=xTime.size();i<length;i++){
			l.add(sdf.format(xTime.get(i)));
		}
		resultMap.put("next",l);
		return resultMap;
	}
	
}
