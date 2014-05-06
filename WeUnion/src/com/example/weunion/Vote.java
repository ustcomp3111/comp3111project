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
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter;

public class Vote extends Activity {

	JSONParser jsonParser = new JSONParser();
	private ProgressDialog pDialog;
	ArrayList<String> eventidlist2 = new ArrayList<String>();
	ArrayList<String> polllist = new ArrayList<String>();
	ArrayList<ArrayList<String>> pollidlist = new ArrayList<ArrayList<String>>();
	protected static String POLLING_ID;
	private ExpandableListView Listpolling;
	ArrayList<HashMap<String,String>> pollinglist = new ArrayList<HashMap<String,String>>();
	public static String[] options;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote);
	
		new AttemptGetEvents().execute();
  
	


		
		
		ExpandableListView Listpolling=(ExpandableListView)findViewById(R.id.expandableListView1);
		Listpolling.setAdapter(new MyAdapter(this));

		Listpolling.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				String ptitle=MyAdapter.childList.get(groupPosition).get(childPosition);
				Vote_Option.pollingtitle=ptitle;
				Vote_Option.pollingid=pollidlist.get(groupPosition).get(childPosition);
            	Toast.makeText(getApplicationContext(),ptitle+" is selected", Toast.LENGTH_LONG).show();
				Intent i = new Intent(Vote.this, Vote_Option.class);
				startActivity(i);
				return true;
			}
        });
	
	
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
	            MyAdapter.parentList.clear();
	            MyAdapter.childList.clear();
	        }

		@Override
		protected String doInBackground(String... arg0) {
		   try{

		    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
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
