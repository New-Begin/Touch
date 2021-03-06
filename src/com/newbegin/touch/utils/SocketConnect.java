package com.newbegin.touch.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SocketConnect {

	/**主机的IP*/
	private static final String HOST = "192.168.0.100";
	
	/**端口号*/
	private static final int PORT = 7777;
	
	/**Socket*/
	private Socket socket = null;
	
	/**输入流*/
	private BufferedReader in = null;
	
	/**输出流*/
	private PrintWriter out = null;
	
	/**接收到的内容*/
	private String content = "";
	
	//私有的默认构造子  
	private SocketConnect(){
		Log.i("tag", "zhty SocketConnect()...");
		this.mThread.start();
	}
		
	//已经自行实例化   
    private static final SocketConnect sc = new SocketConnect();
    
    //静态工厂方法   
    public static SocketConnect getInstance() {  
        return sc;  
    } 
    

	/**
	 * thread
	 * 1.建立Socket连接
	 * 2.监听服务器发送的信息
	 */
	private Thread mThread = new Thread(new Runnable() {

		@Override
		public void run() {
			Log.i("tag", "zhty run...");
			try {
				socket = new Socket(HOST, PORT);
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
						socket.getOutputStream())), true);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			try {
				while (true) {
					if (socket.isConnected()) {
						if (!socket.isInputShutdown()) {
							if ((content = in.readLine()) != null) {
								content += "\n";
								mHandler.sendMessage(mHandler.obtainMessage());
							} else {
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	});

	/**
	 * handler
	 */
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.i("tag", "From Server---->" + content);
			System.out.println("port:" + socket.getPort());
			System.out.println("Localport:" + socket.getLocalPort());
			System.out.println("LocalAddress:" + socket.getLocalAddress());
			System.out.println("LocalSocketAddress:"
					+ socket.getLocalSocketAddress());
		}
	};
	
	/**
	 * 发送信息到服务器
	 */
	public void sendInfo(String info){
		Log.i("sendInfo", "out info = " + info);
		if(socket != null) {
			Log.i("tag", "socket != null");
			if (!socket.isClosed()) {
				if (socket.isConnected()) {
					if (!socket.isOutputShutdown()) {
						out.println(info);
						Log.i("sendInfo", "in info = " + info);
					}
				}
			}
		}
		
	}
	
	/**
	 * 获取Server发送回来的内容
	 * @return content 服务器发送的信息
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * 关闭Socket连接
	 */
	public void closeConnect(){
		//close socket
		if (socket != null) {
			if (!socket.isClosed()) {
				if (socket.isConnected()) {
					if (!socket.isOutputShutdown()) {
						out.println("exit");
					}
				}
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
