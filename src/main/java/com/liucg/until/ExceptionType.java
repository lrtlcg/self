package com.liucg.until;

import javax.servlet.http.HttpServletRequest;
/**
 * 判断异常是不是Ajax请求
 * @author lcg
 *
 */

public class ExceptionType {
	public static boolean isAjax(HttpServletRequest httpRequest){
		return  (httpRequest.getHeader("X-Requested-With") != null  
					&& "XMLHttpRequest"
						.equals( httpRequest.getHeader("X-Requested-With").toString()) );
	}
}
