package com.example.weunion;

import java.util.ArrayList;
import android.app.DatePickerDialog;
import android.os.Bundle;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
public class CreateEvent extends Activity implements OnClickListener,RadioGroup.OnCheckedChangeListener {
	Button confirm_button,set_date_button,set_begin_time_button;
	EditText set_event_name,
	set_event_year, set_event_month ,set_event_day ,
	set_event_venue;//, set_event_start_time;
	TextView selected_time,selected_duration;
	RadioGroup select_min,select_duration_min;
	RadioButton radio_button_00,radio_button_15,radio_button_30,radio_button_45,
	radio_button_duration_00,radio_button_duration_15,radio_button_duration_30,radio_button_duration_45;
	NumberPicker select_hour,select_duration_hour;
	JSONArray jArray;
	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
	 Intent i;
	 JSONParser jsonParser = new JSONParser();
	 private ProgressDialog pDialog;
	 private Calendar now;
	 final int SET_DATE_DIALOG_ID = 1;
	 int year,month,day,hour,min,duration_hour,duration_min;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);

			 confirm_button = (Button)findViewById(R.id.create_event_confirm_button);
			 set_date_button = (Button) findViewById(R.id.create_event_set_date_button);
			 now = Calendar.getInstance();
			 year = now.get(Calendar.YEAR);
			 month = now.get(Calendar.MONTH);
			 day = now.get(Calendar.DAY_OF_MONTH);
			 set_date_button.setText(now.get(Calendar.DAY_OF_MONTH)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.YEAR));
			 
			 set_event_name = (EditText)findViewById(R.id.create_event_event_name_input);
			 //set_event_year = (EditText)findViewById(R.id.create_event_year);
			 //set_event_month = (EditText)findViewById(R.id.create_event_month);
			// set_event_day = (EditText)findViewById(R.id.create_event_day);
			// set_event_start_time = (EditText)findViewById(R.id.create_event_time);
			 set_event_venue = (EditText)findViewById(R.id.create_event_event_venue);
			 //set_event_duration = (EditText)findViewById(R.id.create_event_set_duration);
			 confirm_button.setOnClickListener(this);
			 set_date_button.setOnClickListener(this);
			 selected_time = (TextView) findViewById(R.id.create_event_selected_time);
			 selected_duration = (TextView) findViewById(R.id.create_event_show_selected_duration);
			 
			 select_min = (RadioGroup) findViewById(R.id.create_event_set_min_radioGroup);
			 select_duration_min = (RadioGroup) findViewById(R.id.create_event_set_duration_radioGroup);
			 select_hour = (NumberPicker) findViewById(R.id.create_event_hour_Picker);
			 select_hour.setMaxValue(23);
			 select_hour.setMinValue(0);
			 select_hour.setValue(0);
			 select_hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener (){
             public void onValueChange(NumberPicker view, int oldValue, int newValue) {
               
               hour = newValue;
             if(min!=0)
               selected_time.setText(hour+":"+min*15);
             else
            	 selected_time.setText(hour+":00"); 
             }
        });
			 select_duration_hour = (NumberPicker) findViewById(R.id.create_event_duration_hour_Picker);
			 select_duration_hour.setMaxValue(23);
			 select_duration_hour.setMinValue(0);
			 select_duration_hour.setValue(0);
			 select_duration_hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener (){
             public void onValueChange(NumberPicker view, int oldValue, int newValue) {
               
               duration_hour = newValue;
             selected_duration.setText(duration_hour+"hour(s) "+duration_min*15+"minutes");
             }
        });
			 radio_button_00 = (RadioButton) findViewById(R.id.create_event_set_00);
			 radio_button_15 = (RadioButton) findViewById(R.id.create_event_set_15);
			 radio_button_30 = (RadioButton) findViewById(R.id.create_event_set_30);
			 radio_button_45 = (RadioButton) findViewById(R.id.create_event_set_45);
			 select_min.setOnCheckedChangeListener(this);
			 radio_button_duration_00 = (RadioButton) findViewById(R.id.create_event_duration_set_00);
			 radio_button_duration_15 = (RadioButton) findViewById(R.id.create_event_duration_set_15);
			 radio_button_duration_30 = (RadioButton) findViewById(R.id.create_event_duration_set_30);
			 radio_button_duration_45 = (RadioButton) findViewById(R.id.create_event_duration_set_45);
			 select_duration_min.setOnCheckedChangeListener(this);
			 //set_begin_time_button.setOnClickListener(this);
			// datepicker = (DatePicker) findViewById(R.id.create_event_datepicker);
			 //datepicker.init(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH), null);
			 
			 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	
		getMenuInflater().inflate(R.menu.create_event, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.create_event_confirm_button)
		{
		if(set_event_name.getText().toString().trim().length() ==0)
			Toast.makeText(getApplicationContext(),"Event name cannot be empty!", Toast.LENGTH_LONG).show();
		else if(duration_hour==0&&duration_min==0)
			Toast.makeText(getApplicationContext(),"Duration cannot be zero!", Toast.LENGTH_LONG).show();
		else if(set_event_venue.getText().toString().trim().length() ==0)
			Toast.makeText(getApplicationContext(),"Venue cannot be empty!", Toast.LENGTH_LONG).show();
		else
		{
			new AttemptCreateEvent().execute();
			    
			
		}
		}
		else if(v.getId()==R.id.create_event_set_date_button)
		{
			
			 showDialog(SET_DATE_DIALOG_ID);
			
		}
