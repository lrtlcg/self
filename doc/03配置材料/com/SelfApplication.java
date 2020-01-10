package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = {"com.liucg.dao"})
@MapperScan(basePackages = {"com.liucg.dao"})
public class SelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfApplication.class, args);
	}

}
