package com.newbegin.touch.utils;

public class JsonUtil {

	/**
	 * 用户名密码封装为json
	 * @param user
	 * @param pwd
	 * @return json
	 */
	public static String UserPwd2Json(String user, String pwd, String operType) {
		// refer:{"action":"login", "firstName":"John" , "lastName":"Doe" }
		// Java转义字符
		String str = "{\"action\":\"" + operType + "\",\"user\":\"" + user
				+ "\" , \"pwd\":\"" + pwd + "\" }";
		return str;
	}
}
