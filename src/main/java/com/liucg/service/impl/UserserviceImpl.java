package com.liucg.service.impl;

import com.liucg.pojo.User;
import com.liucg.dao.UserMapper;
import com.liucg.service.Userservice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liucg
 * @since 2019-12-12
 */
@Service
public class UserserviceImpl extends ServiceImpl<UserMapper, User> implements Userservice {
	@Autowired
	UserMapper userMapper;
	/*
	 * 生成帐号
	 * */
	public String accNum() {
		// TODO Auto-generated method stub
		String [] strNow= new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString().split("-");
		String year=strNow[0];
		String month=strNow[1];
		String day=strNow[2];
		String maxNum=maxNum();
		if(maxNum==null) {
			maxNum=year+month+day+"000";//新流水
		}
		Long maxN=Long.valueOf(maxNum);//流水号加1
		maxN ++;
		return maxN.toString();
	}
	//获取当日最大帐号流水号
	public String maxNum() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime startDateNow = LocalDateTime.now();
		String startDate = startDateNow.format(formatter);
		LocalDateTime endDateNow = LocalDateTime.now().plusDays(1);//明天
		String endDate = endDateNow.format(formatter);
		String maxNum=userMapper.getAccount(startDate, endDate);
		return maxNum;
	}
	@Override
	public List<User> ListByParmarm(String search) {
		// TODO Auto-generated method stub
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&"+search);
		return userMapper.ListByParmarm(search);
	}
}
