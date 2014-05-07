package com.example.weunion;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import comp3111project.DateAndTime;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EventSetting extends Fragment implements OnClickListener{
LinearLayout l;
Button edit,matching,delete;
JSONParser jsonParser = new JSONParser();
private ProgressDialog pDialog;	
JSONArray jArray;
List<NameValuePair> params2 = new ArrayList<NameValuePair>();
Intent i;
ArrayList<String> time_matching_list = new ArrayList<String>();
public	 View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		l = (LinearLayout) inflater.inflate(R.layout.activity_event_setting,container,false);
		edit = (Button) l.findViewById(R.id.event_setting_edit_button);
		edit.setOnClickListener(this);
		matching = (Button) l.findViewById(R.id.event_setting_time_matching_button);
		matching.setOnClickListener(this);
		delete = (Button) l.findViewById(R.id.event_setting_delete_button);
		delete.setOnClickListener(this);
		Global.clicked = false;
		return l;

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i;
		if(v.getId()==R.id.event_setting_edit_button)
		{
			Global.edit_event = true;
			i = new Intent(getActivity(), CreateEvent.class);
		getActivity().finish();
		startActivity(i);
		
			}
		else if (v.getId()==R.id.event_setting_delete_button)
		{

			
				if(!Global.clicked)
				{
					Global.clicked = true;
					Toast.makeText(getActivity(),"Click again to confirm your action!", Toast.LENGTH_LONG).show();
				}
				else
					{
		new AttemptDeleteEvent().execute();
		getActivity().finish();
					}
		}
		else if (v.getId()==R.id.event_setting_time_matching_button)
		{
			Global.result = Global.active_user.FreeTimeSlot(DateAndTime.Now(),Global.active_event.event.duration);
			time_matching_list.add("The best date and time:");
			time_matching_list.add(Global.result.toString());
			//Toast.makeText(getActivity(),Global.active_user.FreeTimeSlot(DateAndTime.Now(),Global.active_event.event.duration).toString(), Toast.LENGTH_LONG).show();
			AlertDialog.Builder matching_dialog = new AlertDialog.Builder(getActivity());
			matching_dialog.setAdapter(
			new ArrayAdapter<String>(getActivity(),
			android.R.layout.simple_list_item_1, time_matching_list),				
			new DialogInterface.OnClickListener()
			{

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				if(Global.result.equals(DateAndTime.fail));
					else
				{
						Global.edit_event = true;
				Intent i = new Intent(getActivity(),CreateEvent.class);
				dialog.dismiss();
				getActivity().finish();
				startActivity(i);
					}
				}
			}
					);							
		matching_dialog.show();
		}
	}

	class AttemptDeleteEvent extends AsyncTask<String, String, String> {

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



		               params2.add(new BasicNameValuePair("event_id",Integer.toString(Global.active_event.event.event_id)));


		                jArray = jsonParser.makeHttpRequest(Global.DELETE_EVENT_URL, params2);
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