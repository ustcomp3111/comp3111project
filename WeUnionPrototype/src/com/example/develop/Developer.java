package com.example.develop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
public class Developer extends Activity implements OnClickListener{

	private EditText URL_field_login,URL_field_event,msg_box;
	private Button confirm_button_login,confirm_button_event;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_developer);
	URL_field_login = (EditText)findViewById(R.id.dev_test_field_login);
	URL_field_event = (EditText)findViewById(R.id.dev_test_field_event);
	msg_box = (EditText)findViewById(R.id.dev_msg_box);
	confirm_button_login = (Button)findViewById(R.id.dev_confirm_button_login);
	confirm_button_login.setOnClickListener(this);
	confirm_button_event = (Button)findViewById(R.id.dev_confirm_button_event);
	confirm_button_event.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.developer, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.dev_confirm_button_login)
		{
			Global.LOGIN_URL = URL_field_login.getText().toString();
		msg_box.setText(Global.LOGIN_URL);
		}
		if(v.getId()==R.id.dev_confirm_button_event)
		{
			Global.EVENT_URL = URL_field_event.getText().toString();
		msg_box.setText(Global.EVENT_URL);
		}
	}

}
