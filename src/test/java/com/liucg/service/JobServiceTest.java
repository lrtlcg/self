package com.liucg.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liucg.config.JobProcess2;
import com.liucg.config.JobProcesser;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Spider;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class JobServiceTest {
	@Autowired
	JobProcesser jobProcesser;
	@Autowired
	JobProcess2 jobProcesser2;
	@Test
	public void handel() {
		String urls = "https://search.51job.com/list/090200,000000,0000,38,9,99,JAVA,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
		Spider.create(jobProcesser).
			addUrl(urls).
			thread(5).
			run();
	}
	@Test
	public void handel2() {
		String urls = "https://www.amazon.com/s?i=specialty-aps&bbn=16225006011&rh=n%3A%2116225006011%2Cn%3A11060451&ref=nav_em_T1_0_4_NaN_2__nav_desktop_sa_intl_skin_care";
		Spider.create(jobProcesser2).
			addUrl(urls).
			thread(5).
			run();
	}
}
