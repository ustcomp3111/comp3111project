package com.example.weunion;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseExpandableListAdapter {

	JSONParser jsonParser = new JSONParser();
	private Context context;
	public static final String POLLING_URL ="http://124.244.60.23/weu/event.php";
	public static ArrayList<String> parentList= new ArrayList<String>();
	ArrayList<String> Allevent = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> childList=new ArrayList<ArrayList<String>>();
	
	
	public MyAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}

	
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView tv=new TextView(context);
		tv.setText(childList.get(groupPosition).get(childPosition));
		tv.setPadding(30, 10, 10, 10);
		tv.setTextSize(25);
		return tv;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return childList.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return parentList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView tv=new TextView(context);
		tv.setText(parentList.get(groupPosition));
		tv.setPadding(50, 10, 10, 10);
		tv.setTextSize(25);
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
