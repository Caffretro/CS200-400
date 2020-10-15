//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Exploring a Maze
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
import java.util.EmptyStackException;

public class TestStack {

    public static void main(String[] args) {
        System.out.println(runTests());
        // Run the tests

    }

    public static boolean runTests() {
        boolean result = false;
        result = testConstructor() && testPush() && testPop() 
            && testPeek() && testIsEmpty() && testContains();
        return result;
    }
    
    private static boolean testConstructor() {
        MazeRunnerStack test = new MazeRunnerStack();
        if (!test.isEmpty()) {
            System.out.println("testConstructor failed: test 1 didn't construct correct value");
            return false;
        }
        // test if the default constructor works
        MazeRunnerStack test2 = new MazeRunnerStack();
        test2.push(new Position(0, 0));
        if (test2.isEmpty()) {
            System.out.println("testConstructor failed: test 2 didn't construct correct value");
            return false;
        }
        // test if the size of the stack is updated
        return true;
    }
    private static boolean testPush() {
        MazeRunnerStack test = new MazeRunnerStack();
        Position addItem = new Position(1, 6);
        try {
            test.push(addItem);
        } catch (NullPointerException e) {
            System.out.println("testPush failed: test failed to push to an empty list");
        }
        if (!test.peek().equals(addItem)) {
            System.out.println("testPush failed: test failed to push the list successfully");
            return false;
        }// test if addItem is pushed into the list successfully
        MazeRunnerStack test2 = new MazeRunnerStack();
        Position addItem2 = new Position(0, 0);
        try {
            for (int i = 0; i < 105; i++) {
                test2.push(new Position(i, i));
            }
            // we try to push more than 100 hundred items
        } catch (NullPointerException e) {
            System.out.println("testPush failed: test 2 should have pushed successfully but " + 
                                "instead throwing an NullPointerException");
            return false;
        } catch (Exception e) {
            System.out.println("testPush failed: test 2 thrown an unknown exception");
            return false;
        } 
        // Unknown exception
        for (int i = 104; i >= 0; i--) {
            addItem2 = test2.pop();
            if (addItem2.row != i && addItem.col != i) {
                System.out.println("testPush failed: test 2 failed to push item correctlly");
                return false;
            }
        }
        return true;
    }
    private static boolean testPop() {
        MazeRunnerStack test = new MazeRunnerStack();
        Position addItem = new Position(0, 0);
        Position popItem = new Position(0, 0);
        test.push(addItem);
        // following code should return true
        try {
            if (!test.isEmpty()) {
                popItem = test.pop();
            }
        } catch(EmptyStackException e) {
            System.out.println("testPop failed: test 4 should have popped successfully but " + 
                                "instead throwing an EmptyStackException");
            return false;
        }
        test.push(addItem);
        //since we successfully popped addItem, we add again
        if (!test.peek().equals(addItem)) {
            System.out.println("testPop failed: test 1 didn't pop the first item successfully");
            return false;
        }
        MazeRunnerStack test2 = new MazeRunnerStack();
        Position popItem2 = new Position(0, 0);
        for (int i = 0; i < 10; i++) {
            test2.push(new Position(i, i));
        }
        for (int j = 9; j >= 0; j--) {
            popItem2 = test2.pop();
            if (popItem2.row != j && popItem2.col != j) {
                System.out.println("testPop failed: test 2 could not pop in correct sequence");
                return false;
            }
        }
        return true;
    }
    private static boolean testPeek() {
        MazeRunnerStack test = new MazeRunnerStack();
        Position peekItem = new Position(0, 0);
        try {
            test.peek();
            // if peek() works fine, the following codes shouldn't execute
            System.out.println("testPeek failed: test 1 should have thrown exception but didn't");
            return false;
        } catch (EmptyStackException e) {
            
        }
        test.push(new Position(0, 0));
        if (!test.peek().equals(peekItem)) {
            System.out.println("testPeek failed: test 1 peeked a wrong item");
            return false;
        }
        MazeRunnerStack test2 = new MazeRunnerStack();
        Position peekItem2 = new Position(2, 2);
        for (int i = 0; i < 3; i++) {
            test2.push(new Position(i, i));
        }// the last one in the stack should be (2, 2)
        for (int j = 0; j < 3; j++) {
            if (!test2.peek().equals(peekItem2)) {
                System.out.println("testPeek failed: test 2 should have peeked (2, 2) " + 
                                   "every time but failed at (" + j + ", " + j + ")");
                return false;
            }
        }
        // test if the first one is the one that is peeked
        
        return true;
    }
    private static boolean testIsEmpty() {
        MazeRunnerStack test = new MazeRunnerStack();
        if (test.isEmpty() != true) {
            System.out.println("testIsEmpty failed: test 1 failed to return false");
            return false;
        }
        // stack is now empty
        Position addItem = new Position(0, 0);
        test.push(addItem);
        if (test.isEmpty() != false) {
            System.out.println("testIsEmpty failed: test 2 failed to return true");
            return false;
        }
        // stack is not empty
        return true;
    }
    private static boolean testContains() {
        MazeRunnerStack test = new MazeRunnerStack();
        Position[] testList = new Position[100];
        for (int i = 0; i < testList.length; i++) {
            test.push(new Position(i, i));
        }
        Position testPosition = new Position(5, 5);
        if (test.contains(testPosition) != true) {
            System.out.println("testContains failed: test 1 failed to find the item");
            return false;
        }
        // just test if we can find the item
        testPosition = new Position(5, 6);
        if (test.contains(testPosition) != false) {
            System.out.println("testContains failed: test 2 dare find the item???");
            return false;
        }
        // we cannot find the item
        return true;
    }
}
