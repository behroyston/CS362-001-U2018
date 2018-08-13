package calendar;


import org.junit.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {

    private static final long TestTimeout = 60 * 100 * 1; /* Timeout at 30 seconds */
    private static final int NUM_TESTS=100;
	
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	  public void randomtest()  throws Throwable  {

         long startTime = Calendar.getInstance().getTimeInMillis();
         long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


         System.out.println("Start testing...");

         try{
             for (int iteration = 0; elapsed < TestTimeout; iteration++) {
                 long randomseed = System.currentTimeMillis(); //10
                 //			System.out.println(" Seed:"+randomseed );
                 Random random = new Random(randomseed);

                 int startHour=ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startMinute=ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startDay=ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startYear=ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
                 String title="Birthday Party";
                 String description="This is my birthday party.";
                 String emailAddress="xyz@gmail.com";

                 //Construct a new Appointment object with the initial data
                 //Construct a new Appointment object with the initial data
                 Appt appt = new Appt(startHour,
                         startMinute ,
                         startDay ,
                         startMonth ,
                         startYear ,
                         title,
                         description,
                         emailAddress);

                 int day = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int month = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int year = ValuesGenerator.getRandomIntBetween(random, 2018, 2018);

                 GregorianCalendar gregCalendar = new GregorianCalendar(year,month,day);
                 CalDay calDay = new CalDay(gregCalendar);

                 // set appt to invalid
                 int validOrInvalidAppt = ValuesGenerator.getRandomIntBetween(random, 0, 1);
                 if (validOrInvalidAppt == 1) {
                     appt.setStartHour(-1);
                     appt.setValid();
                 }
                 // add more than one appt to the same calDay
                 int moreThanOneApptOrNot = ValuesGenerator.getRandomIntBetween(random, 0, 1);
                 if (moreThanOneApptOrNot == 1) {
                     Appt appt2;
                     if (startHour < 5)
                        appt2 = new Appt(startHour + 5,
                             startMinute ,
                             startDay ,
                             startMonth ,
                             startYear ,
                             title,
                             description,
                             emailAddress);
                     else
                         appt2 = new Appt(startHour - 5,
                                 startMinute ,
                                 startDay ,
                                 startMonth ,
                                 startYear ,
                                 title,
                                 description,
                                 emailAddress);
                         calDay.addAppt(appt2);
                 }


                 calDay.addAppt(appt);


                 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
                 if((iteration%10000)==0 && iteration!=0 )
                     System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);


             }
         }catch(NullPointerException e){

         }

         System.out.println("Done testing...");
     }


	
}
