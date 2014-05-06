package com.example.weunion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.widget.ListView;

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
	public static final String SHOW_SECRET_LIST_URL = SERVER_URL+"/weu/secretlist.php";
	public static final String ADD_SECRET_LIST_URL = SERVER_URL+"/weu/addsecretlist.php";
	public static final String REMOVE_SECRET_LIST_URL = SERVER_URL+"/weu/delsecretlist.php";
	public static final String MATCH_SECRET_LIST_URL = SERVER_URL+"/weu/matchsecretlist.php";
	public static final String EDIT_EVENT_URL = SERVER_URL+"/weu/updateevent.php";
	public static final String DELETE_EVENT_URL = SERVER_URL+"/weu/delevent.php";
	public static final String VOTE_URL = SERVER_URL+"/weu/updatevote.php";
	public static final String DELETE_REGULAR_EVENT_URL =  SERVER_URL+"/weu/delr_event.php";
 	public static final String EDIT_REGULAR_EVENT_URL =  SERVER_URL+"/weu/updater_event.php";
 	public static int regular_event_position = 0;
 	public static String Default_name = "";
	public static comp3111project.User active_user = new comp3111project.User("",0) , other_users = null;
    public static EventNode active_event;
    public static ArrayList<String> joined_event_list = new ArrayList<String>(),
    my_event_list = new ArrayList<String>(),
    all_event_list  = new ArrayList<String>(),
    all_guest_list = new ArrayList<String>(),
    going_guest_list = new ArrayList<String>(),
    declined_guest_list = new ArrayList<String>(),
    pending_guest_list = new ArrayList<String>(),
    agenda_list = new ArrayList<String>(),
    friend_list = new ArrayList<String>(),
    my_secret_list = new ArrayList<String>(),
    add_secret_list = new ArrayList<String>(),
    matching_list = new ArrayList<String>(),
    check_list = new ArrayList<String>();
    public static ArrayList<Integer> joined_event_id_list = new ArrayList<Integer>(),
    my_event_id_list = new ArrayList<Integer>(),
    all_event_id_list = new ArrayList<Integer>(),
    agenda_id_list = new ArrayList<Integer>(),
    friend_id_list = new ArrayList<Integer>(),
    my_secret_id_list = new ArrayList<Integer>(),
    add_secret_id_list  = new ArrayList<Integer>(),
    matching_id_list  = new ArrayList<Integer>();
    public static boolean initialization_is_completed = false,load_secret_list_is_completed = false,edit_event = false,clicked = false;
   public static int guest_list_choice = 0;
   static ListView my_secret_list_listview,all_guest_listview,pending_guest_listview;
   public static String pollingid;
   
   public static ArrayList<ArrayList<String>> pollidlist = new ArrayList<ArrayList<String>>();
   public static double[] votelist =new double[5];
}