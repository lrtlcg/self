package com.liucg.service;

import com.liucg.pojo.Menu;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liucg
 * @since 2019-12-15
 */
public interface Menuservice extends IService<Menu> {
	//获取用户角色
		public List<Map<String,Object>> getMenuByRoleId(Integer roleId);
		//获取用户树菜单
		public List<Menu> getTreeMenu(String acc);
}
