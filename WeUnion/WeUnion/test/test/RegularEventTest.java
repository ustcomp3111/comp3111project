package test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import comp3111project.DateAndTime;
import comp3111project.EventNode;
import comp3111project.Events;
import comp3111project.Guest;
import comp3111project.RegularEvent;
import comp3111project.RegularEventNode;
import comp3111project.User;
import comp3111project.WeekdayAndTime;

public class RegularEventTest {


	static WeekdayAndTime sunday = new WeekdayAndTime(Calendar.SUNDAY,92),
			monday = new WeekdayAndTime(Calendar.MONDAY,48),sunday2 = new WeekdayAndTime(Calendar.SUNDAY,93),sunday3 = new WeekdayAndTime(Calendar.SUNDAY,1);
		static RegularEvent regular_event1 = new RegularEvent(Calendar.SUNDAY,92,8),regular_event2 = new RegularEvent(Calendar.SUNDAY,72,190),
				regular_event3 = new RegularEvent("",0,Calendar.MONDAY,8,1);
@Test		
		public void testAdd() {
		
			assertEquals(Calendar.MONDAY,regular_event1.add(52).week_day);
			assertEquals(48,regular_event1.add(52).time_slot);

			
		}
@Test
		public void testWithin() {
			assertTrue(regular_event1.Within(sunday2));
		}
@Test	
		public void testWithin2() {
			assertFalse(regular_event1.Within(monday));
		}
@Test	
		public void testWithin3() {
			assertTrue(regular_event2.Within(monday));
		}
@Test	
		public void testWithin4() {
			assertFalse(regular_event1.Within(sunday3));
		}
@Test	
		public void testPrintthis() {
		regular_event1.printthis();
		assertTrue(true);
		}

@Test	
		public void testOverlap()
		{
			assertTrue(regular_event1.overlap(regular_event2));
		}

@Test	
		public void testDifference() {
		assertEquals(4,regular_event1.difference(regular_event3));
		}

}
