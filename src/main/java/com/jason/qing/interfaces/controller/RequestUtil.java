package com.jason.qing.interfaces.controller;


import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 請求工具類
 * @author JasonTan
 * @since 2013年5月6日 
 */
public class RequestUtil {

	/**
	 * 獲得請求路徑
	 * @param request
	 * @return String
	 * @author JasonTan
	 * @since 2013年5月6日  
	*/ 
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		if (requestPath.endsWith("/")) {// 去掉其他參數
			requestPath = requestPath.substring(0, requestPath.lastIndexOf('/'));
		}
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉專案路徑
		return requestPath;
	}
	
	public static String getServicePath(HttpServletRequest request){
		StringBuilder sb = new StringBuilder("http://");
		String serverName = request.getServerName();
		sb.append(serverName);
		int port = request.getServerPort();
		if(port != 80){
			sb.append(":").append(port);
		}
		String content = request.getContextPath();
		sb.append(content);
		return sb.toString();
	}
	/**
	 * 根據url得到最簡單url 比如 去掉'?',最後的'/'
	 * @param urlStr
	 * @return String
	 * @author JasonTan
	 * @since 2013年5月6日  
	*/ 
	public static String getReadyPath(String urlStr){
        String url = urlStr;
		if(url.indexOf('?')>0){
			url = url.substring(0, url.indexOf("?"));
		}
		if (url.endsWith("/")) {// 去掉其他參數
			url = url.substring(0, url.lastIndexOf('/'));
		}
		//url = url.substring(request.getContextPath().length());// 去掉專案路徑
		return url;
	}

}

