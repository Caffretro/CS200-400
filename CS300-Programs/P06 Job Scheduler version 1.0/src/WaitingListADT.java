//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Job Scheduler version 1.0
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
public interface WaitingListADT<T> {
    public void schedule(T newObject); // adds an item of type <T> to the waiting list
    // according to a specific scheduling policy

    public boolean isEmpty(); // checks if the waiting list is empty
    // returns true if the waiting list empty
    // false otherwise

    public int size(); // returns the number of items in the waiting list

    public int clean(float cleaningTime); // removes the obsolete items from the waiting list

    public void clear(); // removes all the items in the waiting list

    public WaitingListADT<T> duplicate(); // returns a new reference to a duplicate copy of the list
}
