package comp3111project;
import java.util.Calendar;
import java.util.GregorianCalendar;;

class DateAndTime{
GregorianCalendar Date;
	int time_slot;
	
	boolean before(DateAndTime date_and_time)
	{
		if (this.Date.before(date_and_time.Date))
			return true;
		else if (this.time_slot<date_and_time.time_slot)
			return true;
		else
			return false;
	}
	
	boolean ValidDayOfMonth(int day,int month,int year)
	{
		if (month==GregorianCalendar.JANUARY||month==GregorianCalendar.MARCH||month==GregorianCalendar.MAY||month==GregorianCalendar.JULY||month==GregorianCalendar.AUGUST
				||month==GregorianCalendar.OCTOBER||month==GregorianCalendar.DECEMBER)
		{
			if (day>0&&day<32)
				return true;
			else
				return false;
		}
		else if (month==GregorianCalendar.APRIL||month==GregorianCalendar.JUNE||month==GregorianCalendar.SEPTEMBER||month==GregorianCalendar.NOVEMBER)
		{
			if (day>0&&day<31)
				return true;
			else
				return false;
		
	}
		else if (year%400==0||(!(year%100==0)&&year%4==0))
		{
			if (day>0&&day<30)
				return true;
			else
				return false;
		
	}
		else
		{
			if (day>0&&day<29)
				return true;
			else
				return false;
		
	}}
	
	int NumOfDay(int month,int year)
	{
		if (month==GregorianCalendar.JANUARY||month==GregorianCalendar.MARCH||month==GregorianCalendar.MAY||month==GregorianCalendar.JULY||month==GregorianCalendar.AUGUST
				||month==GregorianCalendar.OCTOBER||month==GregorianCalendar.DECEMBER)
	return 31;
		else if (month==GregorianCalendar.APRIL||month==GregorianCalendar.JUNE||month==GregorianCalendar.SEPTEMBER||month==GregorianCalendar.NOVEMBER)
return 30;
		else if (year%400==0||(!(year%100==0)&&year%4==0))
	return 29;
		else
			return 28;
	}
	
	void add(int time)
	{
		
		int day=(time-time%96)/96,month=0,year=0;
	time%=96;
	this.time_slot+=time;
	day+=(this.time_slot-this.time_slot%96)/96;
	this.time_slot%=96;
		day+=this.Date.get(Calendar.DAY_OF_MONTH);
		
		if (!this.ValidDayOfMonth(day,this.Date.get(Calendar.MONTH), this.Date.get(Calendar.YEAR)))
		//month+=day/this.NumOfDay(this.Date.MONTH,this.Date.YEAR);
			//month+=(day-day%this.NumOfDay(this.Date.MONTH,this.Date.YEAR))/this.NumOfDay(this.Date.MONTH,this.Date.YEAR);
			
			;
		
	}
}

class User // object which stores user's info
{
	String name;
int user_id;	
EventNode schedule_ptr;

/*DateAndTime FreeTimeSlot(DateAndTime time,int duration) // find a free time slot which is closest to 'time'
{
EventNode ptr=schedule_ptr;

if (!ptr.ending_date_and_time.before(time)&&ptr.next.starting_date_and_time.before(time))


}
*/
boolean IsFree(DateAndTime date_and_time)
{
	
	return true;
	}
};
	
class Guest extends User//node of linked list of guest list
{
	Guest next=null;
};

class EventNode
{
	User eventholder;	
	int event_id,duration;	
	DateAndTime starting_date_and_time;
	DateAndTime ending_date_and_time;
EventNode next=null;	
	EventNode(User a,int b,DateAndTime d,int e)
{
	eventholder = a ;
	event_id = b ;
	starting_date_and_time = d ;	
	duration=e;
}
}

class Event extends EventNode // store extra information of an event
{
String event_name;
Guest guestlist_ptr=null;

Event(String a,int b,User c,DateAndTime d,int e)
{
	super(c,b,d,e);
	event_name = a;
}
	};

	public class ScheduleMatching {

	/**
	 * @param args
	 */
	/*	 DateAndTime matching(Event event,int duration)
			{
			 
			 
			 
			};
		*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GregorianCalendar a =new GregorianCalendar(2014,10,12);
int month=a.get(Calendar.DAY_OF_MONTH);
System.out.println(month);
	}

}
