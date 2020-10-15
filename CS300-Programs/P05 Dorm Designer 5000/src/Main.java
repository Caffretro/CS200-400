import java.util.ArrayList;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Dorm Designer 5000
//Files:           None
//Course:          CS300 Spring 2018
//
//Author:          Junheng Wang
//Email:           jwang922@wisc.edu
//Lecturer's Name: Mouna Kacem
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    N/A
//Partner Email:   N/A
//Lecturer's Name: N/A
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//___ Write-up states that pair programming is allowed for this assignment.
//___ We have both read and understand the course Pair Programming Policy.
//___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates 
//strangers, etc do.  If you received no outside help from either type of 
//source, then please explicitly indicate NONE.
//
//Persons:         None
//Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class Main {
    // Max number of furniture that LoadButton will be allowed to load at once.    
    private final static int MAX_LOAD_FURNITURE = 100;
    private PApplet processing;
    private PImage backgroundImage;
    //private CreateBedButton bedButton;
    //private CreateSofaButton sofaButton;
    //private SaveButton save;
    //private LoadButton load;
    //private Furniture[] furniture = new Furniture[]{null, null, null, null, null, null};
    private ArrayList<DormGUI> guiObjects;
    
    public Main(PApplet processing) {
        guiObjects = new ArrayList<DormGUI>();
        this.processing = processing;
        backgroundImage = processing.loadImage("images/background.png");
        //load the background
        guiObjects.add(new CreateFurnitureButton("bed", 50, 24, processing));
        guiObjects.add(new CreateFurnitureButton("sofa", 150, 24, processing));
        guiObjects.add(new CreateFurnitureButton("dresser", 250, 24, processing));
        guiObjects.add(new CreateFurnitureButton("desk", 350, 24, processing));
        guiObjects.add(new CreateFurnitureButton("sink", 450, 24, processing));
        guiObjects.add(new ClearButton(550, 24, processing));
        guiObjects.add(new SaveButton(650, 24, processing));
        guiObjects.add(new LoadButton(750, 24, processing));
        //creating the buttons we need
    }
    
    public static void main(String[] args) {
        
        Utility.startApplication();
    
    }
    
    public void update() {
        processing.background(100,150,250);
        processing.image(backgroundImage, processing.width/2, processing.height/2);
        //refreshes the background
        
        for (int i = 0; i < guiObjects.size(); i++) {
            guiObjects.get(i).update();
        }
    }
    public void mouseDown() {
        Furniture[] temporary = extractFurnitureFromGUIObjects();
        for (int i = 0; i < guiObjects.size(); i++) {
            if (guiObjects.get(i).isMouseOver()) {
            guiObjects.get(i).mouseDown(temporary);
            replaceFurnitureInGUIObjects(temporary);
            }
        }
        
    }
    public void mouseUp() {
        
        for (int i = 0; i < guiObjects.size(); i ++){
            guiObjects.get(i).mouseUp();
        }
            
    }
    public void keyPressed() {
        switch (processing.key){
            case 'd':
            case 'D':
                for (int i = 0; i < guiObjects.size(); i++){
                    if (guiObjects.get(i) instanceof Furniture) {
                        if (guiObjects.get(i).isMouseOver()) {
                            guiObjects.remove(i--);
                        }//since the arraylist won't result in a space, we have to minus 1 to i
                    }
                }
            break;
            case 'r':
            case 'R':
                for (int i = 0; i < guiObjects.size(); i++){
                    if (guiObjects.get(i) instanceof Furniture) {
                        if (guiObjects.get(i).isMouseOver()) {
                        ((Furniture) guiObjects.get(i)).rotate();
                        }//only rotates the furnitures
                    }
                 }
            break;
            
        
        }
    }
    /**
     * This method creates a new Furniture[] for the old mouseDown() methods
     * to make use of.  It does so by copying all Furniture references from
     * this.guiObjects into a temporary array of size MAX_LOAD_FURNITURE.
     * @return that array of Furniture references.
     */
    private Furniture[] extractFurnitureFromGUIObjects() {
        Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
        int nextFreeIndex = 0;
        for(int i = 0; i < guiObjects.size() && nextFreeIndex < furniture.length; i++) {
            if (guiObjects.get(i) instanceof Furniture) {
                furniture[nextFreeIndex++] = (Furniture)guiObjects.get(i);
            }
        }
        return furniture;        
    }
    /**
     * This method first removes all Furniture references from this.guiObjects,
     * and then adds back in all of the non-null references from it's parameter.
     * @param furniture contains the only furniture that will be left in 
     *   this.guiObjects after this method is invoked (null references ignored).
     */
    private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
        for(int i = 0; i < guiObjects.size(); i++) {
            if (guiObjects.get(i) instanceof Furniture) {
                guiObjects.remove(i--);
            }
        }
        for(int i = 0; i < furniture.length; i++) {
            if (furniture[i] != null) {
                guiObjects.add(furniture[i]);
            }
        }
    }
    
}
