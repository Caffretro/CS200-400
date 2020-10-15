//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           TestMineSweeper
// Files:           
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
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This file contains testing methods for the MineSweeper project.
 * These methods are intended to serve several objectives:
 * 1) provide an example of a way to incrementally test your code
 * 2) provide example method calls for the MineSweeper methods
 * 3) provide examples of creating, accessing and modifying arrays
 *    
 * Toward these objectives, the expectation is that part of the  
 * grade for the MineSweeper project is to write some tests and
 * write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments 
 * you feel would be useful.
 * 
 * Some of the provided comments within this file explain
 * Java code as they are intended to help you learn Java.  However,
 * your comments and comments in professional code, should
 * summarize the purpose of the code, not explain the meaning
 * of the specific Java constructs.
 *    
 */

import java.util.Random;
import java.util.Scanner;


/**
 * This class contains a few methods for testing methods in the MineSweeper
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Jim Williams
 * @author Junheng Wang
 *
 */
public class TestMineSweeper {

    /**
     * This is the main method that runs the various tests. Uncomment the tests
     * when you are ready for them to run.
     * 
     * @param args  (unused)
     */
    public static void main(String [] args) {

        // Milestone 1
        //testing the main loop, promptUser and simplePrintMap, since they have
        //a variety of output, is probably easiest using a tool such as diffchecker.com
        //and comparing to the examples provided.
        testEraseMap();
        
        // Milestone 2
        testPlaceMines();
        testNumNearbyMines();
        testShowMines();
        testAllSafeLocationsSwept();
        
        // Milestone 3
        testSweepLocation();
        testSweepAllNeighbors();
        //testing printMap, due to printed output is probably easiest using a 
        //tool such as diffchecker.com and comparing to the examples provided.
    }
    
    /**
     * This is intended to run some tests on the eraseMap method. 
     * 1. creating two 5*5 arrays, one is initialized that every
     * place holds Config.UNSWEPT, another one is initialized 
     * through eraseMap, testing the difference between these two
     * arrays.
     */
    private static void testEraseMap() {
        //Review the eraseMap method header carefully and write
        //tests to check whether the method is working correctly.
        char normalMap[][] = new char[5][5];
        char testMap[][] = new char[5][5];
        int problems = 0;
        for (int i = 0; i < testMap.length; i++) {
            for (int j = 0; j < testMap[i].length; j++) {
                normalMap[i][j] = Config.UNSWEPT;
            }
        }//creating a 5*5 map with all unswept points.
        MineSweeper.eraseMap(testMap);//initializing a map that should be all unswept points
        for (int i = 0; i < testMap.length; i++) {
            for (int j = 0; j < testMap[i].length; j++) {
                if (normalMap[i][j] != testMap[i][j]) {
                    problems++;
                }
            }
        }//count errors
        if (problems != 0) {
            System.out.println(problems + " Error existed in eraseMap.");
        }
        else {
            System.out.println("testEraseMap: passed");
        }
        
    }      
    
    /*
     * Calculate the number of elements in array1 with different values from 
     * those in array2
     */
    private static int getDiffCells(boolean[][] array1, boolean[][] array2) {
        int counter = 0;
        for (int i = 0 ; i < array1.length; i++){
            for (int j = 0 ; j < array1[i].length; j++){
                if (array1[i][j] != array2[i][j]) 
                    counter++;
            }
        }
        return counter;
    }    
    
