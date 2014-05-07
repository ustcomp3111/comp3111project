package com.example.weunion;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import com.example.weunion.AddSecretList.AttemptAddSecretList;
import com.example.weunion.InviteGuest.AttemptInviteFd;

import comp3111project.EventNode;
import comp3111project.Guest;

import android.os.AsyncTask;
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
import android.widget.Toast;

public class MySecretList extends Fragment{
	//Button create_event_button;
	//ListView my_secret_list_listview;
LinearLayout l;
int p;
boolean friend_removed= false;
JSONParser jsonParser = new JSONParser();
	public	 View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		l = (LinearLayout) inflater.inflate(R.layout.activity_my_secret_list,container,false);
		Global.my_secret_list_listview = (ListView) l.findViewById(R.id.my_secret_list);
	    // create_event_button = (Button) l.findViewById(R.id.event_create_new_event_button);
	     //create_event_button.setOnClickListener(this);
		Global.clicked = false;
		Global.my_secret_list_listview.setAdapter(new ArrayAdapter<String>(getActivity(),
	    android.R.layout.simple_list_item_1,Global.my_secret_list));
		Global.my_secret_list_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		    {
				@Override
				public void onItemClick(AdapterView<?> a, View v, int position,
						long id) {
					p = position;
					
					if(Global.my_secret_id_list.get(p)!=-1)
					{
						if(Global.clicked)
						{	
						Global.clicked = false;
					Global.initialization_is_completed = false;
					new  AttemptRemoveSecretList().execute();
					while(!Global.initialization_is_completed);
					Global.initialization_is_completed = false;
					if(friend_removed)
						{
						Toast.makeText(getActivity(),Global.my_secret_list.get(p)+" removed!", Toast.LENGTH_LONG).show();	
						Global.my_secret_id_list.remove(p);
						Global.my_secret_list.remove(p);
						if(Global.my_secret_list.size()==0)
						{
						 	Global.my_secret_list.add("(your list is empty)");
			            	Global.my_secret_id_list.add(-1);
						}
						Global.my_secret_list_listview.invalidateViews();	
						}
					else
						Toast.makeText(getActivity(),"Failed to remove,\nsomething goes wrong in the app-_-", Toast.LENGTH_LONG).show();
					friend_removed = false;
						}
						else
						{
							Global.clicked = true;
							Toast.makeText(getActivity(),"Click again to confirm your action", Toast.LENGTH_LONG).show();	
						}
					}
					}
		    });
			while(!Global.initialization_is_completed);
			Global.my_secret_list_listview.invalidateViews();
		    
		 return l;
	}
	
class AttemptRemoveSecretList extends AsyncTask<String, String, String> {
		
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();


	    }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
	        try {
	        	
	        	List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("A_id",Integer.toString(Global.active_user.user_id)));
	            params.add(new BasicNameValuePair("A_name",Global.active_user.name));
	            params.add(new BasicNameValuePair("B_id",Integer.toString(Global.my_secret_id_list.get(p))));
	            params.add(new BasicNameValuePair("B_name", Global.my_secret_list.get(p)));    
	            JSONArray jArray = jsonParser.makeHttpRequest(Global.REMOVE_SECRET_LIST_URL, params);
	            if (jArray.getJSONObject(0).getInt("success")==1)
	            {		            	
	         		friend_removed = true;	           
	            }
	         
	            }
	         catch (Exception e) {
	            e.printStackTrace();
	        }
	        Global.initialization_is_completed = true;
	        return null;
			
		}

	    protected void onPostExecute(String file_url) {


	    }
		
	}
	}

