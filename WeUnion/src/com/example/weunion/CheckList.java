package com.example.weunion;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/*
 * OBSOLETED FRAGMENT!
 * PLEASE IGNORE THIS!
 */

public class CheckList extends Fragment {

	LinearLayout l;
	int p;
	ListView checklist;
	JSONParser jsonParser = new JSONParser();
		public	 View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
		{
			l = (LinearLayout) inflater.inflate(R.layout.activity_check_list,container,false);
			checklist.setAdapter(new ChecklistAdapter(getActivity(),android.R.layout.simple_list_item_1,Global.check_list));
			return l;
		
		}
		class ChecklistAdapter extends ArrayAdapter<String>
		{
ArrayList<String> check_list;
			public ChecklistAdapter(Context context,
					int textViewResourceId, ArrayList<String> objects) {
				super(context, textViewResourceId, objects);
				// TODO Auto-generated constructor stub
			this.check_list.addAll(objects); 
			}
	
		
		  private class ViewHolder {
			   TextView text;
			   CheckBox chkbox;
			  }

		/*  public View getView(int position, View convertView, ViewGroup parent) {
		 
		   ViewHolder holder = null;
		  
		   if (convertView == null) {
		   LayoutInflater vi = (LayoutInflater)getSystemService(
		     Context.LAYOUT_INFLATER_SERVICE);
		   convertView = vi.inflate(R.layout.country_info, null);
		 
		   holder = new ViewHolder();
		   holder.text = (TextView) convertView.findViewById(R.id.code);
		   holder.chkbox = (CheckBox) convertView.findViewById(R.id.checkBox1);
		   convertView.setTag(holder);
		 
		    holder.name.setOnClickListener( new View.OnClickListener() {  
		     public void onClick(View v) {  
		      CheckBox cb = (CheckBox) v ;  
		      String item = (String) cb.getTag();  
		      item.setSelected(cb.isChecked());
		     }  
		    });  
		   } 
		   else {
		    holder = (ViewHolder) convertView.getTag();
		   }
		 
		   String item = check_list.get(position);
		   holder.text.setText(" (" +  item.getCode() + ")");
		   holder.chkbox.setText(item.getName());
		   holder.chkbox.setChecked(item.isSelected());
		   holder.chkbox.setTag(item);
		 
		   return convertView;
		 
		  }
		 */
		 }
}

