package com.example.weunion;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class AttemptShowFd extends AsyncTask<String, String, String> {
	private ProgressDialog pDialog;
	private static final String DISPLAY_FD_URL = JSONParser.URL+"friend.php";
	private static final String TAG_A_ID = "A_id";
	private static final String TAG_A_NAME = "A_name";
	private static final String TAG_B_ID = "B_id";
	private static final String TAG_B_NAME = "B_name";
	JSONParser jsonParser = new JSONParser();
   // ArrayList<String> list_ptr ;
    //ArrayList<Integer> id_list_ptr;
    int list = 0;
	@Override
protected void onPreExecute() {
    super.onPreExecute();

}
	AttemptShowFd(int l)
	{
		list = l;
	};
	/*AttemptShowFd(ArrayList<String> l,ArrayList<Integer> l2)
	{
	    list_ptr = l;
	    id_list_ptr = l2;		
	}*/
@Override
protected String doInBackground(String... args) {
	// TODO Auto-generated method stub
    try {
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(TAG_A_ID, Integer.toString(User.getInstance().getId())));
        params.add(new BasicNameValuePair(TAG_A_NAME, User.getInstance().getName()));
        if(list == 0)
        {
        Global.friend_list = new ArrayList<String>();
        Global.friend_id_list = new ArrayList<Integer>();
        }
        else if(list == 1)
        {
        	  Global.add_secret_list = new ArrayList<String>();
              Global.add_secret_id_list = new ArrayList<Integer>();
        }
        JSONArray jArray = jsonParser.makeHttpRequest(DISPLAY_FD_URL, params);

        if (jArray!=null) {

            for(int i = 0; i <jArray.length();i++ ) {
         	                   JSONObject json = jArray.getJSONObject(i);;
         	    
         	                  if(list == 0)
         	                 {
         	                 Global.friend_list.add(json.getString(TAG_B_NAME));
         	                 Global.friend_id_list.add(json.getInt(TAG_B_ID));
         	                if(Global.friend_list.size()==0)
         	               	Global.friend_list.add("(you have no friend :forever alone: )");
         	                 }
         	                 else if(list == 1)
         	                 {
         	                 	  Global.add_secret_list.add(json.getString(TAG_B_NAME));
         	                       Global.add_secret_id_list.add(json.getInt(TAG_B_ID));
         	                      if(Global.add_secret_list.size()==0)
         	         	               	Global.add_secret_list.add("(you have no friend :forever alone: )");
         	                 }           	                   
         	                 
            }

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
