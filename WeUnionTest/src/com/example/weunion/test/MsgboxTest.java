package com.example.weunion.test; 

import com.example.weunion.Msgbox;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2; 
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
 
// ActivityInstrumentationTestCase2 provides functional testing of a single activity 
public class MsgboxTest extends ActivityInstrumentationTestCase2<Msgbox> { 
 
	private Msgbox mActivity;

	EditText msg;
	Button post;

	public MsgboxTest() { 
		 super(Msgbox.class); 	 
	 } 

	@Override 
	protected void setUp() throws Exception { 
	 //this method is called every time before any test execution 
	 super.setUp(); 
	 mActivity = getActivity();
	 
	 msg = (EditText)mActivity.findViewById(com.example.weunion.R.id.post_msg);
	 post = (Button)mActivity.findViewById(com.example.weunion.R.id.post);	 

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
	
	 public void testpostmsg() {
		 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Msgbox.class.getName(), null, false);
		 mActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {

				      msg.setText("Coverage testing");
				      post.performClick();
				}
			  });	
		 
		 com.example.weunion.Msgbox nextActivity = (com.example.weunion.Msgbox) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
		 assertNotNull(nextActivity);
		 nextActivity.finish();
	 } 	
	 
 
}