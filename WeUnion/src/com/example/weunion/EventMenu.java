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


import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.app.ProgressDialog;
import android.content.Intent;
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class EventMenu extends FragmentActivity implements ActionBar.TabListener,OnClickListener{
PagerAdapter pageradapter;
JSONParser jsonParser = new JSONParser();
private ProgressDialog pDialog;
ActionBar bar;
ViewPager pager;
List<Fragment> fragment_list;
Button create_event_button,debug;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_menu);
	    //Global.active_user.name = User.getInstance().getName();   
	   Global.active_user = new comp3111project.User(User.getInstance().getName(),User.getInstance().getId());
	    new AttemptShowEvents().execute();
	    while(!Global.initialization_is_completed);
		
	    	fragment_list = new Vector<Fragment>();
		fragment_list.add(Fragment.instantiate(this, Event.class.getName()));
		fragment_list.add(Fragment.instantiate(this, EventByMe.class.getName()));
		fragment_list.add(Fragment.instantiate(this, JoinedEvent.class.getName()));
		this.pageradapter = new PagerAdapter(super.getSupportFragmentManager(),fragment_list);	
		pager = (ViewPager)super.findViewById(R.id.event_menu_viewpager);
	pager.setAdapter(pageradapter);
	pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
	{
		
		@Override
		public void onPageSelected(int p) {
			// TODO Auto-generated method stub
			bar.setSelectedNavigationItem(p);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	});
     create_event_button = (Button) findViewById(R.id.event_menu_create_event_button);
    create_event_button.setOnClickListener(this);
    debug =  (Button) findViewById(R.id.debug_button);
    debug.setOnClickListener(this);
	
	 bar = getActionBar();
	    bar.setHomeButtonEnabled(false);
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.addTab(bar.newTab().setText("All Events").setTabListener(this));
		bar.addTab(bar.newTab().setText("Events By Me").setTabListener(this));
		bar.addTab(bar.newTab().setText("Events I Joined").setTabListener(this));
	Global.initialization_is_completed = false;	
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_menu, menu);
		return true;
	}
/*
private void initialize()
{

}*/
	class AttemptShowEvents extends AsyncTask<String, String, String> {
		
	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(EventMenu.this);
	            pDialog.setMessage("Loading events...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

		@Override
		protected String doInBackground(String... arg0) {
		   try{

			    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>(),params3 = new ArrayList<NameValuePair>();
		               params2.add(new BasicNameValuePair("username",User.getInstance().getName()));
		               params3.add(new BasicNameValuePair("user_name",User.getInstance().getName()));  
		               Events tmp ;
Global.joined_event_list = new ArrayList<String>();
Global.my_event_list = new ArrayList<String>();
Global.all_event_list = new ArrayList<String>();
Global.joined_event_id_list = new ArrayList<Integer>();
Global.my_event_id_list = new ArrayList<Integer>();
Global.all_event_id_list = new ArrayList<Integer>();
Global.active_user.event_ptr = null;
			              JSONArray jArray2 = jsonParser.makeHttpRequest(Global.EVENT_URL, params2);
			              JSONArray jArray3 = jsonParser.makeHttpRequest(Global.JOINED_EVENT_URL, params3);
			              String [] array;
			              DateAndTime date_and_time;
		              for(int i = 0; i <jArray2.length();i++ ) {		            	 		            	  		            	  		            	  
		            		  JSONObject json2 = jArray2.getJSONObject(i);
		            		  array = json2.getString("date").split("-");
		            	  date_and_time = new DateAndTime(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),json2.getInt("time"));		       
		            	  tmp = new Events(json2.getString("event_name"),json2.getInt("event_id"),new comp3111project.User(json2.getString("holder"),0),
		            			  	date_and_time,json2.getInt("duration"),json2.getString("venue"));		            	 
		            	  Global.active_user.AddEvent(new EventNode(tmp));
		            		/* Global.my_event_list.add(json2.getString("event_name"));
			            	 Global.my_event_id_list.add(json2.getInt("event_id"));
			            	 Global.all_event_list.add(json2.getString("event_name"));
			            	 Global.all_event_id_list.add(json2.getInt("event_id")); 
		              */}
		              for(int i = 0; i <jArray3.length();i++ ) {
			            	 JSONObject json3 = jArray3.getJSONObject(i);
			            	 array = json3.getString("date").split("-");
			            	  date_and_time = new DateAndTime(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),json3.getInt("time"));
			            	  tmp = new Events(json3.getString("event_name"),json3.getInt("event_id"),new comp3111project.User(json3.getString("holder"),0),
			            			  	date_and_time,json3.getInt("duration"),json3.getString("venue"));
			            	  Global.active_user.AddEvent(new EventNode(tmp));
			            /*		 Global.joined_event_list.add(json3.getString("event_name"));
				            	 Global.joined_event_id_list.add(json3.getInt("event_id"));
				            	 Global.all_event_list.add(json3.getString("event_name"));
				            	 Global.all_event_id_list.add(json3.getInt("event_id")); 
		              */}
		              EventNode ptr = Global.active_user.event_ptr;
		              while(ptr != null)
		              {
		            	  Global.all_event_list.add(ptr.event.event_name);
			            	 Global.all_event_id_list.add(ptr.event.event_id); 
		              if(ptr.event.host.name.equals(Global.active_user.name))
		              {
		            	  Global.my_event_list.add(ptr.event.event_name);
			            	 Global.my_event_id_list.add(ptr.event.event_id);
		              }
		              else
		              {
		            	  Global.joined_event_list.add(ptr.event.event_name);
			            	 Global.joined_event_id_list.add(ptr.event.event_id);
		              }
		              ptr = ptr.next;
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

		 }
}
	 @Override
	 public void onPause() {
		    super.onPause();

		    if(pDialog != null)
		        pDialog.dismiss();
		    pDialog = null;
		}
	 @Override
	 public void onTabReselected(Tab arg0, android.app.FragmentTransaction arg1) {
	 	// TODO Auto-generated method stub
	 	
	 }
	 @Override
	 public void onTabSelected(Tab tab, android.app.FragmentTransaction arg1) {
	 	// TODO Auto-generated method stub
	 	pager.setCurrentItem(tab.getPosition());
	 }
	 @Override
	 public void onTabUnselected(Tab arg0, android.app.FragmentTransaction arg1) {
	 	// TODO Auto-generated method stub
	 	
	 }
	@Override
	public void onClick(View v) {
		
		Intent i ;
		// TODO Auto-generated method stub
		if(v.getId()==R.id.event_menu_create_event_button)
		{ i = new Intent(this, CreateEvent.class);
		finish();
		startActivity(i);
		}
		else if(v.getId()==R.id.debug_button)
		{ i = new Intent(this, WeeklyAgenda.class);
		finish();
		startActivity(i);
		}
	}
	public void onBackPressed() {
	    finish();
	}
}
