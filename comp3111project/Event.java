package comp3111project;

import java.util.Calendar;
import java.util.GregorianCalendar;


class Event {
	String event_name;
	Guest guest_list_ptr = null;
	User eventholder;
	int event_id, duration;
	DateAndTime begin;
	DateAndTime end;
	
   Event(String name, int b, User a, DateAndTime d, int e) {
	   String event_name = name;
	   eventholder = a;
       event_id = b;
       begin = d;
       duration = e;
       end = d.add(e);
   }
   Event( User a, DateAndTime d, int e) {
	   String event_name = "";
	   eventholder = a;
       event_id = 0;
       begin = d;
       duration = e;
       end = d.add(e);
   }  
  void printthis()
  {
	  System.out.println("Event name: "+event_name+"\nEvent holder: "+eventholder.name+"\n Guest list: ");
	  Guest ptr = guest_list_ptr;
	  while(ptr!=null)
	  {		  
	  if(ptr.attend)
		  System.out.println(ptr.user.name+" attend: T");
		  else
			  System.out.println(ptr.user.name+" attend: F");
				  ptr=ptr.next;		  
	  }
  System.out.println("begin: "+begin.printthis()+"\nend: "+end.printthis()+"\n"); 		  
  }

   boolean overlap(Event event) {
       if (!begin.before(event.end)||!end.after(event.begin))
           return false;
       else
           return true;
   }
   void AddGuest(Guest guest)
   {
	   Guest ptr = guest_list_ptr;
   if(ptr==null)
	   guest_list_ptr = guest;
   else if(ptr.next==null)
	   guest_list_ptr.next = guest;
   else
   {
	   while(ptr.next!=null)
	 ptr = ptr.next;
		  ptr.next = guest;
   }
   }  
   
   DateAndTime Matching()
   {
	   return Matching(DateAndTime.Now());
	 
   }
   DateAndTime Matching(DateAndTime date_and_time)
   {
	   Guest ptr = guest_list_ptr;
	   DateAndTime time = date_and_time,border_line = date_and_time.add(96*30);	   
	   while(ptr!=null&&time.before(border_line))
	   {
		  time = this.eventholder.FreeTimeSlot(time.add(duration), duration);
		  System.out.println("Proposed date and time:\n"+time.printthis());
		  while(ptr!=null)
	   {
			  if(ptr.user.overlap(time,duration))
			   //if(ptr.attend&&ptr.user.overlap(time,duration))
		   
				  {
				 System.out.println("overlaped: "+ptr.user.name);			  
			   ptr = guest_list_ptr;
			   break;
		   }
			  else
			   ptr = ptr.next;
	   }
		  if(ptr==null)
		  {
			  System.out.println("return time");
			  return time;
		  }  
	   }
	   System.out.println("failed to find a suitable time for every guest");
	   return DateAndTime.Now();
   }
}

//For constructing linked list
class EventNode
{

