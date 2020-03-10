package com.liucg.config;

import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.liucg.pojo.Job;
import com.liucg.service.Jobservice;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 爬虫页面解析类
 * 
 * @author lcg
 *
 */
@Component
public class JobProcesser implements PageProcessor {
	private String url = "https://search.51job.com/list/090200,000000,0000,38,9,99,JAVA,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
	@Autowired
	Jobservice jobService;
	// 解析页面
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		// 解析页面 获取招聘信息详情的url地址
		List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();
		// 判断获取到的集合是否为空 如果为空表示这是招聘详情页 如果不为空表示这是列表页
		if (list.size() == 0) {
			// 如果为空 表示这是招聘详情页 解析页面获取招聘信息 保存数据
			this.saveJobInfo(page);
		} else {
			// 如果不为空 解析详情页的url地址 放到任务队伍中
			for (Selectable selectable : list) {
				String jobUrlInfo = selectable.links().toString();
				page.addTargetRequest(jobUrlInfo);// 把获取到的url 放到任务队列中
			}
			// 获取下一页的url
			String bkurl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
			// 把url 放到任务队列中
			page.addTargetRequest(bkurl);
		}
	}

	// 解析页面获取招聘信息 保存数据
	private void saveJobInfo(Page page) {
		// 创建页面对象
		Job job = new Job();
		// 解析页面
		Html html = page.getHtml();
		// 获取数据
		job.setCompanyName(String.valueOf(html.css("div.cn p.cname a", "text")));
		job.setCompanyAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text());
		//job.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());
		job.setJobName(html.css("div.cn h1", "text").toString());
		job.setJobAddr(html.css("div.cn p.msg", "title").toString());
		//job.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
		job.setWebUrl(page.getUrl().toString());
		job.setSalaryMax(Jsoup.parse(html.css("div.cn strong").toString()).text());
		job.setSalaryMin("");
		String time = html.css("div.cn p.msg", "title").toString();
		job.setJobTime(time.substring(time.length() - 8));
		// 这里是保存到数据库
		jobService.save(job);
		System.out.println("******************获取的页面数据是:" + job.toString());
	}

	private Site site=Site.me().
			setCharset("gbk").
			setTimeOut(10*1000).//超时时间
			setRetrySleepTime(3000).setRetryTimes(3);

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

}
