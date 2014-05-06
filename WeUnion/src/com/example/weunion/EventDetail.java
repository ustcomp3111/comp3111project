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
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetail extends FragmentActivity implements ActionBar.TabListener{
	ViewPager pager;
	ActionBar bar;
	List<Fragment> fragment_list;
	PagerAdapter pageradapter;
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	//Button all_guest,joined_guest,declined_guest,pending_guest;
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_detail);
		new AttemptGetEvents().execute();
		setTitle(Global.active_event.event.event_name);
Global.initialization_is_completed = false;
		Msgbox.Event_Name = Global.active_event.event.event_name;

		fragment_list = new Vector<Fragment>();
	//fragment_list.add(Fragment.instantiate(this, EventSetting.class.getName()));
	fragment_list.add(Fragment.instantiate(this, EventInfo.class.getName()));
	fragment_list.add(Fragment.instantiate(this, Msgbox.class.getName()));
	fragment_list.add(Fragment.instantiate(this, Vote.class.getName()));
	fragment_list.add(Fragment.instantiate(this, EventSetting.class.getName()));
	
	
	pageradapter = new PagerAdapter(super.getSupportFragmentManager(),fragment_list);	
	pager = (ViewPager)super.findViewById(R.id.event_detail_viewpager);
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
bar = getActionBar();
bar.setHomeButtonEnabled(false);
bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//bar.addTab(bar.newTab().setText("Setting").setTabListener(this));
bar.addTab(bar.newTab().setText("Details").setTabListener(this));
bar.addTab(bar.newTab().setText("Messages").setTabListener(this));
bar.addTab(bar.newTab().setText("Voting").setTabListener(this));
bar.addTab(bar.newTab().setText("Setting").setTabListener(this));

Global.initialization_is_completed = false;
//pager.setCurrentItem(1);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_detail, menu);
		return true;
	}



	public void onBackPressed() {
	    finish();
	   startActivity(new Intent(this,EventMenu.class));

	}

	
	class AttemptGetEvents extends AsyncTask<String, String, String> {
		
	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(EventDetail.this);
	            pDialog.setMessage("Loading events...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	            MyAdapter.parentList.clear();
	            MyAdapter.childList.clear();
	        }

		@Override
		protected String doInBackground(String... arg0) {
		   try{

		    	 /*List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		    	 List<NameValuePair> params3 = new ArrayList<NameValuePair>();
		    	 JSONArray jArray2,jArray3;

	               params2.add(new BasicNameValuePair("username",User.getInstance().getName()));

	               jArray2 = jsonParser.makeHttpRequest(Global.EVENT_URL, params2);

	              for(int i = 0; i <jArray2.length();i++ ) {
	            	  
	            	  JSONObject json2 = jArray2.getJSONObject(i);
	            	  MyAdapter.parentList.add(json2.getString("event_name"));
	            	  eventidlist2.add(json2.getString("event_id"));
	            	
		              }
	              
	              for(int i=0;i<eventidlist2.size();i++)
	              {
	            	  params3.add(new BasicNameValuePair("event_id",eventidlist2.get(i)));
	            	  jArray3 = jsonParser.makeHttpRequest(Global.POLLING_URL, params3);
	            	  ArrayList<String> temp = new ArrayList<String>();
	            	  ArrayList<String> temp1 = new ArrayList<String>();
	            	  for(int j = 0; j <jArray3.length();j++ ) {
		            	   JSONObject json3 = jArray3.getJSONObject(j);
		            	   temp1.add(json3.getString("polling_id"));
		            	   temp.add(json3.getString("polling_title"));
		               }
		               MyAdapter.childList.add(temp);
		               pollidlist.add(temp1);
		               params3.clear();
	            	  
	            	  
	              }*/
			   List<NameValuePair> params = new ArrayList<NameValuePair>();
			   params.add(new BasicNameValuePair("event_id",Integer.toString(Global.active_event.event.event_id)));
			   JSONArray jArray;
	            jArray= jsonParser.makeHttpRequest(Global.POLLING_URL, params);
	            ArrayList<String> temp = new ArrayList<String>();
       	  	ArrayList<String> temp1 = new ArrayList<String>();
	            for(int j = 0; j <jArray.length();j++ ) {
	            	   JSONObject json = jArray.getJSONObject(j);
	            	   temp1.add(json.getString("polling_id"));
	            	   temp.add(json.getString("polling_title"));
	               }
	            
	            MyAdapter.childList.add(temp);
	            Global.pollidlist.add(temp1);
	            params.clear();
	            
	            
	            
		       	
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
}