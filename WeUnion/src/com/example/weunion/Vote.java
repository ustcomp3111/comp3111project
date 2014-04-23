package com.example.weunion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.weunion.Select_Event.AttemptShowEvents;

import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter;

public class Vote extends Activity {

	JSONParser jsonParser = new JSONParser();
	private ProgressDialog pDialog;
	ArrayList<String> eventlist2 = new ArrayList<String>();
	ArrayList<String> polllist = new ArrayList<String>();
	private TextView trytry;

	private ExpandableListView Listpolling;
	ArrayList<HashMap<String,String>> pollinglist = new ArrayList<HashMap<String,String>>();
	private SimpleAdapter adapter;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote);
	
		new AttemptGetEvents().execute();
  
	trytry=(TextView) findViewById(R.id.trytry);

	trytry.setText("HIHI");
	


		
		
		ExpandableListView Listpolling=(ExpandableListView)findViewById(R.id.expandableListView1);
		Listpolling.setAdapter(new MyAdapter(this));
	
	
	}

	class AttemptGetEvents extends AsyncTask<String, String, String> {
		
	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(Vote.this);
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
		            	  eventlist2.add(json2.getString("event_name"));
		            	  
		            	  
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
		getMenuInflater().inflate(R.menu.vote, menu);
		return true;
	}

}
