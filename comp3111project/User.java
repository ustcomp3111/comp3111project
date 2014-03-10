package comp3111project;

class User // object which stores user's info
{
    String name;
    int user_id;
    EventNode event_ptr;
    RegularEventNode schedule_ptr;
 
    User(String Name, int id, EventNode eventptr, RegularEventNode scheduleptr) {
        name = Name;
        user_id = id;
        event_ptr = eventptr;
        schedule_ptr = scheduleptr;
    }
 
    void AddEvent(EventNode node) {
        EventNode ptr = event_ptr;
        if (event_ptr == null)
            event_ptr = node;
        else if (event_ptr.next == null)
            event_ptr.next = node;
        else {
            while (ptr.next != null)
                ptr = ptr.next;
            ptr.next = node;
        }
    }
 
    void AddRegularEvent(RegularEventNode node) {        
    	
    	RegularEventNode ptr = schedule_ptr, ptr2 = schedule_ptr;
        //boolean end = false;
    	if (schedule_ptr == null) {
            schedule_ptr = node;
            node.next=node;
            //System.out.println("schedule ptr is null");
        }
        else if (ptr.next==schedule_ptr)
        //	node.next=schedule_ptr;
        	ptr.next=node;        	       
        else {
            while (true) {
            	/*if (end)
            		break;
            	if (ptr.next==schedule_ptr)
                	end = true;
            	*/if(ptr.next==null)
            		ptr.next=schedule_ptr;            	
            	if (node.regular_event.overlap(ptr.regular_event))
            	{
            //		System.out.println("failed to add");
            	break;
            	}
            	else if (node.regular_event.end.week_day < ptr.regular_event.begin.week_day
            		|| (node.regular_event.end.week_day == ptr.regular_event.begin.week_day && node.regular_event.end.time_slot < ptr.regular_event.begin.time_slot)) {
              //       System.out.println("node is put at the front");
                    node.next=ptr;
                     if (ptr == schedule_ptr) 
                    	schedule_ptr = node;
            	else
            		ptr2.next = node;    
                    break;
            	}
            	
            	else if (ptr.next==schedule_ptr)
            	{
            		ptr.next=node;            	
            	node.next=schedule_ptr;
            	break;
            	}
            	else
            	{
            	//     System.out.println("ptr moves on");
                     ptr2 = ptr;
                     ptr = ptr.next;	
            	}/*else if (ptr2.next!=schedule_ptr||ptr.next != schedule_ptr) {
                     System.out.println("ptr moves on");
                    ptr2 = ptr;
                    ptr = ptr.next;
                } 
                else {
                     System.out.println("node is put at the back");
                    node.next=schedule_ptr;
                     ptr.next = node;
                    break;
                }*/
            }
        }
    		//System.out.println("************");
    }
 
    void printregularevent() {
        System.out.println("Regular Event of " + this.name);
        RegularEventNode ptr = schedule_ptr;
        boolean end = false;
        while (true) {
        	if (ptr.next==schedule_ptr||ptr.next == null)
        		end = true;
        	System.out.println("Regular event starts at: "
                    + ptr.regular_event.begin.printthis() + " event ends at: "
                    + ptr.regular_event.end.printthis());
            ptr = ptr.next;
            if (end)
                break;
        }
        System.out.println("***The end***");
    }
 
    void printevent() {
        System.out.println("Event of " + this.name);
        EventNode ptr = event_ptr;
        while (ptr != null) {
            System.out.println("Event id: " + ptr.event.event_id
                    + " event starts at: " + ptr.event.begin.printthis()
                    + " event ends at: " + ptr.event.end.printthis());
            ptr = ptr.next;
        }
        System.out.println("***The end***");
    }
 
    DateAndTime FreeTimeSlot(DateAndTime time, int duration) // find a free time
                                                                // slot which is
                                                                // closest to
                                                                // 'time'
    {
        EventNode Event_ptr = event_ptr;
        RegularEventNode Schedule_ptr = schedule_ptr;
        DateAndTime result = time;//DateAndTime.Now();
        Event Result;
        boolean return_flag = false;
        while(Schedule_ptr.regular_event.begin.week_day<time.weekday())
        	Schedule_ptr=Schedule_ptr.next;
        if(Schedule_ptr.regular_event.end.time_slot<time.time_slot&&Schedule_ptr.regular_event.begin.week_day==time.weekday())
        	Schedule_ptr=Schedule_ptr.next;
        	RegularEventNode starting_point = Schedule_ptr;
        while (true) {
            if (Schedule_ptr == null)
                break;
            else if (Schedule_ptr.next == null
                    || Schedule_ptr.regular_event.difference(Schedule_ptr.next.regular_event) >= duration) {
                result = result.FindDateOfWeekday(Schedule_ptr.regular_event);
                Result = new Event("",0,this,result,duration);
               System.out.println("suitable time: "+result.printthis());
                while (true) {
                	
                    if (Event_ptr == null)
                        return result;
                    System.out.println("comparing: "+Result.begin.printthis()+" \nend: "+Result.end.printthis()
                    +"\nand "+Event_ptr.event.begin.printthis()+"\nend"+Event_ptr.event.end.printthis());
                    if (!Event_ptr.event.overlap(Result))
                        Event_ptr = Event_ptr.next;
                    else {
                     
                        result = Event_ptr.event.end;
                    	Event_ptr = event_ptr;
                        break;
                    }
                }
            }
 
            if (return_flag)
                break;
            else {
                if (Schedule_ptr.next == starting_point)
                    return_flag = true;
                Schedule_ptr = Schedule_ptr.next;
            }
 
        }
        return result;
    }
 
    boolean IsFree(DateAndTime date_and_time) {
        return true;
    }
boolean overlap(DateAndTime time, int duration)
{
	EventNode ptr = event_ptr;
		Event event = new Event("",0,this,time,duration);
	RegularEventNode ptr2 = schedule_ptr; 
	RegularEvent weekday_of_event = new RegularEvent(time.weekday(),time.time_slot,duration);
	
	while(ptr2!=null)
	{
	if(ptr2.regular_event.overlap(weekday_of_event))	
	return true;
	else
		ptr2 = ptr2.next;
	}
	while(ptr!=null)
	{
	if(ptr.event.overlap(event))
		return true;
	else
		ptr = ptr.next;
	}
return false;
}
};
 
class Guest // node of linked list of guest list
{
    Guest next = null;
    boolean attend = false;
    User user; 
    Guest(String Name, int id, EventNode eventptr, RegularEventNode scheduleptr) {
        user=new User(Name, id, eventptr, scheduleptr);
    }
};