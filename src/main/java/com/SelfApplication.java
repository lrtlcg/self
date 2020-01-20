package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

//多数据源,采用aop切入,动态数据源URL配置,会在dynamic下(ylm),因此需要将spring默认的排除在外
@SpringBootApplication(exclude= {DruidDataSourceAutoConfigure.class})
@MapperScan(basePackages = {"com.liucg.dao"})
public class SelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfApplication.class, args);
	}

}
