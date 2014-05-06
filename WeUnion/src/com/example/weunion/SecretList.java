package com.example.weunion;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class SecretList extends FragmentActivity implements ActionBar.TabListener,OnClickListener{
	PagerAdapter pageradapter;
	JSONParser jsonParser = new JSONParser();
	private ProgressDialog pDialog;
	ActionBar bar;
	ViewPager pager;
	List<Fragment> fragment_list;
	Button matching_button;
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secret_list);
		Global.initialization_is_completed = false;
		Global.load_secret_list_is_completed = false;
		new AttemptShowSecretList().execute();
		new AttemptShowFd(1).execute();
		//while(!Global.initialization_is_completed);
		//Global.initialization_is_completed = false;
		matching_button = (Button) findViewById(R.id.matching_button);
		matching_button.setOnClickListener(this);
		
		fragment_list = new Vector<Fragment>();
		fragment_list.add(Fragment.instantiate(this, MySecretList.class.getName()));
		fragment_list.add(Fragment.instantiate(this, AddSecretList.class.getName()));

		this.pageradapter = new PagerAdapter(super.getSupportFragmentManager(),fragment_list);	
		pager = (ViewPager)super.findViewById(R.id.secret_list_viewpager);
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
		bar.addTab(bar.newTab().setText("My List").setTabListener(this));
		bar.addTab(bar.newTab().setText("Add Friend To The List!").setTabListener(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secret_list, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.matching_button)
		{
			Global.initialization_is_completed = false;
			new AttemptMatchSecretList().execute();
			while(!Global.initialization_is_completed);
			Global.initialization_is_completed = false;
			if(Global.matching_list.size()==0)
			{
				Global.matching_list.add("No matching is found");
				Global.matching_id_list.add(-1);
			}
			AlertDialog.Builder matching_dialog = new AlertDialog.Builder(SecretList.this);
			matching_dialog.setAdapter(
			new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, Global.matching_list),				
			new DialogInterface.OnClickListener()
			{

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				Global.Default_name = "A Date With "+Global.matching_list.get(which);	
				finish();
				Intent i = new Intent(SecretList.this,CreateEvent.class);
				dialog.dismiss();
				finish();
				startActivity(i);
				}
				
			}
					);							
		matching_dialog.show();
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	 	pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	public class AttemptShowSecretList extends AsyncTask<String, String, String> {
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
	@Override
	protected void onPreExecute() {
	    super.onPreExecute();
	}
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
	    try {
	    	
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("A_id", Integer.toString(User.getInstance().getId())));
	        params.add(new BasicNameValuePair("A_name", User.getInstance().getName()));
	        Global.my_secret_list.clear();
	        Global.my_secret_id_list.clear();
	        JSONArray jArray = jsonParser.makeHttpRequest(Global.SHOW_SECRET_LIST_URL, params);

	        if (jArray!=null) {

	            for(int i = 0; i <jArray.length();i++ ) {
	         	                   JSONObject json = jArray.getJSONObject(i);;
	         	                  Global.my_secret_id_list.add(json.getInt("B_id"));
	         	                 Global.my_secret_list.add(json.getString("B_name"));	         	                 
	            }
	            if(Global.my_secret_list.size()==0)
	            	{
	            	Global.my_secret_list.add("(your list is empty)");
	            	Global.my_secret_id_list.add(-1);
	            	}
	            }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    //Global.initialization_is_completed = true;
	    Global.load_secret_list_is_completed = true;
	    return null;
		
	}

	protected void onPostExecute(String file_url) {
	}
	}
	public class AttemptMatchSecretList extends AsyncTask<String, String, String> {
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
	@Override
	protected void onPreExecute() {
	    super.onPreExecute();
	}
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
	    try {
	    	
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("A_id", Integer.toString(User.getInstance().getId())));
	        params.add(new BasicNameValuePair("A_name", User.getInstance().getName()));
	        Global.matching_id_list.clear();
	        Global.matching_list.clear();
	        JSONArray jArray = jsonParser.makeHttpRequest(Global.MATCH_SECRET_LIST_URL, params);

	        if (jArray!=null) {

	            for(int i = 0; i <jArray.length();i++ ) {
	         	                   JSONObject json = jArray.getJSONObject(i);;
	         	                  Global.matching_id_list.add(json.getInt("A_id"));
	         	                 Global.matching_list.add(json.getString("A_name"));	         	                 
	            }
	            if(Global.my_secret_list.size()==0)
	            	{
	            	Global.my_secret_list.add("(your list is empty)");
	            	Global.my_secret_id_list.add(-1);
	            	}
	            }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    Global.initialization_is_completed = true;
	    return null;
		
	}

	protected void onPostExecute(String file_url) {
	}
	}
}
