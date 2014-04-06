package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	Button AddOption, Calendar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AddOption= (Button) findViewById(R.id.button1);
		AddOption.setOnClickListener(this);
		Calendar= (Button) findViewById(R.id.button2);
		Calendar.setOnClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View v){
		
		if(v.getId()==R.id.button1){
			Log.d("VIVZ", "button1 was clicked");
			Intent temp = new Intent(this, Activity_addEvent.class);
			startActivity(temp);}
		
		else if(v.getId()==R.id.button2){
				Log.d("VIVZ", "button2 was clicked");
		Intent temp = new Intent(this, calendar.class);
		startActivity(temp);}
			
		};
		
		
	}
	


