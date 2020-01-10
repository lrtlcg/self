package com.liucg.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liucg.pojo.User;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserDaoTest {
	@Autowired
	UserMapper userDao;
	@Test
	public void getList() {
	User user=(User)userDao.selectById(1);
	System.out.println(user.getName());
	}
	@Test
	public void testLog() {
		log.debug("sdsd");
		log.info("你好,中国");
		log.error("中国.{}","怒了");
	}

}
