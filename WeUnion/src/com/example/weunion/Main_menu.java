package com.example.weunion;

import com.example.weunion.Login.AttemptLogin;
import com.example.weunion.Login.AttemptRegister;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main_menu extends Activity implements OnClickListener{

	TextView username;
	ImageView events, msgbox, startvote, schedule;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		username = (TextView) findViewById(R.id.username);
		username.setText("Logged in as "+User.getInstance().getId());
		events = (ImageView) findViewById(R.id.top);
		msgbox = (ImageView) findViewById(R.id.left);
		startvote = (ImageView) findViewById(R.id.right);
		schedule = (ImageView) findViewById(R.id.bottom);
		schedule.setOnClickListener(this);
		startvote.setOnClickListener(this);
		events.setOnClickListener(this);
		msgbox.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.top:
			Intent i = new Intent(Main_menu.this, Event.class);
			startActivity(i);
			break;
		case R.id.left:
			Intent j = new Intent(Main_menu.this, Msgbox.class);
			startActivity(j);
			break;
		case R.id.right:
			Intent k = new Intent(Main_menu.this, Polling.class);
			startActivity(k);			
			break;
		case R.id.bottom:
			Intent l = new Intent(Main_menu.this, Schedule.class);
			startActivity(l);	
			break;
			
		default:
			break;
		}
	}

}
