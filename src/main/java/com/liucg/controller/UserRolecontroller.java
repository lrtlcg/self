package com.liucg.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liucg.pojo.UserRole;
import com.liucg.service.UserRoleservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liucg
 * @since 2019-12-15
 */
@Controller
@RequestMapping("/userRole")
public class UserRolecontroller {
	@Autowired
	private UserRoleservice urService;
	@ResponseBody
	@RequestMapping(value = "/setUserRoleId",method = RequestMethod.GET)
	public Map<String,Object> setUserRoleId(String userId,String roleIds){
		String[] roles=roleIds.split(",");
		Integer userid=Integer.parseInt(userId);
		QueryWrapper<UserRole> queryWrapper=new QueryWrapper<UserRole>();
		Map<String,Integer> muser=new HashMap<String, Integer>();
		muser.put("userid", userid);
		queryWrapper.allEq(muser);
		urService.remove(queryWrapper);
		List<UserRole> list=new ArrayList<UserRole>();
		for(int i=0;i<roles.length;i++) {
			UserRole ur=new UserRole();
			ur.setRoleid(Integer.parseInt(roles[i]));
			ur.setUserid(userid);
			list.add(ur);
		}	
		boolean issava=urService.saveBatch(list);
		Map<String,Object> map=new HashMap<String, Object>();
		if(issava) {
			map.put("msg", "success");}
		else {
			map.put("msg", "error");
		}
		return map;
	}
}

