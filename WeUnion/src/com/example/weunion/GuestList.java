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
			
		    	fragment_list = new Vector<Fragment>();
			fragment_list.add(Fragment.instantiate(this, Event.class.getName()));
			fragment_list.add(Fragment.instantiate(this, EventByMe.class.getName()));
			fragment_list.add(Fragment.instantiate(this, EventByMe.class.getName()));
			fragment_list.add(Fragment.instantiate(this, EventByMe.class.getName()));
			
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

		class AttemptShowEvents extends AsyncTask<String, String, String> {
			
		       @Override
		        protected void onPreExecute() {
		            super.onPreExecute();
		            pDialog = new ProgressDialog(GuestList.this);
		            pDialog.setMessage("Loading events...");
		            pDialog.setIndeterminate(false);
		            pDialog.setCancelable(true);
		            pDialog.show();
		        }

			@Override
			protected String doInBackground(String... arg0) {
			  /* try{

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
		        Global.initialization_is_completed = true;
				*/
				
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
			if(v.getId()==R.id.guest_list_invite_friends)
				 Toast.makeText(getApplicationContext(),"not implemented yet", Toast.LENGTH_LONG).show();
				/*{ i = new Intent(this, CreateEvent.class);
			finish();
			startActivity(i);
			}*/
		}
	}
