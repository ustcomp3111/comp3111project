package com.example.develop;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {
	Button login,event,polling,msgbox,slist,schedule;			//buttons
	EditText debugbox;											//text fields
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		login = (Button) findViewById(R.id.b_login);			//buttons linking
		event = (Button) findViewById(R.id.b_event);
		polling = (Button) findViewById(R.id.b_polling);
		msgbox = (Button) findViewById(R.id.b_msgbox);
		slist = (Button) findViewById(R.id.b_slist);
		schedule = (Button) findViewById(R.id.b_schedule);
		

		debugbox = (EditText) findViewById(R.id.debugbox);		//text fields linking
		
		login.setOnClickListener(this);
		event.setOnClickListener(this);
		polling.setOnClickListener(this);
		msgbox.setOnClickListener(this);
		slist.setOnClickListener(this);
		schedule.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		
		case(R.id.b_login)	:	{
								debugbox.setText("Login clicked");
								Intent temp = new Intent(this, Login.class);
								startActivity(temp);
								break;
								}
		
		case(R.id.b_event)	:	{
								debugbox.setText("Event clicked");
								Intent temp = new Intent(this, Event.class);
								startActivity(temp);
								break;
								}
		
		case(R.id.b_polling)	:	{
									debugbox.setText("Polling clicked");
									Intent temp = new Intent(this, Polling.class);
									startActivity(temp);
									break;
									}
		
		case(R.id.b_msgbox)		:	{
									debugbox.setText("MessageBox clicked");
									Intent temp = new Intent(this, Msgbox.class);
									startActivity(temp);
									break;
									}
		
		case(R.id.b_slist)	:	{								
								debugbox.setText("SecretList clicked");
								Intent temp = new Intent(this, SecretList.class);
								startActivity(temp);
								break;
							 	}
		
		case(R.id.b_schedule)	:	{
									
									debugbox.setText("Schedule clicked");
									Intent temp = new Intent(this, Schedule.class);
									startActivity(temp);
									break;
									}
		default: break;
		
		
		
		
		}
	}

}
