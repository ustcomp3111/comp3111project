package com.example.weunion.test; 

import com.example.weunion.Main_menu;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2; 
import android.test.suitebuilder.annotation.SmallTest;

import android.widget.ImageView;
 
// ActivityInstrumentationTestCase2 provides functional testing of a single activity 
public class MenuTest extends ActivityInstrumentationTestCase2<Main_menu> { 
 
	private Main_menu mActivity;

	ImageView top,left,right,bottom;
	
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
		 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Main_menu.class.getName(), null, false);
		 mActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
				      // click button and open next activity.			    
				      top.performClick();

				}
			  });	
		 
	//	 Main_menu nextActivity = (Main_menu) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
	//	 assertNotNull(nextActivity);
	//	 nextActivity.finish();
   
	 }


 
}