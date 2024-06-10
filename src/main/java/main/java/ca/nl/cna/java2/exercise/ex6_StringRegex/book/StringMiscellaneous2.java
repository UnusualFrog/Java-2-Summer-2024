package main.java.ca.nl.cna.java2.exercise.ex6_StringRegex.book;// Fig. 14.8: StringMiscellaneous2.java
// String methods replace, toLowerCase, toUpperCase, trim and toCharArray.

import java.util.Random;

public class StringMiscellaneous2 {
   public static void main(String[] args) {
      Random random = new Random();
      String name = "Noah Forward";

      for (int i = 0; i < 1000000; i++) {
         int index = random.nextInt(0,name.length());
         char randomChar = (char) random.nextInt(97,122);

         if (index == 0) {
            String substr = name.substring(1);
            name = randomChar + substr;
         } else if (index == name.length()-1){
            String substr = name.substring(0,index);
            name = substr + randomChar;
         } else {
            String substrLeft = name.substring(0, index);
            String substrRight = name.substring(index+1);
            name = substrLeft + randomChar + substrRight;
         }
      }
      System.out.println(name);
      
//      String s1 = "hello";
//      String s2 = "GOODBYE";
//      String s3 = "   spaces   ";
//
//      System.out.printf("s1 = %s\ns2 = %s\ns3 = %s\n\n", s1, s2, s3);
//
//      // test method replace      
//      System.out.printf(
//         "Replace 'l' with 'L' in s1: %s\n\n", s1.replace('l', 'L'));
//
//      // test toLowerCase and toUpperCase
//      System.out.printf("s1.toUpperCase() = %s\n", s1.toUpperCase());
//      System.out.printf("s2.toLowerCase() = %s\n\n", s2.toLowerCase());
//
//      // test trim method
//      System.out.printf("s3 after trim = \"%s\"\n\n", s3.trim());
//
//      // test toCharArray method
//      char[] charArray = s1.toCharArray();
//      System.out.print("s1 as a character array = ");
//
//      for (char character : charArray) {
//         System.out.print(character);
//      }
//
//      System.out.println();
   } 
} 

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
