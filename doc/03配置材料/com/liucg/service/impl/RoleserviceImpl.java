package com.liucg.service.impl;

import com.liucg.pojo.Role;
import com.liucg.dao.RoleMapper;
import com.liucg.service.Roleservice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liucg
 * @since 2019-12-14
 */
@Service
public class RoleserviceImpl extends ServiceImpl<RoleMapper, Role> implements Roleservice {
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Map<String, Object>> getRoleByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleByUserId(userId);
	}
}
