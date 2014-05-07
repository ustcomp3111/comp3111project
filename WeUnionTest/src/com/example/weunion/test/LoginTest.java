package com.example.weunion.test; 

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

import com.example.weunion.Login;
import com.example.weunion.Main_menu;
 
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
	}
	
	private void getid() {
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
		getid();
		assertNotNull(getActivity()); 
	 } 
	
	@UiThreadTest
	 public void testfail_Login() {
		 getid();
		 mActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
			      // click button and open next activity.			    
			      id.setText("abc");
			      pw.setText("def");
			      login.performClick();
			}
		  });
	 }
	
	@MediumTest	 
	 public void testcorrect_Login() {
		 getid();
		 ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Main_menu.class.getName(), null, false);
		 mActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
				      // click button and open next activity.			    
				      id.setText("admin");
				      pw.setText("admin");
				      login.performClick();
				}
			  });	
		 
		 Main_menu nextActivity = (Main_menu) getInstrumentation().waitForMonitor(activityMonitor);
		 assertNotNull(nextActivity);
		 nextActivity.finish();
   	 }		
}