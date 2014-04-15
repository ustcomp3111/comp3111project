package com.example.weunion.test; 

import com.example.weunion.Main_menu;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2; 
import android.test.suitebuilder.annotation.SmallTest;

import android.widget.Button;
import android.widget.ImageView;
 
// ActivityInstrumentationTestCase2 provides functional testing of a single activity 
public class MenuTest extends ActivityInstrumentationTestCase2<Main_menu> { 
 
	private Main_menu mActivity;

	ImageView top,left,right,bottom;
	Button logout;
	
	public MenuTest() { 
		 super(Main_menu.class); 	 
	 } 

	@Override 
	protected void setUp() throws Exception { 
	 //this method is called every time before any test execution 
	 super.setUp(); 
	 mActivity = getActivity();
	 
	 top = (ImageView)mActivity.findViewById(com.example.weunion.R.id.top);
	 right = (ImageView)mActivity.findViewById(com.example.weunion.R.id.right);	 
	 left = (ImageView)mActivity.findViewById(com.example.weunion.R.id.left);	 
	 bottom = (ImageView)mActivity.findViewById(com.example.weunion.R.id.bottom);	 
	 logout = (Button)mActivity.findViewById(com.example.weunion.R.id.b_logout);
	}
	
	@Override 
	 protected void tearDown() throws Exception { 
		 //this method is called every time after any test execution 
		 super.tearDown(); 
	 } 

	@SmallTest // SmallTest: this test doesn't interact with any file system or network. 
	 public void testView() { // checks if the activity is created 
		assertNotNull(getActivity()); 
	 } 
	 
	 public void testtop_iv() {
		 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Event.class.getName(), null, false);
		 mActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
				      // click button and open next activity.			    
				      top.performClick();

				}
			  });	
		 
		 com.example.weunion.Event nextActivity = (com.example.weunion.Event) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		 assertNotNull(nextActivity);
		 nextActivity.finish();
   
	 }

	 public void testbottom_iv() {
		 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Schedule.class.getName(), null, false);
		 mActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
				      // click button and open next activity.			    
				      bottom.performClick();

				}
			  });	
		 
		 com.example.weunion.Schedule nextActivity = (com.example.weunion.Schedule) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		 assertNotNull(nextActivity);
		 nextActivity.finish();
   
	 }
	 
	 public void testleft_iv() {
		 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Msgbox.class.getName(), null, false);
		 mActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
				      // click button and open next activity.			    
				      left.performClick();

				}
			  });	
		 
		 com.example.weunion.Msgbox nextActivity = (com.example.weunion.Msgbox) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		 assertNotNull(nextActivity);
		 nextActivity.finish();
   
	 }
	 
	 public void testright_iv() {
		 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Polling.class.getName(), null, false);
		 mActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
				      // click button and open next activity.			    
				      right.performClick();

				}
			  });	
		 
		 com.example.weunion.Polling nextActivity = (com.example.weunion.Polling) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		 assertNotNull(nextActivity);
		 nextActivity.finish();
   
	 }

	 public void testlogout() {
		 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Login.class.getName(), null, false);
		 mActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
				      // click button and open next activity.			    
				      logout.performClick();

				}
			  });	
		 
		 com.example.weunion.Login nextActivity = (com.example.weunion.Login) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		 assertNotNull(nextActivity);
		 nextActivity.finish();
   
	 }
 
}