/*		else if(v.getId()==R.id.create_event_set_begin_time_button) 
		{}
	*/	Log.d("hi","here2");
	}
	@Override
	 protected Dialog onCreateDialog(int id) {
	  // TODO Auto-generated method stub
	  switch(id){
	   case SET_DATE_DIALOG_ID:
	    return new DatePickerDialog(this,
	    new DatePickerDialog.OnDateSetListener(){
	 	   @Override
	 	   public void onDateSet(DatePicker view, int y, 
	 	     int m, int d) {
	 	    year = y;
	 	    month = m;
	 	    day = d;
	 	    set_date_button.setText(d+"-"+(m+1)+"-"+y);
	 		   // TODO Auto-generated method stub
	 	   }
	 	 },now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
	  
	   default:
	    return null;
	    
	  }
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
		              // params2.add(new BasicNameValuePair("host_name",User.getInstance().getId()));
		               params2.add(new BasicNameValuePair("host_name",Global.active_user.name));
		               // params2.add(new BasicNameValuePair("begin_date",set_event_year.getText().toString()+"-"+set_event_month.getText().toString()+"-"+set_event_day.getText().toString()));		               
		               params2.add(new BasicNameValuePair("begin_date",year+"-"+(month+1)+"-"+day));	
		               params2.add(new BasicNameValuePair("duration",String.valueOf(duration_hour*4+duration_min)));		    
		               params2.add(new BasicNameValuePair("begin_time",String.valueOf(hour*4+min)));
		               params2.add(new BasicNameValuePair("venue",set_event_venue.getText().toString()));

		                jArray = jsonParser.makeHttpRequest(Global.POST_URL, params2);
		            	Log.d("hi","here3");
		               success = jArray.getJSONObject(0).getInt("success");
		               if (success==1)
		               {		            	
		                  	 i = new Intent(CreateEvent.this, EventMenu.class);
		                  	finish();
		    				startActivity(i);
		               }
		               return jArray.getJSONObject(0).getString("message");
		               
		
		}
		catch(Exception e)
		{
			 Toast.makeText(getApplicationContext(),"Failed to create event!", Toast.LENGTH_LONG).show();		
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



	@Override
	public void onCheckedChanged(RadioGroup g, int id) {
		// TODO Auto-generated method stub
		if(id == radio_button_00.getId())
			min = 0;
		else if(id == radio_button_15.getId())
			min = 1;
		else if(id == radio_button_30.getId())
			min = 2;
		else if (id == radio_button_45.getId())
			min = 3;
		else if(id == radio_button_duration_00.getId())
			duration_min = 0;
		else if(id == radio_button_duration_15.getId())
			duration_min = 1;
		else if(id == radio_button_duration_30.getId())
			duration_min = 2;
		else if (id == radio_button_duration_45.getId())
			duration_min = 3;
	    if(min!=0) 
		selected_time.setText(hour+":"+min*15);
	    else
	   	selected_time.setText(hour+":00");
		selected_duration.setText(duration_hour+"hour(s) "+duration_min*15+"minutes");
	}
	public void onBackPressed() {
	    finish();
	    startActivity(new Intent(this,EventMenu.class));
	}	
}

