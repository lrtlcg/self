package com.liucg.controller;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liucg.until.Access_token;
import com.liucg.until.HttpUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/bd")
@Slf4j
public class BaiduImgController {
	/**
    * 获取百度AI应用数据.
    *
    * @return message
    */
   @CrossOrigin
   @RequestMapping(value = "/baidu1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public Map getAisss() {
       Map<String,Object> map1 = new HashMap<>();
       // 获取token
       String accessToken = Access_token.getAuth();
       // 通用识别url
       String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
       try {
           //读取本地图片输入流
           FileInputStream inputStream = new FileInputStream("/home/lcg/code/ch1.jpg");
           String base = Base64.encodeBase64String(IOUtils.toByteArray(inputStream));
           log.info(base);
           String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(base, "UTF-8");
           /**
            * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            */
//           String accessToken = "#####调用鉴权接口获取的token#####";
           String result = HttpUtil.post(otherHost, accessToken, params);
           JSONObject jsonObject = JSONObject.parseObject(result);
           JSONArray words_result = (JSONArray) jsonObject.get("words_result");
           JSONObject o = (JSONObject)words_result.get(0);
           map1.put("lincens",o.get("words"));
           System.out.println(result);
       } catch (Exception e) {
           e.printStackTrace();
       }

       return map1;
   }
}
