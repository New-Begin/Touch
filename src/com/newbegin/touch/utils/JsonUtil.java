package com.newbegin.touch.utils;

public class JsonUtil {

	/**
	 * 用户名密码封装为json
	 * @param user
	 * @param pwd
	 * @return json
	 */
	public static String UserPwd2Json(String user, String pwd) {
		// refer:{"action":"login", "firstName":"John" , "lastName":"Doe" }
		// Java转义字符
//		String operType = "login";
		String str = "{\"action\":\"" + StatusCode.ACTION_LOGIN + "\",\"user\":\"" + user
				+ "\" , \"pwd\":\"" + pwd + "\" }";
		return str;
	}
}
