package com.liucg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liucg.pojo.Menu;
import com.liucg.pojo.Role;
import com.liucg.service.Menuservice;
import com.liucg.service.WebSocket;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liucg
 * @since 2019-12-15
 */
@Controller
@RequestMapping("/menu")
public class Menucontroller {
	@Autowired
	private Menuservice menuService;
	@Autowired
	private WebSocket webSocket;

	/**
	 * 进入菜单
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/inMenuList")
	public String inMenuList(Role role) {
		return "/sys/menu_list";
	}
	/**
	 * 测试,可以放弃
	 * @param map
	 * @return
	 */
	@RequestMapping("/intree")
	public String intree(ModelMap map) {
		List<Menu> items=menuService.getTreeMenu("20191214007");
		map.put("listMenu", items);
		return "/test/tree";
	}
	/**
	 * 获取菜单列表
	 * 原始,可以放弃
	 * @param limit
	 * @param offset
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> menuList(@RequestParam(value = "limit", required = true) Integer limit,
			@RequestParam(value = "offset", required = true) Integer offset) {
		PageHelper.startPage(offset, limit);// 物理分页
		List<Menu> list = menuService.list();// 获取所有用户数据
		PageInfo<Menu> pageInfo = new PageInfo<Menu>(list, limit);
		Long total = pageInfo.getTotal();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", list);
		resultMap.put("total", total);
		System.out.println("*************************:" + list);
		return resultMap;
	}

	/**
	 * 返回树结构1
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/treeList")
	public List<Menu> menuTreeList() {
		List<Menu> list = menuService.list();
		return list;
	}
	/**
	 * 返回树结构2
	 * @param roleid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listByRoleId")
	public List<Map<String, Object>> listByRoleId(String roleid) {
		List<Map<String, Object>> memus = menuService.getMenuByRoleId(Integer.parseInt(roleid));// 获取所有用户数据
		//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+memus);
		return memus;
	}
	/**
	 * 返回树结构3
	 * @param roleid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listByAcc")
	public List<Menu> listByAcc() {
		List<Menu> items=menuService.getTreeMenu("20191214007");
		return items;
	}

	/**
	 * 增加用户 ajax方式
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addmenu_ajax", method = RequestMethod.POST)
	public Map<String, Object> addMenuAjax(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pid = request.getParameter("pid");
		String name = request.getParameter("name");
		String urlsrc = request.getParameter("urlsrc");
		String urlico = request.getParameter("urlico");
		String urllevel = request.getParameter("urllevel");
		String status = request.getParameter("status");
		Menu pojo = new Menu();
		if (!id.equals("add")) {
			pojo.setId(Integer.parseInt(id));
		}else {
			pojo.setPid(Integer.parseInt(pid));
		}		
		pojo.setName(name);
		pojo.setStatus(status);
		pojo.setUrlsrc(urlsrc);
		pojo.setUrlico(urlico);
		pojo.setUrllevel(urllevel);
		menuService.saveOrUpdate(pojo);// 新增
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "success");
		webSocket.sendMessage("增加成功");
		return map;
	}

	/**
	 * 编辑
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMenuByid", method = RequestMethod.GET)
	public Map<String, Object> getMenuByid(String menuid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Long id = Long.parseLong(menuid);
		Menu menu = menuService.getById(id);
		resultMap.put("menu", menu);
		return resultMap;
	}

	/**
	 * 通过id删除
	 * 
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deletemenuByids", method = RequestMethod.GET)
	public Map<String, Object> deletemenuByids(String ids) {
//		System.out.println("******************************:"+ids);
		String[] roles = ids.split(",");
		for (int i = 0; i < roles.length; i++) {
			menuService.removeById(roles[i]);// 通过id删除用户
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "success");
		return map;
	}

	/**
	 * 角色,用户设置页面
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/inUserRole")
	public String in_userRoleList() {
		return "/sys/userRole_list";
	}

	@RequestMapping("/inMenuRole")
	public String inMenuRole() {
		return "/sys/roleMenu_list";
	}
}
