package com.liucg.until;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 百度图片处理类
 * @author lcg
 *
 */
@Component
@Slf4j
public class BaiduImg {
	 public Map getAisss(InputStream inputStream) {
	       Map<String,Object> map1 = new HashMap<>();
	       // 获取token
	       String accessToken = Access_token.getAuth();
	       // 通用识别url
	       String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
	       try {
	           //读取本地图片输入流
//	           FileInputStream inputStream = new FileInputStream("/home/lcg/code/ch1.jpg");
	           String base = Base64.encodeBase64String(IOUtils.toByteArray(inputStream));
	           //log.info(base);
	           String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(base, "UTF-8");
	           /**
	            * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            */
//	           String accessToken = "#####调用鉴权接口获取的token#####";
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
	 /**
	     * 获取网络图片流
	     * 
	     * @param url
	     * @return
	     */
	    public InputStream getImageStream(String url) {
	        try {
	            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	            connection.setReadTimeout(5000);
	            connection.setConnectTimeout(5000);
	            connection.setRequestMethod("GET");
	            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                InputStream inputStream = connection.getInputStream();
	                return inputStream;
	            }
	        } catch (IOException e) {
	            System.out.println("获取网络图片出现异常，图片路径为：" + url);
	            e.printStackTrace();
	        }
	        return null;
	    }

}
