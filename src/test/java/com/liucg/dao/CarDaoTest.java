package com.liucg.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.sql.visitor.functions.Char;

import lombok.extern.slf4j.Slf4j;
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@RunWith(SpringRunner.class)
//多数据源时要加:webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//原因:websocket是需要依赖tomcat等容器的启动。所以在测试过程中我们要真正的启动一个tomcat作为容器。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class CarDaoTest {
	@Autowired
	CarMapper carDao;
	@Test
	public void getList() {
		List<Map<String,Object>> list=carDao.getList("865");
		log.info("{}",list);
	}
	@Test
	public void getList2() {
//		List<Map<String,Object>> list=carDao.getList2("Z97");
		List<Map<String,Object>> list=carDao.getList2("865");
		log.info("{}",list);
	}
	@Test
	public void testLog() {
		log.debug("sdsd");
		log.info("你好,中国");
		log.error("中国.{}","怒了");
	}

}
