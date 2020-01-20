package com.liucg.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liucg.dao.CarDaoTest;
import com.liucg.dao.CarMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class CarServiceTest {
	@Autowired
	Carservice carservice;
	@Test
	public void getList() {
		List<Map<String,Object>> list=carservice.getList("content");
		log.info("{}",list);
	}
	@Test
	public void getListAll() {
		List<Map<String,Object>> list=carservice.getList("865");
		log.info("list数据是,{}",list);
		for(Map map:list) {
			String  phone=(String) map.get("Phone");
			String name=(String) map.get("Name");
			String plate=(String) map.get("PlateID");
			if(phone.isEmpty()) {
				phone="";
				name="临时车辆";
			}
			System.out.println(name+"======"+plate+"============="+phone);
		}
	}
	@Test
	public void getString() {
		String msg=carservice.getPlateMsg("865");
		//log.info("车辆信息:{}",msg);
		System.out.println(msg);
	}
}
