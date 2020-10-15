//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Drawing Triangle
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
public class DrawRightTriangle {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String userInput = input.next();
		int num = input.nextInt();
		System.out.print("Enter a character: ");
		if(num <= 0 || num >= 11) {
		    System.out.print("Enter triangle height (1-10): ");
		}
		else {
		    System.out.println("Enter triangle height (1-10): ");
		}
		
		while(num <= 0 || num >= 11) {
		    System.out.println("Please enter height between 1 and 10.");
			num = input.nextInt();
			if(num >= 0 && num <= 10) {
			    System.out.println();
			    break;
			}
		}
		for (int i = 1; i <= num; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(userInput + " ");
			}
			System.out.println();
		}

	}

}
