package com.example.weunion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import comp3111project.DateAndTime;
import comp3111project.Events;
import comp3111project.EventNode;
import comp3111project.User;
public class Global {
	public static final String SERVER_URL = "http://124.244.60.23";
	public static final String LOGIN_URL = SERVER_URL+"/weu/login.php";
	public static final String EVENT_URL = SERVER_URL+"/weu/event.php";
	public static final String POST_URL = SERVER_URL+"/weu/postevent.php";
	public static final String POLLING_URL = SERVER_URL+"/weu/readpolling.php";
	public static final String SHOW_GUEST_URL = SERVER_URL+"/weu/showguest.php";
	public static final String POLLINGID_URL = SERVER_URL+"/weu/readpollingbyid.php";
	public static final String UPDATE_GUEST_URL = SERVER_URL+"/weu/updateguest.php";
	public static final String INVITE_FD_URL = SERVER_URL+"/weu/postguest.php";
   // static User Default = new User("no name",0);
	//static DateAndTime time = new DateAndTime(0, Calendar.JANUARY, 1, 0);
    //static Events no_name_event = new Events("no name event",01, Default, time, 8,"");
	public static comp3111project.User active_user = new comp3111project.User("",0) , other_users = null;
    public static EventNode active_event;//new EventNode(no_name_event);
    public static ArrayList<String> eventlist = new ArrayList<String>(),list_of_event_by_me = new ArrayList<String>(),
    all_guest_list = new ArrayList<String>(), going_guest_list = new ArrayList<String>(),
    declined_guest_list = new ArrayList<String>(), pending_guest_list = new ArrayList<String>();
    public static ArrayList<Integer> event_id_list = new ArrayList<Integer>(),event_by_me_id = new ArrayList<Integer>();
    
    public static boolean initialization_is_completed = false;
   public static int guest_list_choice = 0;
   
}