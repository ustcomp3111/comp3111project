package com.example.weunion;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.weunion.EventMenu.AttemptShowEvents;
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
import android.widget.Toast;

public class GuestList extends FragmentActivity implements ActionBar.TabListener,OnClickListener{
	PagerAdapter pageradapter;
	JSONParser jsonParser = new JSONParser();
	private ProgressDialog pDialog;
	ActionBar bar;
	ViewPager pager;
	List<Fragment> fragment_list;
	Button add_guest_button;
	//@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_guest_list);
		    //Global.active_user.name = User.getInstance().getId();   
			setTitle("Guest list of: "+Global.active_event.event.event_name);
		   // new AttemptShowEvents().execute();
		   // while(!Global.initialization_is_completed);
			Guest ptr = Global.active_event.event.guest_list_ptr;
			Global.all_guest_list = new ArrayList<String>();
			Global.going_guest_list = new ArrayList<String>();
			Global.declined_guest_list = new ArrayList<String>();
			Global.pending_guest_list  = new ArrayList<String>();
			 Global.all_guest_list.add(Global.active_user.name);
			 Global.going_guest_list.add(Global.active_user.name);
			while(ptr!=null)
			{
				while(ptr!=null)
				{
					if(!ptr.respond)
				Global.pending_guest_list.add(ptr.user.name);
					else if(!ptr.attend)
						Global.declined_guest_list.add(ptr.user.name);
					else
						Global.going_guest_list.add(ptr.user.name);
					
					Global.all_guest_list.add(ptr.user.name);
					ptr = ptr.next;
				}
				
			}
		    	fragment_list = new Vector<Fragment>();
			fragment_list.add(Fragment.instantiate(this, AllGuest.class.getName()));
			fragment_list.add(Fragment.instantiate(this, GoingGuest.class.getName()));
			fragment_list.add(Fragment.instantiate(this, DeclinedGuest.class.getName()));
			fragment_list.add(Fragment.instantiate(this, PendingGuest.class.getName()));
			
			pageradapter = new PagerAdapter(super.getSupportFragmentManager(),fragment_list);	
			pager = (ViewPager)super.findViewById(R.id.guest_list_viewpager);
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
	     add_guest_button = (Button) findViewById(R.id.guest_list_invite_friends);
	    add_guest_button.setOnClickListener(this);
	    
		
		 bar = getActionBar();
		    //bar.setHomeButtonEnabled(false);
			bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			bar.addTab(bar.newTab().setText("All Guests").setTabListener(this));
			bar.addTab(bar.newTab().setText("Join").setTabListener(this));
			bar.addTab(bar.newTab().setText("Decline").setTabListener(this));
			bar.addTab(bar.newTab().setText("Pending").setTabListener(this));
		//Global.initialization_is_completed = false;	
			pager.setCurrentItem(Global.guest_list_choice);	
	}
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.event_menu, menu);
			return true;
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
			if(v.getId()==R.id.guest_list_invite_friends)
				 Toast.makeText(getApplicationContext(),"not implemented yet", Toast.LENGTH_LONG).show();
				/*{ i = new Intent(this, CreateEvent.class);
			finish();
			startActivity(i);
			}*/
		}
		@Override
		public void onBackPressed() {
		    finish();
		    startActivity(new Intent(this,EventDetail.class));
		}	
}
