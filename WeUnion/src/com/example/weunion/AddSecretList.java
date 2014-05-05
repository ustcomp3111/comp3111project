package com.example.weunion;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import comp3111project.EventNode;

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

public class AddSecretList extends Fragment{
	//Button create_event_button;
	ListView add_secret_list_listview;
LinearLayout l;
boolean friend_added= false;
JSONParser jsonParser = new JSONParser();
int p;
	public	 View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		l = (LinearLayout) inflater.inflate(R.layout.activity_add_secret_list,container,false);
		 add_secret_list_listview = (ListView) l.findViewById(R.id.add_secret_list);
	    // create_event_button = (Button) l.findViewById(R.id.event_create_new_event_button);
	     //create_event_button.setOnClickListener(this);
	   
		 add_secret_list_listview.setAdapter(new ArrayAdapter<String>(getActivity(),
	    android.R.layout.simple_list_item_1,Global.add_secret_list));
		 add_secret_list_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		    {
				@Override
				public void onItemClick(AdapterView<?> a, View v, int position,
						long id) {
					p = position;
					int i = 0;
					boolean end = false;
					while(i<Global.my_secret_id_list.size())
					{
						if(Global.my_secret_id_list.get(i)==Global.add_secret_id_list.get(p)||Global.add_secret_id_list.get(p)==Global.active_user.user_id)
						{
							Toast.makeText(getActivity(),Global.add_secret_list.get(p)+" is already addded!", Toast.LENGTH_LONG).show();		
						end = true;
						break;
						}
						i++;
					}
					if(!end)
					{
						Global.initialization_is_completed = false;
						new  AttemptAddSecretList().execute();
					while(!Global.initialization_is_completed);
					Global.initialization_is_completed = false;
					if(friend_added)
					{
						Toast.makeText(getActivity(),Global.add_secret_list.get(p)+" added!", Toast.LENGTH_LONG).show();	
						if(Global.my_secret_id_list.get(0)==-1)
						{
							Global.my_secret_list = new ArrayList<String>();
							Global.my_secret_id_list = new ArrayList<Integer>();
						}
						Global.my_secret_id_list.add(Global.add_secret_id_list.get(p));
						Global.my_secret_list.add(Global.add_secret_list.get(p));
						Global.my_secret_list_listview.invalidateViews();
					}
					friend_added = false;
	
				}
		    	
		    }});
			while(!Global.initialization_is_completed);
		    add_secret_list_listview.invalidateViews();

	    return l;
	}
class AttemptAddSecretList extends AsyncTask<String, String, String> {
		
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
	            params.add(new BasicNameValuePair("B_id",Integer.toString(Global.add_secret_id_list.get(p))));
	            params.add(new BasicNameValuePair("B_name", Global.add_secret_list.get(p)));    
	            JSONArray jArray = jsonParser.makeHttpRequest(Global.ADD_SECRET_LIST_URL, params);
	            if (jArray.getJSONObject(0).getInt("success")==1)
	            {		            	
	         		friend_added = true;	           
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

