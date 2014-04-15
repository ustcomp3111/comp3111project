package com.example.weunion.test; 

import com.example.weunion.Login;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2; 
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
 
// ActivityInstrumentationTestCase2 provides functional testing of a single activity 
public class LoginTest extends ActivityInstrumentationTestCase2<Login> { 
 
	private Login mActivity;
	EditText id,pw;
	Button login,reg;
	
	public LoginTest() { 
		 super(Login.class); 	 
	 } 

	@Override 
	protected void setUp() throws Exception { 
	 //this method is called every time before any test execution 
	 super.setUp(); 
	 mActivity = getActivity();
	 
	 id = (EditText)mActivity.findViewById(com.example.weunion.R.id.et_id);
	 pw = (EditText)mActivity.findViewById(com.example.weunion.R.id.et_pw);
	 login = (Button)mActivity.findViewById(com.example.weunion.R.id.b_confirm);
	 reg = (Button)mActivity.findViewById(com.example.weunion.R.id.b_reg);	 
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

	
	/* public void testfail_Login() {
		 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Login.class.getName(), null, false);
		 mActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
				      // click button and open next activity.			    
				      id.setText("abc");
				      pw.setText("def");
				      login.performClick();
				}
			  });	
		 
		 com.example.weunion.Login nextActivity = (com.example.weunion.Login) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
		 assertNull(nextActivity);
		 
	 } 	*/
	 
	/* public void testcorrect_Login() {
		 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(com.example.weunion.Login.class.getName(), null, false);
		 mActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
				      // click button and open next activity.			    
				      id.setText("admin");
				      pw.setText("admin");
				      login.performClick();
				}
			  });	
		 
		 Login nextActivity = (Login) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
		 assertNotNull(nextActivity);
		 nextActivity.finish();
   
	 }*/


 
}
