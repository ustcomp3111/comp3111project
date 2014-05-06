package com.example.weunion;

import java.util.ArrayList;
import android.app.DatePickerDialog;
import android.os.Bundle;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import com.example.weunion.CreateEvent.AttemptCreateEvent;
import com.example.weunion.CreateEvent.AttemptEditEvent;

import comp3111project.RegularEventNode;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
public class CreateRegularEvent extends Activity implements OnClickListener,RadioGroup.OnCheckedChangeListener {
	Button confirm_button,set_weekday_button,set_begin_time_button;
	EditText set_event_name,
	//set_event_year, set_event_month ,set_event_day ,
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
	 final int SET_WEEKDAY_DIALOG_ID = 1;
	 int hour,min,duration_hour,duration_min;
	String weekday = "sunday";
	int w = 1;
	boolean conflict = false;
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_regular_event);
		 int p=0;
		 RegularEventNode ptr = Global.active_user.schedule_ptr;
		 while(p<Global.regular_event_position&&ptr.next!=null)
			 {
			 p++;
			 ptr = ptr.next;
			 }
		 
		// id_tmp = ptr.regular_event.regular_event_id;
			 confirm_button = (Button)findViewById(R.id.create_regular_event_button);
			 set_weekday_button = (Button) findViewById(R.id.create_regular_event_set_weekday_button);
			
			// year = now.get(Calendar.YEAR);
			 //month = now.get(Calendar.MONTH);
			 //day = now.get(Calendar.DAY_OF_MONTH);
			 //set_weekday_button.setText("Starts on every: "+weekday);
			 
			 set_event_name = (EditText)findViewById(R.id.create_regular_event_event_name_input);
			 //set_event_year = (EditText)findViewById(R.id.create_event_year);
			 //set_event_month = (EditText)findViewById(R.id.create_event_month);
			// set_event_day = (EditText)findViewById(R.id.create_event_day);
			// set_event_start_time = (EditText)findViewById(R.id.create_event_time);
			
			 if(Global.edit_event)
			 {			 
				 set_event_name.setText(ptr.regular_event.regular_event_name);
				
				 switch(ptr.regular_event.begin.week_day)
					{
					case(1):
					weekday = "sunday";
					w = 1;
					break;
					case(2):
					weekday = "monday";
					w = 2;
					break;
					case(3):
					weekday = "tuesday";
					w = 3;
					break;
					case(4):
					weekday = "wednesday";
					w = 4;
					break;
					case(5):
					weekday = "thursday";
					w = 5;
					break;
					case(6):
					weekday = "friday";
					w = 6;
					break;
					case(7):
					weekday = "saturday";
					w = 7;
					break;
					default:
					weekday = "sunday";
					w = 7;
					}
				
			 }
			 set_weekday_button.setText("Starts on every: "+weekday);
			// set_weekday_button.setText(weekday);	
			 set_event_venue = (EditText)findViewById(R.id.create_regular_event_event_venue);
			 //set_event_duration = (EditText)findViewById(R.id.create_event_set_duration);
			 confirm_button.setOnClickListener(this);
			 set_weekday_button.setOnClickListener(this);
			 selected_time = (TextView) findViewById(R.id.create_regular_event_selected_time);
			 selected_duration = (TextView) findViewById(R.id.create_regular_event_show_selected_duration);
			 
			 select_min = (RadioGroup) findViewById(R.id.create_regular_event_set_min_radioGroup);
			 select_duration_min = (RadioGroup) findViewById(R.id.create_regular_event_set_duration_radioGroup);
			 select_hour = (NumberPicker) findViewById(R.id.create_regular_event_hour_Picker);
			 select_hour.setMaxValue(23);
			 select_hour.setMinValue(0);
			
			 int tmp = (ptr.regular_event.begin.time_slot/4);
			 if(!Global.edit_event)
			 select_hour.setValue(0);			
			else
			select_hour.setValue(tmp);
			 
			 select_hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener (){
             public void onValueChange(NumberPicker view, int oldValue, int newValue) {
               
               hour = newValue;
             if(min!=0)
               selected_time.setText(hour+":"+min*15);
             else
            	 selected_time.setText(hour+":00"); 
             }
        });
			 select_duration_hour = (NumberPicker) findViewById(R.id.create_regular_event_duration_hour_Picker);
			 select_duration_hour.setMaxValue(23);
			 select_duration_hour.setMinValue(0);
			
			 tmp = ptr.regular_event.duration/4;
			 if(!Global.edit_event)
			 select_duration_hour.setValue(0);			
			else
			select_duration_hour.setValue(tmp);
			 
			 select_duration_hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener (){
             public void onValueChange(NumberPicker view, int oldValue, int newValue) {
               
               duration_hour = newValue;
             selected_duration.setText(duration_hour+"hour(s) "+duration_min*15+"minute(s)");
             }
        });
			 radio_button_00 = (RadioButton) findViewById(R.id.create_regular_event_set_00);
			 radio_button_15 = (RadioButton) findViewById(R.id.create_regular_event_set_15);
			 radio_button_30 = (RadioButton) findViewById(R.id.create_regular_event_set_30);
			 radio_button_45 = (RadioButton) findViewById(R.id.create_regular_event_set_45);
			 select_min.setOnCheckedChangeListener(this);
			 radio_button_duration_00 = (RadioButton) findViewById(R.id.create_regular_event_duration_set_00);
			 radio_button_duration_15 = (RadioButton) findViewById(R.id.create_regular_event_duration_set_15);
			 radio_button_duration_30 = (RadioButton) findViewById(R.id.create_regular_event_duration_set_30);
			 radio_button_duration_45 = (RadioButton) findViewById(R.id.create_regular_event_duration_set_45);
			 select_duration_min.setOnCheckedChangeListener(this);
			 //set_begin_time_button.setOnClickListener(this);
			// datepicker = (DatePicker) findViewById(R.id.create_regular_event_datepicker);
			 //datepicker.init(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH), null);
			 
			 if(Global.edit_event)
			 {
				
				 
				 set_event_venue.setText(ptr.regular_event.venue);
				 confirm_button.setText("Edit Event");
				 if(ptr.regular_event.begin.time_slot%4==0)
				 select_min.check(radio_button_00.getId());
				 else if(ptr.regular_event.begin.time_slot%4==1)
				 select_min.check(radio_button_15.getId());
				 else if(ptr.regular_event.begin.time_slot%4==2)
				 select_min.check(radio_button_30.getId());
				 else
				 select_min.check(radio_button_45.getId());
				 
				 if(ptr.regular_event.duration%4==0)
				 select_duration_min.check(radio_button_duration_00.getId());
				 else if(ptr.regular_event.duration%4==1)
				 select_duration_min.check(radio_button_duration_15.getId());
				 else if(ptr.regular_event.duration%4==2)
				 select_duration_min.check(radio_button_duration_30.getId());
				 else
				 select_duration_min.check(radio_button_duration_45.getId());
				 
				 hour = ptr.regular_event.begin.time_slot/4;
				 min = ptr.regular_event.begin.time_slot%4;
				 selected_time.setText(hour+":"+min*15);
				 duration_hour = ptr.regular_event.duration/4;
				 duration_min = ptr.regular_event.duration%4;
	             selected_duration.setText(duration_hour+"hour(s) "+duration_min*15+"minute(s)");
			 }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	
		getMenuInflater().inflate(R.menu.create_regular_event, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.create_regular_event_button)
		{
		if(set_event_name.getText().toString().trim().length() ==0)
			Toast.makeText(getApplicationContext(),"Event name cannot be empty!", Toast.LENGTH_LONG).show();
		else if(duration_hour==0&&duration_min==0)
			Toast.makeText(getApplicationContext(),"Duration cannot be zero!", Toast.LENGTH_LONG).show();
		else if(set_event_venue.getText().toString().trim().length() ==0)
			Toast.makeText(getApplicationContext(),"Venue cannot be empty!", Toast.LENGTH_LONG).show();
		else		
		{
		if(!Global.edit_event)
			new AttemptCreateRegularEvent().execute();
			else
			new AttemptEditRegularEvent().execute();
		}	    					
		}
		else if(v.getId()==R.id.create_regular_event_set_weekday_button)
		{
			
			 showDialog(SET_WEEKDAY_DIALOG_ID);
			
		}
