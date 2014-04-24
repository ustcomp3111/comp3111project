package com.example.weunion;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
public class Event extends Fragment implements OnClickListener{
	//Button create_event_button;
	ListView event_listview;
LinearLayout l;

	public	 View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		l = (LinearLayout) inflater.inflate(R.layout.activity_event,container,false);
		event_listview = (ListView) l.findViewById(R.id.my_events);
	    // create_event_button = (Button) l.findViewById(R.id.event_create_new_event_button);
	     //create_event_button.setOnClickListener(this);
	     
	    event_listview.setAdapter(new ArrayAdapter<String>(getActivity(),
	    android.R.layout.simple_list_item_1, Global.eventlist));

	    event_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	    {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				EventNode ptr =  Global.active_user.event_ptr;;
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
				// Toast.makeText(getApplicationContext(),Global.active_event.event.event_name+" is selected", Toast.LENGTH_LONG).show();
				Intent i = new Intent(getActivity(), EventDetail.class);
				startActivity(i);
			}
	    	
	    });
	    return l;
	}

public void onClick(View v) {
	
		Intent i ;
		// TODO Auto-generated method stub
		if(v.getId()==R.id.event_menu_create_event_button)
		{ i = new Intent(getActivity(), CreateEvent.class);
		//finish();
		startActivity(i);
		}
	}

	
	}
