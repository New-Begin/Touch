package com.newbegin.touch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.newbegin.touch.utils.JsonUtil;
import com.newbegin.touch.utils.SocketConnect;
import com.newbegin.touch.utils.StatusCode;

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

public class MatchActivity extends Activity {
	
	private EditText user;// 邮箱账号
	private EditText confirmMsg;// 
	private Button confirmBtn;// 匹配按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match);
		user = (EditText) findViewById(R.id.user);
		confirmMsg = (EditText) findViewById(R.id.confirmMsg);
		confirmBtn = (Button) findViewById(R.id.confirmBtn);

		init();
	}

	private void init() {

		confirmBtn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String usrStr = user.getText().toString();
				String confirmStr = confirmMsg.getText().toString();

				if (usrStr.equals("")) {
					Log.i("init", "usr");
					Toast toast1 = Toast.makeText(getApplicationContext(),
							"请输入用户名", Toast.LENGTH_LONG);
					toast1.setGravity(Gravity.CENTER, 0, 0);
					toast1.show();
					return;
				} else if (confirmStr.equals("")) {
					Log.i("init", "pwd");
					Toast toast2 = Toast.makeText(getApplicationContext(),
							"请输入密码", Toast.LENGTH_LONG);
					toast2.setGravity(Gravity.CENTER, 0, 0);
					toast2.show();
					return;
				} 

				if (!isEmail(usrStr)) {
					Toast toast3 = Toast.makeText(getApplicationContext(),
							"请输入正确的邮箱地址", Toast.LENGTH_LONG);
					toast3.setGravity(Gravity.CENTER, 0, 0);
					toast3.show();
					return;
				}

				if(match(usrStr, confirmStr) == StatusCode.SUCCESS) {
						Toast toast4 = Toast.makeText(getApplicationContext(),
								"发送成功，等待对方确认", Toast.LENGTH_LONG);
						toast4.setGravity(Gravity.CENTER, 0, 0);
						toast4.show();
					} else {
						Toast toast5 = Toast.makeText(getApplicationContext(),
								"发送失败", Toast.LENGTH_LONG);
						toast5.setGravity(Gravity.CENTER, 0, 0);
						toast5.show();
					}
				
			}

		});

		// 失去焦点时，邮箱填写是否正确
		user.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (user.hasFocus() == false) {
					String str = user.getText().toString();
					if (!(str.equals(""))) {
						if (!isEmail(str)) {
							Toast toast3 = Toast.makeText(
									getApplicationContext(), "请输入正确的邮箱地址",
									Toast.LENGTH_LONG);
							toast3.setGravity(Gravity.CENTER, 0, 0);
							toast3.show();
						}
					}
				}

			}
		});

	}
	
	
	/**
	 * 
	 * @param usr
	 * @param pwd
	 * @return
	 */
	private int match(String usr, String Msg) {
		SocketConnect.getInstance().sendInfo(
				JsonUtil.UserPwd2Json(usr, Msg, StatusCode.ACTION_MATCH));
		return Integer.parseInt(SocketConnect.getInstance().getContent());
	}
	
	/**
	 * 邮箱格式是否正确
	 */
	private boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}
}
