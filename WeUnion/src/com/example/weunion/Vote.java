package com.example.weunion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.weunion.Select_Event.AttemptShowEvents;

import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter;

public class Vote extends Fragment implements OnClickListener{

	JSONParser jsonParser = new JSONParser();
	private ProgressDialog pDialog;
	ArrayList<String> eventidlist2 = new ArrayList<String>();
	ArrayList<String> polllist = new ArrayList<String>();
	//ArrayList<ArrayList<String>> pollidlist = new ArrayList<ArrayList<String>>();
	protected static String POLLING_ID;
	private ExpandableListView Listpolling;
	private Button create_polling_button;
	//ArrayList<HashMap<String,String>> pollinglist = new ArrayList<HashMap<String,String>>();
	public static String[] options;
		
	RelativeLayout l;

	public	 View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		l = (RelativeLayout) inflater.inflate(R.layout.activity_vote,container,false);
	create_polling_button =  (Button) l.findViewById(R.id.createpollingbutton);
		create_polling_button.setOnClickListener(this);
		
		//new AttemptGetEvents().execute();
		
		
		
		ExpandableListView Listpolling=(ExpandableListView)l.findViewById(R.id.expandableListView1);
		Listpolling.setAdapter(new MyAdapter(getActivity()));

		Listpolling.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				String ptitle=MyAdapter.childList.get(groupPosition).get(childPosition);
				Vote_Option.pollingtitle=ptitle;
				Global.pollingid=Global.pollidlist.get(groupPosition).get(childPosition);
				new AttemptGetInfo().execute();
            	Toast.makeText(getActivity(),ptitle+" is selected", Toast.LENGTH_LONG).show();
				Intent i = new Intent(getActivity(), Vote_Option.class);
				startActivity(i);
				return true;
			}
        });
	
	return l;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i;
		if(v.getId()==R.id.createpollingbutton)
		{
			
			i = new Intent(getActivity(),Create_Option.class);
			startActivity(i);
		}
	}


	
	
	class AttemptGetInfo extends AsyncTask<String, String, String> {
		
	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	           
	        }

		@Override
		protected String doInBackground(String... arg0) {
		   try{

			    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		               params2.add(new BasicNameValuePair("polling_id",Global.pollingid));


			              JSONArray jArray2 = jsonParser.makeHttpRequest(Global.POLLINGID_URL, params2);
					
		   			
		              for(int i = 0; i <jArray2.length();i++ ) {
		            	 
		            	  JSONObject json2 = jArray2.getJSONObject(i);
		            	  
		            	  Vote_Option.options[0]=json2.getString("option1");
		            	  Vote_Option.options[1]=json2.getString("option2");
		            	  Vote_Option.options[2]=json2.getString("option3");
		            	  Vote_Option.options[3]=json2.getString("option4");
		            	  Vote_Option.options[4]=json2.getString("option5");
		            	  
		            	  Global.votelist[0]=json2.getDouble("vote1");
		            	  Global.votelist[1]=json2.getDouble("vote2");
		            	  Global.votelist[2]=json2.getDouble("vote3");
		            	  Global.votelist[3]=json2.getDouble("vote4");
		            	  Global.votelist[4]=json2.getDouble("vote5");
		            	  
		            	  
		              }
		       	
			   }
		catch(Exception e)
		{
			// Toast.makeText(getApplicationContext(),"exception!", Toast.LENGTH_LONG).show();
			
		}
		  
			// TODO Auto-generated method stub
			return null;
		}
		 protected void onPostExecute(String file_url) {
	        	if (pDialog != null) { 
	                pDialog.dismiss();
	           }

		 }
}
	
}
