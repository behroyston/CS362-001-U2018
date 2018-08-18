/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;


public class ApptTest {

    /**
     * Test Case where everything is set nicely without recurrence
     * @throws Throwable
     */
    @Test(timeout = 4000)
    public void test00()  throws Throwable  {
        Appt appt0 = new Appt(15, 30, 9, 2, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt0.setValid();
        assertEquals(2, appt0.getRecurBy());
        assertFalse(appt0.isRecurring());
        assertEquals(0, appt0.getRecurIncrement());
        assertEquals("Birthday Party",appt0.getTitle());
        assertEquals(15,appt0.getStartHour());
        assertEquals(30,appt0.getStartMinute());
        assertEquals(9,appt0.getStartDay());
        assertEquals(2,appt0.getStartMonth());
        assertEquals(2018,appt0.getStartYear());
        assertEquals("This is my birthday party",appt0.getDescription());
        assertEquals("xyz@gmail.com",appt0.getEmailAddress());
        assertTrue(appt0.hasTimeSet());
        assertEquals(null,appt0.getXmlElement());
        assertTrue(appt0.isOn(9,2,2018));
        assertFalse(appt0.isOn(2,11,2017));

        assertTrue(appt0.getValid());
        assertNotNull(appt0.toString());

    }

    /**
     * Test Case where everything is set nicely with recurrence
     * @throws Throwable
     */
    @Test(timeout = 4000)
    public void test01()  throws Throwable  {
        Appt appt1 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        int[] recurDaysArr={2,3,4};
        appt1.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
        assertTrue(appt1.isRecurring());
        assertEquals(2,appt1.getRecurIncrement());
        assertArrayEquals(recurDaysArr,appt1.getRecurDays());
    }

    /**
     * Test the other Appt constructor method to getStartHour
     */
    @Test(timeout = 4000)
    public void testGetStartHour()  throws Throwable  {
        Appt appt1 = new Appt(9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        assertTrue(!appt1.hasTimeSet());
    }

    /**
     * Test null for inputs - title
     */
    @Test(timeout = 4000)
    public void testSetNullDescription() throws Throwable {
        Appt appt1 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", null, "xyz@gmail.com");
/*
        assertEquals("",appt1.getDescription());
*/
    }

    @Test(timeout = 4000)
    public void testSetNullTitle() throws Throwable {
        Appt appt1 = new Appt(15, 30, 9, 14, 2018, null, "This is my birthday party", "xyz@gmail.com");

        assertEquals("",appt1.getTitle());

    }

    @Test(timeout = 4000)
    public void testSetNullEmailAddress() throws Throwable {
        Appt appt1 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", null);
        assertEquals("",appt1.getEmailAddress());
    }

    @Test(timeout = 4000)
    public void testSetNullRecurDays() throws Throwable {
        Appt appt1 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setRecurrence(null,Appt.RECUR_BY_WEEKLY,2,Appt.RECUR_NUMBER_FOREVER);
    }


    // Test all the setValid errors

    @Test(timeout = 4000)
    public void testSetInvalidNegHour() throws Throwable {
        Appt appt1 = new Appt(-1, 30, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();
        assertTrue(!appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidNegHour2() throws Throwable {
        Appt appt1 = new Appt(0, 30, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();
        assertTrue(appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidNegHour3() throws Throwable {
        Appt appt1 = new Appt(23, 30, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();
        assertTrue(appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidHour() throws Throwable {
        Appt appt1 = new Appt(30, 30, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();
        assertTrue(!appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidMinute() throws Throwable {
        Appt appt1 = new Appt(12, -1, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(!appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidMinuteEdge1() throws Throwable {
        Appt appt1 = new Appt(12, 0, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidMinuteEdge2() throws Throwable {
        Appt appt1 = new Appt(12, 59, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidNegDay() throws Throwable {
        Appt appt1 = new Appt(15, 30, -1, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(!appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidNDay() throws Throwable {
        Appt appt1 = new Appt(15, 30, 50, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(!appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidDayEdge1() throws Throwable {
        Appt appt1 = new Appt(15, 30, 1, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidDayEdge2() throws Throwable {
        Appt appt1 = new Appt(15, 30, 31, 7, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(appt1.getValid());
    }


    @Test(timeout = 4000)
    public void testSetInvalidMonth() throws Throwable {
        Appt appt1 = new Appt(15, 30, 9, -1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(!appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidGreaterMonth() throws Throwable {
        Appt appt1 = new Appt(15, 30, 9, 15, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(!appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidMonthEdge1() throws Throwable {
        Appt appt1 = new Appt(15, 30, 9, 1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidMonthEdge2() throws Throwable {
        Appt appt1 = new Appt(15, 30, 9, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();

        assertTrue(appt1.getValid());
    }

    @Test(timeout = 4000)
    public void testSetInvalidYear() throws Throwable {
        Appt appt1 = new Appt(15, 30, 9, 10, -1, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();
        assertTrue(!appt1.getValid());
/*
        assertEquals("\tThis appointment is not valid",appt1.toString());
*/
    }

    @Test(timeout = 4000)
    public void testSetInvalidYear2() throws Throwable {
        Appt appt1 = new Appt(15, 30, 9, 10, 0, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setValid();
        assertTrue(!appt1.getValid());
/*
        assertEquals("\tThis appointment is not valid",appt1.toString());
*/
    }

    @Test(timeout = 4000)
    public void testRepresentationApp() throws Throwable {
        Appt appt1 = new Appt(23, 30, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        // start hour > 15 so if negated representation App would be wrong
        // this is also valid thus toString method sohuld not return

        String day= appt1.getStartMonth()+"/"+appt1.getStartDay()+"/"+appt1.getStartYear() + " at ";
        String representationApp = "11:30pm";
        String finalString = "\t"+ day +  representationApp  + " ," +  appt1.getTitle()+ ", "+  appt1.getDescription()+"\n";

        assertEquals(finalString,appt1.toString());

    }

    @Test(timeout = 4000)
    public void testRepresentationApp2() throws Throwable {
        Appt appt1 = new Appt(11, 30, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        // start hour > 15 so if negated representation App would be wrong
        // this is also valid thus toString method sohuld not return

        String day= appt1.getStartMonth()+"/"+appt1.getStartDay()+"/"+appt1.getStartYear() + " at ";
        String representationApp = "11:30am";
        String finalString = "\t"+ day +  representationApp  + " ," +  appt1.getTitle()+ ", "+  appt1.getDescription()+"\n";

        assertEquals(finalString,appt1.toString());

    }












}