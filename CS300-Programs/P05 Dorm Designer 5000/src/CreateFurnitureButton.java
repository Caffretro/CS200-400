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
public class CreateFurnitureButton extends Button implements DormGUI{
    public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
        super(x, y, processing);
        label = type;
        //update the label
    }
    public void update() {
        super.update();
        
    }
    @Override
    public void mouseDown(Furniture[] mainFur) {
        int existing = 0;
        for (int j = 0; j < mainFur.length; j++) {
            if (mainFur[j] != null) {
                existing++;
            }
        }
        if (isMouseOver()) {
            mainFur[existing] = new Furniture(label, processing);//creating furniture
        }
    }
    public boolean isMouseOver() {
        return super.isMouseOver();
    }
    public void mouseUp() {}
}
