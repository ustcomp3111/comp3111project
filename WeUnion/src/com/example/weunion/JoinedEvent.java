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
public class JoinedEvent extends Fragment implements OnClickListener{
	//Button create_event_button;
	ListView joined_event_listview;
LinearLayout l;

	public	 View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		l = (LinearLayout) inflater.inflate(R.layout.activity_joined_event,container,false);
		joined_event_listview = (ListView) l.findViewById(R.id.joined_event_list);
	  //   create_event_button = (Button) l.findViewById(R.id.create_new_event_button2);
	    // create_event_button.setOnClickListener(this);
	  
	  
	    	 joined_event_listview.setAdapter(new ArrayAdapter<String>(getActivity(),
	    android.R.layout.simple_list_item_1, Global.joined_event_list));

	    joined_event_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	    {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				EventNode ptr =  Global.active_user.event_ptr;
				
				while(ptr!=null)
				{
					 if (ptr.event.event_id == Global.joined_event_id_list.get(position))
				{
						 Global.active_event =new EventNode (ptr.event);
				break;
				}
					 else 
						 ptr = ptr.next;
				}
				// Toast.makeText(getApplicationContext(),Global.active_event.event.event_name+" is selected", Toast.LENGTH_LONG).show();
				Intent i = new Intent(getActivity(), EventDetail.class);
			//	getActivity().finish();
				startActivity(i);
			
			}
	    	
	    });
	    return l;
	}
	
public void onClick(View v) {
	/*
		Intent i ;
		// TODO Auto-generated method stub
		if(v.getId()==R.id.event_create_new_event_button)
		{ i = new Intent(getActivity(), CreateEvent.class);
		
		startActivity(i);
		}
	*/
	}

	
	}
