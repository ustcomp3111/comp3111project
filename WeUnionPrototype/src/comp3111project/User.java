package comp3111project;

public class User // object which stores user's info
{
   public String name;
   public int user_id;
   public EventNode event_ptr = null;
   public RegularEventNode schedule_ptr = null;
   public  User next = null;
    
    public User(String Name, int id) {
        name = Name;
        user_id = id;
    }
 
   public void AddEvent(EventNode node) {
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
 
    boolean AddRegularEvent(RegularEventNode node) {            	
    	RegularEventNode ptr = schedule_ptr, ptr2 = schedule_ptr;       
    	try{
    	if (schedule_ptr == null) {
            schedule_ptr = node;
            node.next=node;      
        return true;
    	}    	
        else if (ptr.next==schedule_ptr)
        {
        	if (node.regular_event.overlap(ptr.regular_event))
        	{
        	/*	System.out.println("failed to add "+ptr.regular_event.regular_event_name);
        		node.regular_event.printthis();
        		System.out.println("overlaps");
        		ptr.regular_event.printthis();
        		System.out.println("^^^^^^");
        	*/return false;
        	}else
        	ptr.next=node;        	       
        }
        else {
            while (true) {

            	if (node.regular_event.overlap(ptr.regular_event))
            	{
              /*		System.out.println("failed to add "+ptr.regular_event.regular_event_name);      		
            		node.regular_event.printthis();
            		System.out.println("overlaps");
            		ptr.regular_event.printthis();
            		System.out.println("^^^^^");*/
            		return false;
            	}    	
            	if(ptr.next==null)
            		ptr.next=schedule_ptr;            	
            	
            	else if (node.regular_event.end.week_day < ptr.regular_event.begin.week_day
            		|| (node.regular_event.end.week_day == ptr.regular_event.begin.week_day && node.regular_event.end.time_slot < ptr.regular_event.begin.time_slot)) 
            	{
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
                    return true;
            	}            	
            	else if (ptr.next==schedule_ptr)
            	{            
            		node.next=schedule_ptr;
            		ptr.next=node;            	            	
            	return true;
            	}
            	else
            	{
                     ptr2 = ptr;
                     ptr = ptr.next;	
            	}}}
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    	return true;	
    }
 
    void printregularevent() {
        System.out.println("Regular Event of " + this.name);
        RegularEventNode ptr = schedule_ptr,starting_point = schedule_ptr;
       do {
        	
        	System.out.println("Regular event starts at: "
                    + ptr.regular_event.begin.printthis() + " event ends at: "
                    + ptr.regular_event.end.printthis());
            ptr = ptr.next;
       
       }
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
        EventNode Event_ptr = event_ptr;
        RegularEventNode Schedule_ptr = schedule_ptr;
        boolean tmp;
        DateAndTime result = time,fail = new DateAndTime(0,0,0,0);
        Events Result;
        boolean return_flag = false;
        //Move schedule ptr to a suitable position for scanning
        while(Schedule_ptr.next!=null&&Schedule_ptr.regular_event.begin.week_day<time.weekday())
        	Schedule_ptr=Schedule_ptr.next;
        while(Schedule_ptr.regular_event.end.time_slot<time.time_slot&&Schedule_ptr.regular_event.begin.week_day==time.weekday())
        	Schedule_ptr=Schedule_ptr.next;
        //store the starting point of scanning
        	RegularEventNode starting_point = Schedule_ptr;
        	tmp = new RegularEventNode(time.weekday(),time.time_slot,duration).regular_event.overlap(Schedule_ptr.regular_event);
        	if(!tmp)
        	{
        	Schedule_ptr = new RegularEventNode(time.weekday(),time.time_slot,duration);
        		Schedule_ptr.next = starting_point;
        	}
        	while (true) {
        	System.out.println("*******");
        	System.out.println("comparing ");
        	Schedule_ptr.regular_event.printthis();
        	Schedule_ptr.next.regular_event.printthis();
        	System.out.println("difference: "+Schedule_ptr.regular_event.difference(Schedule_ptr.next.regular_event));
        	System.out.println("*******\n");        	
        		//step 1: find a timeslot from regular event schedule of the user
             if (Schedule_ptr.next == null
                    || Schedule_ptr.regular_event.difference(Schedule_ptr.next.regular_event) >= duration) {
                System.out.println("Proposed Date and time:");
            	result = result.FindDateOfWeekday(Schedule_ptr.regular_event);
                Result = new Events("",0,this,result,duration,"");
                Result.printthis();
                while (true) {
                	// step 2: check if the time slot contradict to one-time events of the user                	
                	if (Event_ptr == null)
                     //"Result" has been compared with all one-time events,and there is no conflict                 
                    	return result;
                	System.out.println("#######");
                	System.out.println("comparing ");
                	Event_ptr.event.printthis();
                	Result.printthis();
                	System.out.println("#######\n");
                     if (!Event_ptr.event.overlap(Result))
                        Event_ptr = Event_ptr.next;           
                    	else {                    		                  
                    result = Event_ptr.event.end;
                    Result = new Events(this,result,duration);
                    Event_ptr = event_ptr;
                    if(Schedule_ptr.next==null||!Result.end.before(result.FindDateOfWeekday(Schedule_ptr.next.regular_event)))
                    	break;
                    }
                }
            }
            if (return_flag)
                return fail;
            else {
                if (Schedule_ptr.next == starting_point && !Schedule_ptr.equals(tmp))                
                	return_flag = true;                
                }
            Schedule_ptr = Schedule_ptr.next;
        }
       // return result;
    }
 
boolean overlap(DateAndTime time, int duration)
{
	EventNode ptr = event_ptr;
		Events event = new Events("",0,this,time,duration,"");
	RegularEventNode ptr2 = schedule_ptr; 
	RegularEvent weekday_of_event = new RegularEvent("",0,time.weekday(),time.time_slot,duration);
	
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