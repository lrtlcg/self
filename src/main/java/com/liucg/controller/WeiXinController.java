package com.liucg.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutTextMessage;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.message.WxCpMessageHandler;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;

@Controller
//企业微信
@RequestMapping("/weixin")
@Slf4j
public class WeiXinController {
	/**
	 * 企业微信url认证
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/checkUrl")
	public void checkUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 微信加密签名
		String msg_signature = request.getParameter("msg_signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echoStr = request.getParameter("echostr");
		// 回调key值
		String contacts_token = "weixin";// url认证Token
		String contacts_encodingaeskey = "dSHGgJ7rTYNlK3sC9X4jVjTSAgWfHQT3ViauduU8P78";// url认证EncodingAESKey
		String corpId = "wxc5771e1e819ee85b";// 企业号id即appid
		String Secret = "614FFRtsLPD4tpRv_9d6zR1ATQ-rjsLVGX0hKbHOXzs";
		Integer agaentID = 1000011;

		WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
		config.setCorpId(corpId); // 设置微信企业号的appid
		config.setCorpSecret(Secret); // 设置微信企业号的app corpSecret
		config.setAgentId(agaentID); // 设置微信企业号应用ID
		config.setToken(contacts_token); // 设置微信企业号应用的token
		config.setAesKey(contacts_encodingaeskey); // 设置微信企业号应用的EncodingAESKey

		WxCpServiceImpl wxCpService = new WxCpServiceImpl();
		wxCpService.setWxCpConfigStorage(config);

		WxCpMessageHandler handler = new WxCpMessageHandler() {
			public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context,
					WxCpService wxCpService) {
				WxCpXmlOutTextMessage m = WxCpXmlOutMessage.TEXT().content("测试加密消息").fromUser(wxMessage.getToUserName())
						.toUser(wxMessage.getFromUserName()).build();
				return m;
			}

			@Override
			public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context,
					WxCpService wxCpService, WxSessionManager sessionManager) throws WxErrorException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		WxCpMessageRouter wxCpMessageRouter = new WxCpMessageRouter(wxCpService);
		wxCpMessageRouter.rule().async(false).content("哈哈") // 拦截内容为“哈哈”的消息
				.handler(handler).end();

		if (!wxCpService.checkSignature(msg_signature, timestamp, nonce, echoStr)) {
			// 消息签名不正确，说明不是公众平台发过来的消息
			response.getWriter().println("非法请求");
			return;
		}
		WxCpConfigStorage wxCpConfigStorage = wxCpService.getWxCpConfigStorage();
		log.info("****,{}", wxCpConfigStorage);
		WxCpCryptUtil cryptUtil = new WxCpCryptUtil(wxCpConfigStorage);
		String plainText = cryptUtil.decrypt(echoStr);
		// 说明是一个仅仅用来验证的请求，回显echostr
		response.getWriter().println(plainText);

		WxCpXmlMessage inMessage = WxCpXmlMessage.fromEncryptedXml(request.getInputStream(),
				wxCpService.getWxCpConfigStorage(), timestamp, nonce, msg_signature);
		WxCpXmlOutMessage outMessage = wxCpMessageRouter.route(inMessage);
		if (outMessage != null) {
			response.getWriter().write(outMessage.toEncryptedXml(wxCpService.getWxCpConfigStorage()));
		}

		return;
	}

}
