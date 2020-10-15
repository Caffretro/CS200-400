//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Dorm Designer 5000
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
public class Button implements DormGUI{
    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;
    protected PApplet processing;
    private float[] position;
    protected String label;
    //all the fields that every button class needs
    public Button(float x, float y, PApplet processing) {
        this.processing = processing;
        position = new float[2];
        position[0] = x;
        position[1] = y;
        label = "Button";//constructs the button, but label will be revised
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
        processing.text(label, position[0], position[1]);//print the name of the button
        
    }
    public void mouseDown(Furniture[] furniture) {
        if (isMouseOver()) {
            System.out.println("A button was pressed.");
            //was overriden
        }
    }
    public void mouseUp() {}//do nothing 
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