    /**
     * This runs some tests on the placeMines method. 
     * 1. creating a 5*5 array that holds 3 mines in certain pattern that
     * can be derived through seed 123, then passing seed 123 and an empty
     * 5*5 array to placeMines. Then, check if mine placed are the same amount
     * and at the same location
     */
    private static void testPlaceMines() {
        boolean error = false;
        
        //These expected values were generated with a Random instance set with
        //seed of 123 and MINE_PROBABILITY is 0.2.
        boolean [][] expectedMap = new boolean[][]{
            {false,false,false,false,false},
            {false,false,false,false,false},
            {false,false,false,true,true},
            {false,false,false,false,false},
            {false,false,true,false,false}};
        int expectedNumMines = 3;
            
        Random studentRandGen = new Random( 123);
        boolean [][] studentMap = new boolean[5][5];
        int studentNumMines = MineSweeper.placeMines( studentMap, studentRandGen);
        
        if ( studentNumMines != expectedNumMines) {
            error = true;
            System.out.println("testPlaceMines 1: expectedNumMines=" 
            + expectedNumMines + " studentNumMines=" + studentNumMines);
        }//check if the mine numbers are the same
        
        int diffCells = getDiffCells( expectedMap, studentMap);
        if ( diffCells != 0) {
            error = true;
            System.out.println("testPlaceMines 2: mine map differs.");
        }//check if every place is identical
        
        if (error) {
            System.out.println("testPlaceMines: failed");
        } else {
            System.out.println("testPlaceMines: passed");
        }        
        
    }
    
