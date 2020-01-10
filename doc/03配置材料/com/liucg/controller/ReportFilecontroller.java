package com.liucg.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liucg.pojo.ReportFile;
import com.liucg.service.ReportFileservice;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liucg
 * @since 2019-12-18
 */
@Controller
@RequestMapping("/reportFile")
public class ReportFilecontroller {
	@Autowired
	private ReportFileservice reportFileService;
	/**
	 * 报表列表
	 * @return
	 */
	@RequestMapping("/inList")
	public String reportFileList() {
		return "/bus/reportList";
	}
	@ResponseBody
	@RequestMapping("/list")
	public  Map<String,Object> list(@RequestParam(value = "limit",required = true)Integer limit,
			@RequestParam(value = "offset",required = true)Integer offset){
		System.out.println(limit+"***************"+offset);
		PageHelper.startPage(offset, limit);//物理分页
		List<ReportFile> list=reportFileService.list();//获取所有用户数据
		PageInfo<ReportFile> pageInfo=new PageInfo<ReportFile>(list,limit);
		Long total=pageInfo.getTotal();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("data",list);
		resultMap.put("total", total);
		//System.out.println("*************************:"+list);
		return resultMap;
	}
	/**
	 * 增加 ajax方式
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addReportFile",method = RequestMethod.POST)
	public Map<String, Object> addReportFile(HttpServletRequest request){
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String type=request.getParameter("type");
		String version=request.getParameter("version");
		String devDate=request.getParameter("devDate");
		String devHum=request.getParameter("devHum");
		String fb=request.getParameter("fb");
		String mk=request.getParameter("mk");
		String urlSrc=request.getParameter("urlSrc");
		ReportFile reportFile=new ReportFile(name, type, version, devDate, devHum, fb, mk, urlSrc);
		 if(!id.equals("add")) {
			 reportFile.setId(Integer.parseInt(id));
		 }
		reportFileService.saveOrUpdate(reportFile);//新增用户
		
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("msg", "success");
		return map;
	}
	/*
	 * 通过id 获取数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/getReportFileById",method = RequestMethod.GET)
	public Map<String,Object> getReportFileById(Integer id){
		//System.out.println("***************************"+id);
		ReportFile reportFile=reportFileService.getById(id);
		Map<String,Object>map=new HashMap<String, Object>();
		//System.out.println("***************************"+reportFile);
		map.put("msg", reportFile);
		return map;
	}
	/**
	 * 通过id删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/delReportFileByids",method = RequestMethod.GET)
	public Map<String,Object> delReportFileByids(String ids){
//		System.out.println("******************************:"+ids);
		String[] roles=ids.split(",");
		for(int i=0;i<roles.length;i++) {
			reportFileService.removeById(roles[i]);//通过id删除用户
		}
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("msg", "success");
		return map;
	}
}

