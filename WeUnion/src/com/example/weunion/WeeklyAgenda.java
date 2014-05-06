package com.example.weunion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;
import comp3111project.RegularEvent;
import comp3111project.RegularEventNode;
import comp3111project.WeekdayAndTime;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class WeeklyAgenda extends Activity implements OnClickListener{
	ListView agenda_listview;
	Button create_regular_event_button;
	JSONParser jsonParser = new JSONParser();
	private ProgressDialog pDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weekly_agenda);
		Global.initialization_is_completed = false;
		agenda_listview = (ListView) findViewById(R.id.my_agenda);
		     create_regular_event_button = (Button) findViewById(R.id.weekly_agenda_create_regular_event_button);
		     create_regular_event_button.setOnClickListener(this);
		  new AttemptShowRegularEvents().execute();
		  while(!Global.initialization_is_completed);
		Global.initialization_is_completed = false;
		  agenda_listview.setAdapter(new ArrayAdapter<String>(WeeklyAgenda.this,
		    android.R.layout.simple_list_item_1, Global.agenda_list));

		    agenda_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		    {
				@Override
				public void onItemClick(AdapterView<?> a, View v, int position,
						long id) 
				{
				}		    	
		    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weekly_agenda, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	Intent i;
		if(v.getId()==R.id.weekly_agenda_create_regular_event_button)	
	{
	i = new Intent(this,CreateRegularEvent.class);
	startActivity(i);
	}
		}
	class AttemptShowRegularEvents extends AsyncTask<String, String, String> {
		
	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(WeeklyAgenda.this);
	            pDialog.setMessage("Loading events...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

		@Override
		protected String doInBackground(String... arg0) {
		   try{

			    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		               params2.add(new BasicNameValuePair("username",User.getInstance().getName()));
		
		               RegularEvent tmp ;
Global.agenda_list = new ArrayList<String>();
Global.agenda_id_list = new ArrayList<Integer>();
Global.active_user.schedule_ptr = null;

			              JSONArray jArray2 = jsonParser.makeHttpRequest(Global.REGULAR_EVENT_URL, params2);
			       int weekday = Calendar.SUNDAY;
		              for(int i = 0; i <jArray2.length();i++ ) {		            	 		            	  		            	  		            	  
		            		  JSONObject json2 = jArray2.getJSONObject(i);
		            		if(json2.getString("weekday").equals("sunday"))
		            			weekday = Calendar.SUNDAY;
		            		else if(json2.getString("weekday").equals("monday"))
		            			weekday = Calendar.MONDAY; 
		            		else if(json2.getString("weekday").equals("tuesday"))
		            			weekday = Calendar.TUESDAY;
		            		else if(json2.getString("weekday").equals("wednesday"))
		            			weekday = Calendar.WEDNESDAY;
		            		else if(json2.getString("weekday").equals("thursday"))
		            			weekday = Calendar.THURSDAY;
		            		else if(json2.getString("weekday").equals("friday"))
		            			weekday = Calendar.FRIDAY;
		            		else if(json2.getString("weekday").equals("saturday"))
		            			weekday = Calendar.SATURDAY;
		            			
		            		
		            		  //array = json2.getString("date").split("-");
		            	 // date_and_time = new WeekdayAndTime(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),json2.getInt("time"));		       
		            	  tmp = new RegularEvent(json2.getString("r_event_name"),json2.getInt("r_event_id"),weekday,json2.getInt("time"),
		            			  	json2.getInt("duration"),json2.getString("venue"));		            	 
		            	  Global.active_user.AddRegularEvent(new RegularEventNode(tmp));		            	 
		              
		              }
		              
		             // Global.agenda_list.add(json2.getString("r_event_name"));
	            	 // Global.agenda_id_list.add(json2.getInt("r_event_id"));
		              RegularEventNode ptr = Global.active_user.schedule_ptr,starting_point = Global.active_user.schedule_ptr;
	            	 /*weekday = DateAndTime.Now().weekday();
	            	
	                 while (weekday>ptr.regular_event.begin.week_day&&!ptr.equals(starting_point)&&ptr.next != null);
	                 ptr = ptr.next;
	                 starting_point = ptr;*/
	            	 do {
	            		 Global.agenda_list.add(ptr.regular_event.regular_event_name+
	            				 "\n\nFrom: "+ptr.regular_event.begin.print()+
	            				 "\nTo: "+ptr.regular_event.end.print()+
	            				 "\nVenue: "+ptr.regular_event.venue);
		            	  Global.agenda_id_list.add(ptr.regular_event.regular_event_id);
	                 ptr = ptr.next;
	            	 }
	                 while ((!ptr.equals(starting_point)&&ptr.next != null));
		         	}
		catch(Exception e)
		{
			// Toast.makeText(getApplicationContext(),"exception!", Toast.LENGTH_LONG).show();
			
		}
	        Global.initialization_is_completed = true;
			// TODO Auto-generated method stub
			return null;
		}
		 protected void onPostExecute(String file_url) {
	        	if (pDialog != null) { 
	                pDialog.dismiss();
	           }

		 }
}
}