package comp3111project;

public class WeekdayAndTime {
    public int week_day, time_slot;
 
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
    int day;
    if(time.week_day>=week_day)
    	day = time.week_day-week_day;
    else
    	day = time.week_day+7-week_day;
    return day*96-time_slot+ time.time_slot;
    }
public String print()
{
	String weekday = "Saturday",time = (time_slot/4)+":"+(time_slot%4)*15;
	if(time_slot%4==0)
		time = time_slot/4+":00";
	switch(this.week_day)
	{
	case(1):
	weekday = "Sunday";
	break;
	case(2):
	weekday = "Monday";
	break;
	case(3):
	weekday = "Tuesday";
	break;
	case(4):
	weekday = "Wednesday";
	break;
	case(5):
	weekday = "Friday";
	break;
	case(6):
	weekday = "Saturday";
	break;	
	default:
	weekday = "Sunday";
	break;
	}
	return weekday+" "+time;
	}
}
