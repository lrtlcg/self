package com.liucg.controller;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liucg.pojo.Role;
import com.liucg.pojo.User;
import com.liucg.service.Roleservice;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liucg
 * @since 2019-12-14
 */
@Controller
@RequestMapping("/role")
public class Rolecontroller {
	@Autowired
	private Roleservice roleService;
	/**
	 * 进入列表页面
	 * @return
	 */
	@RequestMapping("/inroleList")
	public String in_userList(Role role) {
		return "/sys/role_list";
	}
	/**
	 * 进入角色列表
	 * @param limit 数量
	 * @param offset 页码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> userList(@RequestParam(value = "limit",required = true)Integer limit,
			@RequestParam(value = "offset",required = true)Integer offset){
		PageHelper.startPage(offset, limit);//物理分页
		List<Role> roles=roleService.list();//获取所有用户数据
		PageInfo<Role> pageInfo=new PageInfo<Role>(roles,limit);
		Long total=pageInfo.getTotal();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("data",roles);
		resultMap.put("total", total);
		System.out.println("*************************:"+roles);
		return resultMap;
	}
	/**
	 * 进入角色列表
	 * @param limit 数量
	 * @param offset 页码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listByuserId")
	public Map<String,Object> listByuserId(@RequestParam(value = "limit",required = true)Integer limit,
			@RequestParam(value = "offset",required = true)Integer offset,String userid){
		PageHelper.startPage(offset, limit);//物理分页
		List<Map<String,Object>> roles=roleService.getRoleByUserId(Integer.parseInt(userid));//获取所有用户数据
		PageInfo<Map<String,Object>> pageInfo=new PageInfo<Map<String,Object> >(roles,limit);
		Long total=pageInfo.getTotal();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("data",roles);
		resultMap.put("total", total);
		System.out.println("*************************:"+roles);
		return resultMap;
	}
	/**
	 * 增加用户 ajax方式
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addRole_ajax",method = RequestMethod.POST)
	public Map<String, Object> add_user_ajax(HttpServletRequest request){
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String status=request.getParameter("status");
		Role role=new Role();
		role.setName(name);
		role.setStatus(status);
		 if(!id.equals("add")) {
			 role.setId(Integer.parseInt(id));
		 }
		roleService.saveOrUpdate(role);//新增用户
		
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("msg", "success");
		return map;
	}
	/**
	 * 通过id获取用户
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoleByid",method = RequestMethod.GET)
	public Map<String,Object> getRoleByid(String roleid){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		Long id=Long.parseLong(roleid);
		Role role=roleService.getById(id);
		resultMap.put("role", role);
		return resultMap;
	}
	/**
	 * 通过id删除用户
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/deleteRoleByids",method = RequestMethod.GET)
	public Map<String,Object> deleteRoleByids(String ids){
//		System.out.println("******************************:"+ids);
		String[] roles=ids.split(",");
		for(int i=0;i<roles.length;i++) {
			roleService.removeById(roles[i]);//通过id删除用户
		}
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("msg", "success");
		return map;
	}
}

