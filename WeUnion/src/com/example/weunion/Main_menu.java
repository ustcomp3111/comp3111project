package com.example.weunion;

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
	ImageView events, friend, secret, schedule;
	Button logout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		setContentView(R.layout.activity_main_menu);

		username = (TextView) findViewById(R.id.username);
		username.setText("Logged in as "+User.getInstance().getName());
		events = (ImageView) findViewById(R.id.top);
		friend = (ImageView) findViewById(R.id.left);
		secret = (ImageView) findViewById(R.id.right);
		schedule = (ImageView) findViewById(R.id.bottom);
		logout = (Button) findViewById(R.id.b_logout);
		
		logout.setOnClickListener(this);
		schedule.setOnClickListener(this);
		secret.setOnClickListener(this);
		events.setOnClickListener(this);
		friend.setOnClickListener(this);
		
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
			Intent i = new Intent(Main_menu.this, EventMenu.class);
			startActivity(i);
			break;
		case R.id.left:
			Intent j = new Intent(Main_menu.this, Friend.class);
			startActivity(j);
			break;
		case R.id.right:
			Intent k = new Intent(Main_menu.this, SecretList.class);
			startActivity(k);			
			break;
		case R.id.bottom:
			Intent l = new Intent(Main_menu.this, Schedule.class);
			startActivity(l);	
			break;
		case R.id.b_logout:
			Intent m = new Intent(Main_menu.this, Login.class);
			finish();
			startActivity(m);	
			break;
			
		default:
			break;
		}
	}

}