    /**
     * This runs some tests on the numNearbyMines method. 
     * 1. Still, creating a 5*5 array that holds mines in some places, 
     * then check two locations and see if numNearbyMines is showing the 
     * right number of surrounding mines
     */
    private static void testNumNearbyMines() {
        boolean error = false;

        boolean [][] mines = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,true,true},
            {false,false,false,false,false},
            {false,false,true,false,false}};
        int numNearbyMines = MineSweeper.numNearbyMines( mines, 1, 1);
        
        if ( numNearbyMines != 2) {
            error = true;
            System.out.println("testNumNearbyMines 1: numNearbyMines=" 
            + numNearbyMines + " expected mines=2");
        }//check at location (2,2), if surrounding mines is 2.
        
       numNearbyMines = MineSweeper.numNearbyMines( mines, 2, 1);
        
        if ( numNearbyMines != 0) {
            error = true;
            System.out.println("testNumNearbyMines 2: numNearbyMines=" 
            + numNearbyMines + " expected mines=0");
        }//check at location (3,2), if surrounding mines is 0.

        if (error) {
            System.out.println("testNumNearbyMines: failed");
        } else {
            System.out.println("testNumNearbyMines: passed");
        }
    }
    
    /**
     * This runs some tests on the showMines method. 
     * 1. Creating the same array as the one in the last test method, but 
     * mine numbers differs, then create a same sized char array that holds
     * Config.UNSWEPT in the same location as the mines, then check if all 
     * mines were mapped out or an not existed mine is showed.
     */
    private static void testShowMines() {
        boolean error = false;
        
        boolean [][] mines = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,false,false},
            {false,false,false,false,false},
            {false,false,true,false,false}};
            
        char [][] map = new char[mines.length][mines[0].length];
        map[0][2] = Config.UNSWEPT;
        map[2][1] = Config.UNSWEPT;
        map[4][2] = Config.UNSWEPT;
        
        MineSweeper.showMines( map, mines);
        if ( !(map[0][2] == Config.HIDDEN_MINE && map[2][1] == 
            Config.HIDDEN_MINE && map[4][2] == Config.HIDDEN_MINE)) {
            error = true;
            System.out.println("testShowMines 1: a mine not mapped");
        }//check if there is a mine that is not changed into *.
        if ( map[0][0] == Config.HIDDEN_MINE || map[0][4] == 
            Config.HIDDEN_MINE || map[4][4] == Config.HIDDEN_MINE) {
            error = true;
            System.out.println("testShowMines 2: unexpected showing of mine.");
        }//check if the places that doesn't store a mine was changed into *.
        
        if (error) {
            System.out.println("testShowMines: failed");
        } else {
            System.out.println("testShowMines: passed");
        }        
    }    
    
    /**
     * This is intended to run some tests on the allSafeLocationsSwept method.
     * 1. Still, creating the same mine map and char map as the last test,
     * but one more place that does not contain a mine is labeled Config.UNSWEPT, 
     * then check if allSafeLocationsSwept recognized that one more place and returns
     * correct information
     */
    private static void testAllSafeLocationsSwept() {
        boolean passed = false;
        
        boolean [][] mines = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,false,false},
            {false,false,false,false,false},
            {false,false,true,false,false}};
            
        char map[][] = new char[mines.length][mines[0].length];
        map[0][2] = Config.UNSWEPT;
        map[2][1] = Config.UNSWEPT;
        map[4][2] = Config.UNSWEPT;
        map[2][2] = Config.UNSWEPT;
        //creating a 5*5 map that has 4 unswept locations
        passed = MineSweeper.allSafeLocationsSwept(map, mines);
        //if works fine, passed should store false
        if (passed != false) {
            System.out.println("testAllSafeLocationsSwept: one place is not swept");
        }//test if the method returns false correctly since there is one place unswept
        
        if (passed != true) {
            System.out.println("testAllSafeLocationsSwept: passed");
        }
        else {
            System.out.println("testAllSafeLocationsSwept: failed");
        }
    }      

    
    /**
     * This is intended to run some tests on the sweepLocation method. 
     * 1. Still, creating the same mine map and char map as the last test, then
     * using different arguments to test if the returning number is correct and matches
     * the passed locations' information
     */
    private static void testSweepLocation() {
        int testNum = 0;
        boolean error = false;
        
        boolean [][] mines = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,false,false},
            {false,false,false,false,false},
            {false,false,true,false,false}};
            
        char [][] map = new char[mines.length][mines[0].length];
        map[0][2] = Config.UNSWEPT;
        map[2][1] = Config.UNSWEPT;
        map[4][2] = Config.UNSWEPT;
        map[2][2] = Config.UNSWEPT;
        map[0][0] = ' ';
        
        testNum = MineSweeper.sweepLocation(map, mines, 2, 2);
        if (testNum != 1) {
            System.out.println("testSweepLocation 1: a mine existed");
            error = true;
        }//test if the existing 1 mine is swept correctly
        testNum = MineSweeper.sweepLocation(map, mines, 0, 0);
        if (testNum != -2) {
            System.out.println("testSweepLocation 2: location is swept, should return -2");
            System.out.println(testNum);
            error = true;
        }//test if the method returns -2 that shows the place is swept correctly
        testNum = MineSweeper.sweepLocation(map, mines, 0, 2);
        if (testNum != -1) {
            System.out.println("testSweepLocation 3: should hit the mine, but didn't return -1");
            error = true;
        }//test if the method returns -1 that shows the place holds a mine correctly
        testNum = MineSweeper.sweepLocation(map, mines, 6, 6);
        if (testNum != -3) {
            System.out.println("testSweepLocation 4: outside the map and not returning -3");
            error = true;
        }//test if the method returns -3 that shows the location is outside the map correctly
        
        if (error) {
            System.out.println("testSweepLocation: failed");
        }
        else {
            System.out.println("testSweepLocation: passed");
        }
    }      
    
    /**
     * This is intended to run some tests on the sweepAllNeighbors method. 
     * 1. Creating a mine map as the one in the last test, and same sized char map
     * that holds Config.UNSWEPT everywhere (for convenience). check a special place
     * that with 0 nearby mines, using sweepAllNeighbors, and check if the outcome is correct.
     */
    private static void testSweepAllNeighbors() {
        boolean error = false;
        
        boolean [][] mines = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,false,false},
            {false,false,false,false,false},
            {false,false,true,false,false}};
            
        char [][] map = new char[mines.length][mines[0].length];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = Config.UNSWEPT;
            }
        }//initializing the map with unswept points
        
        MineSweeper.sweepAllNeighbors(map, mines, 2, 3);
        if (map[1][4] != Config.NO_NEARBY_MINE) {
            System.out.println("testSweepAllNeighbors: did not clear the map completely");
            error = true;
        }//test if the place is correctly changed into Config.NO_NEARBY_MINE
        
        if (error) {
            System.out.println("testSweepAllNeighbors: failed");
        }
        else {
            System.out.println("testSweepAllNeighbors: passed");
        }
    }      
}