package com.newbegin.touch;

import com.newbegin.touch.utils.JsonUtil;
import com.newbegin.touch.utils.SocketConnect;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
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
 * ��¼����
 * @author new begin
 *
 */
public class LoginActivity extends Activity {

	public static SocketConnect sc;
	
	private TelephonyManager telephonyManager;//�绰���������
	private EditText user;//�����˺�
	private EditText password;//����
	private Button loginBtn;//��¼��ť
	private Button registerBtn;//ע�ᰴť
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
		sc = new SocketConnect();
		init();
		
		//test start socket
		

//		mThread.start();
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
	 * ��ʼ������
	 */
	private void init(){
		//��ȡ����绰����
		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String phoneId = telephonyManager.getLine1Number();
		user.setText(phoneId);
		
		loginBtn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {

				//zhty test
				sc.sendInfo(JsonUtil.UserPwd2Json("zhengty", "123456"));
				
				String usrStr = user.getText().toString();
				String pwdStr = password.getText().toString();
				if(usrStr.equals("")){
					Toast toast1 = Toast.makeText(getApplicationContext(),
						     "�������û���", Toast.LENGTH_LONG);
					toast1.setGravity(Gravity.CENTER, 0, 0);
					toast1.show();
					return;
				}
				else if(pwdStr.equals("")){
					Log.i("init", "pwd");
					Toast toast2 = Toast.makeText(getApplicationContext(),
						     "����������", Toast.LENGTH_LONG);
					toast2.setGravity(Gravity.CENTER, 0, 0);
					toast2.show();
					return;
				}
				if(!isEmail(usrStr)){
					Toast toast3 = Toast.makeText(getApplicationContext(),
						     "��������ȷ�������ַ", Toast.LENGTH_LONG);
					toast3.setGravity(Gravity.CENTER, 0, 0);
					toast3.show();
					password.setText("");
					return;
				}
				
				if(confirm(usrStr,pwdStr)){
					//��ת��ƥ��Activity
				}
				else{
					Toast toast3 = Toast.makeText(getApplicationContext(),
						     "�û�����������", Toast.LENGTH_LONG);
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
		
		//ʧȥ����ʱ��������д�Ƿ���ȡ
		user.setOnFocusChangeListener(new View.OnFocusChangeListener() { 

			@Override 
			public void onFocusChange(View v, boolean hasFocus) { 
			if (user.hasFocus() == false) { 
			String str = user.getText().toString();
			if(!(str.equals(""))){
				if(!isEmail(str)){
					Toast toast3 = Toast.makeText(getApplicationContext(),
						     "��������ȷ�������ַ", Toast.LENGTH_LONG);
					toast3.setGravity(Gravity.CENTER, 0, 0);
					toast3.show();
				}
			}
			} 

			} 
			});
	}
	
	/**
	 * 登录时验证用户名、密码是否正确
	 * @param usrStr 
	 * @param pwdStr
	 * @return SUCESS代表正确 EMAIL_NOEXSIT代表用户名不存在 PASSWORD_ERROR代表密码错误 
	 */
	private int confirm(String usrStr,String pwdStr){
		sc.sendInfo(JsonUtil.UserPwd2Json(usrStr, pwdStr));
		return Integer.parseInt(sc.getContent());
	}
	
	//test
	
//	private Thread mThread = new Thread(new Runnable() {
//
//		@Override
//		public void run() {
//			Log.i("tag", "zhty run...");
//			try {
//				socket = new Socket(HOST, PORT);
//				in = new BufferedReader(new InputStreamReader(
//						socket.getInputStream()));
//				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
//						socket.getOutputStream())), true);
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//
//			try {
//				while (true) {
//					if (socket.isConnected()) {
//						if (!socket.isInputShutdown()) {
//							if ((content = in.readLine()) != null) {
//								content += "\n";
//								mHandler.sendMessage(mHandler.obtainMessage());
//							} else {
//							}
//						}
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
//	});
//
//	private Handler mHandler = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			Log.i("tag", "content:" + content);
//			System.out.println("port:" + socket.getPort());
//			System.out.println("Localport:" + socket.getLocalPort());
//			System.out.println("LocalAddress:" + socket.getLocalAddress());
//			System.out.println("LocalSocketAddress:"
//					+ socket.getLocalSocketAddress());
//		}
//	};
	
	@Override
	public void onDestroy(){
		sc.closeConnect();
		super.onDestroy();
	}
	/**
	 * �����ʽ�Ƿ���ȷ
	 */
	private boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
		}
}
