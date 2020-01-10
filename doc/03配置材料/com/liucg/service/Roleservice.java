package com.liucg.service;

import com.liucg.pojo.Role;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liucg
 * @since 2019-12-14
 */
public interface Roleservice extends IService<Role> {
	//获取用户角色
	public List<Map<String,Object>> getRoleByUserId(Integer userId);
}
