package com.example.weunion.test;

import android.annotation.SuppressLint;
import junit.framework.TestSuite;

@SuppressLint("NewApi")
public class AllTest {
	
	 Class[] testClasses = { LoginTest.class, MenuTest.class };
	 TestSuite suite= new TestSuite(testClasses);
	 
}
