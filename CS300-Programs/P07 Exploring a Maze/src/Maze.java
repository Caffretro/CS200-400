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
public class Maze {
    // this is how we build the map, check if solved, and trace the movements
    private final String vWall = "|";
    private final String hWall = "---";

    private MazeRunnerStack path;
    private Boolean solved;
    private char[][] mazeInfo;
    private String[][] map;
    // following fields are the position tracers
    private Position start;
    private Position current;
    private Position finish;
    private char facing;


    public Maze(char[][] mazeInfo) {
        this.facing = 'r';// default facing is r, since we can go out anyway
        this.mazeInfo = mazeInfo;
        this.map = new String[mazeInfo.length * 2 + 1][mazeInfo[0].length * 2 + 1];
        // map size should be twice the width or height plus one
        this.solved = false;
        for (int k = 0; k < map.length; k++) {
            for (int h = 0; h < map[k].length; h++) {
                if (k % 2 == 0) {
                    if (h % 2 == 0) {
                        map[k][h] = "+";
                    } else {
                        map[k][h] = hWall;
                    }
                } // horizontal walls and +
                else {
                    if (h % 2 == 1) {
                        map[k][h] = "   ";
                    } // spots in the maze
                    else if (h == (map[k].length - 1)) {
                        map[k][map[k].length - 1] = "|";
                    } // right side walls, since mazeInfo doesn't say anything about right side
                }
            }
        } // First, set the boundaries and spaces in the map.
        for (int i = 0; i < mazeInfo.length; i++) {
            for (int j = 0; j < mazeInfo[i].length; j++) {
                switch (mazeInfo[i][j]) {
                    case 'L':
                        map[2 * i + 1][2 * j] = vWall;
                        map[2 * i + 2][2 * j + 1] = hWall;
                        break;
                    case '.':
                        map[2 * i + 1][2 * j] = " ";
                        map[2 * i + 2][2 * j + 1] = "   ";
                        break;
                    case '|':
                        map[2 * i + 1][2 * j] = vWall;
                        map[2 * i + 2][2 * j + 1] = "   ";
                        break;
                    case '_':
                        map[2 * i + 1][2 * j] = " ";
                        map[2 * i + 2][2 * j + 1] = hWall;
                        break;
                }
            }
        } // initializing walls using mazeInfo
    }

    public void setStart(int row, int col) {
        this.map[2 * row + 1][2 * col + 1] = " S ";
        this.start = new Position(row, col);
        // initializing start position and update in the map
    }

    public void setFinish(int row, int col) {
        this.map[2 * row + 1][2 * col + 1] = " F ";
        this.finish = new Position(row, col);
        // initializing finish position and update in the map
    }

    public void displayMaze() {
        Position temp = new Position();
        if (solved) {// when the maze is solved
            map[2 * start.row + 1][2 * start.col + 1] = " S ";
            map[2 * finish.row + 1][2 * finish.col + 1] = " F ";
            System.out.println("Solution is:");
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            // print out the map
            System.out.print("Path is: ");
            for (int i = 0; i < path.getSize() - 1; i++) {
                path.printEntry(i);
            }
            // print out the entry reversely according to the properties of stack
            // printing the last one since there's no arrows
            System.out.println("[" + path.peek().row + "," + path.peek().col + "]");
        } else {// when the maze cannot be solved
            System.out.println("No solution could be found.");
            while (!path.isEmpty()) {
                temp = path.pop();
                map[2 * temp.row + 1][2 * temp.col + 1] = "   ";
            }
            // cleaning the trace, leaving the map with blanks
            map[2 * start.row + 1][2 * start.col + 1] = " S ";
            map[2 * finish.row + 1][2 * finish.col + 1] = " F ";
            // Restoring the start and finish positions
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            // printing out the map
        }
    }

