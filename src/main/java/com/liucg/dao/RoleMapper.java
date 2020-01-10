package com.liucg.dao;

import com.liucg.pojo.Role;

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
 * @since 2019-12-14
 */
public interface RoleMapper extends BaseMapper<Role> {
	/**
	 * 获取用户的角色
	 * @return
	 */
	@Select("SELECT sys_role.id,sys_role.name,sys_role.status,IFNULL(sys_userRole.id,0) AS checks FROM sys_role LEFT JOIN sys_userRole ON sys_role.id=sys_userRole.roleid\r\n" + 
			"AND sys_userRole.userid=#{userId}")
	public List<Map<String,Object>> getRoleByUserId(Integer userId);
}
