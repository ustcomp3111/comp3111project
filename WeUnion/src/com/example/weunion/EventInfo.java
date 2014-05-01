package com.example.weunion;

import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventInfo extends Fragment implements OnClickListener {


	Button all_guest,joined_guest,declined_guest,pending_guest;
	@Override
	public	View onCreateView (LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)  {
		RelativeLayout l = (RelativeLayout) inflater.inflate(R.layout.activity_event_info,container,false);
		
TextView host = (TextView) l.findViewById(R.id.event_info_event_holder_value);
TextView begin = (TextView) l.findViewById(R.id.event_info_begin_time_value);
TextView end = (TextView) l.findViewById(R.id.event_info_end_time_value);	
TextView duration = (TextView) l.findViewById(R.id.event_info_value_of_duration);	
TextView venue = (TextView) l.findViewById(R.id.event_info_venue_value);
//TextView event = (TextView) findViewById(R.id.event_detail_event_name);
//msg_box = (Button) findViewById(R.id.event_detail_msg_box_button);
all_guest =  (Button) l.findViewById(R.id.event_info_all_guest_button);
joined_guest =  (Button) l.findViewById(R.id.event_info_going_guest_button);
declined_guest = (Button) l.findViewById(R.id.event_info_not_going_guest_button);
pending_guest =  (Button) l.findViewById(R.id.event_info_pending_guest_button);
//setting =  (Button) findViewById(R.id.event_detail_setting_button);
//msg_box.setOnClickListener(this);
all_guest.setOnClickListener(this);
joined_guest.setOnClickListener(this);
declined_guest.setOnClickListener(this);
pending_guest.setOnClickListener(this);
//setting.setOnClickListener(this);
//event.setText(Global.active_event.event.event_name);
	host.setText(Global.active_event.event.host.name);
	begin.setText(Global.active_event.event.begin.toString());
	end.setText(Global.active_event.event.end.toString());	
	duration.setText(Integer.toString(Global.active_event.event.duration/4)+" hours "+(Integer.toString((Global.active_event.event.duration%4)*15))+" minutes");
	venue.setText(Global.active_event.event.location);	
	Msgbox.Event_Name = Global.active_event.event.event_name;
	
return l;
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
/*		Intent i;
		
				
		 if (v.getId()==R.id.event_detail_all_guest_button)
		{
			i = new Intent(this, GuestList.class);
		Global.guest_list_choice = 0;
		finish();
		startActivity(i);
		}
		else if (v.getId()==R.id.event_detail_going_guest_button)
		{
			i = new Intent(this, GuestList.class);
		Global.guest_list_choice = 1;
		finish();
		startActivity(i);
		}
		else if (v.getId()==R.id.event_detail_not_going_guest_button)
		{
			i = new Intent(this, GuestList.class);
		Global.guest_list_choice = 2;
		finish();
		startActivity(i);
		}
		else if (v.getId()==R.id.event_detail_pending_guest_button)
		{
			i = new Intent(this, GuestList.class);
		Global.guest_list_choice = 3;
		finish();
		startActivity(i);
		}

			
	*/
	}


}
