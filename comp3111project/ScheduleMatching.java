package comp3111project;
 import java.util.Calendar;
import java.util.GregorianCalendar;

public class ScheduleMatching {
 
    /**
     * @param args
     */
	
    public static void main(String[] args) {
 
    	User Gordon = new User("Gordon", 01), Wa = new User ("Wa",02), Chi = new User ("Chi",03),
    			Shirley = new User("Shirley",04), Calvin = new User("Calvin",05);
      
    	DateAndTime a = new DateAndTime(2014, Calendar.MARCH, 18, 72),
        		b = new DateAndTime(2014, Calendar.MARCH, 9, 73);
 
        Event gathering = new Event("gathering",01, Gordon, DateAndTime.Now(), 12),
        		meeting = new Event("meeting",02, Gordon, b, 8);
        
        RegularEvent mon = new RegularEvent(Calendar.MONDAY, 0, 72);
        RegularEvent tue = new RegularEvent(Calendar.TUESDAY, 0, 72);
        RegularEvent wed = new RegularEvent(Calendar.WEDNESDAY, 0,72);
        RegularEvent thu = new RegularEvent(Calendar.THURSDAY, 0, 72);
        RegularEvent fri = new RegularEvent(Calendar.FRIDAY, 0, 72);
        RegularEvent sat = new RegularEvent(Calendar.SATURDAY, 0, 32);
        RegularEvent sun = new RegularEvent(Calendar.SUNDAY, 0, 32);
        RegularEvent part_time = new RegularEvent(Calendar.SUNDAY, 48, 32);
        RegularEvent part_time2 = new RegularEvent(Calendar.SATURDAY, 48, 32);
        RegularEvent part_time3 = new RegularEvent(Calendar.MONDAY, 72, 6);	
        RegularEvent leaveHK = new RegularEvent(Calendar.SUNDAY, 80, 48);
        RegularEvent part_time4 = new RegularEvent(Calendar.WEDNESDAY, 80, 6);
        Gordon.AddRegularEvent(new RegularEventNode(mon));
        Gordon.AddRegularEvent(new RegularEventNode(tue));
        Gordon.AddRegularEvent(new RegularEventNode(wed));
        //Gordon.printregularevent();
        Gordon.AddRegularEvent(new RegularEventNode(thu));
        Gordon.AddRegularEvent(new RegularEventNode(fri));
        Gordon.AddRegularEvent(new RegularEventNode(sat));
        Gordon.AddRegularEvent(new RegularEventNode(sun));
        Gordon.AddRegularEvent(new RegularEventNode(part_time4));
        //Gordon.AddRegularEvent(new RegularEventNode(part_time3));
        //System.out.println(Gordon.schedule_ptr.regular_event.begin.printthis());
       //System.out.println(Gordon.schedule_ptr.next.regular_event.begin.printthis());
        Gordon.printregularevent();
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
        Calvin.printregularevent();
        Gordon.AddEvent(new EventNode(gathering));
        Wa.AddEvent(new EventNode(meeting));
        gathering.AddGuest(new Guest(Wa));
        gathering.AddGuest(new Guest(Chi));
        gathering.AddGuest(new Guest(Shirley));
        gathering.AddGuest(new Guest(Calvin));
        gathering.printthis();
        //System.out.println(Gordon.FreeTimeSlot(a, 1).printthis());
        System.out.println(gathering.Matching(DateAndTime.Now().add(96*3)).printthis());
    }}
