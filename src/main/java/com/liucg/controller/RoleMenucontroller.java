package com.liucg.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liucg.pojo.Role;
import com.liucg.pojo.RoleMenu;
import com.liucg.pojo.UserRole;
import com.liucg.service.RoleMenuservice;

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
@RequestMapping("/roleMenu")
public class RoleMenucontroller {
	@Autowired
	private RoleMenuservice rmService;
	@ResponseBody
	@RequestMapping(value = "/setRoleMenuId",method = RequestMethod.GET)
	public Map<String,Object> setUserRoleId(String roleId,String menuIds){
		String[] menus=menuIds.split(",");
		Integer roleid=Integer.parseInt(roleId);
		QueryWrapper<RoleMenu> queryWrapper=new QueryWrapper<RoleMenu>();
		Map<String,Integer> muser=new HashMap<String, Integer>();
		muser.put("roleId", roleid);
		queryWrapper.allEq(muser);
		rmService.remove(queryWrapper);
		List<RoleMenu> list=new ArrayList<RoleMenu>();
		for(int i=0;i<menus.length;i++) {
			RoleMenu ur=new RoleMenu();
			ur.setMenuId(Integer.parseInt(menus[i]));
			ur.setRoleId(roleid);
			list.add(ur);
		}	
		boolean issava=rmService.saveBatch(list);
		Map<String,Object> map=new HashMap<String, Object>();
		if(issava) {
			map.put("msg", "success");}
		else {
			map.put("msg", "error");
		}
		return map;
	}
}

