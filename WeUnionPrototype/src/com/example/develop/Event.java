package com.example.develop;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.View.OnClickListener;
import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Event extends Activity implements OnClickListener{
	Button create_event_button;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    ArrayList<String> list = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		 ListView event_listview = (ListView) findViewById(R.id.event_list);
	     create_event_button = (Button) findViewById(R.id.event_create_new_event_button);
	     create_event_button.setOnClickListener(this);
	//     JSONParser jsonParser = new JSONParser();
	    Global.active_user.name = User.getInstance().getId();
	  new AttemptShowEvents().execute();

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        android.R.layout.simple_list_item_1, list);
	    event_listview.setAdapter(adapter);
	    event_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	    {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				EventNode ptr =  Global.active_user.event_ptr;
				String event_name = (String) a.getAdapter().getItem(position);
				while(ptr!=null)
				{
					 if (ptr.event.event_name == event_name)
				{
						 Global.active_event =new EventNode (ptr.event);
				break;
				}
					 else 
						 ptr = ptr.next;
				}
				 Toast.makeText(getApplicationContext(),Global.active_event.event.event_name+"is selected", Toast.LENGTH_LONG).show();
//				Global.active_event = 
				Intent i = new Intent(Event.this, EventDetail.class);
				startActivity(i);
			}
	    	
	    });
	}

	class AttemptShowEvents extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			   try{

			    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		               params2.add(new BasicNameValuePair("username",User.getInstance().getId()));	//put the name here, it will return the events, here i use admin
		               Events tmp ;
		               JSONArray jArray2 = jsonParser.makeHttpRequest(Global.EVENT_URL, params2);
		   			EventNode ptr = Global.active_user.event_ptr;
		              for(int i = 0; i <jArray2.length();i++ ) {
		           	 
		           	   JSONObject json2 = jArray2.getJSONObject(i);
		   				String [] array = json2.getString("date").split("-");
		   				DateAndTime date_and_time = new DateAndTime(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),json2.getInt("time"));
		           	  // name = json2.getString("event_name");
		   tmp = new Events(json2.getString("event_name"),json2.getInt("event_id"),new comp3111project.User(json2.getString("holder"),0),
		   date_and_time,json2.getInt("duration"),"");

		           	    if(ptr == null)
		   				{
		   					ptr = new EventNode(tmp);
		   					Global.active_user.event_ptr = ptr;
		   				}
		           	    else
		           	    {
		           	    	ptr.next =new EventNode(tmp);
		           	    	ptr = ptr.next;
		           	    }
		              }
			    if(list.size()==0)
		   for (ptr = Global.active_user.event_ptr; ptr!=null; ptr = ptr.next) {
		     list.add(ptr.event.event_name);
		}
		}
		catch(Exception e)
		{
			 Toast.makeText(getApplicationContext(),"exception!", Toast.LENGTH_LONG).show();
			
		}
			// TODO Auto-generated method stub
			return null;
		}
	
	}


	
	public void onClick(View v) {
	
		Intent i ;
		// TODO Auto-generated method stub
		if(v.getId()==R.id.event_create_new_event_button)
		{ i = new Intent(Event.this, CreateEvent.class);
		list = new ArrayList<String>();
		Global.active_user.event_ptr = null;
		finish();
		startActivity(i);
		
		}
		else
			 Toast.makeText(getApplicationContext(),"exception!", Toast.LENGTH_LONG).show();
	}

	
	}

