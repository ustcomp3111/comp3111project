package com.example.weunion.test; 

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weunion.Main_menu;
import com.example.weunion.R;
 
// ActivityInstrumentationTestCase2 provides functional testing of a single activity 
public class Main_menuTest extends ActivityInstrumentationTestCase2<Main_menu> { 
 
	private Main_menu mActivity;

	TextView username;
	ImageView events, friend, secret, schedule;
	Button logout;

	public Main_menuTest() { 
		 super(Main_menu.class); 	 
	 } 

	@Override 
	protected void setUp() throws Exception { 
	 //this method is called every time before any test execution 
	 super.setUp(); 
	 mActivity = getActivity();
	 
		username = (TextView) mActivity.findViewById(R.id.username);
		events = (ImageView) mActivity.findViewById(R.id.top);
		friend = (ImageView) mActivity.findViewById(R.id.left);
		secret = (ImageView) mActivity.findViewById(R.id.right);
		schedule = (ImageView) mActivity.findViewById(R.id.bottom);
		logout = (Button) mActivity.findViewById(R.id.b_logout);
	}
	
	@Override 
	 protected void tearDown() throws Exception { 
		 //this method is called every time after any test execution 
		 super.tearDown(); 
	 } 
	
	 public void testTop() {
	 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.EventMenu.class.getName(), null, false);
	 mActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
			      // click button and open next activity.
		    	events.performClick();
			}
		  });	
	 
	 com.example.weunion.EventMenu nextActivity = (com.example.weunion.EventMenu) getInstrumentation().waitForMonitor(activityMonitor);
	 assertNotNull(nextActivity);
	 nextActivity.finish();
	 } 	
	 
	 public void testLeft() {
	 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Friend.class.getName(), null, false);
	 mActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
			      // click button and open next activity.
		    	friend.performClick();
			}
		  });	
	 
	 com.example.weunion.Friend nextActivity = (com.example.weunion.Friend) getInstrumentation().waitForMonitor(activityMonitor);
	 assertNotNull(nextActivity);
	 nextActivity.finish();
	 } 	
	 
	 public void testRight() {
	 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.SecretList.class.getName(), null, false);
	 mActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
			      // click button and open next activity.
		    	secret.performClick();
			}
		  });	
	 
	 com.example.weunion.SecretList nextActivity = (com.example.weunion.SecretList) getInstrumentation().waitForMonitor(activityMonitor);
	 assertNotNull(nextActivity);
	 nextActivity.finish();
	 } 	
	 
	 public void testBottom() {
	 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Schedule.class.getName(), null, false);
	 mActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
			      // click button and open next activity.
		    	schedule.performClick();
			}
		  });	
	 
	 com.example.weunion.Schedule nextActivity = (com.example.weunion.Schedule) getInstrumentation().waitForMonitor(activityMonitor);
	 assertNotNull(nextActivity);
	 nextActivity.finish();
	 } 	
	 	 
	 public void testLogout() {
	 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Login.class.getName(), null, false);
	 mActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
			      // click button and open next activity.
		    	logout.performClick();
			}
		  });	
	 
	 com.example.weunion.Login nextActivity = (com.example.weunion.Login) getInstrumentation().waitForMonitor(activityMonitor);
	 assertNotNull(nextActivity);
	 nextActivity.finish();
	 } 
	 
}