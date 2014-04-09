package com.example.weunion;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Vote extends Activity {

	String[] polltitle={"Title1","Title2","Title3","Title4","Title5","Title6","Title7"};
	private ExpandableListView Listpolling;
	ArrayList<HashMap<String,String>> pollinglist = new ArrayList<HashMap<String,String>>();
	private SimpleAdapter adapter;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote);
	
		ExpandableListView Listpolling=(ExpandableListView)findViewById(R.id.expandableListView1);
		Listpolling.setAdapter(new MyAdapter(this));
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vote, menu);
		return true;
	}

}
