package com.liucg.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liucg.pojo.ReportUser;
import com.liucg.pojo.User;
import com.liucg.pojo.UserRole;
import com.liucg.service.ReportUserservice;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liucg
 * @since 2019-12-19
 */
@Controller
@RequestMapping("/reportUser")
public class ReportUsercontroller {
	@Autowired
	ReportUserservice reportUserService;
	/**
	 * 获取用户
	 * @param limit
	 * @param offset
	 * @param reportId  报表id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listByReportId")
	public Map<String,Object> reportUserList(@RequestParam(value = "limit",required = true)Integer limit,
			@RequestParam(value = "offset",required = true)Integer offset,Integer reportId){
		//System.out.println(filter+"**************"+search);
		PageHelper.startPage(offset, limit);//物理分页
		List<Map<String,Object>> pojo=reportUserService.getUserByReportId(reportId);//获取所有用户数据
		PageInfo<Map<String,Object>> pageInfo=new PageInfo<Map<String,Object>>(pojo,limit);
		Long total=pageInfo.getTotal();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("data",pojo);
		resultMap.put("total", total);
		return resultMap;
	}
	/**
	 * 保存报表用户权限
	 * @param reportId
	 * @param userids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/setReportUser",method = RequestMethod.GET)
	public Map<String,Object> setReportUser(String reportId,String userids){
		String[] id2s=userids.split(",");//用户
		Integer id1=Integer.parseInt(reportId);//报表id
		
		List<ReportUser> list=new ArrayList<ReportUser>();
		for(int i=0;i<id2s.length;i++) {
			ReportUser ur=new ReportUser();
			ur.setUserId(Integer.parseInt(id2s[i]));//用户
			ur.setReportId(id1);;
			list.add(ur);
		}	
		boolean issava=reportUserService.saveBatch(list);
		Map<String,Object> map=new HashMap<String, Object>();
		if(issava) {
			map.put("msg", "success");}
		else {
			map.put("msg", "error");
		}
		return map;
	}
	/**
	 * 删除
	 * @param reportUserIds 主键
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delByid",method = RequestMethod.GET)
	public Map<String,Object> delByid(String reportUserIds){
		//System.out.println("**************************************"+reportUserIds);
		String[] id2s=reportUserIds.split(",");
		for(int i=0;i<id2s.length;i++) {
			reportUserService.removeById(Integer.parseInt(id2s[i]));
		}
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("msg", "success");
		return map;
	}
}

