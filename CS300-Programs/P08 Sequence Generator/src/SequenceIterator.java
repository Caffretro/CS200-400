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
import java.util.Iterator;

/**
 * This is the class we use to go over each of the elements in the sequence, 
 * it overrides the Iterator interface's hasNext() and next() methods
 */

public class SequenceIterator implements Iterator<Integer> {
    private NumberGenerator generator; // a NumberGenerator object
    // that generates and returns a number of index n in a
    // sequence of numbers
    private int size; // size of the sequence
    private int index; // index of the next number to be generated in the sequence

    /**
     * Constructs a SequenceIterator with given number generator and size This constructs
     * initializes also the index to 0
     * 
     * @param generator
     * @param size
     */
    public SequenceIterator(NumberGenerator generator, int size) {
        this.generator = generator;
        this.size = size;
        // Since the sequence starts from index 0, we initialize it to 0
        this.index = 0;
        
    }

    @Override
    public boolean hasNext() {
        return index < (size);
        // Index starts from 0
    }

    @Override
    public Integer next() {
        return generator.generateNumber(index++);
        // After returning the number generated, we increment index.
    }

}
