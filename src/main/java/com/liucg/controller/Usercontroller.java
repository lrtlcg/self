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
import com.liucg.pojo.User;
import com.liucg.service.Userservice;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liucg
 * @since 2019-12-10
 */
@Controller
@RequestMapping("/user")
public class Usercontroller {
	@Autowired
	private Userservice userService;
	/**
	 * 进入列表页面
	 * @return
	 */
	@RequestMapping("/inUserList")
	public String in_userList(User user) {
		return "/sys/user_list3";
	}

	/**
	 * 用户列表
	 * @param limit 数量
	 * @param offset 页码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listbyParam")
	public Map<String,Object> userList(@RequestParam(value = "limit",required = true)Integer limit,
			@RequestParam(value = "offset",required = true)Integer offset,
			//@RequestParam(value = "filter",required = true)Integer filter,
			@RequestParam(value = "search",required = true)String search){
		System.out.println("**************"+search);
		if (search==null) {
			search="";
		}
		PageHelper.startPage(offset, limit);//物理分页
		List<User> users=userService.ListByParmarm(search);//获取所有用户数据
		PageInfo<User> pageInfo=new PageInfo<User>(users,limit);
		Long total=pageInfo.getTotal();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("data",users);
		resultMap.put("total", total);
		return resultMap;
	}
	/**
	 * 用户列表
	 * @param limit 数量
	 * @param offset 页码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> userList(@RequestParam(value = "limit",required = true)Integer limit,
			@RequestParam(value = "offset",required = true)Integer offset){
		PageHelper.startPage(offset, limit);//物理分页
		List<User> users=userService.list();//获取所有用户数据
		PageInfo<User> pageInfo=new PageInfo<User>(users,limit);
		Long total=pageInfo.getTotal();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("data",users);
		resultMap.put("total", total);
		return resultMap;
	}
	/**
	 * 增加用户(通过form表单)
	 * @param id
	 * @param map
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)
	public String add_user_DB(String id,ModelMap map,User user) {
		String acc = userService.accNum();//设置流水号用户名称
		user.setAcc(acc);
		user.setCreateDate(LocalDateTime.now());
		// 设置初始密码为帐号,并进行加密
		String p1String = DigestUtils.md5DigestAsHex(acc.getBytes());
		user.setPwd(p1String);//设置密码
		user.setStatus("启用");
		userService.save(user);//新增用户
		return "redirect:/user/inUserList";
	}
	/**
	 * 增加用户 ajax方式
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addUser_ajax",method = RequestMethod.POST)
	public Map<String, Object> add_user_ajax(HttpServletRequest request){
		//System.out.println("success");
		String name=request.getParameter("name");
		String status=request.getParameter("status");
		//System.out.println("-------------------------------status:"+status);
		User user=new User();
		user.setName(name);
		user.setStatus(status);
		String acc = userService.accNum();//设置流水号用户名称
		user.setAcc(acc);
		user.setCreateDate(LocalDateTime.now());
		// 设置初始密码为帐号,并进行加密
		String p1String = DigestUtils.md5DigestAsHex(acc.getBytes());
		user.setPwd(p1String);//设置密码
		//user.setStatus("启用");
		userService.save(user);//新增用户
		
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
	@RequestMapping(value = "/getUserByid",method = RequestMethod.GET)
	public Map<String,Object> getUserByid(String userid){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		Long id=Long.parseLong(userid);
		User user=userService.getById(id);
		resultMap.put("user", user);
		return resultMap;
	}
	/**
	 * 更新用户
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update_User",method = RequestMethod.POST)
	public Map<String, Object> update_user(HttpServletRequest request){
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		Integer id=Integer.parseInt(request.getParameter("id"));
		String status=request.getParameter("status");
		User user=new User();
		user.setId(id);
		user.setName(name);
		String p1String = DigestUtils.md5DigestAsHex(pwd.getBytes());
		user.setPwd(p1String);
		user.setStatus(status);
		userService.updateById(user);
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("msg", "success");
		return map;
	}
	/**
	 * 通过id删除用户
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/deleteUserByids",method = RequestMethod.GET)
	public Map<String,Object> deleteUserByids(String ids){
//		System.out.println("******************************:"+ids);
		String[] users=ids.split(",");
		for(int i=0;i<users.length;i++) {
			userService.removeById(users[i]);//通过id删除用户
		}
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("msg", "success");
		return map;
	}
}

