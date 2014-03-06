package comp3111project;

import java.util.Calendar;
import java.util.GregorianCalendar;

//Example: 1/1/2014 12:45pm = DateAndTime(2014,Calendar.January,1,51)
class DateAndTime {
	GregorianCalendar Date;
	static GregorianCalendar now = new GregorianCalendar();
	int time_slot;
	// int weekday;
	static DateAndTime Now = new DateAndTime(now.get(Calendar.YEAR),
			now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH),
			now.get(Calendar.HOUR) * 4 + (now.get(Calendar.MINUTE) + 1) / 15);

	DateAndTime(int year, int month, int day_of_month, int interval) {
		Date = new GregorianCalendar(year, month, day_of_month);
		// Date.set(year, month, day_of_month,0,0,0);
		time_slot = interval;
		// weekday=Date.get(Calendar.DAY_OF_WEEK);
	}

	int weekday() {
		return Date.get(Calendar.DAY_OF_WEEK);
	}

	String printthis() {

		return " Year: " + Date.get(Calendar.YEAR) + " Month: "
				+ (Date.get(Calendar.MONTH) + 1) + " Day: "
				+ Date.get(Calendar.DAY_OF_MONTH) + " Weekday: "
				+ Date.get(Calendar.DAY_OF_WEEK) + " time: " + time_slot;
	}

	boolean before(DateAndTime date_and_time) {
		if (Date.after(date_and_time.Date))
			return false;
		else if (Date.before(date_and_time.Date))

			return true;
		else if (time_slot < date_and_time.time_slot
				&& Date.equals(date_and_time.Date))

			return true;
		else
			return false;
	}

	boolean after(DateAndTime date_and_time) {
		if (Date.after(date_and_time.Date))
			return true;
		else if (Date.before(date_and_time.Date))
			return false;

		else if (time_slot > date_and_time.time_slot
				&& Date.equals(date_and_time.Date))
			return true;
		else

			return false;
	}

	int NumOfDay(int month, int year) {
		if (month == GregorianCalendar.JANUARY
				|| month == GregorianCalendar.MARCH
				|| month == GregorianCalendar.MAY
				|| month == GregorianCalendar.JULY
				|| month == GregorianCalendar.AUGUST
				|| month == GregorianCalendar.OCTOBER
				|| month == GregorianCalendar.DECEMBER)
			return 31;
		else if (month == GregorianCalendar.APRIL
				|| month == GregorianCalendar.JUNE
				|| month == GregorianCalendar.SEPTEMBER
				|| month == GregorianCalendar.NOVEMBER)
			return 30;
		else if (year % 400 == 0 || (!(year % 100 == 0) && year % 4 == 0))
			return 29;
		else
			return 28;
	}

	int NextMonth(int month) {
		switch (month) {
		case (Calendar.JANUARY):
			return Calendar.FEBRUARY;
		case (Calendar.FEBRUARY):
			return Calendar.MARCH;
		case (Calendar.MARCH):
			return Calendar.APRIL;
		case (Calendar.APRIL):
			return Calendar.MAY;
		case (Calendar.MAY):
			return Calendar.JUNE;
		case (Calendar.JUNE):
			return Calendar.JULY;
		case (Calendar.JULY):
			return Calendar.AUGUST;
		case (Calendar.AUGUST):
			return Calendar.SEPTEMBER;
		case (Calendar.SEPTEMBER):
			return Calendar.OCTOBER;
		case (Calendar.OCTOBER):
			return Calendar.NOVEMBER;
		case (Calendar.NOVEMBER):
			return Calendar.DECEMBER;
		case (Calendar.DECEMBER):
			return Calendar.JANUARY;
		default:
			return -1;
		}
	}

	DateAndTime add(int time) {
		DateAndTime result = new DateAndTime(this.Date.get(Calendar.YEAR),
				this.Date.get(Calendar.MONTH),
				this.Date.get(Calendar.DAY_OF_MONTH), this.time_slot);
		int day = time / 96, year = result.Date.get(Calendar.YEAR);
		time %= 96;
		result.time_slot += time;
		day += result.time_slot / 96;
		result.time_slot %= 96;
		day += result.Date.get(Calendar.DAY_OF_MONTH);
		// System.out.println("day(before loop): "+day);
		while (true) {
			if (day > result.NumOfDay(result.Date.get(Calendar.MONTH),
					result.Date.get(Calendar.YEAR))) {
				day -= result.NumOfDay(result.Date.get(Calendar.MONTH),
						result.Date.get(Calendar.YEAR));
				// System.out.println("day(after loop): "+day);
				result.Date.set(year, result.NextMonth(Calendar.MONTH),
						result.Date.get(Calendar.DAY_OF_MONTH));
				if (result.Date.get(Calendar.MONTH) == Calendar.JANUARY)
					year++;
			} else
				break;
		}
		result.Date.set(year, result.Date.get(Calendar.MONTH), day);
		return result;
	}

	DateAndTime FindDateOfWeekday(RegularEventNode Event) {
		int count = Event.begin.week_day - this.weekday();
		if (count < 0)
			count += 7;
		DateAndTime result = this.add(count * 96 - this.time_slot
				+ Event.end.time_slot);
		return result;
	}

	DateAndTime FindDateOfWeekday(int week_day) {
		int count = week_day - this.weekday();
		if (count < 0)
			count += 7;
		DateAndTime result = this.add(count * 96 - this.time_slot);
		return result;
	}
}

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
		node.next = schedule_ptr;
		if (schedule_ptr == null) {
			schedule_ptr = node;
			// System.out.println("schedule ptr is null");
		} else {
			while (true) {
				if (node.end.week_day < ptr.begin.week_day
						|| (node.end.week_day == ptr.begin.week_day && node.end.time_slot < ptr.begin.time_slot)) {
					// System.out.println("node is put at the front");
					node.next = ptr;
					if (ptr == schedule_ptr)
						schedule_ptr = node;
					else
						ptr2.next = node;
					break;
				} else if (ptr.next != schedule_ptr && ptr.next != null) {
					// System.out.println("ptr moves on");
					ptr2 = ptr;
					ptr = ptr.next;
				} else {
					// System.out.println("node is put at the back");
					ptr.next = node;
					break;
				}
			}
			// System.out.println("************");
		}
	}

	void printregularevent() {
		System.out.println("Regular Event of " + this.name);
		RegularEventNode ptr = schedule_ptr;
		boolean end = false;
		while (ptr != null) {
			System.out.println("Regular event starts at: "
					+ ptr.begin.printthis() + " event ends at: "
					+ ptr.end.printthis());
			ptr = ptr.next;

			if (end)
				break;
			if (ptr.next == schedule_ptr || ptr.next == null)
				end = true;

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
				if (Schedule_ptr.next == schedule_ptr)
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

	int difference(RegularEventNode node) {
		int day_difference = (node.begin.week_day - this.end.week_day), interval = 0;
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
}

class EventNode {
	User eventholder;
	int event_id, duration;
	DateAndTime begin;
	DateAndTime end;
	EventNode next;

	EventNode(User a, int b, DateAndTime d, int e) {
		eventholder = a;
		event_id = b;
		begin = d;
		duration = e;
		end = d.add(e);
		next = null;
	}

	boolean overlap(DateAndTime date_and_time) {
		if (end.after(date_and_time) && begin.before(date_and_time))
			return true;
		else
			return false;
	}

	boolean overlap(EventNode event) {
		if (end.before(event.begin) || begin.after(event.end))
			return false;
		else
			return true;
	}
}

class Event extends EventNode // store extra information of an event
{
	String event_name;
	Guest guestlist_ptr = null;

	Event(String a, int b, User c, DateAndTime d, int e) {
		super(c, b, d, e);
		event_name = a;
	}
};

public class ScheduleMatching {

	/**
	 * @param args
	 */
	/*
	 * DateAndTime matching(Event event,int duration) {
	 * 
	 * 
	 * 
	 * };
	 */
	public static void main(String[] args) {

		User Gordon = new User("Gordon", 01, null, null);
		DateAndTime a = new DateAndTime(2014, Calendar.MARCH, 9, 70), b = new DateAndTime(
				2014, Calendar.MARCH, 9, 72);

		EventNode event1 = new EventNode(Gordon, 01, b, 12), event2 = new EventNode(
				Gordon, 02, a, 12);
		RegularEventNode event3 = new RegularEventNode(Calendar.MONDAY, 0, 72);
		RegularEventNode event4 = new RegularEventNode(Calendar.TUESDAY, 0, 72);
		RegularEventNode event5 = new RegularEventNode(Calendar.WEDNESDAY, 0,
				72);
		RegularEventNode event6 = new RegularEventNode(Calendar.THURSDAY, 0, 72);
		RegularEventNode event7 = new RegularEventNode(Calendar.FRIDAY, 0, 72);
		RegularEventNode event8 = new RegularEventNode(Calendar.SATURDAY, 0, 72);
		RegularEventNode event9 = new RegularEventNode(Calendar.SUNDAY, 0, 72);

		 Gordon.AddEvent(event1);
		Gordon.AddEvent(event2);
		 System.out.println("add event done");
		 Gordon.AddRegularEvent(event8);
		 Gordon.AddRegularEvent(event7);
		 Gordon.AddRegularEvent(event9);
		Gordon.AddRegularEvent(event7);
		Gordon.AddRegularEvent(event4);
		Gordon.AddRegularEvent(event5);
		Gordon.AddRegularEvent(event3);
		Gordon.AddRegularEvent(event9);
		Gordon.AddRegularEvent(event6);
		Gordon.AddRegularEvent(event8);
		 System.out.println("add regular event done");
		Gordon.printevent();
		Gordon.printregularevent();
		DateAndTime test = DateAndTime.Now;
		 System.out.println("object created");

		test = Gordon.FreeTimeSlot(test, 4);
		 System.out.println(test.printthis());
		/*
		 * DateAndTime e = new DateAndTime(2014,1,1,0),f = new
		 * DateAndTime(2014,1,1,0); System.out.println(e.Date.toString());
		 * System.out.println(f.Date.toString());
		 * 
		 * if(e.Date.after(f.Date)) System.out.println("true");
		 */}

}
