package com.example.develop;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Event_detail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_detail);
TextView host = (TextView) findViewById(R.id.event_detail_event_holder_value);
TextView begin = (TextView) findViewById(R.id.event_detail_begin_time_value);
TextView end = (TextView) findViewById(R.id.event_detail_end_time_value);	
TextView duration = (TextView) findViewById(R.id.event_detail_duration_value);	
TextView venue = (TextView) findViewById(R.id.event_detail_venue_value);	
	host.setText(Global.active_event.event.host.name);
	begin.setText(Global.active_event.event.begin.toString());
	end.setText(Global.active_event.event.end.toString());	
	duration.setText(Integer.toString(Global.active_event.event.duration/4)+" hours "+(Integer.toString((Global.active_event.event.duration%4)*15))+" minutes");
	venue.setText(Global.active_event.event.location);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_detail, menu);
		return true;
	}

}
