package com.example.weunion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import android.app.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class Schedule extends Activity {
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	ArrayList<String> dateStringArray=new ArrayList<String>();
	ArrayList<String> wholeweekdays=new ArrayList<String>();
	ArrayList<String> eventlist = new ArrayList<String>();
	ArrayList<String> durationlist= new ArrayList<String>();
	ArrayList<String> begindate= new ArrayList<String>();
	ArrayList<Integer> begintime= new ArrayList<Integer>();
	Button addEvent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		addEvent = (Button) findViewById(R.id.addEvent);
		
		addEvent.setOnClickListener (new OnClickListener() {
			
			public void onClick (View v){
				Intent intent=new Intent (v.getContext(),CreateEvent.class);
				startActivityForResult(intent,0);
				finish();
				
			}
			});
		
		Calendar c = Calendar.getInstance();


		DateFormat current_year = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();

		DateFormat current_month = new SimpleDateFormat("MMMMM");

		DateFormat current_weekday = new SimpleDateFormat("E");

		DateFormat current_date = new SimpleDateFormat("dd");
		
		DateFormat current_full=new SimpleDateFormat("yyyy-MM-dd");

		TextView display_month =(TextView) findViewById(R.id.textMonth);
		display_month.setText(current_month.format(cal.getTime()));
		
		TextView display_year =(TextView) findViewById(R.id.textYear);
		display_year.setText(current_year.format(cal.getTime()));
		/*switch(Integer.valueOf(current_year.format(cal.getTime()))){
		
		}*/
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		DateFormat df = new SimpleDateFormat("dd");
		  for (int i = 0; i < 7; i++) {
		   dateStringArray.add(df.format(c.getTime()));
		   wholeweekdays.add(current_full.format(c.getTime()));
		   c.add(Calendar.DATE, 1);
		  }
		  	TextView display_date0 =(TextView) findViewById(R.id.textDate0);
			display_date0.setText(dateStringArray.get(0)+" ");
			TextView display_date1 =(TextView) findViewById(R.id.textDate1);
			display_date1.setText(dateStringArray.get(1)+" ");
			TextView display_date2 =(TextView) findViewById(R.id.textDate2);
			display_date2.setText(dateStringArray.get(2)+" ");
			TextView display_date3 =(TextView) findViewById(R.id.textDate3);
			display_date3.setText(dateStringArray.get(3)+" ");
			TextView display_date4 =(TextView) findViewById(R.id.textDate4);
			display_date4.setText(dateStringArray.get(4)+" ");
			TextView display_date5 =(TextView) findViewById(R.id.textDate5);
			display_date5.setText(dateStringArray.get(5)+" ");
			TextView display_date6 =(TextView) findViewById(R.id.textDate6);
			display_date6.setText(dateStringArray.get(6)+" ");
				
		/*For adding TextView dynamically under RelativeLayout
			RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
			TextView child = getLayoutInflater().inflate(R.layout.child);
			layout.addView(child);*/
				
			new AttemptShowEvents().execute();
			while(!Global.initialization_is_completed);
			for(int day=0;day<wholeweekdays.size();day++)
			{
				for(int n=0;n<eventlist.size();n++){
					if(wholeweekdays.get(day).equals(begindate.get(n)))
						
			{ int layoutID=0;
						if(day==0){
							layoutID=R.id.sundayRelativeLayout;
						}
						if(day==1){
							layoutID=R.id.mondayRelativeLayout;
						}
						if(day==2){
							layoutID=R.id.tuesdayRelativeLayout;
						}
						if(day==3){
							layoutID=R.id.wednesdayRelativeLayout;
						}
						if(day==4){
							layoutID=R.id.thursdayRelativeLayout;
						}
						if(day==5){
							layoutID=R.id.fridayRelativeLayout;
						}
						if(day==6){
							layoutID=R.id.saturdayRelativeLayout;
						}
				RelativeLayout targetday = (RelativeLayout)findViewById(layoutID);
				TextView tv=new TextView(getApplicationContext());
				int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());
				
				tv.setText(eventlist.get(n));
				tv.setTextColor(Color.BLACK);
				tv.setId(5);
				tv.setBackgroundColor(Color.YELLOW);
				RelativeLayout.LayoutParams lp= 
						new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT
								);

				lp.height=px*Integer.valueOf(durationlist.get(n));
				lp.setMargins(0, px*begintime.get(n)+px*23/10,0, 0);
				tv.setLayoutParams(lp);
				targetday.addView(tv, lp);
				}
			}}
Global.initialization_is_completed = false;
	}
	class AttemptShowEvents extends AsyncTask<String, String, String> {
		 //for debug purpose only
        int i = 0;
	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(Schedule.this);
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
		               Global.active_user.event_ptr = null;
			           JSONArray jArray2 = jsonParser.makeHttpRequest(Global.EVENT_URL, params2);
			           
			           Events tmp ;
			           
			           for(int i = 0; i <jArray2.length();i++ ) {
			        	   
			           JSONObject json2 = jArray2.getJSONObject(i);
			           eventlist.add(json2.getString("event_name"));
			           durationlist.add(json2.getString("duration"));
			           begintime.add(json2.getInt("time"));
			           begindate.add(json2.getString("date"));
			          // begindate.add(json2.getString("begin"));
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
		        	   			Global.my_event_list.add(ptr.event.event_name);
		        	   			Global.joined_event_list.add(ptr.event.event_name);
		        	   		
		        	   			ptr = ptr.next;
		           		i++;
		           		}

	    	   		}
	    	   catch(Exception e)
	    	   {
	
	    	   }
		  Global.initialization_is_completed = true;
	    	   // TODO Auto-generated method stub
			return null;
		}
		 protected void onPostExecute(String file_url) {
	        	if (pDialog != null) { 
	                pDialog.dismiss();
	           }
	        	/*Toast.makeText(Schedule.this,"i = "+i+"eventlist.size(): "+wholeweekdays.get(4)+durationlist.get(0),Toast.LENGTH_LONG).show();	*/
		 }
		 }	
		 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule, menu);
		return true;
	}

	}