    public void solveMaze() {
        this.path = new MazeRunnerStack();
        path.push(start);
        //set the start position
        this.current = new Position(start.row, start.col);
        //
        Position temp = new Position();
        this.facing = 'r';
        char move = ' ';
        int steps = 0;
        //count how many steps we have stepped through
        String wayOut = "";
        do {
            // following cases are in the sequence of right-hand rule
            wayOut = findWay(current.row, current.col);
            if (facing == 'u' || facing == 'U') {// facing upward
                if (wayOut.contains("R")) {
                    move = 'R';
                    current = turn(move);
                } else if (wayOut.contains("U")) {
                    move = 'S';
                    current = turn(move);
                } else if (wayOut.contains("L")) {
                    move = 'L';
                    current = turn(move);
                } else if (wayOut.contains("D")) {
                    move = 'B';
                    current = turn(move);
                }
            } else if (facing == 'r' || facing == 'R') {
                if (wayOut.contains("D")) {
                    move = 'R';
                    current = turn(move);
                } else if (wayOut.contains("R")) {
                    move = 'S';
                    current = turn(move);
                } else if (wayOut.contains("U")) {
                    move = 'L';
                    current = turn(move);
                } else if (wayOut.contains("L")) {
                    move = 'B';
                    current = turn(move);
                }
            } else if (facing == 'd' || facing == 'D') {
                if (wayOut.contains("L")) {
                    move = 'R';
                    current = turn(move);
                } else if (wayOut.contains("D")) {
                    move = 'S';
                    current = turn(move);
                } else if (wayOut.contains("R")) {
                    move = 'L';
                    current = turn(move);
                } else if (wayOut.contains("U")) {
                    move = 'B';
                    current = turn(move);
                }
            } else if (facing == 'l' || facing == 'L') {
                if (wayOut.contains("U")) {
                    move = 'R';
                    current = turn(move);
                } else if (wayOut.contains("L")) {
                    move = 'S';
                    current = turn(move);
                } else if (wayOut.contains("D")) {
                    move = 'L';
                    current = turn(move);
                } else if (wayOut.contains("R")) {
                    move = 'B';
                    current = turn(move);
                }
            }
            if (path.contains(current)) {
                while (path.contains(current)) {
                    if (!path.peek().equals(current)) {
                        temp = path.pop();
                        map[2 * temp.row + 1][2 * temp.col + 1] = "   ";
                    } else {
                        break;
                    }
                }
                //for clearing redundant steps
            } else {
                map[2 * current.row + 1][2 * current.col + 1] = " * ";
                path.push(current);
            }//recording steps
            steps++;
            // System.out.println("" + path.peek().row + " , " + path.peek().col + " " + facing);
        } while (!current.equals(finish) && steps <= (mazeInfo.length * mazeInfo[0].length * 4));
        //ends either we found a solution or the solution could not be found
        if (path.peek().equals(finish) && steps <= (mazeInfo.length * mazeInfo[0].length * 4)) {
            solved = true;
        } else {
            solved = false;
        }

    }

    public String findWay(int row, int col) {
        //private helper method that returns a String that records what are the ways we can go
        String wayOut = "";
        if (map[row * 2][col * 2 + 1] != hWall) {
            wayOut += "U";
        }
        if (map[row * 2 + 1][col * 2 + 2] != vWall) {
            wayOut += "R";
        }
        if (map[row * 2 + 2][col * 2 + 1] != hWall) {
            wayOut += "D";
        }
        if (map[row * 2 + 1][col * 2] != vWall) {
            wayOut += "L";
        }
        return wayOut;
    }

    public Position turn(char move) {
        //moving the current Position object according to the position and movement
        Position nextStep = new Position(current.row, current.col);
        if (move == 'r' || move == 'R') {//go to the right
            switch (this.facing) {
                case 'u':
                case 'U':
                    this.facing = 'r';
                    nextStep.setPosition(current.row, current.col + 1);
                    break;
                case 'r':
                case 'R':
                    this.facing = 'd';
                    nextStep.setPosition(current.row + 1, current.col);
                    break;
                case 'd':
                case 'D':
                    this.facing = 'l';
                    nextStep.setPosition(current.row, current.col - 1);
                    break;
                case 'l':
                case 'L':
                    this.facing = 'u';
                    nextStep.setPosition(current.row - 1, current.col);
                    break;
            }
        } else if (move == 'l' || move == 'L') {//go to the left
            switch (this.facing) {
                case 'u':
                case 'U':
                    this.facing = 'l';
                    nextStep.setPosition(current.row, current.col - 1);
                    break;
                case 'r':
                case 'R':
                    this.facing = 'u';
                    nextStep.setPosition(current.row - 1, current.col);
                    break;
                case 'd':
                case 'D':
                    this.facing = 'r';
                    nextStep.setPosition(current.row, current.col + 1);
                    break;
                case 'l':
                case 'L':
                    this.facing = 'd';
                    nextStep.setPosition(current.row + 1, current.col);
                    break;
            }
        } else if (move == 's' || move == 'S') {//go straight on
            switch (this.facing) {
                case 'u':
                case 'U':
                    nextStep.setPosition(current.row - 1, current.col);
                    break;
                case 'r':
                case 'R':
                    nextStep.setPosition(current.row, current.col + 1);
                    break;
                case 'd':
                case 'D':
                    nextStep.setPosition(current.row + 1, current.col);
                    break;
                case 'l':
                case 'L':
                    nextStep.setPosition(current.row, current.col - 1);
                    break;
            }
        } else if (move == 'b' || move == 'B') {//go back
            switch (this.facing) {
                case 'u':
                case 'U':
                    this.facing = 'd';
                    nextStep.setPosition(current.row + 1, current.col);
                    break;
                case 'r':
                case 'R':
                    this.facing = 'l';
                    nextStep.setPosition(current.row, current.col - 1);
                    break;
                case 'd':
                case 'D':
                    this.facing = 'u';
                    nextStep.setPosition(current.row - 1, current.col);
                    break;
                case 'l':
                case 'L':
                    this.facing = 'r';
                    nextStep.setPosition(current.row, current.col + 1);
                    break;
            }
        }
        return nextStep;
    }

    public static void main(String[] args) {
        char[][] info = new char[3][3];
        info[0][0] = 'L';
        info[0][1] = '.';
        info[0][2] = '|';
        info[1][0] = 'L';
        info[1][1] = '|';
        info[1][2] = '|';
        info[2][0] = 'L';
        info[2][1] = '_';
        info[2][2] = '_';
        Maze test = new Maze(info);
        test.setStart(0, 0);
        test.setFinish(0, 2);
        test.solveMaze();
        test.displayMaze();

    }

}

