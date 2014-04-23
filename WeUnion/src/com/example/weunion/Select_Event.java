package com.example.weunion;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;

import android.app.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;


public class Select_Event extends Activity {

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	ArrayList<String> eventlist = new ArrayList<String>();
	ArrayList<String> hostlist = new ArrayList<String>();
	ArrayList<String> venuelist = new ArrayList<String>();
	private SimpleAdapter adapter;
	private ListView selecteventlist;
	private static final String TAG_ENAME = "event";
	private static final String TAG_HOST = "host";
	private static final String TAG_VENUE = "venue";
	protected static final String EVENT_NAME = null;
	ArrayList<HashMap<String,String>> elist = new ArrayList<HashMap<String,String>>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select__event);
		
		selecteventlist=(ListView) findViewById(R.id.eventlist1);
		adapter = new SimpleAdapter(this, elist,
		R.layout.eventlist,
		new String[] {TAG_ENAME,TAG_HOST, TAG_VENUE},
		new int[] { R.id.t_ename, R.id.t_hname, R.id.t_vname} );
				
		
				
		selecteventlist.setAdapter(adapter);
		selecteventlist.setTextFilterEnabled(true);
		
		new AttemptShowEvents().execute();
		
		 selecteventlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					
					String event_name = eventlist.get(position);
					Toast.makeText(getApplicationContext(),event_name+" is selected", Toast.LENGTH_LONG).show();
					Intent i = new Intent(Select_Event.this, Create_Option.class);
					i.putExtra(EVENT_NAME, event_name);
					startActivity(i);
					
				}
	        });
		  
	}
	
	
	class AttemptShowEvents extends AsyncTask<String, String, String> {
		
	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(Select_Event.this);
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

			              JSONArray jArray2 = jsonParser.makeHttpRequest(Global.EVENT_URL, params2);
					
		   			//EventNode ptr = Global.active_user.event_ptr;
		              for(int i = 0; i <jArray2.length();i++ ) {
		            	 
		            	  JSONObject json2 = jArray2.getJSONObject(i);
		            	//  Global.eventlist.add(json2.getString("event_name"));
		            	  eventlist.add(json2.getString("event_name"));
		            	  hostlist.add(json2.getString("holder"));
		            	  venuelist.add(json2.getString("venue"));
		            	  
		            	  HashMap<String, String> map = new HashMap<String, String>();
    	                   map.put(TAG_ENAME, json2.getString("event_name"));
    	                   map.put(TAG_HOST, "holder : "+json2.getString("holder"));
    	                   map.put(TAG_VENUE, "		venue : "+json2.getString("venue"));
    	                   
    	                   elist.add(map);
		            	  
		            	  String [] array = json2.getString("date").split("-");
		            	  DateAndTime date_and_time = new DateAndTime(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),json2.getInt("time"));
		       
		            	  tmp = new Events(json2.getString("event_name"),json2.getInt("event_id"),new comp3111project.User(json2.getString("holder"),0),
		            			  	date_and_time,json2.getInt("duration"),json2.getString("venue"));
		            	 
		            	  Global.active_user.AddEvent(new EventNode(tmp));
		           	    /*
		           	     if(ptr == null)
		   				{
		   					ptr = new EventNode(tmp);
		   					Global.active_user.event_ptr = ptr;
		   				}
		           	    else
		           	    {
		           	    	ptr.next = new EventNode(tmp);
		           	    	ptr = ptr.next;
		           	    }*/
		              }
		              EventNode ptr = Global.active_user.event_ptr;
		              while(ptr!=null)
		              {          
		            	  if(ptr.event.host.name.equals(Global.active_user.name))
		       		  Global.list_of_event_by_me.add(ptr.event.event_name);
	            	  Global.eventlist.add(ptr.event.event_name);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select__event, menu);
		return true;
	}

}
