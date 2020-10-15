//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Drawing Arrow
// Files:           ...
// Course:          CS200, fall 2017
//
// Author:          Junheng Wang
// Email:           jwang922@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         Nope
// Online Sources:  Nope
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner; 

public class DrawHalfArrow {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      int arrowBaseHeight = 0;
      int arrowBaseWidth  = 0;
      int arrowHeadWidth = 0;

      int i = 0;
      int j = 0;

      System.out.print("Enter arrow base height: ");
      arrowBaseHeight = scnr.nextInt();

      System.out.print("Enter arrow base width: ");
      arrowBaseWidth = scnr.nextInt();

      System.out.print("Enter arrow head width: ");
      arrowHeadWidth = scnr.nextInt();
      
      while (arrowHeadWidth <= arrowBaseWidth) {
          System.out.print("Enter arrow head width: ");//FIXME AFTER TESTING
          arrowHeadWidth = scnr.nextInt();
      }
      System.out.println();

      for (i = 0; i < arrowBaseHeight; i++) {
          for (j = 0; j < arrowBaseWidth; j++) {
              System.out.print("*");
          }
          System.out.println();
      }


      for (i = arrowHeadWidth; i > 0; i--) {
          for (j = 0; j < i; j++) {
              System.out.print("*");
          }
          System.out.println();
      }



      return;
   }
}