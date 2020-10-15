//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P08 Sequence Generator
// Files:           None
// Course:          CS300 Spring 2018
//
// Author:          Junheng Wang
// Email:           jwang922@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Lecturer's Name: N/A
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This is a Fibonacci number generator, the only method it has returns the nth
 * number in a Fibonacci sequence
 */

public class FibonacciNumberGenerator implements NumberGenerator {

    @Override
    public int generateNumber(int n) {
        // Time complexity: O(N)
        // computes the number of index n in a Fibonacci sequence
        // iteratively. (Do not use a recursive algorithm here!)
        
        // Since Fibonacci sequence needs to start from at least two numbers, the
        // first two numbers are the base cases
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            // We need the following nums to do the calculation by themselves
            int num1 = 0;
            int num2 = 1;
            int num3 = 0;
            // Starting from i = 1 is because we have done the first two in num1 and num2
            for (int i = 1; i < n; i++) {
                num3 = num1 + num2;
                num1 = num2;
                num2 = num3;
            }
            return num3;
        }
    }
}
