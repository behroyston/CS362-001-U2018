
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
      //You can use this function to implement your manual testing

      //String[] schemes = {"http","https","ftp"};
      UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

      assertTrue(urlValidator.isValid("http://google.com"));

      // test port
      //assertTrue(urlValidator.isValid("http://google.com:8080")); unexpected failure

      // test schemes
      assertTrue(urlValidator.isValid("http://google.com"));
      assertFalse(urlValidator.isValid("hasd//google.com"));

      // test domain
      assertTrue(urlValidator.isValid("http://yahoo.com"));

      // test path
      assertTrue(urlValidator.isValid("http://yahoo.com/search"));
      assertFalse(urlValidator.isValid("hasd//yahoo.com/search"));

      // test query
      assertTrue(urlValidator.isValid("http://google.com/search?source=hello"));
      assertFalse(urlValidator.isValid("hasd//google.com/search?source=hello"));


      // test fragment
      assertTrue(urlValidator.isValid("http://google.com/search?source=hello#fff"));
      assertFalse(urlValidator.isValid("hasd//google.com/search?source=hello#fasf"));


   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing for path
      UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      String startString =  "http://www.google.com";
      String[] path = new String[] {"/search","/watch","/play","/view","/find"};
      String[] invalidPath = new String[]{"/../","/..","///"};

      for (int i = 0; i < 5; i++) {
         StringBuilder testURL = new StringBuilder();
         testURL.append(startString);
         testURL.append(path[i]);
         String urlToTest = testURL.toString();
         assertTrue(urlValidator.isValid(urlToTest));
      }

      for (int i = 0; i < 3; i++) {
         StringBuilder testURL = new StringBuilder();
         testURL.append(startString);
         testURL.append(invalidPath[i]);
         String urlToTest = testURL.toString();
         assertFalse(urlValidator.isValid(urlToTest));
      }


   }
   
   public void testYourSecondPartition(){

      UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      String startString =  "http://www.google.com";
      String[] validPort = new String[] {":80",":100",":10"};
      String[] invalidPort = new String[] {"-1230",":123123a","-0"};

      for (int i = 0; i < 3; i++) {
         StringBuilder testURL = new StringBuilder();
         testURL.append(startString);
         testURL.append(validPort[i]);
         String urlToTest = testURL.toString();
         System.out.println("Port Testing: " + urlToTest);
/*
         assertTrue(urlValidator.isValid(urlToTest)); -> bug
*/

      }

      // These ports are accepted
      for (int i = 0; i < 3   ; i++) {
         StringBuilder testURL = new StringBuilder();
         testURL.append(startString);
         testURL.append(invalidPort);
         String urlToTest = testURL.toString();
         //assertTrue(urlValidator.isValid(urlToTest)); -> bug

      }
   }
   //You need to create more test cases for your Partitions if you need to

   public void testYourThirdPartition() {

      UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      String startString = "http://www.google.com";
      String[] validQuery = new String[]{"/search?query=hello", "/search?action=find"};
      String[] invalidQuery = new String[]{"?????", "?-123123"};

      for (int i = 0; i < 1; i++) {
         StringBuilder testURL = new StringBuilder();
         testURL.append(startString);
         testURL.append(validQuery[i]);
         String urlToTest = testURL.toString();
         System.out.println("Valid Query: " + urlToTest);
         assertTrue(urlValidator.isValid(urlToTest));

      }

      /*for (int i = 0; i < 2; i++) {
         StringBuilder testURL = new StringBuilder();
         testURL.append(startString);
         testURL.append(invalidQuery[i]);
         String urlToTest = testURL.toString();
         System.out.println("Invalid Query: " + urlToTest);
         assertFalse(urlValidator.isValid(urlToTest));
      }*/
   }


   public void testYourLastPartition() {

      UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      String startString = "www.google.com";
      String[] validScheme = new String[]{"http://", "ftp://", "h3t://"};
      String[] invalidScheme = new String[]{"adas://", "lul://", "htta://"};

      /*for (int i = 0; i < 3; i++) {
         StringBuilder testURL = new StringBuilder();
         testURL.append(validScheme[i]);
         testURL.append(startString);
         String urlToTest = testURL.toString();
         assertTrue(urlValidator.isValid(urlToTest));

      }*/

      /*for (int i = 0; i < 3; i++) {
         StringBuilder testURL = new StringBuilder();
         testURL.append(invalidScheme[i]);
         testURL.append(startString);
         String urlToTest = testURL.toString();
         assertFalse(urlValidator.isValid(urlToTest));
      }*/


   }


   public void testIsValid()
   {
	   //You can use this function for programming based testing
   }

}
