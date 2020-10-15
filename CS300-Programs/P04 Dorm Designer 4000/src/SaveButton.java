import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveButton {
    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;
    
    private PApplet processing;
    private float[] position;
    private String label;
    
    public SaveButton(float x, float y, PApplet processing) {
        this.processing = processing;
        position = new float[2];
        position[0] = x;
        position[1] = y;
        label = "save";
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
        processing.text("Save", position[0], position[1]);
        
    }
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
