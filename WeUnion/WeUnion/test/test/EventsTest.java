package test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;
import comp3111project.Guest;
import comp3111project.RegularEvent;
import comp3111project.RegularEventNode;
import comp3111project.User;

public class EventsTest {


	static	User user1 = new User ("user1",0);
	static	Events event1 = new Events("event1",0,user1,new DateAndTime(2014,1,1,48),8,""),
		event2 = new Events("event2",0,user1,new DateAndTime(2014,1,1,50),6,""),
		event3 = new Events("event3",0,user1,new DateAndTime(2014,1,2,72),4,"");
	 
	static User Gordon = new User("Gordon", 01), Wa = new User ("Wa",02), Chi = new User ("Chi",03),
			Shirley = new User("Shirley",04), Calvin = new User("Calvin",05);
  
	static DateAndTime a = new DateAndTime(2014, Calendar.APRIL, 4, 0),
    		b = new DateAndTime(2014, Calendar.APRIL, 7, 82),
	c = new DateAndTime(2014, Calendar.APRIL, 8, 70);
    static Events gathering = new Events("gathering",01, Gordon, a, 8,""),
    		meeting = new Events("meeting",02, Gordon, b, 13,""),
    meeting2 = new Events("meeting2",03, Gordon, c, 4,"");
    static RegularEvent mon = new RegularEvent("mon",0,Calendar.MONDAY, 0, 72),
    tue = new RegularEvent("tue",0,Calendar.TUESDAY, 0, 72),
    wed = new RegularEvent("wed",0,Calendar.WEDNESDAY, 0,72),
    thu = new RegularEvent("thu",0,Calendar.THURSDAY, 0, 72),
    fri = new RegularEvent("fri",0,Calendar.FRIDAY, 0, 72),
    sat = new RegularEvent("sat",0,Calendar.SATURDAY, 0, 72),
    sun = new RegularEvent("sun",0,Calendar.SUNDAY, 0, 72),
    part_time = new RegularEvent("pt",0,Calendar.MONDAY, 76, 10),
    part_time2 = new RegularEvent("pt2",0,Calendar.FRIDAY, 75, 10),
    part_time3 = new RegularEvent("pt3",0,Calendar.SUNDAY, 72, 20),	
    leaveHK = new RegularEvent("",0,Calendar.SUNDAY, 80, 48),
   part_time4 = new RegularEvent("",0,Calendar.WEDNESDAY, 80, 6);
    
    static{
    	Gordon.AddRegularEvent(new RegularEventNode(mon));
    Gordon.AddRegularEvent(new RegularEventNode(mon));
   Gordon.AddRegularEvent(new RegularEventNode(tue));
    Gordon.AddRegularEvent(new RegularEventNode(wed));;
    Gordon.AddRegularEvent(new RegularEventNode(thu));
    Gordon.AddRegularEvent(new RegularEventNode(fri));
    Gordon.AddRegularEvent(new RegularEventNode(sat));
    Gordon.AddRegularEvent(new RegularEventNode(sun));
    Gordon.AddRegularEvent(new RegularEventNode(part_time));
    Gordon.AddRegularEvent(new RegularEventNode(part_time2));
    Gordon.AddRegularEvent(new RegularEventNode(part_time3));
    //Gordon.AddRegularEvent(new RegularEventNode(part_time4)); 
  
    Gordon.AddEvent(new EventNode(meeting));
    Gordon.AddEvent(new EventNode(meeting2));
    
    Gordon.printregularevent();
    Gordon.printevent();
    //System.out.println(Gordon.schedule_ptr.regular_event.begin.printthis());
   //System.out.println(Gordon.schedule_ptr.next.regular_event.begin.printthis());
   // System.out.println(Gordon.FreeTimeSlot(a,12).printthis());
    Wa.AddRegularEvent(new RegularEventNode(mon));
    Wa.AddRegularEvent(new RegularEventNode(tue));
    Wa.AddRegularEvent(new RegularEventNode(wed));
    Wa.AddRegularEvent(new RegularEventNode(thu));
    Wa.AddRegularEvent(new RegularEventNode(fri));
    Wa.AddRegularEvent(new RegularEventNode(sat));
    Wa.AddRegularEvent(new RegularEventNode(sun));
    Wa.AddRegularEvent(new RegularEventNode(part_time3));
    Wa.printregularevent();
    Shirley.AddRegularEvent(new RegularEventNode(mon));
    Shirley.AddRegularEvent(new RegularEventNode(tue));
    Shirley.AddRegularEvent(new RegularEventNode(wed));
    Shirley.AddRegularEvent(new RegularEventNode(thu));
    Shirley.AddRegularEvent(new RegularEventNode(fri));
    Shirley.AddRegularEvent(new RegularEventNode(sat));
    Shirley.AddRegularEvent(new RegularEventNode(sun));
    Shirley.AddRegularEvent(new RegularEventNode(part_time2));
    Shirley.printregularevent();
    Chi.AddRegularEvent(new RegularEventNode(mon));
    Chi.AddRegularEvent(new RegularEventNode(tue));
    Chi.AddRegularEvent(new RegularEventNode(wed));
    Chi.AddRegularEvent(new RegularEventNode(thu));
    Chi.AddRegularEvent(new RegularEventNode(fri));
    Chi.AddRegularEvent(new RegularEventNode(sat));
    Chi.AddRegularEvent(new RegularEventNode(sun));
    Chi.AddRegularEvent(new RegularEventNode(part_time));
    Chi.printregularevent();
    Calvin.AddRegularEvent(new RegularEventNode(mon));
    Calvin.AddRegularEvent(new RegularEventNode(tue));
    Calvin.AddRegularEvent(new RegularEventNode(wed));
    Calvin.AddRegularEvent(new RegularEventNode(thu));
    Calvin.AddRegularEvent(new RegularEventNode(fri));
    Calvin.AddRegularEvent(new RegularEventNode(sat));
    Calvin.AddRegularEvent(new RegularEventNode(sun));
    Calvin.AddRegularEvent(new RegularEventNode(part_time3));
    Calvin.AddEvent(new EventNode(meeting2));
    Calvin.printregularevent();
    Gordon.AddEvent(new EventNode(gathering));
    Wa.AddEvent(new EventNode(meeting));

    }
    @Test
	public void testOverlap() {
		assertTrue(event1.overlap(event2));
	}

	@Test
	public void testOverlap2() {
		assertFalse(event1.overlap(event3));
	}
	@Test
	
	public void testAddGuest() {
	    gathering.AddGuest(new Guest(Wa));
	    gathering.AddGuest(new Guest(Chi));
	    gathering.AddGuest(new Guest(Shirley));
	    gathering.AddGuest(new Guest(Calvin));
	}
	@Test

	public void testMatching() {
		
		assertEquals(new DateAndTime(2014,2,1,72).time_slot,gathering.Matching(new DateAndTime(2014,1,1,0)).time_slot);
	
		
	}

	@Test
	public void testMatchingDateAndTime() {
	
	}

}
