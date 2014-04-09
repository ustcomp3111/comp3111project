package com.example.weunion;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class CreateEvent extends Activity implements OnClickListener {
	Button confirm_button;
	EditText set_event_name, set_event_year, set_event_month ,
 set_event_day , set_event_duration,set_event_venue, set_event_start_time;
	JSONArray jArray;
	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
	 Intent i;
	 JSONParser jsonParser = new JSONParser();
	 private ProgressDialog pDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);

			 confirm_button = (Button)findViewById(R.id.b_create_event);
			 set_event_name = (EditText)findViewById(R.id.create_event_event_name_input);
			 set_event_year = (EditText)findViewById(R.id.create_event_year);
			 set_event_month = (EditText)findViewById(R.id.create_event_month);
			 set_event_day = (EditText)findViewById(R.id.create_event_day);
			 set_event_start_time = (EditText)findViewById(R.id.create_event_time);
			 set_event_venue = (EditText)findViewById(R.id.create_event_venue);
			 set_event_duration = (EditText)findViewById(R.id.create_event_hour_input);
			 confirm_button.setOnClickListener(this);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	
		getMenuInflater().inflate(R.menu.create_event, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.b_create_event)
			new AttemptCreateEvent().execute();
    	Log.d("hi","here2");
	}


	class AttemptCreateEvent extends AsyncTask<String, String, String> {
		 
		int success = 0;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateEvent.this);
            pDialog.setMessage("Creating event...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
		  @Override
		protected String doInBackground(String... arg0) {
			   try{


		               params2.add(new BasicNameValuePair("event_name",set_event_name.getText().toString()));
		               params2.add(new BasicNameValuePair("host_name",User.getInstance().getId()));
		               params2.add(new BasicNameValuePair("begin_date",set_event_year.getText().toString()+"-"+set_event_month.getText().toString()+"-"+set_event_day.getText().toString()));		               
		               params2.add(new BasicNameValuePair("duration",set_event_duration.getText().toString()));		    
		               params2.add(new BasicNameValuePair("begin_time",Integer.toString((Integer.parseInt(set_event_start_time.getText().toString())))));
		               params2.add(new BasicNameValuePair("venue",set_event_venue.getText().toString()));

		                jArray = jsonParser.makeHttpRequest(Global.POST_URL, params2);
		            	Log.d("hi","here3");
		               success = jArray.getJSONObject(0).getInt("success");
		               if (success==1)
		               {		            	
		                  	 i = new Intent(CreateEvent.this, Event.class);
		                  	finish();
		    				startActivity(i);
		               }
		               return jArray.getJSONObject(0).getString("message");
		               
		
		}
		catch(Exception e)
		{
			 //Toast.makeText(getApplicationContext(),"exception!", Toast.LENGTH_LONG).show();		
		}
			// TODO Auto-generated method stub
			return null;
		}
	
		  protected void onPostExecute(String file_url) {
	            pDialog.dismiss();
	            if (file_url != null){
	            	Toast.makeText(CreateEvent.this, file_url, Toast.LENGTH_LONG).show();
	            }
	 
	        }
	}
}
