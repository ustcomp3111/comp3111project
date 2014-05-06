package com.example.weunion;

import java.text.NumberFormat;
import java.util.ArrayList;
import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.weunion.Login.AttemptLogin;
import com.example.weunion.Login.AttemptRegister;
import com.example.weunion.Select_Event.AttemptShowEvents;

import comp3111project.Events;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Vote_Option extends Activity implements OnClickListener {

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private ListView optionlist;
	private CheckBox chkbox;
	private SimpleAdapter adapter;
	private static final String TAG_ONAME = "oname";
	ArrayList<HashMap<String,String>> olist = new ArrayList<HashMap<String,String>>();
	ArrayList<String> oplist = new ArrayList<String>();
	public static String pollingid="1";
	public static String pollingtitle="";
	private Button vote,view;
	private TextView title;
	ArrayList<Integer> votelist = new ArrayList<Integer>();
	boolean[] checked = new boolean [5];
	public static String[] options= {"1","2","3","4","5"};
	public static double[] votes={0,0,0,0,0};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote__option);
		
		title = (TextView)findViewById(R.id.textView_ptitle);
		title.setText(pollingtitle);
		vote = (Button)findViewById(R.id.button_vote);
		view = (Button)findViewById(R.id.button_viewresult);
		chkbox = (CheckBox)findViewById(R.id.option_chkbox);
		optionlist=(ListView) findViewById(R.id.option_lv);
		optionlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		pollingid=Global.pollingid;
		
		//new AttemptGetInfo().execute();
		
		
		MyCustomAdapter cadapter=new MyCustomAdapter(this, R.layout.optionnamelist,options);
		
		
		
        optionlist.setAdapter(cadapter);
 		optionlist.setTextFilterEnabled(true);
 		
 		
 		
 		vote.setOnClickListener(this);
 		view.setOnClickListener(this);

	}
	
	
	public class MyCustomAdapter extends ArrayAdapter<String> {
		
        private ArrayList<Boolean> status = new ArrayList<Boolean>();
        private ArrayList<String> name = new ArrayList<String>();

        public MyCustomAdapter(Context context, int textViewResourceId,
        		String[] objects) {
        	super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.length; i++) {
                status.add(false);
                name.add(objects[i]);
                
            }
        }

        @Override
        public View getView(final int position, View convertView,
                ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.optionnamelist,
                        parent, false);
            }

            
            CheckBox checkBox = (CheckBox) row.findViewById(R.id.option_chkbox);
            checkBox.setText(options[position]);
            checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                        boolean isChecked) {
                    //Toast.makeText(getApplicationContext(), "" + position,Toast.LENGTH_SHORT).show();
                    if (isChecked) {
                        status.set(position, true);
                        checked[position]=true;
                    } else {
                        status.set(position, false);
                        checked[position]=false;
                    }
                }
            });
            checkBox.setChecked(status.get(position));
            return row;
        }
    }

	public String[] clean(String[] s){
		
		int length=0;
		for(int i=0;i<s.length;i++)
		{
			if(!(s[i].equals("")))
				length++;
		}
		String[] temp=new String[length];
		for(int i=0;i<length;i++)
		{
			temp[i]=s[i];
		}
		return temp;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.button_vote) 
		{
	        new AttemptVote().execute();
	        
		}
		else if(v.getId()==R.id.button_viewresult)
		{
			
			//new UpdateVote().execute();
			CreatePieChart();
			
		}
	}
	
	private void CreatePieChart() {

		List<String> code = new ArrayList<String>();
		  // Pie Chart Section Names
		for(int i=0;i<options.length;i++)
		{
			if(!(options[i].equals("")))
				code.add(options[i]);
		}
			
		List<Double> distribution = new ArrayList<Double>();
		  // Pie Chart Section Value
		for(int i=0;i<code.size();i++)
		{
			distribution.add(Global.votelist[i]);
		}
		

		  // Color of each Pie Chart Sections
		  int[] colors = { Color.BLUE, Color.GREEN,Color.RED,Color.YELLOW,Color.CYAN };

		  // Instantiating CategorySeries to plot Pie Chart
		  CategorySeries distributionSeries = new CategorySeries(
		    "Mobile Platforms");
		  for (int i = 0; i < distribution.size(); i++) {
		   // Adding a slice with its values and name to the Pie Chart
		   distributionSeries.add(code.get(i), distribution.get(i));
		  }
		  // Instantiating a renderer for the Pie Chart
		  DefaultRenderer defaultRenderer = new DefaultRenderer();
		  for (int i = 0; i < distribution.size(); i++) {
		   SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
		   seriesRenderer.setColor(colors[i]);
		   
		   // Adding a renderer for a slice
		   defaultRenderer.addSeriesRenderer(seriesRenderer);
		  }
		  
		  defaultRenderer.setDisplayValues(true);
		  defaultRenderer.setLegendTextSize(42);
		  defaultRenderer.setLabelsTextSize(20);
		  defaultRenderer.setChartTitle(Vote_Option.pollingtitle);
		  defaultRenderer.setChartTitleTextSize(50);
		  defaultRenderer.setZoomButtonsVisible(true);
		  defaultRenderer.setApplyBackgroundColor(true);
		  defaultRenderer.setBackgroundColor(Color.BLACK);

		  // Creating an intent to plot bar chart using dataset and
		  // multipleRenderer
		  Intent intent = ChartFactory.getPieChartIntent(getBaseContext(),
		    distributionSeries, defaultRenderer,
		    "PieChart");
		  
		  // Start Activity
		  startActivity(intent);
		  
		 }
	
	class UpdateVote extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub

            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("polling_id", Global.pollingid));

                JSONArray jArray = jsonParser.makeHttpRequest(Global.POLLINGID_URL, params);

                if (jArray!=null) {

                    for(int i = 0; i <jArray.length();i++ ) {
                 	               JSONObject json = jArray.getJSONObject(i);
                 	               Global.votelist[0]=json.getDouble("vote1");
                 	               Global.votelist[1]=json.getDouble("vote2");
                 	               Global.votelist[2]=json.getDouble("vote3");
                 	               Global.votelist[3]=json.getDouble("vote4");
                 	               Global.votelist[4]=json.getDouble("vote5");
                 	                  
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            
            return null;			
		}		
	}
	
	class AttemptGetInfo extends AsyncTask<String, String, String> {
		
	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(Vote_Option.this);
	            pDialog.setMessage("Loading Options...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

		@Override
		protected String doInBackground(String... arg0) {
		   try{

			    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		               params2.add(new BasicNameValuePair("polling_id",pollingid));


			              JSONArray jArray2 = jsonParser.makeHttpRequest(Global.POLLINGID_URL, params2);
					
		   			
		              for(int i = 0; i <jArray2.length();i++ ) {
		            	 
		            	  JSONObject json2 = jArray2.getJSONObject(i);
		            	  
		            	  options[0]=json2.getString("option1");
		            	  options[1]=json2.getString("option2");
		            	  options[2]=json2.getString("option3");
		            	  options[3]=json2.getString("option4");
		            	  options[4]=json2.getString("option5");
		            	  
		            	  Global.votelist[0]=json2.getDouble("vote1");
		            	  Global.votelist[1]=json2.getDouble("vote2");
		            	  Global.votelist[2]=json2.getDouble("vote3");
		            	  Global.votelist[3]=json2.getDouble("vote4");
		            	  Global.votelist[4]=json2.getDouble("vote5");
		            	  
		              }
		       	
			   }
		catch(Exception e)
		{
			// Toast.makeText(getApplicationContext(),"exception!", Toast.LENGTH_LONG).show();
			
		}
		  
			// TODO Auto-generated method stub
			return null;
		}
		 protected void onPostExecute(String file_url) {
	        	if (pDialog != null) { 
	                pDialog.dismiss();
	           }

		 }
}
	
	
	
		
	class AttemptVote extends AsyncTask<String, String, String> {
		
	       @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(Vote_Option.this);
	            pDialog.setMessage("Voting...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	            
	        }

		@Override
		protected String doInBackground(String... arg0) {
		   try{

			   
		        
		        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
	               params1.add(new BasicNameValuePair("polling_id",pollingid));


		              JSONArray jArray1 = jsonParser.makeHttpRequest(Global.POLLINGID_URL, params1);
				
	   			
	              for(int i = 0; i <jArray1.length();i++ ) {
	            	  
	            	  JSONObject json1 = jArray1.getJSONObject(i);
	            	  votelist.add(json1.getInt("vote1"));
	            	  votelist.add(json1.getInt("vote2"));
	            	  votelist.add(json1.getInt("vote3"));
	            	  votelist.add(json1.getInt("vote4"));
	            	  votelist.add(json1.getInt("vote5"));
	              }
	              
	              for(int i=0;i<checked.length;i++)
	              {
	            	  if(checked[i]==true)
	            	  {
	            		  votelist.set(i, votelist.get(i)+1);
	            		  Global.votelist[i]++;
	            	  }
	              }
	             
	              
		        
			    	 List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		               params2.add(new BasicNameValuePair("polling_id",pollingid));
		               params2.add(new BasicNameValuePair("vote1",votelist.get(0).toString()));
		               params2.add(new BasicNameValuePair("vote2",votelist.get(1).toString()));
		               params2.add(new BasicNameValuePair("vote3",votelist.get(2).toString()));
		               params2.add(new BasicNameValuePair("vote4",votelist.get(3).toString()));
		               params2.add(new BasicNameValuePair("vote5",votelist.get(4).toString()));

		               
			             JSONArray jArray2 = jsonParser.makeHttpRequest(Global.VOTE_URL, params2);
			             JSONObject json = jArray2.getJSONObject(0);
			             return json.getString("message");
		   			
		              
		       	
			   }
		catch(Exception e)
		{
			// Toast.makeText(getApplicationContext(),"exception!", Toast.LENGTH_LONG).show();
			
		}
		  
			// TODO Auto-generated method stub
			return null;
		}
		 protected void onPostExecute(String file_url) {
	        	if (pDialog != null) { 
	                pDialog.dismiss();
	           }
	        	
	        	
	        	Toast.makeText(getApplicationContext(),"Vote successful!", Toast.LENGTH_LONG).show();
	        	uncheckAllChildrenCascade(optionlist);
	        
		 }
}

		 private void uncheckAllChildrenCascade(ViewGroup vg) {
			    for (int i = 0; i < vg.getChildCount(); i++) {
			        View v = vg.getChildAt(i);
			        if (v instanceof CheckBox) {
			            ((CheckBox) v).setChecked(false);
			        } else if (v instanceof ViewGroup) {
			            uncheckAllChildrenCascade((ViewGroup) v);
			        }
			    }
			}
		 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vote__option, menu);
		return true;
	}

	

}
