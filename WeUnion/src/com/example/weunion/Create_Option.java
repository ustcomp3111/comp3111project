package com.example.weunion;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Create_Option extends Activity implements OnClickListener{

	private int counter=0;
	private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();	
	private static final String TAG_OPTIONNUM = "option";
	private static final String TAG_VOTE = "vote";
	private static final String TAG_OPTIONNAME = "option_name";
	private static final String VOTE_URL ="http://124.244.60.23/weu/startvote.php";

    private static final String TAG_MESSAGE = "message";
	private TextView eventname2;
    private EditText title, option;
	private Button add,ok;
	private ListView list;
	ArrayList<HashMap<String,String>> optionlist = new ArrayList<HashMap<String,String>>();
	private SimpleAdapter adapter;
	private String[] options = new String[5];
	String eid;
	public static String eventname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_option);
		// Show the Up button in the action bar.
		
			eventname2 = (TextView) findViewById(R.id.t_ename2);
			title = (EditText)findViewById(R.id.editText_title);
			option = (EditText)findViewById(R.id.editText_option);
			add=(Button)findViewById(R.id.b_add_option);
			ok=(Button)findViewById(R.id.b_ok);
			list=(ListView)findViewById(R.id.listOption);

			//Intent intent = getIntent();
			//eid = intent.getStringExtra(Select_Event.EVENT_ID);
			eid=Integer.toString(Global.active_event.event.event_id);
			eventname2.setText(Global.active_event.event.event_name);
			adapter = new SimpleAdapter(this, optionlist,
			R.layout.optionlist,
			new String[] {TAG_OPTIONNUM,TAG_OPTIONNAME, TAG_VOTE},
			new int[] { R.id.t_optionnum, R.id.t_optionname, R.id.t_votenum} );	
			
			list.setAdapter(adapter);
			list.setTextFilterEnabled(true);
			
			add.setOnClickListener(this);
			ok.setOnClickListener(this);
	
		
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.polling, menu);
		return true;
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.b_add_option:
		        HashMap<String, String> map = new HashMap<String, String>();
		        options[counter++]=option.getText().toString();		        
		        map.put(TAG_OPTIONNUM, (Integer.toString(counter)+"."));
		        map.put(TAG_OPTIONNAME, option.getText().toString());
		        map.put(TAG_VOTE, "vote : 0");
		        optionlist.add(map);
		        option.setText("");
			break;
			
		case R.id.b_ok:
			// put in server
			new AttemptStartVote().execute();
			finish();
			Intent i = new Intent(Create_Option.this, EventDetail.class);
			startActivity(i);
		default:
			break;
		}
	}

	class AttemptStartVote extends AsyncTask<String, String, String> {

		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Create_Option.this);
            pDialog.setMessage("Connecting...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub

            String titlename = title.getText().toString();
            String eventname = eventname2.getText().toString(); //event name
    		
            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
            	params.add(new BasicNameValuePair("event_id", eid));
                params.add(new BasicNameValuePair("title", titlename));
                params.add(new BasicNameValuePair("option1", options[0]));
                params.add(new BasicNameValuePair("option2", options[1]));
                params.add(new BasicNameValuePair("option3", options[2]));
                params.add(new BasicNameValuePair("option4", options[3]));
                params.add(new BasicNameValuePair("option5", options[4]));
                params.add(new BasicNameValuePair("vote1", "0"));
                params.add(new BasicNameValuePair("vote2", "0"));
                params.add(new BasicNameValuePair("vote3", "0"));
                params.add(new BasicNameValuePair("vote4", "0"));
                params.add(new BasicNameValuePair("vote5", "0"));

              JSONArray jArray = jsonParser.makeHttpRequest(VOTE_URL, params);

                	finish();
                	JSONObject json = jArray.getJSONObject(0);
                	return json.getString(TAG_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }
 
            return null;
			
		}

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(Create_Option.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}
	
	

}
