package comp3111project;
import java.util.Calendar;

class DateAndTime
{
	Calendar Date;
	int time_slot;
	}

class User // object which stores user's info
{
	String name;
int user_id;	
class Schedule
{
EventNode schedule_ptr;

};

};
	
class Guest extends User//node of linked list of guest list
{
	Guest next=null;
};

class EventNode
{
	User eventholder;	
	int event_id;	
	DateAndTime starting_date_and_time,ending_date_and_time;
	
EventNode next=null;
	
	EventNode(User a,int b,DateAndTime d,DateAndTime e)
{
	eventholder = a ;
	event_id = b ;
	starting_date_and_time = d ;	
	ending_date_and_time=e;
}
}

class Event extends EventNode
{
String event_name;
Guest guestlist_ptr=null;

Event(String a,int b,User c,DateAndTime d,DateAndTime e)
{
	super(c,b,d,e);
	event_name = a;
}
	};

	public class ScheduleMatching {

	/**
	 * @param args
	 */
		 DateAndTime matching(Event event,int duration)
			{
			 int count=duration;
			 
			 
			};
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		
	}

}
