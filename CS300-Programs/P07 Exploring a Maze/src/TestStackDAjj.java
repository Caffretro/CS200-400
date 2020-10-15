//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P07 EXPLORING A MAZE
// Files:           StackADT.java, MazeRunnerStack.java, Maze.java, 
//					TestStack.java
// Course:          CS 300
//
// Author:          Yuhao Liu
// Email:           liu697@wisc.edu
// Lecturer's Name: Gary Gahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __x__ Write-up states that pair programming is allowed for this assignment.
//   __x__ We have both read and understand the course Pair Programming Policy.
//   __x__ We have registered our team prior to the team registration deadline.
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

/**
 * This class tests the implementation of MazeRunnerStack class and aims to ]
 * reveal potential errors
 * @author LiuYuhao
 *
 */
public class TestStackDAjj {
	
	/**
	 * Main method that initiates the test sequence.
	 * @param args
	 */
	public static void main(String[] args) {
		boolean testResult = runTests();
		if (testResult == true) {
			System.out.println("All tests passed.");
		}
		
	}
	
	/**
	 * The method runs all private tests and check for potential 
	 * failures.
	 * @return
	 */
	public static boolean runTests() {
		boolean allPassed = true;
		//run all tests
		allPassed = testConstructor1();
		allPassed = testConstructor2();
		allPassed = testPushFromEmpty1();
		allPassed = testPopFromEmpty1();
		allPassed = testPop1();
		allPassed = testPushLargeNumber1();
		allPassed = testPeekFromEmpty1();
		allPassed = testPeek1();
		allPassed = testIsEmpty1();
		allPassed = testIsEmpty2();
		allPassed = testContains1();
		allPassed = testContains2();
		return allPassed;
	}
	
	/**
	 * First test method for the constructor and check if there is any exception thrown.
	 * @return
	 */
	private static boolean testConstructor1() {
		MazeRunnerStack stack;
		try {
			stack = new MazeRunnerStack();
		} catch (Exception e) {
			System.out.println("Failed: caught unexpected exception while running "
					+ "testConstructor");
			return false;
		}
		if (!stack.isEmpty()) {
			System.out.println("Failed: caught error while running testConstructor."
					+ " the stack should have been empty.");
			return false;
		}
		return true;
	}
	
	/**
	 * Second test method for the constructor and check if the stack is accidently
	 * written as a static field.
	 * @return
	 */
	private static boolean testConstructor2() {
		MazeRunnerStack stack1 = new MazeRunnerStack();
		MazeRunnerStack stack2 = new MazeRunnerStack();
		stack1.push(new Position(1, 1)); //item to be pushed
		try {
			stack2.pop(); //pop an element from the second stack, which's empty
		} catch (EmptyStackException e) {
			//expected emptyStackException here
			return true;
		}
		System.out.println("Expected an EmptyStackExcpetion when running testCOnstructor2()"
				+ "Instead, the stack poped the item that was pushed into the first stack");
		return false; 
	}
	
	/**
	 * First test method for the push() method. It checks for error by calling push() on 
	 * an empty stack.
	 * @return
	 */
	private static boolean testPushFromEmpty1() {
		MazeRunnerStack stack = new MazeRunnerStack();
		Position itemPushed = new Position(0, 0); //item to be pushed
		try {
			stack.push(itemPushed); 
		} catch (NullPointerException e) {
			System.out.println("Failed: caught NullPointerException while running "
					+ "testPushFromEmpty");
			return false;
		}
		//check if the item popped is the same as the one that was pushed in
		if (itemPushed.equals(stack.pop())) { 
			return true;
		} else {
			System.out.println("Failed: testPushFromEmpty1");
			return false;
		}
	}
	
