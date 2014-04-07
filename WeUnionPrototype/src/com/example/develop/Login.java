package com.example.develop;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import comp3111project.DateAndTime;

import comp3111project.EventNode;
import comp3111project.Events;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	
	private EditText id, pass;
	private Button mSubmit, mRegister;
	
	 // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    private static final String LOGIN_URL = "http://124.244.60.23/weu/login.php";
    private static final String REGISTER_URL = "http://124.244.60.23/weu/register.php";
    
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		id = (EditText)findViewById(R.id.et_id);
		pass = (EditText)findViewById(R.id.et_pw);
		
		mSubmit = (Button)findViewById(R.id.b_confirm);
		mRegister = (Button)findViewById(R.id.b_reg);
		
		mSubmit.setOnClickListener(this);
		mRegister.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.b_confirm:
				new AttemptLogin().execute();
			break;
		case R.id.b_reg:
				new AttemptRegister().execute();
			break;

		default:
			break;
		}
	}
	
	class AttemptLogin extends AsyncTask<String, String, String> {

		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Connecting...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub

            int success;
            String username = id.getText().toString();
            String password = pass.getText().toString();
    		User user = User.getInstance();
            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                JSONArray jArray = jsonParser.makeHttpRequest(LOGIN_URL, params);

                JSONObject json = jArray.getJSONObject(0);
                success = json.getInt(TAG_SUCCESS);
//***
               
                	/*
                    List<NameValuePair> params2 = new ArrayList<NameValuePair>();
                    params2.add(new BasicNameValuePair("username",username));	//put the name here, it will return the events, here i use admin
                    Events tmp ;
                    JSONArray jArray2 = jsonParser.makeHttpRequest(Global.EVENT_URL, params2);
        			EventNode ptr = Global.active_user.event_ptr;
        			Global.count = jArray2.length();
                   for(int i = 0; i <jArray2.length();i++ ) {
                	 Global.test++;
                	   JSONObject json2 = jArray2.getJSONObject(i);
        				String [] array = json2.getString("date").split("-");
        				DateAndTime date_and_time = new DateAndTime(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),json2.getInt("time"));
                	  // name = json2.getString("event_name");
        tmp = new Events(json2.getString("event_name"),json2.getInt("event_id"),Global.active_user,
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
                   */
              
                //***
                
                
                
                
                if (success == 1) {
                	Intent i = new Intent(Login.this, MainActivity.class);
                	user.setId(username);
                	finish();
    				startActivity(i);
                	return json.getString(TAG_MESSAGE);
                }else{
                	return json.getString(TAG_MESSAGE);
                	
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
         
            return null;
			
		}

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}
	
	class AttemptRegister extends AsyncTask<String, String, String> {

		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Registering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub

            String username = id.getText().toString();
            String password = pass.getText().toString();

            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                JSONArray jArray = jsonParser.makeHttpRequest(REGISTER_URL, params);

                JSONObject json = jArray.getJSONObject(0);

               	return json.getString(TAG_MESSAGE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
       
           return null;
			
		}

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}

}
