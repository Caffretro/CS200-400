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
 * This class is the "main" class that controls underlying functions, which is 
 * sequence generating
 */

public class Sequence implements Iterable<Integer> {
    private NumberGenerator generator; // a NumberGenerator object
    // that generates and returns a number of index n in a
    // sequence of numbers
    private int size; // Number of items in the sequence

    /**
     * Creates a Sequence of Integers with a given instance of NumberGenerator and a given size
     * 
     * @param generator
     * @param size
     * @throws IllegalArgumentException if size is negative
     */
    public Sequence(NumberGenerator generator, int size) throws IllegalArgumentException {
        if (size < 0) {
            throw new IllegalArgumentException();
            // while the size is less than 0, we are supposed to handle it
        } else {
            this.generator = generator;
            this.size = size;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new SequenceIterator(this.generator, this.size);
        // this method simply returns a new SequenceIterator
    }

    @Override
    public String toString() {
        // overrides the toString method of java.lang.Object
        // class to return a String representation of the sequence.
        // The different numbers of the sequence would be
        // separated by a single space
        Iterator<Integer> iter = iterator();
        String output = "";
        while (iter.hasNext()) {
            output += "" + iter.next() + " ";
        }
        return output;
    }
    // Following main() is for testing
    public static void main(String[] args) {
        System.out.println("****************************************");
        System.out.println("           Sequence Generator");
        System.out.println("****************************************");

        System.out.println("==========Arithmetic Sequence==========");
        Sequence sequence = new Sequence(new ArithmeticNumberGenerator(2, 2), 4);
        System.out.println(sequence.toString());

        System.out.println("==========Geometric Sequence==========");
        sequence = new Sequence(new GeometricNumberGenerator(2, 2), 10);
        System.out.println(sequence.toString());

        System.out.println("==========Fibonacci Sequence==========");
        sequence = new Sequence(new FibonacciNumberGenerator(), 20);
        System.out.println(sequence.toString());
    }

}
