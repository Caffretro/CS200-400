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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadButton {
    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;
    
    private PApplet processing;
    private float[] position;
    private String label;
    
    public LoadButton(float x, float y, PApplet processing) {
        this.processing = processing;
        position = new float[2];
        position[0] = x;
        position[1] = y;
        label = "load";
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
        processing.text("Load", position[0], position[1]);
        
    }
    public void mouseDown(Furniture[] loadFur) {
        File file = new File("RoomData.ddd");
        String info = "";
        String furnitureInfo = "";
        float xCoor;
        float yCoor;
        int rotation;
        int i = 0;
        try{
            if (!file.exists()) {
                throw new NullPointerException();
            }
            Scanner scnr = new Scanner(file);
            for (int j = 0; j < loadFur.length; j++) {
                loadFur[j] = null;
            }
            while (scnr.hasNextLine()) {
                info = scnr.nextLine();
                info.trim();//eliminate spaces
                if (!info.equals("")) {//check if the input line is empty
                    furnitureInfo = info.split(":")[0].trim();//split into two parts
                    if (info.split(":").length != 2) {
                        i++;
                        System.out.println("WARNING: Found incorrectly formatted line in file: "
                            + i);
                        continue;
                    }
                    File furImage = new File("images/" + furnitureInfo + ".png");
                    if (!furImage.exists()) {
                        throw new FileNotFoundException();
                    }//check if the picture loads correctly
                    xCoor = Float.parseFloat(info.split(":")[1].split(",")[0].trim());
                    yCoor = Float.parseFloat(info.split(":")[1].split(",")[1].trim());
                    rotation = Integer.parseInt(info.split(":")[1].split(",")[2].trim());
                    //uploading furniture information
                    loadFur[i] = new Furniture(furnitureInfo, xCoor, yCoor, rotation, processing);
                    i++;//nextLine
                }
            
            }
            scnr.close();
        } 
        catch (NullPointerException e) {
            System.out.println("WARNING: Could not load room contents from file RoomData.ddd.");
        }
        catch (FileNotFoundException e) {
            System.out.println("WARNING: Could not find an image for a furniture object of type: "
                + furnitureInfo);   
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("WARNING: Unable to load more furniture.");
        }
        catch (Exception e) {
            e.printStackTrace();//needs enhancement
        }
        
        return;//FIXME
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
