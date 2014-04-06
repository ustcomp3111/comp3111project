package com.example.develop;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import comp3111project.EventNode;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Event extends Activity {
	
    private ProgressDialog pDialog;
   // public static String LOGIN_URL = "http://124.244.60.23/weu/event.php";
    JSONParser jsonParser = new JSONParser();
   // TextView Events;
    //String temp="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		 final ListView event_listview = (ListView) findViewById(R.id.event_list);
	//	TextView test_view = (TextView) findViewById(R.id.event_textView);
		 //	Events = (TextView)findViewById(R.id.events);
		//new ShowEvents().execute();

	     ArrayList<String> list = new ArrayList<String>();
	    EventNode ptr;
	    /*if(ptr == null)
	    	  Toast.makeText(getApplicationContext(),"ptr = null!", Toast.LENGTH_LONG).show();
	    if(ptr.next == null)
	    	  Toast.makeText(getApplicationContext(),"ptr.next = null!", Toast.LENGTH_LONG).show();
	  */
	    for (ptr = Global.user.event_ptr; ptr!=null; ptr = ptr.next) {
	      list.add(ptr.event.event_name);
	    }
/*	  list.add("test");
	  list.add("test2");*/
	  	  //Toast.makeText(getApplicationContext(),"string: "+Global.string, Toast.LENGTH_LONG).show();
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        android.R.layout.simple_list_item_1, list);
	    event_listview.setAdapter(adapter);
	    event_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	    {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Global.active_event = 
				Intent i = new Intent(Event.this, Event_detail.class);
				startActivity(i);
			}
	    	
	    });
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event, menu);
		return true;
	}
	
	class ShowEvents extends AsyncTask<String, String, String> {

		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Event.this);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
		
		@Override
/*		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
            List<String> Events_joined = new ArrayList<String>();
            
            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", Global.user.name));	//put the name here, it will return the events, here i use admin

                JSONArray jArray = jsonParser.makeHttpRequest(Global.EVENT_URL, params);

               for(int i = 0; i <jArray.length();i++ ) {
            	                   JSONObject json = jArray.getJSONObject(i);

            	                   Events_joined.add(json.getString("event_name"));	//an example to show you how to get data from json object
               }

               for(int i = 0; i<Events_joined.size();i++) {
            	   temp = temp+Events_joined.get(i).toString()+"\n";
            	   
               }
               
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
			
		}

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
         //   Events.setText(temp);
        }

		@Override

		
	}

*/
}
