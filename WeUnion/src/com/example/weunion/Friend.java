package com.example.weunion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Friend extends Activity implements OnClickListener{
	
	private ListView friendlist;
	private Button search, add;
	private EditText name;
	private EditText id;
	
	ArrayList<HashMap<String,String>> fdlist = new ArrayList<HashMap<String,String>>();
	ArrayList<HashMap<String,String>> templist = new ArrayList<HashMap<String,String>>();
	private SimpleAdapter adapter;
	 // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    private static final String DISPLAY_FD_URL = JSONParser.URL+"friend.php";
    private static final String ADD_FD_URL = JSONParser.URL+"addfriend.php";
    private static final String SEARCH_FD_URL = JSONParser.URL+"searchfriend.php";

    private static final String TAG_A_ID = "A_id";
    private static final String TAG_A_NAME = "A_name";
    private static final String TAG_B_ID = "B_id";
    private static final String TAG_B_NAME = "B_name";
    private static final String TAG_USERNAME = "name";
    private static final String TAG_USERID = "id";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		
		
		adapter = new SimpleAdapter( this, fdlist,
				 R.layout.friendlist,
				 new String[] {TAG_B_ID, TAG_B_NAME},
				 new int[] { R.id.friendlist_id, R.id.friendlist_name} );		
		
		friendlist = (ListView)findViewById(R.id.friendlist);
		friendlist.setAdapter(adapter);
		friendlist.setTextFilterEnabled(true);
		
		search = (Button) findViewById(R.id.search);
		add = (Button) findViewById(R.id.add_fd);
		name = (EditText) findViewById(R.id.search_name);
		id = (EditText) findViewById(R.id.search_id);
		
		search.setOnClickListener(this);
		add.setOnClickListener(this);
		new AttemptDisplayfd().execute();
			
	}

	 @Override
	    protected void onDestroy() {
		 // TODO Auto-generated method stub
		 super.onDestroy();
	    }
	 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search:
				//search starts
				new AttemptSearch().execute();
			break;
		case R.id.add_fd:
			//add friend
			if (name.getText().toString().equals(User.getInstance().getName())) {
	        	Toast.makeText(Friend.this, "Are you kidding me?\nYou are adding yourself!", Toast.LENGTH_LONG).show();				
			} else new AttemptAdd().execute();
		break;
		default:
			break;
		}
	}
	

	class AttemptDisplayfd extends AsyncTask<String, String, String> {
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Friend.this);
            pDialog.setMessage("Loading friend list...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair(TAG_A_ID, Integer.toString(User.getInstance().getId())));
                params.add(new BasicNameValuePair(TAG_A_NAME, User.getInstance().getName()));

                JSONArray jArray = jsonParser.makeHttpRequest(DISPLAY_FD_URL, params);

                if (jArray!=null) {

                    for(int i = 0; i <jArray.length();i++ ) {
                 	                   JSONObject json = jArray.getJSONObject(i);
                 	                   HashMap<String, String> map = new HashMap<String, String>();
                 	                   map.put(TAG_B_ID, "ID: "+json.getString(TAG_B_ID));
                 	                   map.put(TAG_B_NAME, "Name: "+json.getString(TAG_B_NAME));             	                   
                 	                   templist.add(map);  
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
        	for (HashMap<String, String> map : templist) fdlist.add(map);
        	adapter.notifyDataSetChanged();
        	templist.clear();
        }
        
 		
	}
	
	class AttemptSearch extends AsyncTask<String, String, String> {
		String search_name = name.getText().toString();
		String search_id = id.getText().toString();
		int mode = -1;
		boolean found = false;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Friend.this);
            pDialog.setMessage("Searching...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub

            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
            	
            	if (search_name.equals("")) {
            		mode = 1;	//Use ID to search name
                    params.add(new BasicNameValuePair(TAG_USERID, search_id));           		
            	} else if (search_id.equals("")) {
            		mode = 2;	//Use name to search ID
                    params.add(new BasicNameValuePair(TAG_USERNAME, search_name));           		
            	} else {
            		mode = 3;	//Verify exist user
                    params.add(new BasicNameValuePair(TAG_USERID, search_id)); 
                    params.add(new BasicNameValuePair(TAG_USERNAME, search_name));             		
            	}
            	JSONObject json = null;
                JSONArray jArray = jsonParser.makeHttpRequest(SEARCH_FD_URL, params);
                
                if (jArray!=null) {
                	json = jArray.getJSONObject(0);
                	if (json.getString("success").equals("1")) {
                		found = true;
                	}
                }
                
                if (mode==1) {
                	if (found) {
                		json = jArray.getJSONObject(1);
                		search_name = json.getString(TAG_USERNAME);
                		return "Search finished!\nUser name is found.";
                	}
                	else return "Search finished!\nUser not found!";
                }
                
                if (mode==2) {
                  	if (found) {
                		json = jArray.getJSONObject(1);
                  		search_id = json.getString(TAG_USERID);
                  		return "Search finished!\nUser ID is found.";
                	}
                	else return "Search finished!\nUser not found!";
                	
                }
                
                if (mode==3) {
                	if (found) {                		
                		return "User exist,\nClick add to add him/her.";
                	}
                	else return "Wrong Input!\nPlease try again.";
                }
              

            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
			
		}

        protected void onPostExecute(String file_url) {
    		switch (mode) {
    		case 1:
    			if (found) name.setText(search_name);
    			break;
    		case 2:
    			if (found) id.setText(search_id);
    		break;
    		default:
    		break;
    		}
        	Toast.makeText(Friend.this, file_url, Toast.LENGTH_LONG).show();
        	if (pDialog != null) { 
                pDialog.dismiss();
        	}
        }
	}
	
	class AttemptAdd extends AsyncTask<String, String, String> {
		boolean success = false;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair(TAG_A_ID, Integer.toString(User.getInstance().getId())));
                params.add(new BasicNameValuePair(TAG_A_NAME, User.getInstance().getName()));
                params.add(new BasicNameValuePair(TAG_B_ID, id.getText().toString()));
                params.add(new BasicNameValuePair(TAG_B_NAME, name.getText().toString()));                

                JSONArray jArray = jsonParser.makeHttpRequest(ADD_FD_URL, params);
                
                if (jArray!=null) {
                	JSONObject json = jArray.getJSONObject(0);
                	if (json.getString("success").equals("1")) {
                		success = true;
                	} else success = false;
                	return json.getString("message");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Error occured,\nPlease try again!";
            }
			return null;
			
		}

        protected void onPostExecute(String file_url) {
        	if (success) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(TAG_B_ID, "ID: "+id.getText().toString());
                map.put(TAG_B_NAME,"Name: "+name.getText().toString());
                fdlist.add(map);
            	adapter.notifyDataSetChanged();
            	name.setText("");
            	id.setText("");        		
        	}

        	Toast.makeText(Friend.this, file_url, Toast.LENGTH_LONG).show();
        }
         		
	}
}
