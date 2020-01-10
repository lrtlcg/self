package com.liucg.dao;

import com.liucg.pojo.Menu;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liucg
 * @since 2019-12-15
 */
public interface MenuMapper extends BaseMapper<Menu> {
	/**
	 * 根据角色id 获取菜单
	 * @param roleId
	 * @return
	 */
	@Select("SELECT sys_menu.id,sys_menu.pid,sys_menu.name,sys_menu.status,IFNULL(sys_roleMenu.id,0) AS checks FROM sys_menu LEFT JOIN sys_roleMenu ON sys_menu.id=sys_roleMenu.menuid  AND sys_roleMenu.roleid=#{roleId}")
	public List<Map<String,Object>> getMenuByUserId(Integer roleId);
	/*
	 * 根据帐号获取菜单
	 * */
	@Select("select a.* from (select distinct sys_menu.id as id,sys_menu.pid,sys_menu.name,sys_menu.urlsrc,sys_menu.urlico,sys_menu.urllevel,sys_menu.status  \r\n" + 
			"from sys_user \r\n" + 
			"LEFT JOIN sys_userRole on sys_user.id=sys_userRole.userid \r\n" + 
			"left join sys_role ON sys_role.id=sys_userRole.roleid\r\n" + 
			"left join sys_roleMenu on sys_roleMenu.roleId=sys_role.id\r\n" + 
			"left join sys_menu on sys_menu.id=sys_roleMenu.menuId\r\n" + 
			"where sys_user.acc=#{acc} \r\n" + 
			"and sys_user.status='启用')a where a.id is not null order by a.id \r\n")
			//+ "and sys_user.id<>'1' ")
	public List<Menu> getMenuTree(String acc);
}
