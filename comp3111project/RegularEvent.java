package comp3111project;
class RegularEvent extends RegularEventNode
{
RegularEvent(int Weekday, int Begin, int Duration) 
{
	super(Weekday,Begin,Duration) ;
	}
}
class RegularEventNode {
    WeekdayAndTime begin, end;
    int duration;
    RegularEventNode next;
 
    RegularEventNode(int Weekday, int Begin, int Duration) {
        begin = new WeekdayAndTime(Weekday, Begin);
        duration = Duration;
        end = this.add(duration);
        next = null;
    }
 
    WeekdayAndTime add(int time) {
        WeekdayAndTime result;// = new WeekdayAndTime(begin.week_day,duration);
        int day = 0;
        time += begin.time_slot;
        if (time > 95) {
            day += time / 96;
            time %= 96;
        }
 
        // System.out.println("day: "+day+" time: "+time);
        result = new WeekdayAndTime(begin.week_day + day, time);
        return result;
    }
 
    boolean overlap(RegularEventNode node)
    {
    	if (Math.max(begin.difference(node.end), node.begin.difference(end))-duration-node.duration<0)
    	return true;
    	else
    		return false;
    	
    }
    	  int difference(RegularEventNode node) {
    		
    		    if (begin.difference(node.end)<node.begin.difference(end))
    		       //this event is in front of node
    		    	
    		    	return node.begin.difference(end)-duration-node.duration;
    		    else
    		    
    		    
    		    	return begin.difference(node.end)-duration-node.duration;
    		    }
    	  
    	/* int day_difference = (node.begin.week_day - this.end.week_day), interval = 0;
        day_difference = Math.abs(day_difference);
        if (Math.abs(day_difference) > 3)
            day_difference = 7 - day_difference;
        // System.out.println("day difference: "+day_difference);
        if (node.begin.week_day - this.end.week_day < 0) {
            // System.out.println("begin.time_slot: "+begin.time_slot+" node.end.time_slot: "+node.end.time_slot);
            interval = day_difference * 96 + begin.time_slot
                    - node.end.time_slot;
        } else {
            // System.out.println("node.begin.time_slot: "+node.begin.time_slot+" end.time_slot: "+end.time_slot);
            interval = day_difference * 96 + node.begin.time_slot
                    - end.time_slot;
        }
        return interval;
    }*/
 
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
    int difference(WeekdayAndTime time)
    {
    int day = Math.abs(time.week_day-week_day);
    if(day>3)
        day = 7-day;
    if(time.week_day-week_day<0)
    return day*96-time.time_slot+time_slot;
    else
    	return day*96-time_slot+time.time_slot;
    }

}