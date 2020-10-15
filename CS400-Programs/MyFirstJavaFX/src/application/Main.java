package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {
    class MyHandler implements EventHandler<ActionEvent>{
        Button button;
        MyHandler(Button button) {this.button = button;}
        public void handle(ActionEvent e) {
            if (button.getText().equals("我是你爸爸"))
                button.setText("爸爸我错了");
            else
                button.setText("我是你爸爸");
        }
    }
    
	@Override
	public void start(Stage primaryStage) {
		try {
		    Label foodInputLabel = new Label("Enter a food name: ");
		    Label calInputLabel = new Label("Enter calories for your food: ");
		    TextField foodName = new TextField();
		    TextField calorieNum = new TextField();
		    HBox box1 = new HBox();
		    HBox box2 = new HBox();
		    VBox verticalBox = new VBox();
		    
		    
		    int score = 0;
		    Label scoreLabel = new Label("Score: " + score);
		    Button scoreButton = new Button("add 2 points");
		    box1.getChildren().addAll(foodInputLabel, foodName, scoreLabel);
		    box2.getChildren().addAll(calInputLabel, calorieNum);
		    verticalBox.getChildren().addAll(box1, box2);
			BorderPane root = new BorderPane();
			root.setCenter(verticalBox);
			Button switcher = new Button("我是你爸爸");
			switcher.setOnAction(event -> {
			    System.out.println(field.getText());
			});
			root.setBottom(switcher);
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Caffrey's First JavaFX program");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
