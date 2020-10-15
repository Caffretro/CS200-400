//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           MineSweeper
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
 * This is a minesweeper! Just like the minesweeper in your windows games, every
 * game turn you design your own capacity of map, and there will be mines underneath
 * the map. You have to carefully test each location, and utilize the number showed on 
 * the map to determine where the mines are. While you have explored all the places
 * without mines, you win!
 **/
import java.util.Random;
import java.util.Scanner;
public class MineSweeper {
    
    /**
     * This is the main method for Mine Sweeper game!
     * This method contains the within game and play again loops and calls
     * the various supporting methods.
     *  
     * @param args (unused)
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Mine Sweeper!");
        Scanner scnr = new Scanner(System.in);
        String promptText = "";
        int width = 0;
        int height = 0;
        int row = 0;
        int column = 0;
        int mineNum = 0;
        int nearbyMines = 0;
        char nearbyNum = ' ';
        String keepPlaying = "";
        boolean gameEnds = false;
        Random randGen = new Random(Config.SEED);
        
        do {
            promptText = "Expected a number from " + Config.MIN_SIZE + 
                " to " + Config.MAX_SIZE + ".";
            System.out.print("What width of map would you like (" + 
                Config.MIN_SIZE + " - " + Config.MAX_SIZE + "): ");
            width = promptUser(scnr, promptText, Config.MIN_SIZE, Config.MAX_SIZE);
            System.out.print("What height of map would you like (" + 
                Config.MIN_SIZE + " - " + Config.MAX_SIZE + "): ");
            height = promptUser(scnr, promptText, Config.MIN_SIZE, Config.MAX_SIZE);//Opening
           
            boolean [][] mineMap = new boolean[height][width];
            mineNum = placeMines(mineMap, randGen);//initializing the mineMap
            
            System.out.println();
            System.out.println("Mines: " + mineNum);
            char[][] map = new char[height][width];
            eraseMap(map);
            printMap(map);//showing the game map to players
            
            do {
                System.out.print("row: ");
                promptText = "Expected a number from " + 1 + " to " + height + ".";
                row = promptUser(scnr, promptText, 1, height) - 1;
                //change into array index, easy for coding
                System.out.print("column: ");
                promptText = "Expected a number from " + 1 + " to " + width + ".";
                column = promptUser(scnr, promptText, 1, width) - 1;
                //change into array index, easy for coding
                //getting user's pointed location and remind them if the location is not valid
                if (mineMap[row][column] == true) {
                    map[row][column] = Config.SWEPT_MINE;
                    showMines(map, mineMap);
                    printMap(map);
                    gameEnds = true;
                    System.out.println("Sorry, you lost.");
                }//while the user touches mines, end the game
                else {
                    nearbyMines = sweepLocation(map, mineMap, row, column);
                    if (nearbyMines == 0) {
                        sweepAllNeighbors(map, mineMap, row, column);
                    }//while the place has 0 nearby mines, do the same check on the surrounding 8 places
                    gameEnds = allSafeLocationsSwept(map, mineMap);//if all non-mine locations are swept
                    if(gameEnds == false) {
                        System.out.println();
                        System.out.println("Mines: " + mineNum);
                        printMap(map);
                    }//while the user hasn't found all the mines, keep gaming
                    else {
                        showMines(map, mineMap);
                        printMap(map);
                        System.out.println("You Win!");
                    }//while the user found all the mines, end the game
                         
                    
                }
            } while(gameEnds == false);
            System.out.print("Would you like to play again (y/n)? ");//ask the player if he/she wants to keep gaming
            keepPlaying = scnr.next();
        } while(keepPlaying.charAt(0) == 'y' || keepPlaying.charAt(0) == 'Y');
        System.out.println("Thank you for playing Mine Sweeper!");//game ends
    }


    /**
     * This method prompts the user for a number, verifies that it is between min
     * and max, inclusive, before returning the number.  
     * 
     * If the number entered is not between min and max then the user is shown 
     * an error message and given another opportunity to enter a number.
     * If min is 1 and max is 5 the error message is:
     *      Expected a number from 1 to 5.  
     * 
     * If the user enters characters, words or anything other than a valid int then 
     * the user is shown the same message.  The entering of characters other
     * than a valid int is detected using Scanner's methods (hasNextInt) and
     * does not use exception handling.
     * 
     * Do not use constants in this method, only use the min and max passed
     * in parameters for all comparisons and messages.
     * Do not create an instance of Scanner in this method, pass the reference
     * to the Scanner in main, to this method.
     * The entire prompt should be passed in and printed out.
     *
     * @param in  The reference to the instance of Scanner created in main.
     * @param prompt  The text prompt that is shown once to the user.
     * @param min  The minimum value that the user must enter.
     * @param max  The maximum value that the user must enter.
     * @return The integer that the user entered that is between min and max, 
     *          inclusive.
     */
    public static int promptUser(Scanner in, String prompt, int min, int max) {
        int userInput = 0;
        boolean haveValidNumber = false;
        do {
            if(in.hasNextInt()) {
                userInput = in.nextInt();
                in.nextLine();
                if (userInput >= min && userInput <= max) {
                    haveValidNumber = true;
                }
                else {
                    System.out.println(prompt);
                }
            }
            else {
                in.nextLine();
                System.out.println(prompt);
            }
        } while(!haveValidNumber);
            
        return userInput; 
    }

