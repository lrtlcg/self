package com.liucg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liucg.pojo.Menu;
import com.liucg.pojo.User;
import com.liucg.service.Menuservice;
import com.liucg.service.Userservice;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liucg
 * @since 2019-12-14
 */
@Controller
@RequestMapping("/main")
public class Maincontroller {
	@Autowired
	private Userservice userService;
	@Autowired
	private Menuservice menuService;
	/**
	 * 进入登录页面
	 * @param user
	 * @param map
	 * @return
	 */
	@RequestMapping("/index")
	public String in_userList(User user,ModelMap map) {
		//List<Menu> items=menuService.getTreeMenu("20191214007");
		//map.put("listMenu", items); 
		return "/sys/login";
	}
	/**
	 * 跳转至成功页面
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("/IndexMian")
	public String indexMain(ModelMap map,HttpServletRequest request) {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		if(user!=null) {
			List<Menu> items=menuService.getTreeMenu(user.getAcc());
			map.put("listMenu", items);
			return "/sys/main";
		}else {
			return "redirect:/main/index";
		}
		
	}
	/**
	 * 登录过程判断
	 * @param request
	 * @param maps
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(HttpServletRequest request,ModelMap maps,User user) {
		String ac=request.getParameter("acc");
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		Map<String,String> map=new HashMap<String, String>();
		map.put("acc", ac);
		queryWrapper.allEq(map);
		User users=userService.getOne(queryWrapper);
		if(users !=null) {
			String pwd=request.getParameter("pwd");
			String p1String = DigestUtils.md5DigestAsHex(pwd.getBytes());
			if(users.getPwd().equals(p1String)) {
				HttpSession session=request.getSession();
				session.setAttribute("user", users);
				return "redirect:/main/IndexMian";
			}else {
				request.setAttribute("meg", "密码错误");
				return "/sys/login";
			}
		}else {
			request.setAttribute("meg", "用户名错误");
			return  "/sys/login";
		}		
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,User user) {
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		return "/sys/login";
	}
}

