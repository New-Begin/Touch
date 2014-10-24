package com.newbegin.touch;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ChatActivity extends Activity {
	
	private Button sendBtn;
	private EditText editText;
	private ListView listView;
	private ChatAdapter  chatAdapter;
	private List<String> chatList = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		sendBtn = (Button) findViewById(R.id.btn_send);
		editText = (EditText) findViewById(R.id.et_sendmessage);
		listView = (ListView) findViewById(R.id.listview);
		chatList = new ArrayList<String>();
		
		chatAdapter = new ChatAdapter(this,chatList);
		listView.setAdapter(chatAdapter);
		
		sendBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String editString = editText.getText().toString();
				if(editString.length() > 0){
					Log.i("adapter", editString);
					addString(editString);
					editText.setText("");
					listView.setSelection(listView.getCount() - 1);
				}
				else return;
			}
		});
	}
	
	void addString(String str){
		chatList.add(str);
		chatAdapter.notifyDataSetChanged();
		
		
	}
	
	class ChatAdapter extends BaseAdapter{
		
		List<String> chatList;
		private LayoutInflater mInflater;

		public ChatAdapter(Context context, List<String> list) {
			// TODO Auto-generated constructor stub
			chatList = list;
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return chatList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView textView;
			
			convertView = mInflater.inflate(R.layout.msg, null);
			textView = (TextView) convertView.findViewById(R.id.msgText);
			textView.setText(chatList.get(position));
			
			return convertView;
		}
		
	}
	


}
