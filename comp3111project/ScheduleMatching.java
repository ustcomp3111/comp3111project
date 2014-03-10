package comp3111project;
 import java.util.Calendar;
import java.util.GregorianCalendar;

public class ScheduleMatching {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
 
      User Gordon = new User("Gordon", 01, null, null);
        DateAndTime a = new DateAndTime(2014, Calendar.MARCH, 8, 72),
        		b = new DateAndTime(2014, Calendar.MARCH, 9, 73);
 
        EventNode event1 = new EventNode("event1",01, Gordon, a, 12),
        		event2 = new EventNode("event2",02, Gordon, b, 12);
        RegularEventNode mon = new RegularEventNode(Calendar.MONDAY, 0, 72);
        RegularEventNode tue = new RegularEventNode(Calendar.TUESDAY, 0, 72);
        RegularEventNode wed = new RegularEventNode(Calendar.WEDNESDAY, 0,
                72);
        RegularEventNode thu = new RegularEventNode(Calendar.THURSDAY, 0, 72);
        RegularEventNode fri = new RegularEventNode(Calendar.FRIDAY, 0, 72);
        RegularEventNode sat = new RegularEventNode(Calendar.SATURDAY, 0, 72);
        RegularEventNode sun = new RegularEventNode(Calendar.SUNDAY, 0, 72);
 
        Gordon.AddEvent(event1);
       	Gordon.AddEvent(event2);
        System.out.println("add event done");
        System.out.println("adding mon");
        Gordon.AddRegularEvent(mon);
        System.out.println("adding wed");
        Gordon.AddRegularEvent(wed);
        System.out.println("adding sun");
        Gordon.AddRegularEvent(sun);
        System.out.println("adding thu");
        Gordon.AddRegularEvent(thu);
       	System.out.println("adding tue");
        Gordon.AddRegularEvent(tue);
       	System.out.println("adding thu");
        Gordon.AddRegularEvent(thu);
        System.out.println("adding sat");
        Gordon.AddRegularEvent(sat);
        System.out.println("adding fri");
        Gordon.AddRegularEvent(fri);
        System.out.println("add regular event done");
        Gordon.printevent();
        Gordon.printregularevent();
      
        DateAndTime test = DateAndTime.Now();
       Calendar test2;
     test2  = GregorianCalendar.getInstance();
       System.out.println("Now: "+test.printthis());      
        test = Gordon.FreeTimeSlot(test, 12);
        System.out.println(test.printthis());
        
        
    	}}
