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

public class DeclinedGuest extends Fragment implements OnClickListener{
	//Button create_event_button;
	ListView declined_guest_listview;
LinearLayout l;

	public	 View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		l = (LinearLayout) inflater.inflate(R.layout.activity_declined_guest,container,false);
		 declined_guest_listview = (ListView) l.findViewById(R.id.declined_guest_list);
	    // create_event_button = (Button) l.findViewById(R.id.event_create_new_event_button);
	     //create_event_button.setOnClickListener(this);
	     if(Global.declined_guest_list.size()==0)
	    	 Global.declined_guest_list.add("(empty)");
		 declined_guest_listview.setAdapter(new ArrayAdapter<String>(getActivity(),
	    android.R.layout.simple_list_item_1,Global.declined_guest_list ));

	    return l;
	}

public void onClick(View v) {
	
}

	
	}
