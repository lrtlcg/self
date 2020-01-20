package com.liucg.handler;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liucg.builder.TextBuilder;
import com.liucg.service.Carservice;
import com.liucg.until.BaiduImg;
import com.liucg.until.JsonUtils;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@Slf4j
public class MsgHandler extends AbstractHandler {
	@Autowired
	BaiduImg baiduImg;
	@Autowired
	Carservice carService;

	@Override
	public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
			WxSessionManager sessionManager) {

//		if (!wxMessage.getMsgType().equals(WxConsts.XmlMsgType.EVENT)) {
//			// TODO 可以选择将消息保存到本地
//		}

		// TODO 组装回复消息
//		String content = "收到信息内容：" + JsonUtils.toJson(wxMessage.getContent());
		//log.info("收到图片的信息,{}", wxMessage.getPicUrl());
		//log.info("消息类型,{}",wxMessage.getMsgType());
		String msgType=wxMessage.getMsgType();//消息类型
		String assis=""; //车牌号
		String msg="";//反馈信息
		if(msgType.equals("text")) {
			assis=wxMessage.getContent();
			msg=carService.getPlateMsg(assis);
			if(msg.isEmpty()) {
				msg="临时车辆,请联系行政部!";
			}
		}else if(msgType.equals("image")) {
			//调用百度智能云接口,这里是图片识别
			InputStream inputStream = baiduImg.getImageStream(wxMessage.getPicUrl());
			//获取结果-->从map中获取,在map中定义了key是licence(自定义)
			assis = (String) baiduImg.getAisss(inputStream).get("lincens");
			msg=carService.getPlateMsg(assis);
			if(msg.isEmpty()) {
				msg="临时车辆,请联系行政部!";
			}
		}else {
			msg="车辆信息有误,请联系行政部!";
		}
			
		//log.info("车牌号:{}", msg);

		return new TextBuilder().build(msg, wxMessage, cpService);

	}

}
