package com.newbegin.touch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.newbegin.touch.utils.JsonUtil;
import com.newbegin.touch.utils.SocketConnect;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
	private TelephonyManager telephonyManager;//�绰���������
	private EditText user;//�绰����
	private EditText password;//����
	private Button loginBtn;//��¼��ť
	
	//test zhty
//	private static final String HOST = "192.168.0.100";
//	private static final int PORT = 9997;
//	private Socket socket = null;
//	private BufferedReader in = null;
//	private PrintWriter out = null;
//	private String content = "";
	public static SocketConnect sc;
	
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
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
				Log.i("init", "onclick");
				if(usrStr.equals("")){
					Log.i("init", "usr");
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
				Log.i("init", "else");
				Log.i("init", usrStr);
				Log.i("init", pwdStr);
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
	}
	
	/**
	 * ��֤�û��������Ƿ���ȷ
	 * @param usrStr 
	 * @param pwdStr
	 * @return ��ȷ����true
	 */
	private boolean confirm(String usrStr,String pwdStr){
		return false;
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
}
