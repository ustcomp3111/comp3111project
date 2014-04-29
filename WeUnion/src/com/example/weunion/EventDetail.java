package com.example.weunion;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EventDetail extends FragmentActivity implements OnClickListener{
	ViewPager pager;
	List<Fragment> fragment_list;
	PagerAdapter pageradapter;
	Button msg_box,all_guest,joined_guest,declined_guest,pending_guest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_detail);
		setTitle(Global.active_event.event.event_name);
TextView host = (TextView) findViewById(R.id.event_detail_event_holder_value);
TextView begin = (TextView) findViewById(R.id.event_detail_begin_time_value);
TextView end = (TextView) findViewById(R.id.event_detail_end_time_value);	
TextView duration = (TextView) findViewById(R.id.event_detail_value_of_duration);	
TextView venue = (TextView) findViewById(R.id.event_detail_venue_value);
//TextView event = (TextView) findViewById(R.id.event_detail_event_name);
msg_box = (Button) findViewById(R.id.event_detail_msg_box_button);
all_guest =  (Button) findViewById(R.id.event_detail_all_guest_button);
joined_guest =  (Button) findViewById(R.id.event_detail_going_guest_button);
declined_guest = (Button) findViewById(R.id.event_detail_not_going_guest_button);
pending_guest =  (Button) findViewById(R.id.event_detail_pending_guest_button);
msg_box.setOnClickListener(this);
all_guest.setOnClickListener(this);
joined_guest.setOnClickListener(this);
declined_guest.setOnClickListener(this);
pending_guest.setOnClickListener(this);
//event.setText(Global.active_event.event.event_name);
	host.setText(Global.active_event.event.host.name);
	begin.setText(Global.active_event.event.begin.toString());
	end.setText(Global.active_event.event.end.toString());	
	duration.setText(Integer.toString(Global.active_event.event.duration/4)+" hours "+(Integer.toString((Global.active_event.event.duration%4)*15))+" minutes");
	venue.setText(Global.active_event.event.location);	
	fragment_list = new Vector<Fragment>();
	fragment_list.add(Fragment.instantiate(this, Event.class.getName()));
	
	pageradapter = new PagerAdapter(super.getSupportFragmentManager(),fragment_list);	
	pager = (ViewPager)super.findViewById(R.id.event_detail_viewpager);
pager.setAdapter(pageradapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_detail, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, Msgbox.class);
		
		if(v.getId()==R.id.event_detail_msg_box_button)
		{ 
		finish();
		}
		else if (v.getId()==R.id.event_detail_all_guest_button)
		{
			i = new Intent(this, GuestList.class);
		Global.guest_list_choice = 0;
		}
		else if (v.getId()==R.id.event_detail_going_guest_button)
		{
			i = new Intent(this, GuestList.class);
		Global.guest_list_choice = 1;
		}
		else if (v.getId()==R.id.event_detail_not_going_guest_button)
		{
			i = new Intent(this, GuestList.class);
		Global.guest_list_choice = 2;
		}
		else if (v.getId()==R.id.event_detail_pending_guest_button)
		{
			i = new Intent(this, GuestList.class);
		Global.guest_list_choice = 3;
		}
		startActivity(i);
	
	}

}
