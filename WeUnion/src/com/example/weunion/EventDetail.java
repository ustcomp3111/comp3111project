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
		setTitle(Global.active_event.event.event_name);
Global.initialization_is_completed = false;
		Msgbox.Event_Name = Global.active_event.event.event_name;
	fragment_list = new Vector<Fragment>();
	fragment_list.add(Fragment.instantiate(this, EventInfo.class.getName()));
	fragment_list.add(Fragment.instantiate(this, Msgbox.class.getName()));
	
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
bar.addTab(bar.newTab().setText("Details").setTabListener(this));
bar.addTab(bar.newTab().setText("Messages").setTabListener(this));
Global.initialization_is_completed = false;
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


}
