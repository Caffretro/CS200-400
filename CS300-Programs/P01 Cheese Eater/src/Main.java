//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Cheese Eater
// Files:           None
// Course:          CS300 Spring 2018
//
// Author:          Junheng Wang
// Email:           jwang922@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Lecturer's Name: (name of your partner's lecturer)
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
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int CHEESE_NUMBER = 10;
        int WALL_NUMBER = 20;
        int ROOM_HEIGHT = 10;
        int ROOM_WIDTH = 20;
        int mouseX = 0;
        int mouseY = 0;
        int moves = 0;
        int movedSteps = 0;
        char instruction = ' ';
        int cheeseEaten = 0;
        Random randGen = new Random();
        int[] mouseCoordinates = new int[2];
        char[][] roomMap = new char[ROOM_HEIGHT][ROOM_WIDTH];//creating the map
        int[][] cheeseCoordinates = new int[CHEESE_NUMBER][2];//Declaring the coordinates for cheese
        System.out.println("Welcome to the Cheese Eater simulation.");  
        System.out.println("=======================================");
        System.out.println("Enter the number of steps for this simulation to run: ");
        moves = scnr.nextInt();
        placeWalls(roomMap, WALL_NUMBER, randGen);
        placeCheeses(cheeseCoordinates, roomMap, randGen);
        placeMouse(mouseX, mouseY, roomMap, cheeseCoordinates, randGen);
        for (int i = 0; i < roomMap.length; i++) {
            for (int j = 0; j < roomMap[i].length; j++) {
                if (roomMap[i][j] == '@') {
                    mouseY = i;
                    mouseX = j;
                    break;
                }
                else {
                    continue;
                }
            }
        }//get the position of the mouse.
        printRoom(roomMap, cheeseCoordinates, mouseX, mouseY);
        do {
            System.out.println("Enter the next step you'd like the mouse to take (WASD): ");
            System.out.println();
            instruction = scnr.next().charAt(0);//prompt the user to enter
            mouseCoordinates = moveMouse(mouseX, mouseY, roomMap, instruction);
            if (mouseCoordinates != null) {
                mouseX = mouseCoordinates[0];
                mouseY = mouseCoordinates[1];
                if (tryToEatCheese(mouseX, mouseY, cheeseCoordinates)) {
                    cheeseEaten++;
                }
            }//count the cheese eaten
            
            System.out.println("The mouse has eaten " + cheeseEaten + " cheese!");
            printRoom(roomMap, cheeseCoordinates, mouseX, mouseY);
            if (mouseCoordinates != null) {
                movedSteps++;
            }//check if the move was valid
        } while(movedSteps < moves);
        System.out.println("==================================================");
        System.out.print("Thank you for running the Cheese Eater simulation.");
        scnr.close();
    }
    public static void placeWalls(char[][] room, int numberOfWalls, Random randGen) {
        int existingWalls = 0;
        int roomSpace = 0;
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                room[i][j] = '.';
                roomSpace++;
            }
        }//count the number of empty spaces, in this case also the room space
        do {
            for (int i = 0; i < room.length; i++) {
                for (int j = 0; j < room[i].length; j++) {
                    if (existingWalls < numberOfWalls) {
                        if (randGen.nextInt(roomSpace / numberOfWalls) < 1) {
                            room[i][j] = '#';
                            existingWalls++;
                        }
                    }
                    else {
                        break;
                    }
                }
            }
        }while(existingWalls < numberOfWalls);//place walls
    }
    public static void placeCheeses(int[][] cheesePositions, char[][] room, Random randGen) {
        int existingCheese = 0;
        int cheeseX = 0;
        int cheeseY = 0;
        
        do {
            cheeseX = randGen.nextInt(room[0].length);
            cheeseY = randGen.nextInt(room.length);
            if (room[cheeseY][cheeseX] == '.') {
                room[cheeseY][cheeseX] = '%';//update cheese to the map so that placemouse is easier
                cheesePositions[existingCheese][0] = cheeseX;
                cheesePositions[existingCheese][1] = cheeseY;
                existingCheese++;
            }
        } while(existingCheese < cheesePositions.length);//placing cheeses and store their info
    }
    public static void placeMouse(int mouseX, int mouseY, char[][] room, int[][] cheesePosition, Random randGen) {
        int mouseCreated = 0;
        do {
            mouseX = randGen.nextInt(room[0].length);
            mouseY = randGen.nextInt(room.length);
            if (room[mouseY][mouseX] == '.') {
                room[mouseY][mouseX] = '@';
                mouseCreated++;
            }
        } while(mouseCreated < 1);//place the mouse
    }
    public static void printRoom(char[][] room, int[][] cheesePositions, int mouseX, int mouseY) {
        for (int k = 0; k < cheesePositions.length; k++) {
            if (cheesePositions[k][0] >= 0 && cheesePositions[k][1] >= 0) {
                room[cheesePositions[k][1]][cheesePositions[k][0]] = '%';
            }
            else {
                continue;
            }
        }//cheeses are the second important things
        room[mouseY][mouseX] = '@';//mouse takes the first priority
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                System.out.print(room[i][j]);
            }
            System.out.println();
        }
        
    }
    public static int[] moveMouse(int mouseX, int mouseY, char[][] room, char move) {
        int[] mousePosition = new int[2];
        int mouseXOrigin = mouseX;
        int mouseYOrigin = mouseY;
        if (move != 'w' && move != 'W' && move != 'a' && move != 'A' &&
            move != 's' && move != 'S' && move != 'd' && move != 'D') {
            System.out.println("WARNING: Didn't recognize move command: " + move);
            mousePosition[0] = mouseXOrigin;
            mousePosition[1] = mouseYOrigin;
            return null;
        }
        else {
            switch (move) {
                case 'w':
                case 'W':
                    mouseY--;
                    break;
                case 'a':
                case 'A':
                    mouseX--;
                    break;
                case 's':
                case 'S':
                    mouseY++;
                    break;
                case 'd':
                case 'D':
                    mouseX++;
                    break;
            }
            if (mouseX < 0 || mouseX >= room[0].length || mouseY < 0 || mouseY >= room.length) {
                System.out.println("WARNING: Mouse cannot move outside the room.");
                mousePosition[0] = mouseXOrigin;
                mousePosition[1] = mouseYOrigin;
                return null;
            }
            else if (room[mouseY][mouseX] == '#') {
                System.out.println("WARNING: Mouse cannot move into wall.");
                mousePosition[0] = mouseXOrigin;
                mousePosition[1] = mouseYOrigin;
                return null;
            }
            else {
                switch (move) {
                    case 'w':
                    case 'W':
                        room[mouseY][mouseX] = '@';
                        room[mouseYOrigin][mouseXOrigin] = '.';
                        break;
                    case 'a':
                    case 'A':
                        room[mouseY][mouseX] = '@';
                        room[mouseYOrigin][mouseXOrigin] = '.';
                        break;
                    case 's':
                    case 'S':
                        room[mouseY][mouseX] = '@';
                        room[mouseYOrigin][mouseXOrigin] = '.';
                        break;
                    case 'd':
                    case 'D':
                        room[mouseY][mouseX] = '@';
                        room[mouseYOrigin][mouseXOrigin] = '.';
                        break;
                }
                mousePosition[0] = mouseX;
                mousePosition[1] = mouseY;
                return mousePosition;
            }//update the mouse's position and erase its trace
        }
    }
    public static boolean tryToEatCheese(int mouseX, int mouseY, int[][] cheesePositions) {
        boolean cheeseEaten = false;
        for (int i = 0; i < cheesePositions.length; i++) {
            if (cheesePositions[i][0] == mouseX && cheesePositions[i][1] == mouseY) {
                cheesePositions[i][0] = -1;
                cheesePositions[i][1] = -1;
                cheeseEaten = true;
            }
        }//send the cheese to another universe
        return cheeseEaten;
    }
}
