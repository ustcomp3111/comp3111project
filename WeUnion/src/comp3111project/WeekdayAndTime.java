package comp3111project;

public class WeekdayAndTime {
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
    int day;
    if(time.week_day>=week_day)
    	day = time.week_day-week_day;
    else
    	day = time.week_day+7-week_day;
    return day*96-time_slot+ time.time_slot;
    }
}
