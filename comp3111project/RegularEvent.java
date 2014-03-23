package comp3111project;

//For constructing linked lists
class RegularEventNode
{
	RegularEventNode next=null;
	RegularEvent regular_event;  //Points to the regular event which this objects refers to

RegularEventNode(RegularEvent event)
{
	regular_event = event;
	}
	RegularEventNode(int Weekday, int Begin, int Duration) 
{
	 regular_event = new RegularEvent(Weekday,Begin,Duration) ;
	}
}

//Too avoid redundancy, RegularEventNode is used instead of this
class RegularEvent {
    WeekdayAndTime begin, end;
    int duration;
    RegularEventNode next;
    String name = "Unavailable time slot";
    RegularEvent(int Weekday, int Begin, int Duration) {
        begin = new WeekdayAndTime(Weekday, Begin);
        duration = Duration;
        end = this.add(duration);
        next = null;
    }
 
    //e.g time = 95 (= 23 hours and 45 minutes )
    //[Monday 13:00] + 95 = Tuesday 12:45
   // time should be a +ve integer 
    WeekdayAndTime add(int time) {
        WeekdayAndTime result;// = new WeekdayAndTime(begin.week_day,duration);
        int day = 0;
        time += begin.time_slot;
        if (time > 95) {
            day += time / 96;
            time %= 96;
        }
        // System.out.println("day: "+day+" time: "+time);
        result = new WeekdayAndTime((begin.week_day-1 + day)%7+1, time);
        return result;
    }
    void printthis ()
    {
    System.out.println("Regular event name: "+name+" begin: "+begin.printthis()+"\nend: "+end.printthis());	
    }
    boolean overlap(RegularEvent node)
    {
    	if (Math.max(begin.difference(node.end), node.begin.difference(end))-duration-node.duration<0)
    	return true;
    	else
    		return false;
    	
    }
    	  int difference(RegularEvent node) {
    		 //case 1 : this event is after event "node"
    		    if (begin.difference(node.end)<node.begin.difference(end))    		    	
    		    	return node.begin.difference(end)-duration-node.duration;
    		    // case 2: otherwise
    		    else    		        		    
    		    	return begin.difference(node.end)-duration-node.duration;
    		    }
    	  }
 
class WeekdayAndTime {
    int week_day, time_slot;
 
    WeekdayAndTime(int weekday, int timeslot) {
        week_day = weekday;
        time_slot = timeslot;
    }
 
    String printthis() {
        return "weekday:" + week_day + " time slot: " + time_slot;
    }
    
    //Find the difference of two WeekdayAndTime in terms of interval, it should always return +ve integer
    int difference(WeekdayAndTime time)
    {
    int day = Math.abs(time.week_day-week_day);
    if(day>3)
        day = 7-day;
    if(time.week_day<week_day)
    return day*96-time.time_slot+time_slot;
    else
    	return day*96-time_slot+time.time_slot;
    }

}