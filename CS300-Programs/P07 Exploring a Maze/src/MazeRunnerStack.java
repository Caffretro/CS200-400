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

public class MazeRunnerStack implements StackADT<Position> {
    private Position[] list;
    private int size;

    // No-arg constructor
    public MazeRunnerStack() {
        this.list = new Position[100];
        this.size = 0;
    }

    

    public int getSize() {
        return this.size;
    }

    @Override
    public void push(Position item) {
        Position temp;
        // for moving the entries in the list to the back
        try {
            if (item == null) {
                throw new IllegalArgumentException();
            } else {
                if (size == 0) {// while the stack is empty
                    list[0] = item;
                    size++;
                } else {
                    if (size == list.length) {// while the list cannot hold more Position
                        Position[] biggerList = new Position[size * 2];
                        for (int i = 0; i < list.length; i++) {
                            biggerList[i + 1] = list[i];
                        }
                        biggerList[0] = item;
                        list = biggerList;
                        size++;
                    } else {// regular case
                        for (int i = size - 1; i >= 0; i--) {
                            temp = list[i];
                            list[i + 1] = temp;
                        } // move to the right to create room for item
                        list[0] = item;
                        size++;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();// FIXME
        }

    }

    @Override
    public Position pop() throws EmptyStackException {

        if (size == 0 || list.length == 0) {//while the stack is empty, throw exception
            throw new EmptyStackException();
        } else {
            Position popItem = list[0];
            for (int i = 0; i < size - 1; i++) {
                list[i] = list[i + 1];
            }//move the list to the left
            list[size - 1] = null;//change the last one to null
            size--;
            return popItem;
        }

    }

    @Override
    public Position peek() throws EmptyStackException {
        if (size == 0 || list.length == 0) {//while the stack is empty
            throw new EmptyStackException();
        } else {
            return list[0];
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Position p) {
        for (int i = 0; i < size; i++) {
            if (list[i] != null && list[i].equals(p)) {
                return true;
            }
        }
        return false;
    }

    // Reports whether the Position p can be found within the stack
    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.println(list[i].row + "," + list[i].col);
        }
    }

    public void printEntry(int index) {
        System.out
            .print("[" + list[size - index - 1].row + "," + list[size - index - 1].col + "] --> ");
    }
}


class Position {
    int col;
    int row;

    Position() {
        this.col = 0;
        this.row = 0;
    }

    Position(int row, int col) {
        this.col = col;
        this.row = row;
    }

    boolean equals(Position other) {
        return this.col == other.col && this.row == other.row;
    }

    void setPosition(int row, int col) {
        this.col = col;
        this.row = row;
    }
}
