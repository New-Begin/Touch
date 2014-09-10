package com.newbegin.touch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
	private TelephonyManager telephonyManager;//电话号码管理器
	private EditText user;//邮箱账号
	private EditText password;//密码
	private EditText password1;//重复密码
	private Button registerBtn;//注册按钮
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Log.i("init", "oncreate");
		user = (EditText) findViewById(R.id.user);
		password = (EditText) findViewById(R.id.password);
		password1 = (EditText) findViewById(R.id.password1);	
		registerBtn = (Button) findViewById(R.id.registerBtn);	
		
		init();
	}

	private void init(){
		//获取本机电话号码
		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String phoneId = telephonyManager.getLine1Number();
		user.setText(phoneId);
		Log.i("init", "init");
		
		registerBtn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
Log.i("init", "click");
				
				String usrStr = user.getText().toString();
				String pwdStr = password.getText().toString();
				String pwdStr1 = password1.getText().toString();
				
				if(usrStr.equals("")){
					Log.i("init", "usr");
					Toast toast1 = Toast.makeText(getApplicationContext(),
						     "请输入用户名", Toast.LENGTH_LONG);
					toast1.setGravity(Gravity.CENTER, 0, 0);
					toast1.show();
					return;
				}
				else if(pwdStr.equals("")){
					Log.i("init", "pwd");
					Toast toast2 = Toast.makeText(getApplicationContext(),
						     "请输入密码", Toast.LENGTH_LONG);
					toast2.setGravity(Gravity.CENTER, 0, 0);
					toast2.show();
					return;
				}
				else if(pwdStr1.equals("")){
					Log.i("init", "pwd");
					Toast toast3 = Toast.makeText(getApplicationContext(),
						     "请再次输入密码", Toast.LENGTH_LONG);
					toast3.setGravity(Gravity.CENTER, 0, 0);
					toast3.show();
					return;
				}

				if(pwdStr.equals(pwdStr1)){
					if(register(usrStr,pwdStr)){
						Toast toast4 = Toast.makeText(getApplicationContext(),
							     "注册成功", Toast.LENGTH_LONG);
						toast4.setGravity(Gravity.CENTER, 0, 0);
						toast4.show();
					}
					else{
						Toast toast5 = Toast.makeText(getApplicationContext(),
							     "该邮箱已被注册", Toast.LENGTH_LONG);
						toast5.setGravity(Gravity.CENTER, 0, 0);
						toast5.show();
						password.setText("");
						password1.setText("");
					}
				}
				else{
					Toast toast6 = Toast.makeText(getApplicationContext(),
						     "密码不一致,请重新输入", Toast.LENGTH_LONG);
					toast6.setGravity(Gravity.CENTER, 0, 0);
					toast6.show();
					password.setText("");
					password1.setText("");
				}
				
			}
			
		});
		
	}
	
	private boolean register(String usr,String pwd){
		return true;
	}
}
