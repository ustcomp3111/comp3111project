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
	public static final String JOINED_EVENT_URL = SERVER_URL+"/weu/readmyevents.php";
	public static final String REGULAR_EVENT_URL = SERVER_URL+"/weu/r_event.php";
	public static final String CREATE_REGULAR_EVENT_URL = SERVER_URL+"/weu/postr_event.php";
	public static comp3111project.User active_user = new comp3111project.User("",0) , other_users = null;
    public static EventNode active_event;
    public static ArrayList<String> joined_event_list = new ArrayList<String>(),
    my_event_list = new ArrayList<String>(),
    all_event_list  = new ArrayList<String>(),
    all_guest_list = new ArrayList<String>(),
    going_guest_list = new ArrayList<String>(),
    declined_guest_list = new ArrayList<String>(),
    pending_guest_list = new ArrayList<String>(),
    agenda_list = new ArrayList<String>();
    public static ArrayList<Integer> joined_event_id_list = new ArrayList<Integer>(),
    my_event_id_list = new ArrayList<Integer>(),
    all_event_id_list = new ArrayList<Integer>(),
    agenda_id_list = new ArrayList<Integer>();
    public static boolean initialization_is_completed = false;
   public static int guest_list_choice = 0;
   
}