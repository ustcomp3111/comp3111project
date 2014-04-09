package com.example.weunion;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Schedule extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		/*TextView display_month =(TextView) findViewById(R.id.textView0);
		display_month.setText(current_month);*/
		
		/*For adding TextView dynamicallyunder RelativeLayout
			RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
			TextView child = getLayoutInflater().inflate(R.layout.child);
			layout.addView(child);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule, menu);
		return true;
	}

}
