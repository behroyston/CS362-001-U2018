package calendar;

import java.util.Calendar;
import java.util.Random;
import java.util.GregorianCalendar;
import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	
    /**
     * Generate Random Tests that tests DataHandler Class.
     */

    private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
    private static final int NUM_TESTS=100;

    /**
     * Return a randomly selected method to be tests !.
     */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"deleteAppt","getApptRange"};// The list of the of methods to be tested in the DataHandler class

        int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

        return methodArray[n] ; // return the method name
    }

    public static DataHandler RandomSelectDataHandler(Random random) throws Throwable {

        int field = ValuesGenerator.getRandomIntBetween(random, 0, 1);
        DataHandler dataHandler;
        if (field == 0)
            dataHandler = new DataHandler("test.xml",true);
        else
            dataHandler = new DataHandler("test.xml",false);

        return dataHandler ; // return the method name
    }




    // delete Appt,getApptRange
	 @Test
	  public void radnomtest()  throws Throwable {

         long startTime = Calendar.getInstance().getTimeInMillis();
         long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

         System.out.println("Start testing...");

         try {
             for (int iteration = 0; elapsed < TestTimeout; iteration++) {
                 long randomseed = System.currentTimeMillis(); //10
                 //			System.out.println(" Seed:"+randomseed );
                 Random random = new Random(randomseed);

                 int startHour = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startMinute = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startDay = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startYear = ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
                 String title = "Birthday Party";
                 String description = "This is my birthday party.";
                 String emailAddress = "xyz@gmail.com";

                 //Construct a new Appointment object with the initial data
                 //Construct a new Appointment object with the initial data
                 Appt appt = new Appt(startHour,
                         startMinute,
                         startDay,
                         startMonth,
                         startYear,
                         title,
                         description,
                         emailAddress);

                 int startHour2 = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startMinute2 = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startDay2 = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startMonth2 = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                 int startYear2 = ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
                 String title2 = "Birthday Party 2";
                 String description2 = "This is my birthday party 2.";
                 String emailAddress2 = "abc@gmail.com";

                 //Construct a new Appointment object with the initial data
                 //Construct a new Appointment object with the initial data
                 Appt appt2 = new Appt(startHour2,
                         startMinute2,
                         startDay2,
                         startMonth2,
                         startYear2,
                         title2,
                         description2,
                         emailAddress2);

                 int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
                 int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
                 int recur= Appt.RECUR_BY_WEEKLY;
                 int recurIncrement = 1;
                 int recurNumber= Appt.RECUR_NUMBER_FOREVER;
                 appt2.setRecurrence(recurDays, recur, recurIncrement, recurNumber);

                 if (!appt.getValid()) continue;

                 DataHandler dataHandler = DataHandlerRandomTest.RandomSelectDataHandler(random);

                 for (int i = 0; i < NUM_TESTS; i++) {

                     dataHandler.saveAppt(appt);
                     dataHandler.saveAppt(appt2);

                     String methodName = DataHandlerRandomTest.RandomSelectMethod(random);
                     if (methodName.equals("deleteAppt")) {
                         int setValidOrInvalid = ValuesGenerator.getRandomIntBetween(random, 0, 1);
                         if (setValidOrInvalid == 0) {
                             appt.setStartHour(-1);
                             appt.setValid();
                         }

                         dataHandler.deleteAppt(appt);
                         dataHandler.deleteAppt(appt2);
                     } else if (methodName.equals("getApptRange")) {
                         GregorianCalendar firstDay = new GregorianCalendar(startDay2,startHour2,startYear2);
                         GregorianCalendar secondDay;
                         int secondDayInt;

                         /*int startDayBefore = ValuesGenerator.getRandomIntBetween(random, 0, 1);
                         if (startDayBefore == 0) {
                             secondDayInt = startDay2 - 10;
                         }*/
                         /*else {*/
                             secondDayInt = startDay2 + 20;
/*
                         }
*/

                         secondDay = new GregorianCalendar(secondDayInt, startHour2, startYear2);
                         /*else
                         {
                             secondDayInt = startDay + 4;
                             secondDay = new GregorianCalendar(secondDayInt, startHour, startYear);
                         }*/

                         dataHandler.getApptRange(firstDay,secondDay);

                     }

                 }

                 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
                 if ((iteration % 10000) == 0 && iteration != 0)
                     System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);
             }
         } catch (NullPointerException e) {

         }

         System.out.println("Done testing...");


     }

}
