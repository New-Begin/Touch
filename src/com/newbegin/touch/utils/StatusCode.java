package com.newbegin.touch.utils;

/**
 * 为了统一规范服务器与客户端之间交互状态码。建议在服务器和客户端都新建一个 StatusCode 的类，
 * 定义其属性为public static final int类型数据，属性名字采用直观的命名方式
 * @author new begin
 *
 */
public class StatusCode {
	
	//状态码，用于标识函数执行结果
	public static final int SUCCESS = 0;
	public static final int EMAIL_ALREADY_REGISTED = 1;
	public static final int USER_ALREADY_REGISTED = 2;
	public static final int PWD_ERROR = 3;
	public static final int ID_NOT_EXSIT = 4;
	 
	//动作名，用于标识客户端传输数据的意义
	public static final String ACTION_LOGIN = "login";

}