    /**
     * This initializes the map char array passed in such that all
     * elements have the Config.UNSWEPT character.
     * Within this method only use the actual size of the array. Don't
     * assume the size of the array.
     * This method does not print out anything. This method does not
     * return anything.
     * 
     * @param map An allocated array. After this method call all elements
     *      in the array have the same character, Config.UNSWEPT. 
     */
    public static void eraseMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Config.UNSWEPT;
            }
        }
        return;
    }    

    /**
     * This prints out a version of the map without the row and column numbers.
     * A map with width 4 and height 6 would look like the following: 
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     * For each location in the map a space is printed followed by the 
     * character in the map location.
     * @param map The map to print out.
     */
    public static void simplePrintMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
            System.out.print(" " + map[i][j]);    
            }
            System.out.println();
        }
        return;
    }

    /**
     * This prints out the map. This shows numbers of the columns
     * and rows on the top and left side, respectively. 
     * map[0][0] is row 1, column 1 when shown to the user.
     * The first column, last column and every multiple of 5 are shown.
     * 
     * To print out a 2 digit number with a leading space if the number
     * is less than 10, you may use:
     *     System.out.printf("%2d", 1); 
     * 
     * @param map The map to print out.
     */
    public static void printMap(char[][] map) {
        String [][] enhancedMap = new String[map.length + 1][map[0].length + 1];
        enhancedMap[0][0] = " ";
        enhancedMap[0][1] = "1";
        enhancedMap[1][0] = "1";//special cases
        for (int i = 2; i < enhancedMap[0].length; i++) {//horizontal ruler
            if (i / 5 == 0) {
                if (i < (enhancedMap[0].length - 1)) {
                    enhancedMap[0][i] = "--";
                }
                else {
                    enhancedMap[0][i] = "" + i;
                }
            }//under the situation that map width is less than 5.
            else {
                if (i % 5 == 0) {
                    enhancedMap[0][i] = "" + i;
                }
                else if (i == map[0].length){
                    enhancedMap[0][i] = "" + i;
                }//last Number
                else{
                    enhancedMap[0][i] = "--";
                }
            }//general situation after 5.
        }
        for (int i = 2; i < enhancedMap.length; i++) {//vertical ruler
            if (i / 5 == 0) {
                if (i < (enhancedMap.length - 1)) {
                    enhancedMap[i][0] = "|";
                }
                else {
                    enhancedMap[i][0] = "" + i;
                }
            }//under the situation that map height is less than 5.
            else {
                if (i % 5 == 0) {
                    enhancedMap[i][0] = "" + i;
                }
                else if (i == map.length) {
                    enhancedMap[i][0] = i + "";
                }
                else {
                    enhancedMap[i][0] = "|";
                }
            }//general situation after 5.
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                enhancedMap[i + 1][j + 1] = "" + map[i][j];
            }
        }//completing the map with original value
        
        for (int i = 0; i < enhancedMap[0].length; i++) {
            if (enhancedMap[0][i].equals("--")) {
                System.out.print(enhancedMap[0][i]);
            }//special case when no space needed
            else if (i >= 10) {
                System.out.print(enhancedMap[0][i]);
            }//special case when no space needed
            else {
                System.out.print(" " + enhancedMap[0][i]);
            }
        }
        System.out.println();
        for (int i = 1; i < enhancedMap.length; i++) {
            for (int j = 0; j < enhancedMap[i].length; j++) {
                if (((i % 5 == 0 && i >= 10) || (i == map.length && i >= 10)) && j == 0) {
                    System.out.print(enhancedMap[i][j]);
                }//special case when no space needed
                else if (j == 0 && (i < 10 && i == map.length)) {
                    System.out.print(" " + enhancedMap[i][j]);
                }//special case when space needed(when the length of vertical ruler is less than 10)
                else {
                    System.out.print(" " + enhancedMap[i][j]);
                }//general case
            }
            System.out.println();
        }
        return; 
    }

    /**
     * This method initializes the boolean mines array passed in. A true value for
     * an element in the mines array means that location has a mine, false means
     * the location does not have a mine. The MINE_PROBABILITY is used to determine
     * whether a particular location has a mine. The randGen parameter contains the
     * reference to the instance of Random created in the main method.
     * 
     * Access the elements in the mines array with row then column (e.g., mines[row][col]).
     * 
     * Access the elements in the array solely using the actual size of the mines
     * array passed in, do not use constants. 
     * 
     * A MINE_PROBABILITY of 0.3 indicates that a particular location has a
     * 30% chance of having a mine.  For each location the result of
     *      randGen.nextFloat() < Config.MINE_PROBABILITY 
     * determines whether that location has a mine.
     * 
     * This method does not print out anything.
     *  
     * @param mines  The array of boolean that tracks the locations of the mines.
     * @param randGen The reference to the instance of the Random number generator
     *      created in the main method.
     * @return The number of mines in the mines array.
     */
    public static int placeMines(boolean[][] mines, Random randGen) {
        int mineNum = 0;
        for (int i = 0; i < mines.length; i++) {
            for (int j = 0; j < mines[i].length; j++) {
                if (randGen.nextFloat() < Config.MINE_PROBABILITY) {
                    mines[i][j] = true;
                    mineNum += 1;
                }
            }
        }
        //placing mines in mineMap
        return mineNum;
    }

    /**
     * This method returns the number of mines in the 8 neighboring locations.
     * For locations along an edge of the array, neighboring locations outside of 
     * the mines array do not contain mines. This method does not print out anything.
     * 
     * If the row or col arguments are outside the mines array, then return -1.
     * This method (or any part of this program) should not use exception handling.
     * 
     * @param mines The array showing where the mines are located.
     * @param row The row, 0-based, of a location.
     * @param col The col, 0-based, of a location.
     * @return The number of mines in the 8 surrounding locations or -1 if row or col
     *      are invalid.
     */
    public static int numNearbyMines( boolean [][]mines, int row, int col) {
        int surrounding = 0;
        if (((0 <= row) && (row <= (mines.length - 1))) 
            && ((0 <= col) && (col <= (mines[0].length - 1)))) {
            //see if the point is in the map
            if ((0 < row) && (row < (mines.length - 1))) {     //height in the middle
                if ((0 < col) && (col < (mines[0].length - 1))) {     //width in the middle
                    for (int i = 0; i <= 2; i++) {
                        for (int j = 0; j <= 2; j++) {
                            if (i == 1 && j == 1) {
                                continue;
                            }
                            if (mines[row - 1 + i][col - 1 + j] == true) {
                                surrounding += 1;
                            }
                        }
                    }
                }
            }//point in the middle
            if (0 < row && row < (mines.length - 1)) {    //height in the middle
                if (col == 0) {    //width in the left edge
                    for (int i = 0; i <= 2; i++) {
                        for (int j = 0; j <= 1; j++) {
                            if (i == 1 && j == 0) {
                                continue;
                            }
                            if (mines[row - 1 + i][col + j] == true) {
                                surrounding += 1;
                            }
                        }
                    }
                }
                else if (col == (mines[0].length - 1)) {    //width in the right edge
                    for (int i = 0; i <= 2; i++) {
                        for (int j = 0; j <= 1; j++) {
                            if (i == 1 && j == 1) {
                                continue;
                            }
                            if (mines[row - 1 + i][col - 1 + j] == true) {
                                surrounding += 1;
                            }
                        }
                    }
                }
            }//point in the left and right side (except for corners)
            if (0 < col && col < (mines[0].length - 1)) {    //width in the middle
                if (row == 0) {    //height in the upper edge
                    for (int i = 0; i <= 1; i++) {
                        for (int j = 0; j <= 2; j++) {
                            if (i == 0 && j == 1) {
                                continue;
                            }
                            if (mines[row + i][col - 1 + j] == true) {
                                surrounding += 1;
                            }
                        }
                    }
                }
                else if(row == (mines.length - 1)) {    //height in the bottom edge
                    for (int i = 0; i <= 1; i++) {
                        for (int j = 0; j <= 2; j++) {
                            if (i == 1 && j == 1) {
                                continue;
                            }
                            if (mines[row - 1 + i][col - 1 + j] == true) {
                                surrounding += 1;
                            }
                        }
                    }
                }
            }//point in the upper and bottom edge (except for corners)
            if (row == 0) {    //upper two corners
                if (col == 0) {    //upper-left corner
                    for (int i = 0; i <= 1; i++) {
                        for (int j = 0; j <= 1; j++) {
                            if (i == 0 && j == 0) {
                                continue;
                            }
                            if (mines[row + i][col + j] == true) {
                                surrounding += 1;
                            }
                        }
                    }
                }
                else if (col == (mines[0].length - 1)) {
                    for (int i = 0; i <= 1; i++) {
                        for (int j = 0; j <= 1; j++) {    //upper-right corner
                            if (i == 0 && j == 1) {
                                continue;
                            }
                            if (mines[row + i][col - 1 + j] == true) {
                                surrounding += 1;
                            }
                        }
                    }
                }
            }//point in the upper corners
            if (row == (mines.length - 1)) {    //bottom two corners
                if (col == 0) {    //bottom-left corner
                    for (int i = 0; i <= 1; i++) {
                        for (int j = 0; j <= 1; j++) {
                            if (i == 1 && j == 0) {
                                continue;
                            }
                            if (mines[row - 1 + i][col + j] == true) {
                                surrounding += 1;
                            }
                        }
                    }
                }
                else if (col == (mines[0].length - 1)) {
                    for (int i = 0; i <= 1; i++) {
                        for (int j = 0; j <= 1; j++) {    //bottom-right corner
                            if (i == 1 && j == 1) {
                                continue;
                            }
                            if (mines[row - 1 + i][col - 1 + j] == true) {
                                surrounding += 1;
                            }
                        }
                    }
                }
            }//point in the bottom corners
        }
        else {
            surrounding = -1;
        }
        return surrounding;
    }

    /**
     * This updates the map with each unswept mine shown with the Config.HIDDEN_MINE
     * character. Swept mines will already be mapped and so should not be changed.
     * This method does not print out anything.
     * 
     * @param map  An array containing the map. On return the map shows unswept mines.
     * @param mines An array indicating which locations have mines.  No changes
     *      are made to the mines array.
     */
    public static void showMines(char[][] map, boolean[][] mines) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (mines[i][j] == true) {
                    if (map[i][j] == Config.UNSWEPT) {
                        map[i][j] = Config.HIDDEN_MINE;
                    }
                }
            }
        }
        //while game ends, show all hidden mines
        return; 
    }

    /**
     * Returns whether all the safe (non-mine) locations have been swept. In 
     * other words, whether all unswept locations have mines. 
     * This method does not print out anything.
     * 
     * @param map The map showing touched locations that is unchanged by this method.
     * @param mines The mines array that is unchanged by this method.
     * @return whether all non-mine locations are swept.
     */
    public static boolean allSafeLocationsSwept(char[][] map, boolean[][] mines) {
        boolean minesSwept = false;
        int bombs = 0;
        for (int i = 0; i < mines.length; i++) {
            for (int j = 0; j < mines[0].length; j++) {
                if (mines[i][j] == true) {
                    bombs += 1;
                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == Config.UNSWEPT) {
                    bombs -= 1;
                }
            }
        }
        //comparing the difference of all unswept locations and mines
        if (bombs == 0) {
            minesSwept = true;
        }
        //returning information on if all safe locations are swept
        return minesSwept;

    }

    /**
     * This method sweeps the specified row and col.
     *   - If the row and col specify a location outside the map array then 
     *     return -3 without changing the map.
     *   - If the location has already been swept then return -2 without changing
     *     the map.
     *   - If there is a mine in the location then the map for the corresponding
     *     location is updated with Config.SWEPT_MINE and return -1.
     *   - If there is not a mine then the number of nearby mines is determined 
     *     by calling the numNearbyMines method. 
     *        - If there are 1 to 8 nearby mines then the map is updated with 
     *          the characters '1'..'8' indicating the number of nearby mines.
     *        - If the location has 0 nearby mines then the map is updated with
     *          the Config.NO_NEARBY_MINE character.
     *        - Return the number of nearbyMines.
     *        
     * @param map The map showing swept locations.
     * @param mines The array showing where the mines are located.
     * @param row The row, 0-based, of a location.
     * @param col The col, 0-based, of a location.
     * @return the number of nearby mines, -1 if the location is a mine, -2 if 
     * the location has already been swept, -3 if the location is off the map.
     */
    public static int sweepLocation(char[][] map, boolean[][] mines, int row, int col) {
        int indicator = 0;//Remember: row and col are already changed into 0 based parameters in main
        
        if ((row < 0 || row > map.length - 1) || (col < 0 || col > map[0].length - 1)) {
            indicator = -3;
        }//when the chosen point is outside the map
        else {//following cases are all in the map, avoiding OutOfBoundsException
            if (!(map[row][col] == Config.UNSWEPT)) {
                indicator = -2;
            }//when the point was swept
            else {
                if (mines[row][col] == true) {
                    map[row][col] = Config.SWEPT_MINE;
                    indicator = -1;
                }//when the point has a mine, boom
                if (mines[row][col] == false) {
                    indicator = numNearbyMines(mines, row, col);
                    if (indicator != 0) {
                        map[row][col] = (char)('0' + indicator);
                    }
                    else {
                        map[row][col] = Config.NO_NEARBY_MINE;
                    }
                }
            }
        }
        return indicator;
    }

    /**
     * This method iterates through all 8 neighboring locations and calls sweepLocation
     * for each. It does not call sweepLocation for its own location, just the neighboring
     * locations.
     * @param map The map showing touched locations.
     * @param mines The array showing where the mines are located.
     * @param row The row, 0-based, of a location.
     * @param col The col, 0-based, of a location.
     */
    public static void sweepAllNeighbors(char [][]map, 
        boolean[][] mines, int row, int col) {
        int keepSweeping = 0;
        //keepSweeping = sweepLocation(map, mines, row, col);
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col) {
                    continue;
                }//not checking the point itself
                keepSweeping = sweepLocation(map, mines, i, j);
                if (keepSweeping < 0) {
                    continue;
                }
                
                
              
            }
        }
        return;
    }
}