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
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetail extends FragmentActivity{
	ViewPager pager;
	List<Fragment> fragment_list;
	PagerAdapter pageradapter;
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	//Button all_guest,joined_guest,declined_guest,pending_guest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_detail);
		setTitle(Global.active_event.event.event_name);

		Msgbox.Event_Name = Global.active_event.event.event_name;
	fragment_list = new Vector<Fragment>();
	fragment_list.add(Fragment.instantiate(this, EventInfo.class.getName()));
	fragment_list.add(Fragment.instantiate(this, Msgbox.class.getName()));
	
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


	
	public void onBackPressed() {
	    finish();
	    startActivity(new Intent(this,EventMenu.class));
	}

}
