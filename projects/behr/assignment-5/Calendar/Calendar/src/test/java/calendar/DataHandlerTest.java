
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.io.*;
import org.junit.After;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DataHandlerTest {

    DataHandler dataHandler;
    DataHandler invalidHandler;
    int thisDay;
    int thisMonth;
    int thisYear;
    Calendar calendar;
    GregorianCalendar today;
    GregorianCalendar tomorrow;
    GregorianCalendar nextMonth;
    GregorianCalendar nextWeek;
    GregorianCalendar nextYear;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    @Before
    public void init() throws Throwable, IOException {
        dataHandler = new DataHandler();
        //get todays date
        Calendar rightnow = Calendar.getInstance();
        //current month/year/date is today
        /** the month the user is currently viewing **/
        thisMonth = rightnow.get(Calendar.MONTH);
        /** todays date **/
        thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
        /** the year the user is currently viewing **/
        thisYear = rightnow.get(Calendar.YEAR);

        //		// Get a calendar which is set to a specified date.
        calendar = new GregorianCalendar(thisYear, thisMonth, thisDay);
        // Increment the calendar's date by 1 day.
        calendar.add(calendar.DAY_OF_MONTH, 1);

        today = new GregorianCalendar(thisYear, thisMonth, thisDay);
        tomorrow = new GregorianCalendar(thisYear, thisMonth, thisDay);
        nextWeek = new GregorianCalendar(thisYear, thisMonth, thisDay);

        nextMonth = new GregorianCalendar(thisYear, thisMonth, thisDay);
        tomorrow.add(today.DAY_OF_MONTH, 1);
        nextMonth.add(today.MONTH,1);
        nextMonth.add(today.DAY_OF_MONTH,1);
        nextWeek.add(today.DAY_OF_MONTH,7);

        nextYear = new GregorianCalendar(thisYear, thisMonth, thisDay);
        nextYear.add(today.YEAR,1);
        nextYear.add(today.DAY_OF_MONTH,1);

        File testfile = new File("calendar_test.xml");
        testfile.delete();
        testfile = new File("calendar.xml");
        testfile.delete();
    }

    @After
    public void tearDown() {
        File testfile = new File("calendar_test.xml");
        testfile.delete();
        testfile = new File("calendar.xml");
        testfile.delete();
    }

//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));


    @Test(timeout = 4000)
    public void testSaveAndDeleteAppt() throws Throwable {
        Appt appt0 = new Appt(15, 30, thisDay, thisMonth, thisYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        dataHandler.saveAppt(appt0);
        dataHandler.deleteAppt(appt0);
    }

    @Test(timeout = 4000)
    public void testGetApptRangeWithoutRecurring() throws Throwable {
        Appt appt0 = new Appt(15, 30, thisDay, thisMonth, thisYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        dataHandler.saveAppt(appt0);
        Appt appt1 = new Appt(15, 30, thisDay, thisMonth, thisYear + 1, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        dataHandler.saveAppt(appt1);
        LinkedList<CalDay> newList = new LinkedList<CalDay>();
        newList = (LinkedList<CalDay>) dataHandler.getApptRange(today, tomorrow);
    }
    //startHour, int startMinute,
    //            int startDay, int startMonth, int startYear

    @Test(timeout = 4000)
    public void testRecurrenceToString()  throws Throwable  {
        System.setOut(new PrintStream(outContent));
        DataHandler data = new DataHandler("calendar_test.xml");
        GregorianCalendar day1 = new GregorianCalendar(2018, 7, 1);
        GregorianCalendar day2 = new GregorianCalendar(2018, 8, 1);
        Appt appt0 = new Appt(2, 3, 1, 1, 2018, "lesson", "cooking", "xyz@gmail.com");
        int[] recurDaysArr = {5};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 1);
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(day1, day2);
        assertEquals("", outContent.toString());
    }

    @Test(timeout = 4000)
    public void testNumberOfWeeklyReccurence()  throws Throwable  {

        DataHandler data = new DataHandler();
        GregorianCalendar day1 = new GregorianCalendar(2018, 6, 1);
        GregorianCalendar day2 = new GregorianCalendar(2018, 7, 1);
        Appt appt0 = new Appt(5, 5, 1, 7, 2018, "lesson", "cooking", "xyz@gmail.com");
        int[] recurDaysArr = {1};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 10);
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(day1, day2);

        int totalCount = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int j = 0; j < appts.size(); j++) {
                totalCount++;
            }
        }

        assertEquals(5, totalCount);

    }

    @Test(timeout = 4000)
    public void testNumberOfMonthlyReccurence()  throws Throwable  {

        DataHandler data = new DataHandler();
        GregorianCalendar day1 = new GregorianCalendar(2018, 6, 1);
        GregorianCalendar day2 = new GregorianCalendar(2018, 7, 1);
        Appt appt0 = new Appt(5, 5, 1, 6, 2018, "lesson", "cooking", "xyz@gmail.com");
        int[] recurDaysArr = {1};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_MONTHLY, 1, 10);
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(day1, day2);

        int totalCount = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int j = 0; j < appts.size(); j++) {
                totalCount++;
            }
        }

        assertEquals(1, totalCount);

    }

    @Test(timeout = 4000)
    public void testNumberOfYearlyReccurence()  throws Throwable  {

        DataHandler data = new DataHandler();
        GregorianCalendar day1 = new GregorianCalendar(2018, 6, 1);
        GregorianCalendar day2 = new GregorianCalendar(2019, 7, 1);
        Appt appt0 = new Appt(5, 5, 1, 6, 2018, "lesson", "cooking", "xyz@gmail.com");
        int[] recurDaysArr = {1};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 1, 10);
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(day1, day2);

        int totalCount = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int j = 0; j < appts.size(); j++) {
                totalCount++;
            }
        }

        assertEquals(1, totalCount);

    }

    @Test(timeout = 4000)
    public void testWithWeeklyRecurDaysWithNullRange()  throws Throwable  {
        DataHandler data = new DataHandler();
        GregorianCalendar day1 = new GregorianCalendar(2018, 6, 1);
        GregorianCalendar day2 = new GregorianCalendar(2018, 7, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2019, "lesson", "cooking", "xyz@gmail.com");
        int[] recurDaysArr = {1};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 10);
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(day1, day2);

        int totalCount = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int j = 0; j < appts.size(); j++) {
                totalCount++;
            }
        }
        assertEquals(0, totalCount);
    }


    @Test(timeout = 4000)
    public void testWithNullRecurDays()  throws Throwable  {
        DataHandler data = new DataHandler();
        GregorianCalendar day1 = new GregorianCalendar(2018, 6, 1);
        GregorianCalendar day2 = new GregorianCalendar(2018, 7, 1);
        Appt appt0 = new Appt(5, 5, 1, 7, 2018, "lesson", "cooking", "xyz@gmail.com");
        int[] recurDaysArr = {};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(day1, day2);

        int totalCount = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int j = 0; j < appts.size(); j++) {
                totalCount++;
            }
        }
        assertEquals(5, totalCount);
    }



    @Test(timeout = 4000)
    public void testWithNoApptRange()  throws Throwable  {
        DataHandler data = new DataHandler();
        GregorianCalendar day1 = new GregorianCalendar(2018, 7, 1);
        GregorianCalendar day2 = new GregorianCalendar(2020, 1, 1);
        Appt appt0 = new Appt(1, 1, 1, 1, 2000, "lesson", "cooking", "xyz@gmail.com");
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(day1, day2);

        int totalCount = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int j = 0; j < appts.size(); j++) {
                totalCount++;
            }
        }
        assertEquals(0, totalCount);
    }

    @Test(timeout = 4000)
    public void testDeletionOfInvalidAppt()  throws Throwable  {

        DataHandler data = new DataHandler();
        Appt appt0 = new Appt(1,1,1,1,-1,"lesson","cooking", "xyz@gmail.com");	//invalid year
        appt0.setValid();
        assertFalse(data.saveAppt(appt0));
        assertFalse(data.deleteAppt(appt0));
    }

    // here all testing XML/Saving of Data
    @Test(timeout = 4000)
    public void testNullXMLAfterDeletion()  throws Throwable  {
        DataHandler data  = new DataHandler("calendar_test.xml");
        Appt appt0 = new Appt(1, 1, 1, 1, 2018, "lesson","cooking", "xyz@gmail.com");
        appt0.setValid();
        data.saveAppt(appt0);
        data.deleteAppt(appt0);
        assertNull(appt0.getXmlElement());
    }

    @Test(timeout = 4000)
    public void testAutoSave1()  throws Throwable  {
        GregorianCalendar dayToTest = new GregorianCalendar(2018, 1, 1);
        CalDay day = new CalDay(dayToTest);
        Appt appt0 = new Appt(1, 1, 1, 1, 2018, "lesson","cooking", "xyz@gmail.com");
        day.addAppt(appt0);
        DataHandler data = new DataHandler("calendar_test.xml", false);
        assertFalse(data.deleteAppt(appt0));
    }

    @Test(timeout = 4000)
    public void testAutoSave2()  throws Throwable  {
        GregorianCalendar dayToTest = new GregorianCalendar(2018, 1, 1);
        CalDay day = new CalDay(dayToTest);
        Appt appt0 = new Appt(1, 1, 1, 1, 2018, "lesson","cooking", "xyz@gmail.com");
        day.addAppt(appt0);
        DataHandler data= new DataHandler("calendar_test.xml", false);
        data.saveAppt(appt0);
        assertTrue(data.deleteAppt(appt0));
    }

}