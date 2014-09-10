package com.newbegin.touch.utils;

public class HandleInfo {

	public int login(String user, String pwd){
		JsonUtil ju = new JsonUtil();
		SocketConnect sc = new SocketConnect();
		sc.sendInfo(ju.UserPwd2Json(user, pwd));
		return 0;
	}
}