/*		else if(v.getId()==R.id.create_event_set_begin_time_button) 
		{}
	*/	Log.d("hi","here2");
	}
	@Override
	 protected Dialog onCreateDialog(int id) {
	  // TODO Auto-generated method stub
		final CharSequence[] weekdays={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	  switch(id){
	   case SET_WEEKDAY_DIALOG_ID:
	   {
		   AlertDialog.Builder set_week_day_dialog =
	new AlertDialog.Builder(CreateRegularEvent.this).setSingleChoiceItems(weekdays,0,
			new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				switch(which)
				{
				case(0):
				weekday = "sunday";
				w = 1;
				break;
				case(1):
				weekday = "monday";
				w = 2;
				break;
				case(2):
				weekday = "tuesday";
				w = 3;
				break;
				case(3):
				weekday = "wednesday";
				w = 4;
				break;
				case(4):
				weekday = "thursday";
				w = 5;
				break;
				case(5):
				weekday = "friday";
				w = 6;
				break;
				case(6):
				weekday = "saturday";
				w = 7;
				break;
				default:
				weekday = "sunday";
				w = 7;
				}
				dialog.dismiss();
				 set_weekday_button.setText("Starts on every: "+weekday);
				}
			});
	return  set_week_day_dialog.create();
	   }
	   
	   default:
	    return null;
	    
	  }
	 }
	    


	class AttemptCreateRegularEvent extends AsyncTask<String, String, String> {
		 
		int success = 0;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateRegularEvent.this);
            pDialog.setMessage("Creating event...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
		  @Override
		protected String doInBackground(String... arg0) {
			  
			  
			  if(Global.active_user.AddRegularEvent(new RegularEventNode(w,(hour*4+min),(duration_hour*4+duration_min))))
					  {		  
			  try{		               
		              // params2.add(new BasicNameValuePair("host_name",User.getInstance().getId()));
		               params2.add(new BasicNameValuePair("host_name",Global.active_user.name));
		               params2.add(new BasicNameValuePair("event_name",set_event_name.getText().toString()));
		               // params2.add(new BasicNameValuePair("begin_date",set_event_year.getText().toString()+"-"+set_event_month.getText().toString()+"-"+set_event_day.getText().toString()));		               
		               params2.add(new BasicNameValuePair("weekday",weekday));	
		               params2.add(new BasicNameValuePair("time",String.valueOf(hour*4+min)));
		               params2.add(new BasicNameValuePair("duration",String.valueOf(duration_hour*4+duration_min)));
		               params2.add(new BasicNameValuePair("venue",set_event_venue.getText().toString()));
		                jArray = jsonParser.makeHttpRequest(Global.CREATE_REGULAR_EVENT_URL, params2);
		            	Log.d("hi","here3");
		               success = jArray.getJSONObject(0).getInt("success");
		               if (success==1)
		               {		            	
		                  	 i = new Intent(CreateRegularEvent.this, EventMenu.class);
		                  	finish();
		    				startActivity(i);
		               }
		               return jArray.getJSONObject(0).getString("message");
		               
		
		}
		catch(Exception e)
		{
			// Toast.makeText(getApplicationContext(),"Failed to create event!", Toast.LENGTH_LONG).show();		
		}
					  }
			  else
			  conflict = true;
			// TODO Auto-generated method stub
			return null;
		}
	
		  protected void onPostExecute(String file_url) {
	            pDialog.dismiss();
	          if(conflict)
	        		Toast.makeText(CreateRegularEvent.this,"The event Conflicts with other regular events!\nCheck your agenda carefully!", Toast.LENGTH_LONG).show();  
	          else if (file_url != null){
	            	Toast.makeText(CreateRegularEvent.this, file_url, Toast.LENGTH_LONG).show();
	            }
	 
	        }
	}
	class AttemptEditRegularEvent extends AsyncTask<String, String, String> {
		 
		int success = 0;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateRegularEvent.this);
            pDialog.setMessage("Editing event...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
		  @Override
		protected String doInBackground(String... arg0) {
			   try{


		               params2.add(new BasicNameValuePair("r_event_name",set_event_name.getText().toString()));
		              // params2.add(new BasicNameValuePair("host_name",User.getInstance().getId()));
		               params2.add(new BasicNameValuePair("r_event_id",Integer.toString(Global.agenda_id_list.get(Global.regular_event_position))));
		               params2.add(new BasicNameValuePair("r_holder",Global.active_user.name));
		               // params2.add(new BasicNameValuePair("begin_date",set_event_year.getText().toString()+"-"+set_event_month.getText().toString()+"-"+set_event_day.getText().toString()));		               
		               params2.add(new BasicNameValuePair("weekday",weekday));	
		               params2.add(new BasicNameValuePair("duration",String.valueOf(duration_hour*4+duration_min)));		    
		               params2.add(new BasicNameValuePair("begin_time",String.valueOf(hour*4+min)));
		               params2.add(new BasicNameValuePair("venue",set_event_venue.getText().toString()));

		                jArray = jsonParser.makeHttpRequest(Global.EDIT_REGULAR_EVENT_URL, params2);
		            	Log.d("hi","here3");
		               success = jArray.getJSONObject(0).getInt("success");
		               if (success==1)
		               {		            	
		                  	 i = new Intent(CreateRegularEvent.this, EventMenu.class);
		                  	 Global.edit_event = false;
		                  	 finish();
		    				startActivity(i);
		              
		               }
		               return jArray.getJSONObject(0).getString("message");
		               
		
		}
		catch(Exception e)
		{
			// Toast.makeText(getApplicationContext(),"Failed to create event!", Toast.LENGTH_LONG).show();		
		}
			// TODO Auto-generated method stub
			return null;
		}
	
		  protected void onPostExecute(String file_url) {
	            pDialog.dismiss();
	            if (file_url != null){
	            	Toast.makeText(CreateRegularEvent.this, file_url, Toast.LENGTH_LONG).show();
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
		selected_duration.setText(duration_hour+"hour(s) "+duration_min*15+" minutes");
	}
	public void onBackPressed() {
	    finish();
	    startActivity(new Intent(this,EventMenu.class));
	}	
}

