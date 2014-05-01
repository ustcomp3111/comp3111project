package com.example.weunion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Msgbox extends Fragment implements OnClickListener{
	
	public int msg_id = -1;
	public int p_msg_id = -1;
	private ListView msg;
	private Button post;
	private EditText my_msg;
	//private TextView event;
	
	ArrayList<HashMap<String,String>> postlist = new ArrayList<HashMap<String,String>>();
	ArrayList<HashMap<String,String>> templist = new ArrayList<HashMap<String,String>>();
	private SimpleAdapter adapter;
	 // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    private static final String DISPLAY_URL = "http://124.244.60.23/weu/msgbox.php";
    private static final String POST_URL = "http://124.244.60.23/weu/postmsg.php";
    
    private static final String TAG_MESSAGE = "message";

    private static final String TAG_TIME = "time";
    private static final String TAG_USERNAME = "user_name";
    private static final String TAG_MSG = "msg";
    
    public static String Event_Name = "First_test_event";	//default, use for debug


	@Override
	public	View onCreateView (LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)  {
		// TODO Auto-generated method stub
	RelativeLayout l = (RelativeLayout) inflater.inflate(R.layout.activity_msgbox,container,false);
		//super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_msgbox);
		adapter = new SimpleAdapter( getActivity(), postlist,
				 R.layout.msglist,
				 new String[] {TAG_USERNAME, TAG_MSG, TAG_TIME},
				 new int[] { R.id.msglist_username, R.id.msglist_msg, R.id.msglist_time} );		
		
		msg = (ListView)l.findViewById(R.id.msg);
		msg.setAdapter(adapter);
		msg.setTextFilterEnabled(true);
		
		post = (Button) l.findViewById(R.id.post_button);
		my_msg = (EditText) l.findViewById(R.id.post_msg);
		my_msg.setFocusable(false);
		//event = (TextView) l.findViewById(R.id.msg_event);
		//event.setText(Event_Name);
		
		post.setOnClickListener(this);
		new AttemptDisplayMsg().execute();

        Timer timer = new Timer();
        timer.schedule(task, 1000, 1500);
	return l;
	}

	 @Override
	public void onDestroy() {
	 // TODO Auto-generated method stub
	 super.onDestroy();
	 task.cancel();
	    }
	 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.post_button:
				new AttemptPost().execute();
			break;
		default:
			break;
		}
	}
	
	class UpdateMsg extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub

            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("event_name", Event_Name));
                params.add(new BasicNameValuePair("msg_id", Integer.toString(msg_id)));

                JSONArray jArray = jsonParser.makeHttpRequest(DISPLAY_URL, params);

                if (jArray!=null) {

                    for(int i = 0; i <jArray.length();i++ ) {
                 	                   JSONObject json = jArray.getJSONObject(i);
                 	                   msg_id = json.getInt("msg_id");
                 	                   HashMap<String, String> map = new HashMap<String, String>();
                 	                   map.put(TAG_USERNAME, json.getString(TAG_USERNAME)+" wrote :");
                 	                   map.put(TAG_MSG, json.getString(TAG_MSG));
                 	                   map.put(TAG_TIME, "at "+json.getString(TAG_TIME));
                 	                   
                 	                   templist.add(map);
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            
            return null;			
		}		
		
		protected void onPostExecute(String file_url) {            
        	if (p_msg_id<msg_id) {
        		for (HashMap<String, String> map : templist) postlist.add(map);
        		adapter.notifyDataSetChanged();
        		templist.clear();
        		p_msg_id = msg_id;
        		msg.setSelection(msg.getAdapter().getCount() - 1);
        	}            
        	
        }
	}
	
	private TimerTask task = new TimerTask() {
		
		 @Override
		 public void run() {
		  // TODO Auto-generated method stub
			 new UpdateMsg().execute();	
		 }
	};


	class AttemptDisplayMsg extends AsyncTask<String, String, String> {

		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Retrieving Data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub

            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("event_name", Event_Name));

                JSONArray jArray = jsonParser.makeHttpRequest(DISPLAY_URL, params);

                if (jArray!=null) {

                    for(int i = 0; i <jArray.length();i++ ) {
                 	                   JSONObject json = jArray.getJSONObject(i);
                 	                   msg_id = json.getInt("msg_id");
                 	                   HashMap<String, String> map = new HashMap<String, String>();
                 	                   map.put(TAG_USERNAME, json.getString(TAG_USERNAME)+" wrote :");
                 	                   map.put(TAG_MSG, json.getString(TAG_MSG));
                 	                   map.put(TAG_TIME, "at "+json.getString(TAG_TIME));
                 	                   
                 	                   postlist.add(map);                 	                   
										
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
			
		}

        protected void onPostExecute(String file_url) {
        	if (pDialog != null) { 
                pDialog.dismiss();
           }
            
        	if (p_msg_id<msg_id) {
        		adapter.notifyDataSetChanged();
        		p_msg_id = msg_id;
        	}            

        }
        
 		
	}
	
	class AttemptPost extends AsyncTask<String, String, String> {

		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub

            String username = User.getInstance().getId();

            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("eventname", Event_Name)); //put event variable here, now for testing only
                params.add(new BasicNameValuePair("msg",my_msg.getText().toString()));

                JSONArray jArray = jsonParser.makeHttpRequest(POST_URL, params);

                JSONObject json = jArray.getJSONObject(0);
   
               	return json.getString(TAG_MESSAGE);

            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
			
		}

        protected void onPostExecute(String file_url) {
            my_msg.setText("");
            if (file_url != null){
            	Toast.makeText(getActivity(), file_url, Toast.LENGTH_LONG).show(); 
            }
            task.run();
        	if (p_msg_id<msg_id) {
        		adapter.notifyDataSetChanged();
        		 msg.setSelection(msg.getAdapter().getCount() - 1);
        		p_msg_id = msg_id;
        	}
        }
		
	}
	

}
