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
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class Main {
    
    private PApplet processing;
    private PImage backgroundImage;
    private CreateBedButton bedButton;
    private CreateSofaButton sofaButton;
    private SaveButton save;
    private LoadButton load;
    private Furniture[] furniture = new Furniture[]{null, null, null, null, null, null};
    
    public Main(PApplet processing) {
        this.processing = processing;
        backgroundImage = processing.loadImage("images/background.png");
        //load the background
        bedButton = new CreateBedButton(50, 24, processing);
        sofaButton = new CreateSofaButton(150, 24, processing);
        save = new SaveButton(650, 24, processing);
        load = new LoadButton(750, 24, processing);
    }
    
    public static void main(String[] args) {
        
        Utility.startApplication();
    
    }
    
    public void update() {
        processing.background(100,150,250);
        processing.image(backgroundImage, processing.width/2, processing.height/2);
        //refreshes the background
        
        for (int i = 0; i < furniture.length; i++) {
            if (furniture[i] != null) {
                furniture[i].update();
            }
        }
        bedButton.update();
        sofaButton.update();
        save.update();
        load.update();
        
    }
    public void mouseDown() {
        int existing = 0;
        for (int i = 0; i < furniture.length; i++) {
            if (furniture[i] != null) {
                furniture[i].mouseDown();
            }
        }//move the beds
        for (int j = 0; j < furniture.length; j++) {
            if (furniture[j] != null) {
                existing++;
            }
        }//again, count how many beds are there
        if (existing < 6) {
            if (bedButton.isMouseOver()) {
                furniture[existing] = bedButton.mouseDown();
            }
            if (sofaButton.isMouseOver()) {                
                furniture[existing] = sofaButton.mouseDown();
            }
        }
        if (existing <= 6) {
            if (save.isMouseOver()) {
                save.mouseDown(furniture);
            }
            if (load.isMouseOver()) {                
                load.mouseDown(furniture);
            }
        }
    }
    public void mouseUp() {
        
        for (int i = 0; i < furniture.length; i ++){
            if (furniture[i] != null) {
                furniture[i].mouseUp();
            }
        }
            
    }
    public void keyPressed() {
        switch (processing.key){
            case 'd':
            case 'D':
                for (int i = 0; i < furniture.length; i++){
                   if (furniture[i] != null) {
                       if (furniture[i].isMouseOver()) {
                           furniture[i] = null;
                       }
                   }
                }
            break;
            case 'r':
            case 'R':
                for (int i = 0; i < furniture.length; i++){
                    if (furniture[i] != null) {
                        if (furniture[i].isMouseOver()) {
                            furniture[i].rotate();
                        }
                    }
                 }
            break;
            
        
        }
    }
    
}
