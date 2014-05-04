package com.example.weunion;

import comp3111project.EventNode;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MySecretList extends Fragment{
	//Button create_event_button;
	ListView my_secret_list_listview;
LinearLayout l;

	public	 View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		l = (LinearLayout) inflater.inflate(R.layout.activity_my_secret_list,container,false);
		 my_secret_list_listview = (ListView) l.findViewById(R.id.my_secret_list);
	    // create_event_button = (Button) l.findViewById(R.id.event_create_new_event_button);
	     //create_event_button.setOnClickListener(this);
	   
		 my_secret_list_listview.setAdapter(new ArrayAdapter<String>(getActivity(),
	    android.R.layout.simple_list_item_1,Global.my_secret_list));
		 my_secret_list_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		    {
				@Override
				public void onItemClick(AdapterView<?> a, View v, int position,
						long id) {
					/*EventNode ptr =  Global.active_user.event_ptr;
					//String event_name = (String) a.getAdapter().getItem(position);
					while(ptr!=null)
					{
						 if (ptr.event.event_id == Global.m.get(position))
					{
							 Global.active_event =new EventNode (ptr.event);
							 break;
					}
						 else 
							 ptr = ptr.next;
					}
					// Toast.makeText(getApplicationContext(),Global.active_event.event.event_name+" is selected", Toast.LENGTH_LONG).show();
					Intent i = new Intent(getActivity(), EventDetail.class);
					//getActivity().finish();
					startActivity(i);
				*/}
		    	
		    });
	    return l;
	}
	
	}