	/**
	 * Second tester for the push class and checks if the stack is able to handle over 100 
	 * elements.
	 * @return
	 */
	private static boolean testPushLargeNumber1() {
		MazeRunnerStack stack = new MazeRunnerStack();
		Position newPos = new Position(0, 0); //item to be pushed
		try {
			for (int i = 0; i < 110; i ++) {
				stack.push(new Position(i, i));
			}
		} catch (NullPointerException e) {
			System.out.println("Failed: caught NullPointerException while running "
					+ "testPushLargeNumber1");
			return false;
		} catch (Exception e) {
			System.out.println("Failed: caught error when ruuning testPushLargeNumber1");
			return false;
		}
		//pop the stack 110 times, and indicate if there is any mismatches
		for (int i = 109; i >= 0; i--) { 
			//pop all items and check if they have the correct value
			newPos = stack.pop();
			if (newPos.row != i) {
				System.out.println("Failed: caught error when ruuning testPushLargeNumber1");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * First test method for pop(). It check if the method can correctly pop out when only 
	 * one element has been pushed in.
	 * @return
	 */
	private static boolean testPop1() {
		MazeRunnerStack stack = new MazeRunnerStack();
		Position itemPoped; //item popped
		stack.push(new Position(0, 0));
		Position itemAdded = new Position(1, 1);
		stack.push(itemAdded);
		try {
			itemPoped = stack.pop();
		} catch (EmptyStackException e) {
			System.out.println("Failed: Caught unexpected error when running testPop1.");
			return false;
		}
		//compare two Position objects
		if (itemPoped.equals(itemAdded)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Second test method for pop(). It checks if the pop() correctly throw an
	 * EmptyStackException when trying to pop from an empty stack.
	 * @return
	 */
	private static boolean testPopFromEmpty1() {
		MazeRunnerStack stack = new MazeRunnerStack();
		Position item; //item popped
		try {
			item = stack.pop(); //pop from an empty stack
		} catch (EmptyStackException e) {
			return true; //expect an error here.
		}
		//if no errors have been caught
		System.out.println("Failure: pop from an empty stack."
				+ " Expected EmptyStackException. Got " 
				+ item.toString() + ".");
		return false; 
	}
	
	/**
	 * First test method for peek(). It checks if the pop() correctly throw an
	 * EmptyStackException when trying to peek from an empty stack.
	 * @return
	 */
	private static boolean testPeekFromEmpty1() {
		MazeRunnerStack stack = new MazeRunnerStack();
		try {
			stack.peek();
		} catch (EmptyStackException e) {
			//expected exception here
			return true;
		}
		//if error not caught
		System.out.println("Failure: peek from an empty stack."
				+ " Expected but did not catch EmptyStackException.");
		return false;
	}
	
	/**
	 * Second test method for peek(). It checks if the method can retrieve the
	 * correct item without modifying the stack.
	 * @return
	 */
	private static boolean testPeek1() {
		MazeRunnerStack stack = new MazeRunnerStack();
		Position newPos; //items peeked
		Position expectedPos = new Position(3, 3);
		//push three items onto the stack
		stack.push(new Position(1, 1));
		stack.push(new Position(2, 2));
		stack.push(new Position(3, 3));
		//peek three times and check if each attempt correctly retireves the item on 
		//the top of the stack 
		for (int i = 0; i < 3; i++) {
			newPos = stack.peek();
			if (!newPos.equals(expectedPos)) {
				System.out.println("Failure: caught error when running testPeek1()."
						+ "peed() method did not fetch the item on the top of the stack "
						+ "without removing it.");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * First test method for isEmpty(), which checks by calling it on an empty stack.
	 * @return
	 */
	private static boolean testIsEmpty1() {
		MazeRunnerStack stack = new MazeRunnerStack();
		if (stack.isEmpty()) { //test if the method return true on an empty stack
			return true;
		} else {
			System.out.println("Failure: caught error when running testIsEmpty1()."
					+ "isEmpty returned false on a empty stack.");
			return false;
		}
	}
	
	/**
	 * First test method for isEmpty(), which checks by calling it on an occupied stack.
	 * @return
	 */
	private static boolean testIsEmpty2() {
		MazeRunnerStack stack = new MazeRunnerStack();
		stack.push(new Position(0, 0)); //add one element
		if (!stack.isEmpty()) { //test if the method return true on an empty stack
			return true;
		} else {
			System.out.println("Failure: caught error when running testIsEmpty2()."
					+ "isEmpty returned true on a occupied stack.");
			return false;
		}
	}
	
	/**
	 * First test method for contains(). It checks by calling the method on an empty stack.
	 * @return
	 */
	private static boolean testContains1() {
		MazeRunnerStack stack = new MazeRunnerStack(); //empty stack
		boolean result; //returned value from contains()
		try {
			result = stack.contains(new Position(0,0));
		} catch (Exception e) {
			System.out.println("Failed: Caught unexpected exception when running testPop1.");
			return false;
		}
		if (result) {
			System.out.println("Failed: Caught unexpected exception when running testContains11."
					+ "Expected false, got true.");
			return false;
		}
		return true; 
	}
	
	/**
	 * Second test method for contains(). It checks by calling the method on an stack
	 * that actually contains the value that is trying to be checked.
	 * @return
	 */
	private static boolean testContains2() {
		MazeRunnerStack stack = new MazeRunnerStack();
		Position newPos; //items to push
		boolean result; //returned value from contains()
		//push three elements
		stack.push(new Position(1, 1));
		stack.push(new Position(2, 2));
		stack.push(new Position(3, 3));
		result = stack.contains(new Position(0,0));
		
		//test searching for an non-existing object
		if (result) {
			System.out.println("Failed: Caught unexpected exception when running testContains2."
					+ "Expected false, got true.");
			return false;
		} 
		//test searching for an existing object
		result = stack.contains(new Position(2,2));
		if (!result) {
			System.out.println("Failed: Caught unexpected exception when running testContains2."
					+ "Expected true, got false.");
			return false;
		}
		for (int i = 3; i > 0; i--) {
			newPos = stack.pop();
			//check if the stack has been changed after calling contains
			if (newPos.row != i) {
				System.out.println("Failed: Caught unexpected exception when running "
						+ "testContains2. The stack has been modified after calling contains");
				return false;
			}
		}
		return true;
	}
}
