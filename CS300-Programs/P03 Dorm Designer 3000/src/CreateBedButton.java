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
public class CreateBedButton {

    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;
    
    private PApplet processing;
    private float[] position;
    private String label;
    
    public CreateBedButton(float x, float y, PApplet processing) {
        this.processing = processing;
        position = new float[2];
        position[0] = x;
        position[1] = y;
        label = "bed";
    }
    
    public void update() {
        if (isMouseOver()) {
            processing.fill(100);
        }
        else {
            processing.fill(200);
        }
        
        processing.rect(position[0] - WIDTH/2, position[1] - HEIGHT/2, 
                        position[0] + WIDTH/2, position[1] + HEIGHT/2);//draw the button
        processing.fill(0);
        processing.text("Create Bed", position[0], position[1]);
        
    }
    public Furniture mouseDown() {
        if (isMouseOver()) {
            return new Furniture(label, processing);
        }
        else {
            return null;
        }
    }
    public boolean isMouseOver() {
        if (processing.mouseX > (position[0] - WIDTH/2)&&
            processing.mouseX < (position[0] + WIDTH/2)&&
            processing.mouseY > (position[1] - HEIGHT/2)&&
            processing.mouseY < (position[1] + HEIGHT/2)) {
            return true;
        }
        else {
            return false;
        }
    }
}
