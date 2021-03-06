package comp3111project;

//For constructing linked lists
//Too avoid redundancy, RegularEventNode is used instead of this
public class RegularEvent {
    public String regular_event_name = "Occupied time slot";
    public int regular_event_id;
	public WeekdayAndTime begin, end;
    public int duration;
    public String venue;
    RegularEvent next;
    
    public RegularEvent(String n,int i,int Weekday, int Begin, int Duration,String v) {
        regular_event_name = n;
        regular_event_id = i;
    	begin = new WeekdayAndTime(Weekday, Begin);
        duration = Duration;
        end = this.add(duration);
        venue = v;
        next = null;
    }
    RegularEvent(int Weekday, int Begin, int Duration) {
        regular_event_name = "";
        regular_event_id = 0;
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
    boolean Within(WeekdayAndTime time)
    {
   if(this.begin.week_day==this.end.week_day)
    	{
	//   System.out.println("time.time_slot:"+time.time_slot+"\nthis.begin.time_slot:"+this.begin.time_slot+"\nthis.end.time_slot:"+this.end.time_slot);
    		if(time.time_slot>this.begin.time_slot&&time.time_slot<this.end.time_slot&&time.week_day==this.begin.week_day)
    			return true;
    		else
    			return false;
    	}
	else if(Math.abs(this.begin.week_day-this.end.week_day)==1||this.begin.week_day==7&&this.end.week_day==1)
    	{
    		if((time.week_day==this.begin.week_day&&time.time_slot>this.begin.time_slot)
    				||(time.week_day==this.end.week_day&&time.time_slot<this.end.time_slot))
    	return true;
    		else
    			return false;
    	} 		
    	else if((this.begin.week_day<this.end.week_day&&this.begin.week_day<time.week_day&&time.week_day<this.end.week_day)
    			||(this.begin.week_day>this.end.week_day&&this.begin.week_day<time.week_day||time.week_day<this.end.week_day))
    		return true;
    	else
    		return false;
    }
    
    void printthis ()
    {
    System.out.println("Regular event name: "+regular_event_name+"\nbegin: "+begin.printthis()+"\nend: "+end.printthis());	
    }
    boolean overlap(RegularEvent node)
    {
    	 if((this.Within(node.begin)||this.Within(node.end))||(node.Within(begin)||node.Within(end))||(this.begin.equals(node.begin)&&this.end.equals(node.end)))
    	return true;
    	else
    		return false;
    	
    }
    int difference(RegularEvent node) 
    {
   return end.difference(node.begin);
    }
    	  }
