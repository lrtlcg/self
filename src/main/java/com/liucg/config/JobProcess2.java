package com.liucg.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liucg.pojo.GoodMsg;
import com.liucg.service.GoodMsgservice;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
/**
 * 亚马逊页面爬虫
 * @author lcg
 *
 */
@Component
public class JobProcess2 implements PageProcessor {
	@Autowired
	GoodMsgservice goodMsgservice;//页面抽取后的对象服务方法
	//定义
	private Site site=Site.me().
			setCharset("gbk").
			setTimeOut(10*1000).//超时时间
			setRetrySleepTime(3000).setRetryTimes(3);
	@Override
	//页面地址解析,用于获取单纯的页面
	public void process(Page page) {
		// TODO Auto-generated method stub
		//这里是css 解析规则
		List<Selectable> list=page.getHtml().css("div.sg-col-inner").nodes();
		if(list.size()==0) {
			this.saveWebMsg(page);// 如果页面不含有其它链接,进入页面数据保存方法
		}else {
			//存在url
			for(Selectable selectable:list) {
				//"http://www.amazon.com/"+
				String webUrl=selectable.links().toString();
				page.addTargetRequest(webUrl);//进入url地址页面
			}
			//获取分页信息
			String pageWebUrl=page.getHtml().css("ul.a-pagination li.a-disabled").nodes().get(1).links().toString();
			page.addTargetRequest(pageWebUrl);//进入url地址页(下一页)
		}

	}
	//解析页面,保存数据进入数据库
	private void saveWebMsg(Page page) {
		GoodMsg goodMsg=new GoodMsg();
		Html html = page.getHtml();
		//goodMsg.setName(String.valueOf(html.css("id#titleSection span#productTitle","text")));
		goodMsg.setName(html.xpath("//span[@id='productTitle']/text()").toString());
//		goodMsg.setPrice(String.valueOf(html.css("id#price span#priceblock_ourprice","text")));
		goodMsg.setPrice(html.xpath("//span[@id='priceblock_ourprice']/text()").toString());
		System.out.println(goodMsg);
		//goodMsgservice.save(goodMsg);
		
	}
	// 单例模式,获取前面定义的对象
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

}
