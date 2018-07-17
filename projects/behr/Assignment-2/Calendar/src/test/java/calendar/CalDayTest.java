/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import calendar.CalDay;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import static org.junit.Assert.*;


public class CalDayTest{





  /**
   * Test getMethods
   * @throws Throwable
   */
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
    GregorianCalendar cal = new GregorianCalendar(2018,1,20);
    CalDay calday = new CalDay(cal);

    assertEquals(2018, calday.year);
    assertEquals(1,calday.month);
    assertEquals(20,calday.day);
    assertEquals(20,calday.getDay());
    assertEquals(1,calday.getMonth());
    assertEquals(2018,calday.getYear());
  }

  @Test(timeout = 4000)
  public void testNull()  throws Throwable {

    CalDay calday = new CalDay();
    assertFalse(calday.isValid());
    assertNull(calday.iterator());
  }

  @Test(timeout = 4000)
  public void testToString()  throws Throwable  {

    CalDay cal = new CalDay();
    assertNotNull(cal.toString());
  }

  @Test(timeout = 4000)
  public void testGetFullInfo()  throws Throwable  {
    GregorianCalendar cal = new GregorianCalendar(2018, 1, 2);
    CalDay calday = new CalDay(cal);
    Appt appt0 = new Appt(1, 2, 2018, "party", "party all day", "xyz@gmail@gmail.com");
    Appt appt1 = new Appt(3, 8, 5, 3, 2018, "birthday", "my party", "xyz@gmail@gmail@gmail.com");
    Appt appt2 = new Appt(14, 10, 6, 4, 2018, "party", "night", "xyz@gmail.com");
    calday.addAppt(appt0);
    calday.addAppt(appt1);
    calday.addAppt(appt2);
    //String testInfo = calday.getFullInfomrationApp(calday);
    assertNotNull(calday.getFullInfomrationApp(calday));
    }

  @Test(timeout = 4000)
  public void testGetSize()  throws Throwable {
    GregorianCalendar cal = new GregorianCalendar(2018, 10, 20);
    Appt appt0 = new Appt(1, 10, 4, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt1 = new Appt(1, 12, 3, 11, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    CalDay calday = new CalDay(cal);
    calday.addAppt(appt0);
    calday.addAppt(appt1);
    assertEquals(2, calday.getSizeAppts());
  }

  @Test(timeout = 4000)
  public void testInvalid()  throws Throwable {

    GregorianCalendar cal = new GregorianCalendar(2018, 4, 3);
    CalDay calday = new CalDay(cal);
    Appt appt0 = new Appt(-10, 2, 10, 5, 2018, "appointment", "Dentist", "xyz@gmail.com");    //invalid appt
    appt0.setValid();
    calday.addAppt(appt0);
    assertNotNull(calday.toString());
  }




}
