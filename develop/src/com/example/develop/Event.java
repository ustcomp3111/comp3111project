package com.example.develop;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.TextView;

public class Event extends Activity {
	
    private ProgressDialog pDialog;
    private static final String LOGIN_URL = "http://124.244.60.23/weu/event.php";
    JSONParser jsonParser = new JSONParser();
    TextView Events;
    String temp="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		Events = (TextView)findViewById(R.id.events);
		new ShowEvents().execute();
	}

	@Override
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
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
            List<String> Events_joined = new ArrayList<String>();
    		User user = User.getInstance();
    		
            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", user.getId()));

                JSONArray jArray = jsonParser.makeHttpRequest(LOGIN_URL, params);

               for(int i = 0; i <jArray.length();i++ ) {
            	                   JSONObject json = jArray.getJSONObject(i);

            	                   Events_joined.add(json.getString("event_name"));
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
            Events.setText(temp);
        }
		
	}


}
