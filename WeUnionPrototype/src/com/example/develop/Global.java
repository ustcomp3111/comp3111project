package com.example.develop;
import java.util.Calendar;

import comp3111project.DateAndTime;
import comp3111project.Events;
import comp3111project.EventNode;
import comp3111project.RegularEvent;
import comp3111project.User;
public class Global {
	public static  String LOGIN_URL = "http://124.244.60.23/weu/login.php";
	public static  String EVENT_URL = "http://124.244.60.23/weu/event.php";
	public static  String POST_URL = "http://124.244.60.23/weu/postevent.php";
	
    static User Default = new User("no name",0);
	static DateAndTime time = new DateAndTime(2014, Calendar.JANUARY, 1, 0);
    static Events no_name_event = new Events("no name event",01, Default, time, 8,"");
	public static comp3111project.User active_user = new comp3111project.User("",0) , other_users = null;
    public static final  User Null = null;
    public static EventNode active_event = new EventNode(no_name_event);
      public static int count = 0, test = 1;
   // public static String string = "";
    {
    Default.AddEvent(new EventNode(no_name_event));	
    Default.AddEvent(new EventNode(no_name_event));	
    }
}