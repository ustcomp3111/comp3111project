package comp3111project;

import java.util.Calendar;


import junit.framework.TestCase;

public class RegularEventTest extends TestCase {

	static WeekdayAndTime sunday = new WeekdayAndTime(Calendar.SUNDAY,92),
			monday = new WeekdayAndTime(Calendar.MONDAY,48),sunday2 = new WeekdayAndTime(Calendar.SUNDAY,93),sunday3 = new WeekdayAndTime(Calendar.SUNDAY,1);
		static RegularEvent regular_event1 = new RegularEvent(Calendar.SUNDAY,92,8),regular_event2 = new RegularEvent(Calendar.SUNDAY,72,190),
				regular_event3 = new RegularEvent("",0,Calendar.MONDAY,8,1);
		
		public void testAdd() {
		
			assertEquals(Calendar.MONDAY,regular_event1.add(52).week_day);
			assertEquals(48,regular_event1.add(52).time_slot);

			
		}

		public void testWithin() {
			assertTrue(regular_event1.Within(sunday2));
		}
		
		public void testWithin2() {
			assertFalse(regular_event1.Within(monday));
		}
		
		public void testWithin3() {
			assertTrue(regular_event2.Within(monday));
		}
		
		public void testWithin4() {
			assertFalse(regular_event1.Within(sunday3));
		}
		
		public void testPrintthis() {
		regular_event1.printthis();
		assertTrue(true);
		}

		
		public void testOverlap()
		{
			assertTrue(regular_event1.overlap(regular_event2));
		}

		
		public void testDifference() {
		assertEquals(4,regular_event1.difference(regular_event3));
		}
}
