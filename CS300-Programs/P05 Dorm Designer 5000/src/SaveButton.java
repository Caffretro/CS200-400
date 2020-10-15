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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveButton extends Button implements DormGUI{
    
    public SaveButton(float x, float y, PApplet processing) {
        super(x, y, processing);
        label = "save";//updating the label
    }
    public void update() {
        super.update();
    }
    @Override
    public void mouseDown(Furniture[] saveFur) {
        int existing = 0;
        for (int j = 0; j < saveFur.length; j++) {
            if (saveFur[j] != null) {
                existing++;
            }
        }//count how many furniture are there
        try{
            FileOutputStream fileStream = new FileOutputStream("RoomData.ddd");
            PrintWriter writer = new PrintWriter(fileStream);//writes information into the file
            for (int i = 0; i < existing; i++) {
                writer.print(saveFur[i].getName());
                writer.print(":");
                writer.print(saveFur[i].getInfo());
                writer.println();//writes furniture information into RoomData.ddd
            }
            writer.close();
            fileStream.close();
        } catch(IOException e){
            System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");
        }
        
    }
    public boolean isMouseOver() {
        return super.isMouseOver();
    }
    public void mouseUp() {}
}
