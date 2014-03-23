package comp3111project;

class User // object which stores user's info
{
    String name;
    int user_id;
    EventNode event_ptr = null;
    RegularEventNode schedule_ptr = null;
 
    User(String Name, int id) {
        name = Name;
        user_id = id;
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
       
    	if (schedule_ptr == null) {
            schedule_ptr = node;
            node.next=node;
      
        }
        else if (ptr.next==schedule_ptr)
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
            		//System.out.println("failed to add");
            	break;
            	}
            	else if (node.regular_event.end.week_day < ptr.regular_event.begin.week_day
            		|| (node.regular_event.end.week_day == ptr.regular_event.begin.week_day && node.regular_event.end.time_slot < ptr.regular_event.begin.time_slot)) {
                  //   System.out.println("node is put at the front");
                    node.next=ptr;
                     if (ptr == schedule_ptr) 
                     {
                    	 do
                    		 ptr=ptr.next;
                    	while(ptr.next!=schedule_ptr);
                    	 ptr.next = node;
                    	 schedule_ptr = node;
                     
                     }
                     else
            		ptr2.next = node;    
                    break;
            	}
            	
            	else if (ptr.next==schedule_ptr)
            	{
            
            		node.next=schedule_ptr;
            		//System.out.println(node.next.regular_event.begin.printthis());
            		ptr.next=node;            	
            	
            	break;
            	}
            	else
            	{
            	    // System.out.println("ptr moves on");
                     ptr2 = ptr;
                     ptr = ptr.next;	
            	}
            }
        }
    		//System.out.println("************");
    }
 
    void printregularevent() {
        System.out.println("Regular Event of " + this.name);
        RegularEventNode ptr = schedule_ptr,starting_point = schedule_ptr;
       //int count = 16;
       do {
        	
        	System.out.println("Regular event starts at: "
                    + ptr.regular_event.begin.printthis() + " event ends at: "
                    + ptr.regular_event.end.printthis());
            ptr = ptr.next;
       
       }
      //while(count!=0);
       while ((!ptr.equals(starting_point)&&ptr.next != null));
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
 // find a free time slot which is closest to 'time' , and duration = "duration"
    DateAndTime FreeTimeSlot(DateAndTime time, int duration) 
                                                               
    {
    	  System.out.println("find free time slot");
        EventNode Event_ptr = event_ptr;
        RegularEventNode Schedule_ptr = schedule_ptr;
        DateAndTime result = time;
        Event Result;
        boolean return_flag = false;
        //Move schedule ptr to a suitable position for scanning
        while(Schedule_ptr.regular_event.begin.week_day<time.weekday())
        	Schedule_ptr=Schedule_ptr.next;
        while(Schedule_ptr.regular_event.end.time_slot<time.time_slot&&Schedule_ptr.regular_event.begin.week_day==time.weekday())
        	Schedule_ptr=Schedule_ptr.next;
        //store the starting point of scanning
        	RegularEventNode starting_point = Schedule_ptr;
        while (true) {
        	//step 1: find a timeslot from regular event schedule of the user
            if (Schedule_ptr == null)
                break;
            else if (Schedule_ptr.next == null
                    || Schedule_ptr.regular_event.difference(Schedule_ptr.next.regular_event) >= duration) {
                result = result.FindDateOfWeekday(Schedule_ptr.regular_event);
                Result = new Event("",0,this,result,duration);
             //  System.out.println("suitable time slot: ");
              // Schedule_ptr.regular_event.printthis();
               // System.out.println("suitable time: "+result.printthis());
                while (true) {
                	/**
                	 * This part is buggy, it must be fixed later
                	 */
                	// step 2: check if the time slot contradict to one-time events of the user
                    if (Event_ptr == null)
                        //"result" has been compared with all one-time events,and there is no conflict
                    	return result;
                   // System.out.println("comparing: "+Result.begin.printthis()+" \nend: "+Result.end.printthis()
                   // +"\nand "+Event_ptr.event.begin.printthis()+"\nend"+Event_ptr.event.end.printthis());
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
 
boolean overlap(DateAndTime time, int duration)
{
	EventNode ptr = event_ptr;
		Event event = new Event("",0,this,time,duration);
	RegularEventNode ptr2 = schedule_ptr; 
	RegularEvent weekday_of_event = new RegularEvent(time.weekday(),time.time_slot,duration);
	
	do
	{
		
	if(ptr2.regular_event.overlap(weekday_of_event))	
	return true;
	else
		ptr2 = ptr2.next;
	}
	while(ptr2!=schedule_ptr);
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
 
// For constructing linked list
class Guest 
{
    Guest next = null;
    boolean attend = false;
    User user; 
    Guest(User u)
    {
    	user=u;
    }
    Guest(String Name, int id) {
        user=new User(Name, id);
    }
};