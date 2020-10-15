//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Dorm Designer
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
public class Main {

    public static void main(String[] args) {
        
        
        Utility.startApplication();
        PImage bedImage;
        PImage backgroundImage;//declare them in main, use them in the methods
        
        
        
    }
    public static void setup(Data data) {
        
        
        data.backgroundImage = data.processing.loadImage("images/background.png");
        //load the background
        data.bedImage = data.processing.loadImage("images/bed.png");//load bed image
        data.bedPositions = new float[6][2];
        for (int i = 1; i < data.bedPositions.length; i++) {
            data.bedPositions[i] = null;
        }//create a float 2D array to store beds and positions
        data.dragBedIndex = -1;
        data.bedPositions[0][0] = data.processing.mouseX;
        data.bedPositions[0][1] = data.processing.mouseY;//the first bed follows the mouse
        
        
        
    }
    
    public static void update(Data data) {
        data.processing.background(100,150,250);
        data.processing.image(data.backgroundImage, data.processing.width/2, data.processing.height/2);
        //refreshes the background
        int beds = 0;
        for (int j = 0; j < data.bedPositions.length; j++) {
            if (data.bedPositions[j] != null) {
                beds++;
            }
        }//count how many beds are there
        for (int i = 0; i < beds; i++) {
            if (data.dragBedIndex == i) {
                data.bedPositions[i][0] = data.processing.mouseX;
                data.bedPositions[i][1] = data.processing.mouseY;
                data.processing.image(data.bedImage, data.bedPositions[data.dragBedIndex][0],
                             data.bedPositions[data.dragBedIndex][1]);
            
            }// if the mouse is dragging the bed, move it
            else {
                data.processing.image(data.bedImage, data.bedPositions[i][0], data.bedPositions[i][1]);
            }//the rest beds stays where they are
        }
        
    }
    public static void mouseDown(Data data) {
        int beds = 0;
        for (int j = 0; j < data.bedPositions.length; j++) {
            if (data.bedPositions[j] != null) {
                beds++;
            }
        }//still, count how many beds are there
        for (int i = 0; i < beds; i++) {
            if (data.processing.mouseX < (data.bedPositions[i][0] + data.bedImage.width)&&
                data.processing.mouseX > (data.bedPositions[i][0] - data.bedImage.width)&&
                data.processing.mouseY < (data.bedPositions[i][1] + data.bedImage.height)&&
                data.processing.mouseY > (data.bedPositions[i][1] - data.bedImage.height)) {
                    data.dragBedIndex = i;
            }//determine whether the mouse is on the bed
            
        }
    }
    public static void mouseUp(Data data) {
        data.dragBedIndex = -1;//easy, just reset dragBedIndex
    }
    public static void keyPressed(Data data) {
        int beds = 0;
        for (int j = 0; j < data.bedPositions.length; j++) {
            if (data.bedPositions[j] != null) {
                beds++;
            }
        }//again, count how many beds are there
        if (beds < 6) {
            data.bedPositions[beds] = new float[2];
            data.bedPositions[beds][0] = data.processing.width/2;
            data.bedPositions[beds][1] = data.processing.height/2;
        }//if less than the maximum 6 beds, create a new bed and initialize it in the middle
                 
        
        
    }
    
}
