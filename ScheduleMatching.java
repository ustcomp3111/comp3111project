package comp3111project;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.math.*;;

class DateAndTime{
GregorianCalendar Date;
	int time_slot;
	int weekday;
	
	DateAndTime(int year,int month,int day_of_month,int interval){
		Date = new GregorianCalendar();
		Date.set(year, month, day_of_month);
		time_slot=interval;
		weekday=Date.get(Calendar.DAY_OF_WEEK);
	}
	boolean before(DateAndTime date_and_time){
		if (this.Date.before(date_and_time.Date))
			return true;
		else if (this.time_slot<date_and_time.time_slot)
			return true;
		else
			return false;
	}
	
	int NumOfDay(int month,int year){
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
	
	int NextMonth(int month){
		switch (month)
		{
		case (Calendar.JANUARY):
			return Calendar.FEBRUARY;
		case (Calendar.FEBRUARY):
			return Calendar.MARCH;
		case (Calendar.MARCH):
			return Calendar.APRIL;
		case (Calendar.APRIL):
			return Calendar.MAY;
		case (Calendar.MAY):
			return Calendar.JUNE;
		case (Calendar.JUNE):
			return Calendar.JULY;
		case (Calendar.JULY):
			return Calendar.AUGUST;
		case (Calendar.AUGUST):
			return Calendar.SEPTEMBER;
		case (Calendar.SEPTEMBER):
			return Calendar.OCTOBER;
		case (Calendar.OCTOBER):
			return Calendar.NOVEMBER;
		case (Calendar.NOVEMBER):
			return Calendar.DECEMBER;
		case (Calendar.DECEMBER):
			return Calendar.JANUARY;
		default:
			return -1;
		}
					
		
	}
	DateAndTime add(int time)
	{
		DateAndTime result = new DateAndTime(this.Date.get(Calendar.YEAR),this.Date.get(Calendar.MONTH),this.Date.get(Calendar.DAY_OF_MONTH),this.time_slot);
		int day = time/96, year = result.Date.get(Calendar.YEAR);
		time%=96;
	result.time_slot += time;
	day += result.time_slot/96;
	result.time_slot%=96;
		
	day+=result.Date.get(Calendar.DAY_OF_MONTH);
		            System.out.println("day(before loop): "+day);
		     
	while(true){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
		if (day>result.NumOfDay(result.Date.get(Calendar.MONTH), result.Date.get(Calendar.YEAR))){
			day-=result.NumOfDay(result.Date.get(Calendar.MONTH), result.Date.get(Calendar.YEAR));
		System.out.println("day(after loop): "+day);
			result.Date.set(year,result.NextMonth(Calendar.MONTH), result.Date.get(Calendar.DAY_OF_MONTH));
		if (result.Date.get(Calendar.MONTH)==Calendar.JANUARY)
			year++;
		}
		else
			break;
	}
	result.Date.set(year,result.Date.get(Calendar.MONTH), day);
		return result;
		
		//if (!this.ValidDayOfMonth(day,this.Date.get(Calendar.MONTH), this.Date.get(Calendar.YEAR)))
		//month+=day/this.NumOfDay(this.Date.MONTH,this.Date.YEAR);
			//month+=(day-day%this.NumOfDay(this.Date.MONTH,this.Date.YEAR))/this.NumOfDay(this.Date.MONTH,this.Date.YEAR);
			
		
	}
}

class User // object which stores user's info
{
	String name;
int user_id;	
EventNode event_ptr;
RegularEventNode schedule_ptr;

/*DateAndTime FreeTimeSlot(DateAndTime time,int duration) // find a free time slot which is closest to 'time'
{
EventNode event_node_ptr=event_ptr;
RegularEventNode regular_ptr=schedule_ptr;

if ()

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

class RegularEventNode
{
int weekday,starting_interval,ending_interval,duration;
RegularEventNode ptr;
RegularEventNode(int Weekday,int Starting_interval,int Duration)
{
	weekday=Weekday;
	starting_interval=Starting_interval;
	duration=Duration;
	ending_interval=starting_interval+duration;
	}
int difference (RegularEventNode node)
{
int day_difference = (node.weekday-this.weekday);
if (day_difference<0)
	day_difference+=7;
	int interval = Math.abs(this.duration-node.duration)+day_difference*96;
return interval;
}
}
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
//ending_date_and_time=d.add(e);
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
	//	GregorianCalendar a =new GregorianCalendar(2014,10,12);
		//DateAndTime b = new DateAndTime(2001,Calendar.FEBRUARY,27,8);
	//DateAndTime c=b.add(386);
	//System.out.println("year: "+c.Date.get(Calendar.YEAR)+" month: "+c.Date.get(Calendar.MONTH)+" day: "+c.Date.get(Calendar.DAY_OF_MONTH)+" interval: "+c.time_slot);
		RegularEventNode a= new RegularEventNode(Calendar.MONDAY,48,4),b=new RegularEventNode(Calendar.SUNDAY,52,4);
System.out.print(a.difference(b));
	}

	}
