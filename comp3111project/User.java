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
        boolean end = false;
    	if (schedule_ptr == null) {
            schedule_ptr = node;
            node.next=schedule_ptr;
            System.out.println("schedule ptr is null");
        } 
        else if (ptr.next==schedule_ptr)
        	ptr.next=node;
        	else {
            while (true) {
            	if (end)
            		break;
            	if (ptr.next==schedule_ptr)
                	end = true;
            	if(ptr.next==null)
            		ptr.next=schedule_ptr;            	
            	if (node.overlap(ptr))
            	{
            		System.out.println("failed to add");
            	break;
            	}
            	else if (node.end.week_day < ptr.begin.week_day
            		|| (node.end.week_day == ptr.begin.week_day && node.end.time_slot < ptr.begin.time_slot)) {
                     System.out.println("node is put at the front");
                    node.next = ptr;
                    if (ptr == schedule_ptr) 
                    	schedule_ptr = node;
            	else
            		ptr2.next = node;    
                    break;
            	}
            	else if (ptr.next != schedule_ptr) {
                     System.out.println("ptr moves on");
                    ptr2 = ptr;
                    ptr = ptr.next;
                } 
                else {
                     System.out.println("node is put at the back");
                    ptr.next = node;
                    break;
                }
            }
        }
        System.out.println("************");
    }
 
    void printregularevent() {
        System.out.println("Regular Event of " + this.name);
        RegularEventNode ptr = schedule_ptr;
        boolean end = false;
        while (ptr != null) {
        	if (ptr.next==schedule_ptr||ptr.next == null)
        	{
        		end = true;
        	}System.out.println("Regular event starts at: "
                    + ptr.begin.printthis() + " event ends at: "
                    + ptr.end.printthis());
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
            System.out.println("Event id: " + ptr.event_id
                    + " event starts at: " + ptr.begin.printthis()
                    + " event ends at: " + ptr.end.printthis());
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
        DateAndTime result = DateAndTime.Now;
        boolean return_flag = false;
        while(Schedule_ptr.begin.week_day<time.weekday())
        	Schedule_ptr=Schedule_ptr.next;
        RegularEventNode starting_point = Schedule_ptr;
        while (true) {
            if (Schedule_ptr == null)
                break;
            else if (Schedule_ptr.next == null
                    || Schedule_ptr.difference(Schedule_ptr.next) >= duration) {
                result = result.FindDateOfWeekday(Schedule_ptr);
                while (true) {
                    if (Event_ptr == null)
                        return result;
                    else if (!Event_ptr.overlap(result))
                        Event_ptr = Event_ptr.next;
                    else {
                        // result = result.FindDateOfWeekday(Schedule_ptr);
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
};
 
class Guest extends User// node of linked list of guest list
{
    Guest next = null;
 
    Guest(String Name, int id, EventNode eventptr, RegularEventNode scheduleptr) {
        super(Name, id, eventptr, scheduleptr);
    }
};