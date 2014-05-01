package com.example.weunion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.weunion.Select_Event.AttemptShowEvents;

import comp3111project.Events;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Vote_Option extends Activity {
	
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private ListView optionlist;
	private SimpleAdapter adapter;
	private static final String TAG_ONAME = "oname";
	ArrayList<HashMap<String,String>> olist = new ArrayList<HashMap<String,String>>();
	ArrayList<String> oplist = new ArrayList<String>();
	private String pollingid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote__option);
		
		Intent intent = getIntent();
		pollingid = intent.getStringExtra(Vote.POLLING_ID);
		optionlist=(ListView) findViewById(R.id.option_lv);
		adapter = new SimpleAdapter(this, olist,
		R.layout.eventlist,
		new String[] {TAG_ONAME},
		new int[] { R.id.option_chkbox} );
		
		

        optionlist.setAdapter(adapter);
 		optionlist.setTextFilterEnabled(true);
 		
 		new AttemptGetOptions().execute();
 		
 		
 		HashMap<String, String> map = new HashMap<String, String>();
 		for(int i=0;i<oplist.size();i++)
 		{
 			map.put(TAG_ONAME, oplist.get(i));
 			olist.add(map);
 		}
		
		
		
		
	}
	
	
	class AttemptGetOptions extends AsyncTask<String, String, String> {
		
	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(Vote_Option.this);
	            pDialog.setMessage("Loading Options...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

		@Override
		protected String doInBackground(String... arg0) {
		   try{

			    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		               params2.add(new BasicNameValuePair("poll_id",pollingid));
		               Events tmp ;

			              JSONArray jArray2 = jsonParser.makeHttpRequest(Global.POLLING_URL, params2);
					
		   			//EventNode ptr = Global.active_user.event_ptr;
		              for(int i = 0; i <jArray2.length();i++ ) {
		            	 
		            	  JSONObject json2 = jArray2.getJSONObject(i);
		            	//  Global.eventlist.add(json2.getString("event_name"));
		            	  oplist.add(json2.getString("option1"));
		            	  oplist.add(json2.getString("option2"));
		            	  oplist.add(json2.getString("option3"));
		            	  oplist.add(json2.getString("option4"));
		            	  oplist.add(json2.getString("option5"));
		            	  
		            
		            	  
		            	  
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
		getMenuInflater().inflate(R.menu.vote__option, menu);
		return true;
	}

}
