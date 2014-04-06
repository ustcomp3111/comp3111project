package comp3111project;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Event {
	public String event_name = "no name event";
	public Guest guest_list_ptr = null;
	public User host;
	public int event_id, duration;
	public DateAndTime begin;
	public DateAndTime end;
	public Event next = null;
	public String location;
   public Event(String name, int b, User a, DateAndTime d, int e,String f) {
	   event_name = name;
	   host = a;
       event_id = b;
       begin = d;
       duration = e;
       end = d.add(e);
       
       location = f;
   }
   Event( User a, DateAndTime d, int e) {
	   String event_name = "";
	   host = a;
       event_id = 0;
       begin = d;
       duration = e;
       end = d.add(e);
   }  
  void printthis()
  {
	  System.out.println("Event name: "+event_name+"\nEvent holder: "+host.name+"\n Guest list: ");
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
		  time = this.host.FreeTimeSlot(time.add(duration), duration);
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




 