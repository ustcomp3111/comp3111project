package com.example.weunion;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;
import comp3111project.Guest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class EventInfo extends Fragment implements OnClickListener,RadioGroup.OnCheckedChangeListener {


	Button all_guest,joined_guest,declined_guest,pending_guest,confirm_response,invite_friends;
	RadioGroup response_radio_group;
	RadioButton join_radio_button,decline_radio_button;
	JSONParser jsonParser = new JSONParser();
	private ProgressDialog pDialog;
	int all,going,declined,pending,response = 1;
	boolean response_updated = false;
	TextView response_message;
	@Override
	public	View onCreateView (LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)  {
		ScrollView l = (ScrollView) inflater.inflate(R.layout.activity_event_info,container,false);
		all = 0;
		going = 0;
		declined = 0;
		pending = 0;
		new AttemptGetGuests().execute();
		//Global.initialization_is_completed = false;
		while(!Global.initialization_is_completed);
		Global.initialization_is_completed = false;

TextView host = (TextView) l.findViewById(R.id.event_info_event_holder_value);
TextView begin = (TextView) l.findViewById(R.id.event_info_begin_time_value);
TextView end = (TextView) l.findViewById(R.id.event_info_end_time_value);	
TextView duration = (TextView) l.findViewById(R.id.event_info_value_of_duration);	
TextView venue = (TextView) l.findViewById(R.id.event_info_venue_value);
response_message = (TextView) l.findViewById(R.id.event_info_response_text);
//TextView event = (TextView) findViewById(R.id.event_detail_event_name);
//msg_box = (Button) findViewById(R.id.event_detail_msg_box_button);
all_guest =  (Button) l.findViewById(R.id.event_info_all_guest_button);
joined_guest =  (Button) l.findViewById(R.id.event_info_going_guest_button);
declined_guest = (Button) l.findViewById(R.id.event_info_not_going_guest_button);
pending_guest =  (Button) l.findViewById(R.id.event_info_pending_guest_button);
invite_friends = (Button) l.findViewById(R.id.event_info_invite_friend_button);
confirm_response =  (Button) l.findViewById(R.id.event_info_response_button);
response_radio_group =(RadioGroup) l.findViewById(R.id.event_info_response_radio_group);
join_radio_button = (RadioButton) l.findViewById(R.id.event_info_going_radio_button);
decline_radio_button = (RadioButton) l.findViewById(R.id.event_info_decline_radio_button);
//setting =  (Button) findViewById(R.id.event_detail_setting_button);
//msg_box.setOnClickListener(this);
all_guest.setOnClickListener(this);
joined_guest.setOnClickListener(this);
declined_guest.setOnClickListener(this);
pending_guest.setOnClickListener(this);
confirm_response.setOnClickListener(this);
invite_friends.setOnClickListener(this);
response_radio_group.setOnCheckedChangeListener(this);
//setting.setOnClickListener(this);
//event.setText(Global.active_event.event.event_name);
	host.setText(Global.active_event.event.host.name);
	begin.setText(Global.active_event.event.begin.toString());
	end.setText(Global.active_event.event.end.toString());	
	duration.setText(Integer.toString(Global.active_event.event.duration/4)+" hours "+(Integer.toString((Global.active_event.event.duration%4)*15))+" minutes");
	venue.setText(Global.active_event.event.location);	
	Guest ptr = Global.active_event.event.guest_list_ptr;
		response_message.setText("Your response: Going");
	while(ptr!=null)
	{

		if(!ptr.respond)
	{
		if(ptr.user.name.equals(Global.active_user.name))
		response_message.setText("Your response: Pending");
			pending++;
	}
		else if(!ptr.attend)
		{
		if(ptr.user.name.equals(Global.active_user.name))
		{
			response_message.setText("Your response: Declined");
			response_radio_group.check(R.id.event_info_decline_radio_button);
		}
			declined++;
			
		}
		else
			going++;
		ptr = ptr.next;
	all++;
	}
	all++;
	all_guest.setText("All:"+all);
	joined_guest.setText("Going:"+going);
	declined_guest.setText("Declined:"+declined);
	pending_guest.setText("Pending:"+pending);
	return l;
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i;


		 if (v.getId()==R.id.event_info_all_guest_button)
		{
			i = new Intent(getActivity(), GuestList.class);
		Global.guest_list_choice = 0;
		getActivity().finish();
		startActivity(i);
		}
		else if (v.getId()==R.id.event_info_going_guest_button)
		{
			i = new Intent(getActivity(), GuestList.class);
		Global.guest_list_choice = 1;
		getActivity().finish();
		startActivity(i);
		}
		else if (v.getId()==R.id.event_info_not_going_guest_button)
		{
			i = new Intent(getActivity(), GuestList.class);
		Global.guest_list_choice = 2;
		getActivity().finish();
		startActivity(i);
		}
		else if (v.getId()==R.id.event_info_pending_guest_button)
		{
			i = new Intent(getActivity(), GuestList.class);
		Global.guest_list_choice = 3;
		getActivity().finish();
		startActivity(i);
		}
		else if (v.getId()==R.id.event_info_invite_friend_button)
		{
			i = new Intent(getActivity(), GuestList.class);
		Global.guest_list_choice = 4;
		getActivity().finish();
		startActivity(i);
		}
		else if (v.getId() == R.id.event_info_response_button)
		{
		if(Global.active_event.event.host.name.equals(Global.active_user.name))
			Toast.makeText(getActivity(),"You are the host, so you must attend this event!", Toast.LENGTH_LONG).show();
			else
		{
		new AttemptResponse().execute();
		while(!Global.initialization_is_completed);
		Global.initialization_is_completed = false;
		if(response_updated)
		{
			Toast.makeText(getActivity(),"Your response has been sent!", Toast.LENGTH_LONG).show();	
			if(response==1)
			response_message.setText("Your response: Going");
			else
			response_message.setText("Your response: Declined");
			response_updated = false;
		}
		}
		}


	}
	class AttemptGetGuests extends AsyncTask<String, String, String> {

	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(getActivity());
	            pDialog.setMessage("Loading events...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

		@Override
		protected String doInBackground(String... arg0) {
		   try{

			    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		               params2.add(new BasicNameValuePair("event_id",Integer.toString(Global.active_event.event.event_id)));

Global.active_event.event.guest_list_ptr = null;
			              JSONArray jArray2 = jsonParser.makeHttpRequest(Global.SHOW_GUEST_URL, params2);

		   			//EventNode ptr = Global.active_user.event_ptr;
		              for(int i = 0; i <jArray2.length();i++ ) {

		            	  JSONObject json2 = jArray2.getJSONObject(i);
		            	  boolean r = false, a = false;
		            	  if(json2.getInt("responded")==1)
		            	r = true;
		            	  if(json2.getInt("joined")==1)
		            		  a = true;
		            		  //  Global.eventlist.add(json2.getString("event_name"));
		            	  Global.active_event.event.AddGuest(new Guest(json2.getString("user_name"),json2.getInt("user_id"),r,a));
		            	  		            			//Log.d(EventInfo,json2.getString("user_name"+" "+json2.getInt("user_id")+" "+);     
		              }


			   }
		catch(Exception e)
		{
			// Toast.makeText(getApplicationContext(),"exception!", Toast.LENGTH_LONG).show();

		}
		   Global.initialization_is_completed = true;
			// TODO Auto-generated method stub
			return null;
		}
		 protected void onPostExecute(String file_url) {
	        	if (pDialog != null) { 
	                pDialog.dismiss();
	           }
	        //	Toast.makeText(getActivity(),"debug: "+debug, Toast.LENGTH_LONG).show();
		 }
	}
	class AttemptResponse extends AsyncTask<String, String, String> {

	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(getActivity());
	            pDialog.setMessage("Loading events...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

		@Override
		protected String doInBackground(String... arg0) {
		   try{

			    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
			    	 params2.add(new BasicNameValuePair("user_name",Global.active_user.name));  
			    	 params2.add(new BasicNameValuePair("event_id",Integer.toString(Global.active_event.event.event_id)));
			    	 params2.add(new BasicNameValuePair("joined",Integer.toString(response)));
			    	 params2.add(new BasicNameValuePair("responded",Integer.toString(1)));

			              JSONArray jArray2 = jsonParser.makeHttpRequest(Global.UPDATE_GUEST_URL, params2);

			               if (jArray2.getJSONObject(0).getInt("success")==1)
			               {		            	
			            		response_updated = true;	           
			               }
		   }
		catch(Exception e)
		{
			// Toast.makeText(getApplicationContext(),"exception!", Toast.LENGTH_LONG).show();

		}
		   Global.initialization_is_completed = true;
			// TODO Auto-generated method stub
			return null;
		}
		 protected void onPostExecute(String file_url) {
	        	if (pDialog != null) { 
	                pDialog.dismiss();
	           }
	        //	Toast.makeText(getActivity(),"debug: "+debug, Toast.LENGTH_LONG).show();
		 }
	}

	@Override
	public void onCheckedChanged(RadioGroup g, int id) {
		// TODO Auto-generated method stub
	if(id == join_radio_button.getId())	
		response = 1;
	else if (id == decline_radio_button.getId())	
	response = 0;
	}
}