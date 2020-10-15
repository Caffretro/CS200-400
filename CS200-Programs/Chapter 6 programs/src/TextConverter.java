//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Text
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
public class TextConverter {
    /**
     * 1337 - convert the string to leet-speak:
     *   Replace each L or l with a 1 (numeral one)
     *   Replace each E or e with a 3 (numeral three)
     *   Replace each T or t with a 7 (numeral seven)
     *   Replace each O or o with a 0 (numeral zero)
     *   Replace each S or s with a $ (dollar sign)
     *    
     * @param current Original string
     * @return string converted to leet-speak.
     */
    public static String action1337(String current) {
            String changedVersion = "";
            changedVersion = current;
            changedVersion = changedVersion.replace("L", "1");
            changedVersion = changedVersion.replace("l", "1");
            changedVersion = changedVersion.replace("E", "3");
            changedVersion = changedVersion.replace("e", "3");
            changedVersion = changedVersion.replace("T", "7");
            changedVersion = changedVersion.replace("t", "7");
            changedVersion = changedVersion.replace("O", "0");
            changedVersion = changedVersion.replace("o", "0");
            changedVersion = changedVersion.replace("S", "$");
            changedVersion = changedVersion.replace("s", "$");
            return changedVersion;
    }
    /**
     * This reverses the order of characters in the current string. 
     * @param current  Original string to be reversed.
     * @return  The string in reversed order.
     */
    public static String actionReverse(String current) {
            String changedVersionRev = "";
            int length;
            length = current.length();
            for (int i = length - 1; i >= 0; i--) {
                changedVersionRev = changedVersionRev + current.charAt(i);
            }
            return changedVersionRev;
    }
    /**
     * Provides the main menu for the text converter and calls methods based
     * on menu options chosen.
     * @param args
     */

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String input = "";
        String action = "";
            
        System.out.println("Welcome to the Text Converter.");
        System.out.println("Available Actions:");
        System.out.println("  1337) convert to 1337-speak");
        System.out.println("  rev) reverse the string");
        System.out.println("  quit) exit the program");
        System.out.println();
        System.out.print("Please enter a string: ");
        
        input = scnr.nextLine();
        action = scnr.nextLine();
        
            while(!action.equals("quit")) {
            System.out.print("Action (1337, rev, quit): ");
            
            if (action.equals("1337")) {
                input = action1337(input);
                System.out.println(input);
            }
            else if (action.equals("rev")) {
                input = actionReverse(input);
                System.out.println(input);
            }
            else {
                System.out.println("Unrecognized action.");
            }
            action = scnr.nextLine();
            
            }
            System.out.println(input);
        if (action.equals("quit")) {
            System.out.println("Action (1337, rev, quit): See you next time!");
        }
        
    }

}