   EventNode next = null;
   Event event;
   EventNode(Event e)
   {
	   event = e;
   }
   EventNode(String a, int b, User c, DateAndTime d, int e) {
       event = new Event(a,b, c, d, e);
   }
   
};
//Example: 1/1/2014 12:45pm = DateAndTime(2014,Calendar.January,1,51)
class DateAndTime {
    GregorianCalendar Date;
    private static GregorianCalendar now = new GregorianCalendar();
    int time_slot;
//    Find the current time in DateAndTime format
    static DateAndTime Now()
    {
    	DateAndTime Now = new DateAndTime(now.get(Calendar.YEAR),
                now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH),
                now.get(Calendar.HOUR_OF_DAY) * 4 + (now.get(Calendar.MINUTE) + 1) / 15);
    	return Now;
    }
    DateAndTime(int year, int month, int day_of_month, int interval) {
        Date = new GregorianCalendar(year, month, day_of_month);
        time_slot = interval;
    }
 
    int weekday() {
        return Date.get(Calendar.DAY_OF_WEEK);
    }
    WeekdayAndTime ReturnWeekdayAndTime()
    {
 	   return new WeekdayAndTime(weekday(),time_slot);
 	   
    }
    String printthis() {
 
        return " Year: " + Date.get(Calendar.YEAR) + " Month: "
                + (Date.get(Calendar.MONTH) + 1) + " Day: "
                + Date.get(Calendar.DAY_OF_MONTH) + " Weekday: "
                + Date.get(Calendar.DAY_OF_WEEK) + " time: " + time_slot;
    }
 
    boolean before(DateAndTime date_and_time) {
        if (Date.after(date_and_time.Date))
            return false;
        else if (Date.before(date_and_time.Date))
 
            return true;
        else if (time_slot < date_and_time.time_slot
                && Date.equals(date_and_time.Date))
 
            return true;
        else
            return false;
    }
 
    boolean after(DateAndTime date_and_time) {
        if (Date.after(date_and_time.Date))
            return true;
        else if (Date.before(date_and_time.Date))
            return false;
 
        else if (time_slot > date_and_time.time_slot
                && Date.equals(date_and_time.Date))
            return true;
        else
 
            return false;
    }
 //Find the number of days of a month in a year
    int NumOfDay(int month, int year) {
        if (month == GregorianCalendar.JANUARY
                || month == GregorianCalendar.MARCH
                || month == GregorianCalendar.MAY
                || month == GregorianCalendar.JULY
                || month == GregorianCalendar.AUGUST
                || month == GregorianCalendar.OCTOBER
                || month == GregorianCalendar.DECEMBER)
            return 31;
        else if (month == GregorianCalendar.APRIL
                || month == GregorianCalendar.JUNE
                || month == GregorianCalendar.SEPTEMBER
                || month == GregorianCalendar.NOVEMBER)
            return 30;
        else if (year % 400 == 0 || (!(year % 100 == 0) && year % 4 == 0))
            return 29;
        else
            return 28;
    }
 
    int NextMonth(int month) {
        switch (month) {
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
 
    //e.g time = 100(15 minutes*100 = 1 day and 1 hours)
    //[31/1/2014 15:00] + 100 = 1/2/2014 16:00
    //time should be a +ve integer
    DateAndTime add(int time) {
        DateAndTime result = new DateAndTime(this.Date.get(Calendar.YEAR),
                this.Date.get(Calendar.MONTH),
                this.Date.get(Calendar.DAY_OF_MONTH), this.time_slot);
        int day = time / 96, year = result.Date.get(Calendar.YEAR);
        time %= 96;
        result.time_slot += time;
        day += result.time_slot / 96;
        result.time_slot %= 96;
        day += result.Date.get(Calendar.DAY_OF_MONTH);
        while (true) {
            if (day > result.NumOfDay(result.Date.get(Calendar.MONTH),
                    result.Date.get(Calendar.YEAR))) {
                day -= result.NumOfDay(result.Date.get(Calendar.MONTH),
                        result.Date.get(Calendar.YEAR));
                result.Date.set(result.Date.get(Calendar.YEAR), result.NextMonth(result.Date.get(Calendar.MONTH)),
                        result.Date.get(Calendar.DAY_OF_MONTH));
                if (result.Date.get(Calendar.MONTH) == Calendar.JANUARY)
                   // year++;
                	result.Date.set(result.Date.get(Calendar.YEAR)+1,result.Date.get(Calendar.MONTH),result.Date.get(Calendar.DAY_OF_MONTH));
            } else
                break;
        }
        result.Date.set(year, result.Date.get(Calendar.MONTH), day);
        return result;
    }
 
    //Find the next date of a weekday from the date represented by this object
    //e.g Regular event = Tuesday 12:00, this event = 1/1/2014 12:00
    //FindDateOfWeekday = 7/1/2014 12:00
    DateAndTime FindDateOfWeekday(RegularEvent Event) {
        int count = Event.begin.week_day - this.weekday();
        if (count < 0||(count==0&&this.time_slot>Event.end.time_slot))
            count += 7;
        DateAndTime result = this.add(count * 96 - this.time_slot
                + Event.end.time_slot);
        return result;
    }
 
    DateAndTime FindDateOfWeekday(int week_day) {
        int count = week_day - this.weekday();
        if (count < 0)
            count += 7;
        DateAndTime result = this.add(count * 96 - this.time_slot);
        return result;
    }
}
 