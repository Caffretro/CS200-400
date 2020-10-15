//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Dorm Designer 4000
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
public class Furniture {
    private PApplet processing;
    private PImage image;
    private float[] position;
    private boolean isDragging;
    private int rotations;
    private String item;
 
    
    // initializes the fields of a new bed object positioned in the center of the display
    public Furniture(String item, PApplet processing) {
        this.processing = processing;
        this.item = item;
        image = processing.loadImage("images/" + this.item + ".png");//load bed image
        isDragging = false;
        position = new float[2];
        position[0] = processing.width/2;
        position[1] = processing.height/2;
        rotations = 0;
    }
    // draws this bed at its current position
    public void update() {
        if (isDragging) {
            position[0] = processing.mouseX;
            position[1] = processing.mouseY;
            processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
        }
        else {
            processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
        }

    }
    
    // used to start dragging the bed, when the mouse is over this bed when it is pressed
    public void mouseDown() {
        if (isMouseOver()) {
            isDragging = true;
        }
    }
    
    // used to indicate that the bed is no longer being dragged
    public void mouseUp() {
        isDragging = false;
    }
    // used to rotate the bed that the mouse is on
    public void rotate() {
        rotations++;
    }
    // helper method to determine whether the mouse is currently over this bed
    public boolean isMouseOver() {
        if (processing.mouseX < (position[0] + image.width/2)&&
            processing.mouseX > (position[0] - image.width/2)&&
            processing.mouseY < (position[1] + image.height/2)&&
            processing.mouseY > (position[1] - image.height/2)&&
            rotations % 2 == 0) {
            return true;
                
        }//determine whether the mouse is on the bed while the bed is horizontally placed
        else if (processing.mouseX < (position[0] + image.height/2)&&
                processing.mouseX > (position[0] - image.height/2)&&
                processing.mouseY < (position[1] + image.width/2)&&
                processing.mouseY > (position[1] - image.width/2)&&
                rotations % 2 == 1) {
            return true;
                
        }//determine whether the mouse is on the bed while the bed is vertically placed
        else{
            return false;
        }
        
    }
    public String getName() {
        return this.item;
    }
}
