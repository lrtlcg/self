package com.liucg.service;

import com.liucg.pojo.User;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liucg
 * @since 2019-12-12
 */

public interface Userservice extends IService<User> {
	public String accNum();
	public List<User> ListByParmarm(String search);//bootstrap table 查询
	
}
