
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DataHandlerTest{

    DataHandler dataHandler;
    DataHandler invalidHandler;
    int thisDay;
    int thisMonth;
    int thisYear;
    Calendar calendar;
    GregorianCalendar today;
    GregorianCalendar tomorrow;

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
        calendar.add(calendar.DAY_OF_MONTH,1);

        today = new GregorianCalendar(thisYear,thisMonth,thisDay);
        tomorrow = new GregorianCalendar(thisYear,thisMonth,thisDay);

        tomorrow.add(today.DAY_OF_MONTH,1);


    }

    @Test(timeout = 4000)
    public void testSaveAndDeleteAppt()  throws Throwable  {
        Appt appt0 = new Appt(15, 30, thisDay, thisMonth, thisYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        dataHandler.saveAppt(appt0);
        dataHandler.deleteAppt(appt0);

    }

    @Test(timeout = 4000)
    public void testInvalidDataHandler()  throws Throwable  {
        Appt appt0 = new Appt(15, 30, thisDay, thisMonth, thisYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
/*
        invalidHandler.saveAppt(appt0);
*/

    }

    @Test(timeout = 4000)
    public void testGetApptRangeWithoutRecurring()  throws Throwable  {
        Appt appt0 = new Appt(15, 30, thisDay, thisMonth, thisYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        dataHandler.saveAppt(appt0);
        Appt appt1 = new Appt(15, 30, thisDay, thisMonth, thisYear+1, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        dataHandler.saveAppt(appt1);
        LinkedList<CalDay> newList = new LinkedList<CalDay>();
        newList = (LinkedList<CalDay>)dataHandler.getApptRange(today, tomorrow);
    }

    @Test(timeout = 4000)
    public void testGetApptRangeWithRecurring()  throws Throwable  {
        Appt appt0 = new Appt(15, 30, thisDay, thisMonth, thisYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        int[] recurDaysArr={2,3,4};
        appt0.setRecurrence( null, Appt.RECUR_BY_WEEKLY, 2, 1);
        Appt appt1 = new Appt(12, 30, thisDay, thisMonth, thisYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt1.setRecurrence(recurDaysArr, Appt.RECUR_BY_MONTHLY, 2, 1);
        Appt appt2 = new Appt(13, 30, thisDay, thisMonth, thisYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        appt2.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 2, 1);

        dataHandler.saveAppt(appt0);
        dataHandler.saveAppt(appt1);
        dataHandler.saveAppt(appt2);

        LinkedList<CalDay> newList = new LinkedList<CalDay>();
        newList = (LinkedList<CalDay>)dataHandler.getApptRange(today, tomorrow);

    }

    @Test(timeout = 4000)
    public void testGetInvalidApptRange()  throws Throwable  {
        Appt appt0 = new Appt(15, 30, thisDay, thisMonth, thisYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        dataHandler.saveAppt(appt0);
        Appt appt1 = new Appt(15, 30, thisDay, thisMonth, thisYear, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
        dataHandler.saveAppt(appt1);
        LinkedList<CalDay> newList = new LinkedList<CalDay>();
/*
        newList = (LinkedList<CalDay>)dataHandler.getApptRange(tomorrow, today);
*/

    }


}
