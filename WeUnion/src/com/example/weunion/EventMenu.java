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
Button create_event_button;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_menu);
	    Global.active_user.name = User.getInstance().getId();   
	   
	    new AttemptShowEvents().execute();
		fragment_list = new Vector<Fragment>();
		fragment_list.add(Fragment.instantiate(this, Event.class.getName()));
		fragment_list.add(Fragment.instantiate(this, EventByMe.class.getName()));
		
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
    
	
	 bar = getActionBar();
	    bar.setHomeButtonEnabled(false);
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.addTab(bar.newTab().setText("All Events").setTabListener(this));
		bar.addTab(bar.newTab().setText("Events By Me").setTabListener(this));
		
		
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

			    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		               params2.add(new BasicNameValuePair("username",User.getInstance().getId()));
		               Events tmp ;
Global.eventlist = new ArrayList<String>();
Global.list_of_event_by_me = new ArrayList<String>();
Global.active_user.event_ptr = null;
			              JSONArray jArray2 = jsonParser.makeHttpRequest(Global.EVENT_URL, params2);
					
		   			//EventNode ptr = Global.active_user.event_ptr;
		              for(int i = 0; i <jArray2.length();i++ ) {
		            	 
		            	  JSONObject json2 = jArray2.getJSONObject(i);
		            	//  Global.eventlist.add(json2.getString("event_name"));
		            	  
		            	  String [] array = json2.getString("date").split("-");
		            	  DateAndTime date_and_time = new DateAndTime(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),json2.getInt("time"));
		       
		            	  tmp = new Events(json2.getString("event_name"),json2.getInt("event_id"),new comp3111project.User(json2.getString("holder"),0),
		            			  	date_and_time,json2.getInt("duration"),json2.getString("venue"));
		            	 
		            	  Global.active_user.AddEvent(new EventNode(tmp));
		         
		              }
		              EventNode ptr = Global.active_user.event_ptr;
		              while(ptr!=null)
		              {          
		            	  if(ptr.event.host.name.equals(Global.active_user.name))
		            	  {
		       		  Global.list_of_event_by_me.add(ptr.event.event_name);
		            	  Global.event_by_me_id.add(ptr.event.event_id);
		            	  }
		            	 Global.eventlist.add(ptr.event.event_name);
		            	 Global.event_id_list.add(ptr.event.event_id);
		            	 ptr = ptr.next;
		              }
		          
		       	
			   }
		catch(Exception e)
		{
			// Toast.makeText(getApplicationContext(),"exception!", Toast.LENGTH_LONG).show();
			
		}
		  
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
		// TODO Auto-generated method stub
		Intent i ;
		// TODO Auto-generated method stub
		if(v.getId()==R.id.event_menu_create_event_button)
		{ i = new Intent(this, CreateEvent.class);
		finish();
		startActivity(i);
		}
	}
}
