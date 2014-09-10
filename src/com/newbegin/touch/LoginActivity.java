package com.newbegin.touch;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 登录界面
 * @author new begin
 *
 */
public class LoginActivity extends Activity {
	private TelephonyManager telephonyManager;//电话号码管理器
	private EditText user;//邮箱账号
	private EditText password;//密码
	private Button loginBtn;//登录按钮
	private Button registerBtn;//注册按钮
	/**
	 *
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		user = (EditText) findViewById(R.id.user);
		password = (EditText) findViewById(R.id.password);
		loginBtn = (Button) findViewById(R.id.loginBtn);	
		registerBtn = (Button) findViewById(R.id.btn_register);	
		
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 初始化函数
	 */
	private void init(){
		//获取本机电话号码
		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String phoneId = telephonyManager.getLine1Number();
		user.setText(phoneId);
		
		loginBtn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String usrStr = user.getText().toString();
				String pwdStr = password.getText().toString();
				Log.i("init", "onclick");
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
				Log.i("init", "else");
				Log.i("init", usrStr);
				Log.i("init", pwdStr);
				if(confirm(usrStr,pwdStr)){
					//跳转到匹配Activity
				}
				else{
					Toast toast3 = Toast.makeText(getApplicationContext(),
						     "用户名或密码错误", Toast.LENGTH_LONG);
					toast3.setGravity(Gravity.CENTER, 0, 0);
					toast3.show();
					password.setText("");
				}
			}
			
		});
		
		registerBtn.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//ComponentName componentName = new ComponentName(LoginActivity.this,"com.newbegin.RegisterActivity");
				Intent intent=new Intent();
				//intent.setComponent(componentName);
				intent.setClass(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
			
		});
	}
	
	/**
	 * 验证用户名、密码是否正确
	 * @param usrStr 
	 * @param pwdStr
	 * @return 正确返回true
	 */
	private boolean confirm(String usrStr,String pwdStr){
		return false;
	}
}
