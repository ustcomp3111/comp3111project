package com.example.weunion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import comp3111project.EventNode;
import comp3111project.Guest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class InviteGuest extends Fragment{
	//Button invite_button;
	ListView invite_guest_listview;
	//ArrayList<String > friend_list = new ArrayList<String>();
	//ArrayList<Integer> friend_id_list = new ArrayList<Integer>();
LinearLayout l;
JSONParser jsonParser = new JSONParser();
/*private ProgressDialog pDialog;
private static final String DISPLAY_FD_URL = JSONParser.URL+"friend.php";
private static final String TAG_A_ID = "A_id";
private static final String TAG_A_NAME = "A_name";
private static final String TAG_B_ID = "B_id";
private static final String TAG_B_NAME = "B_name";
*/
int position;
boolean friend_invited = false;
	public	 View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		l = (LinearLayout) inflater.inflate(R.layout.activity_invite_guest,container,false);
		//new AttemptDisplayfd().execute();
		new AttemptShowFd(0).execute();
		while(!Global.initialization_is_completed);
		Global.initialization_is_completed = false;
		invite_guest_listview = (ListView) l.findViewById(R.id.invite_friend_list);
	    // create_event_button = (Button) l.findViewById(R.id.event_create_new_event_button);
	     //create_event_button.setOnClickListener(this);
		 
		 invite_guest_listview.setAdapter(new ArrayAdapter<String>(getActivity(),
	    android.R.layout.simple_list_item_1,Global.friend_list ));
		 invite_guest_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		    {
				@Override
				public void onItemClick(AdapterView<?> a, View v, int p,
						long id) {
			position = p;
			Guest ptr = Global.active_event.event.guest_list_ptr;
			boolean end = false;
			while(ptr!=null)
			{
				if(ptr.user.user_id==Global.friend_id_list.get(p)||Global.friend_id_list.get(p)==Global.active_event.event.host.user_id)
				{
					Toast.makeText(getActivity(),Global.friend_list.get(p)+" is already invited!", Toast.LENGTH_LONG).show();		
				end = true;
				break;
				}
				ptr = ptr.next;
			}
			if(!end)
			{
			new AttemptInviteFd().execute();
			while(!Global.initialization_is_completed);
			Global.initialization_is_completed = false;
			if(friend_invited)
				Toast.makeText(getActivity(),Global.friend_list.get(p)+" invited!", Toast.LENGTH_LONG).show();	
			friend_invited = false;
			Global.active_event.event.AddGuest(new Guest(Global.friend_list.get(p),Global.friend_id_list.get(p),false,false));
			}}});
	    return l;
	}
/*class AttemptDisplayfd extends AsyncTask<String, String, String> {
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
   

        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair(TAG_A_ID, Integer.toString(User.getInstance().getId())));
                params.add(new BasicNameValuePair(TAG_A_NAME, User.getInstance().getName()));
                Global.friend_list = new ArrayList<String>();
                Global.friend_id_list = new ArrayList<Integer>();
                JSONArray jArray = jsonParser.makeHttpRequest(DISPLAY_FD_URL, params);

                if (jArray!=null) {

                    for(int i = 0; i <jArray.length();i++ ) {
                 	                   JSONObject json = jArray.getJSONObject(i);;
                 	                  Global.friend_id_list.add(json.getInt(TAG_B_ID));
                 	                 Global.friend_list.add(json.getString(TAG_B_NAME));             	                   
                 	                 
                    }
                    if(Global.friend_list.size()==0)
                    	Global.friend_list.add("(you have no friend :forever alone: )");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Global.initialization_is_completed = true;
            return null;
			
		}

        protected void onPostExecute(String file_url) {


        }
  	
	}
*/
class AttemptInviteFd extends AsyncTask<String, String, String> {
	
    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }
	
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
        try {
        	
        	List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("user_id",Integer.toString(Global.friend_id_list.get(position))));
            params.add(new BasicNameValuePair("user_name", Global.friend_list.get(position)));
            params.add(new BasicNameValuePair("event_id", Integer.toString(Global.active_event.event.event_id)));
            params.add(new BasicNameValuePair("event_name", Global.active_event.event.event_name));
            params.add(new BasicNameValuePair("joined", Integer.toString(0)));
            params.add(new BasicNameValuePair("responded", Integer.toString(0)));
            JSONArray jArray = jsonParser.makeHttpRequest(Global.INVITE_FD_URL, params);
            if (jArray.getJSONObject(0).getInt("success")==1)
            {		            	
         		friend_invited = true;	           
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
