package com.example.weunion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;
import comp3111project.RegularEvent;
import comp3111project.RegularEventNode;
import comp3111project.WeekdayAndTime;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class WeeklyAgenda extends Fragment implements OnClickListener{
	ListView agenda_listview;
	//Button create_regular_event_button;
	JSONParser jsonParser = new JSONParser();
	private ProgressDialog pDialog;

	LinearLayout l;
	ArrayList<String> option = new ArrayList<String>();

	JSONArray jArray;
	List<NameValuePair> params2 = new ArrayList<NameValuePair>();
	Intent i;	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_weekly_agenda);
		l = (LinearLayout) inflater.inflate(R.layout.activity_weekly_agenda,container,false);
		option.add("Edit");
		option.add("Delete");
		//Global.initialization_is_completed = false;
		agenda_listview = (ListView) l.findViewById(R.id.my_agenda);
		//     create_regular_event_button = (Button) l.findViewById(R.id.weekly_agenda_create_regular_event_button);
		 //    create_regular_event_button.setOnClickListener(this);
		 // new AttemptShowRegularEvents().execute();
		 // while(!Global.initialization_is_completed);
		//Global.initialization_is_completed = false;
		  agenda_listview.setAdapter(new ArrayAdapter<String>(getActivity(),
		    android.R.layout.simple_list_item_1, Global.agenda_list));

		    agenda_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		    {
				@Override
				public void onItemClick(AdapterView<?> a, View v, int position,
						long id) 
				{
					Global.regular_event_position = position;
					AlertDialog.Builder matching_dialog = new AlertDialog.Builder(getActivity());
					matching_dialog.setAdapter(
					new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, option),				
					new DialogInterface.OnClickListener()
					{
						Intent i;
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
					if(which==0)
					{
						Global.edit_event = true;
					//	Global.regular_event_position = which;
						i= new Intent(getActivity(),CreateRegularEvent.class);
						startActivity(i);
					}
					else
					{
						new AttemptDeleteRegularEvent().execute();
					}
						}

					}
							);							
				matching_dialog.show();
				}		    	
		    });
		    while(!Global.initialization_is_completed);
		    agenda_listview.invalidateViews();
	return l;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	Intent i;
		if(v.getId()==R.id.weekly_agenda_create_regular_event_button)	
	{
	i = new Intent(getActivity(),CreateRegularEvent.class);
	startActivity(i);
	}
		}
	class AttemptDeleteRegularEvent extends AsyncTask<String, String, String> {

		int success = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Deleting event...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
		  @Override
		protected String doInBackground(String... arg0) {
			   try{



		               params2.add(new BasicNameValuePair("r_event_id",Integer.toString(Global.agenda_id_list.get(Global.regular_event_position))));


		                jArray = jsonParser.makeHttpRequest(Global.DELETE_REGULAR_EVENT_URL, params2);
		            	Log.d("hi","here3");
		               success = jArray.getJSONObject(0).getInt("success");
		               if (success==1)
		               {		            	
		                  	 i = new Intent(getActivity(), EventMenu.class);	                  	 
		                  	 getActivity().finish();
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
	            	Toast.makeText(getActivity(), file_url, Toast.LENGTH_LONG).show();
	            }	 
	        }
}
}