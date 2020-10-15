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
 * This is an arithmetic number generator, where the difference between two numbers
 * next to each other is constant
 */

public class ArithmeticNumberGenerator implements NumberGenerator {
    private final int init; // first term in the sequence
    private final int diff; // common difference

    /**
     * Constructs an arithmetic number generator with given start value init and common difference
     * diff
     * 
     * @param init start value
     * @param diff common difference
     * @throws IllegalArgumentException if any of the input arguments is illegal
     */
    public ArithmeticNumberGenerator(int init, int diff) throws IllegalArgumentException {
        if (init < 0 || diff < 0) {
            throw new IllegalArgumentException();
        } else {
            this.init = init;
            this.diff = diff;
        }
    }


    @Override
    public int generateNumber(int n) {
        // Time Complexity: O(N)
        // This method generates the number of index n
        // in an arithmetic sequence recursively
        if (n == 0) {
            return this.init;
        } else {
            return this.diff + generateNumber(n - 1);
        }
    }
}
