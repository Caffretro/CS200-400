package application;
/**
 * Filename:   Main.java
 * Project:    CS400 Final Project
 * Authors:    Ruijian Huang, Yuhao Liu, Huifeng Su, Junheng Wang, Leon Zhang
 * <p>
 * Semester:   Fall 2018
 * Course:     CS400
 * <p>
 * Due Date:   10pm, 11/30/2018
 * Version:    1.0
 * <p>
 * Credits:    None
 * <p>
 * Bugs:       For now there seems to be no bug
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * This is a GUI class for the purpose of establishing a bridge between users and the final program.
 * It contains all functions required to utilize the function, with information of available foods,
 * current meal selected, and current query applied given to users.
 *
 * @author Ruijian Huang, Yuhao Liu, Huifeng Su, Junheng Wang, Leon Zhang
 */
public class Main extends Application {
    public int width = 1700; // Scene's initial width
    public int height = 800; // Scene's initial height

    /**
     * This method contains all the settings of GUI's layout, with only one primaryStage passed
     * into.
     *
     * @param primaryStage
     */

    FoodData foodData = new FoodData();
    MealList mealList = new MealList();

    Label labAddFoodId = new Label();
    TextField txtAddFoodId = new TextField();
    Label labAddFoodName = new Label();
    TextField txtAddFoodName = new TextField();
    Label labAddFoodCal = new Label();
    TextField txtAddFoodCal = new TextField();
    Label labAddFoodFat = new Label();
    TextField txtAddFoodFat = new TextField();
    Label labAddFoodCarb = new Label();
    TextField txtAddFoodCarb = new TextField();
    Label labAddFoodFib = new Label();
    TextField txtAddFoodFib = new TextField();
    Label labAddFoodPro = new Label();
    TextField txtAddFoodPro = new TextField();

    String addFoodProteinHolder = "";
    String addFoodFiberHolder = "";
    String addFoodCarboHydrateHolder = "";
    String addFoodCaloriesHolder = "";
    String addFoodFatHolder = "";
    String addFoodNameHolder = "";
    String addFoodIdHolder = "";

    Double addFoodCal;
    Double addFoodFat;
    Double addFoodCarb;
    Double addFoodFib;
    Double addFoodPro;
    String addFoodName;
    String addFoodId;
    
    String calLowerLimHolder = "";  //saved previous info
    String calUpperLimHolder = "";  //saved previous info
    String fatLowerLimHolder = "";  //saved previous info
    String fatUpperLimHolder = "";  //saved previous info
    String carboLowerLimHolder = "";  //saved previous info
    String carboUpperLimHolder = "";  //saved previous info
    String fiberLowerLimHolder = "";  //saved previous info
    String fiberUpperLimHolder = "";  //saved previous info
    String proteinLowerLimHolder = "";  //saved previous info
    String proteinUpperLimHolder = "";  //saved previous info

    Double calLower;  //saved previous info
    Double calUpper;  //saved previous info
    Double fatLower;  //saved previous info
    Double fatUpper;  //saved previous info
    Double carboLower;  //saved previous info
    Double carboUpper;  //saved previous info
    Double fiberLower;  //saved previous info
    Double fiberUpper;  //saved previous info
    Double proteinLower;  //saved previous info
    Double proteinUpper;  //saved previous info

    TextField caloriesLowerLim = new TextField();
    TextField caloriesUpperLim = new TextField();

    TextField fatLowerLim = new TextField();
    TextField fatUpperLim = new TextField();

    TextField carboLowerLim = new TextField();
    TextField carboUpperLim = new TextField();

    TextField fiberLowerLim = new TextField();
    TextField fiberUpperLim = new TextField();

    TextField proteinLowerLim = new TextField();
    TextField proteinUpperLim = new TextField();

    GridPane filterGrid = new GridPane(); // GridPane for viewing query list
    ArrayList<FilterEntry> filterEntryList = new ArrayList<>();
    final static String LARGER_EQUAL = ">=";
    final static String EQUAL = "==";
    final static String SMALLER_EQUAL = "<=";
    final static Double LOWER_LIM = 0.0;
    final static Double UPPER_LIM = Double.MAX_VALUE;

    Text filterTableHeader1 = new Text(" Nutrient Type : ");
    Text filterTableHeader2 = new Text(" Comaprison Type : ");
    Text filterTableHeader3 = new Text(" Threshhold amount : ");

    ScrollPane foodSP = new ScrollPane();
    ScrollPane querySP = new ScrollPane();
    ScrollPane mealSP = new ScrollPane();
    ScrollPane sumSP = new ScrollPane();

    Button btnInvisibleFoodListRefresher = new Button();
    Button btnInvisibleMealListRefresher = new Button();
    Button btnResetFilter = new Button("Reset Query");     //event handler located in filter method
    //global filter setup: name-substring, list of nutrient rules
    ArrayList<String> queryStringList = new ArrayList<>();
    String nameFilter = "";
    List<FoodItem> listAllFoodItem = new ArrayList<>();
    List<FoodItem> listAllMealItem = new ArrayList<>();
    ArrayList<Boolean> foodListSelectedList = new ArrayList<Boolean>(); // Checkboxes for foodList
    ArrayList<Boolean> mealListSelectedList = new ArrayList<Boolean>(); // Checkboxex for mealList

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, width, height);

            HBox biggestBox = new HBox(); // Biggest box to carry vBox1 and vBox2
            VBox vBox1 = new VBox(); // Left side Box containing all elements
            VBox vBox2 = new VBox(); // Right side Box containing all elements

            ObservableList<FoodItem> foodListDisplay = FXCollections.observableArrayList();
            foodListDisplay.addAll(foodData.getAllFoodItems());

            // Menu bar - setup
            MenuBar menuBar = new MenuBar();
            Menu FileMenu = new Menu("File");
            Menu HelpMenu = new Menu("Help");
            MenuItem filemenu1 = new MenuItem("Load");
            MenuItem filemenu2 = new MenuItem("Save");
            MenuItem instruction = new MenuItem("User Manual");
            
            FileMenu.getItems().addAll(filemenu1, filemenu2);
            HelpMenu.getItems().addAll(instruction);
            menuBar.getMenus().addAll(FileMenu, HelpMenu);

            filemenu1.setOnAction(event -> {
                // We may just use a pop up window with a textbox asking for
                FileChooser chooser = new FileChooser();
                String currentDir = System.getProperty("user.dir");
                File startPlace = new File(currentDir);
                chooser.setInitialDirectory(startPlace);
                chooser.setTitle("Choose File");
                // Get the file from filechooser
                File file = chooser.showOpenDialog(primaryStage);
                // Pass the filename to foodData so that it can load information
                if (file != null) {
                    System.out.println(file.getName());
                    this.foodData.loadFoodItems(file.getAbsolutePath());
                    listAllFoodItem = foodData.getAllFoodItems();
                    btnInvisibleFoodListRefresher.fire();
                }

            });
            filemenu2.setOnAction(event ->{
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Save File");
                File file = chooser.showSaveDialog(primaryStage);
                if (file != null) {
                    this.foodData.saveFoodItems(file.getAbsolutePath());
                }
            });
            instruction.setOnAction(event -> {
                Dialog dialogManual = new Dialog();
                showManual(dialogManual);
                
            });
            
            
            // Setting up each part of the vBox at left
            // Including HBox for Query, GridPane for Query within ScrollPane, HBox for FoodList,
            // GridPane for Foodlist within ScrollPane

            // Query - HBox setup
            HBox queryBox = new HBox(); // HBox containing name of Querypane and a add query button
            Text queryBoxName = new Text("Currently Applied Query");
            Button btnEditQuery = new Button("Edit Query");
            HBox queryButtonContainer = new HBox();
            queryButtonContainer.getChildren().addAll(btnEditQuery, btnResetFilter);
            queryButtonContainer.setSpacing(10);
           
            queryBox.getChildren().addAll(queryBoxName, queryButtonContainer);
            queryBox.setSpacing(300); // Manually place this button to the right of HBox

            // Foodlist - HBox setup
            HBox foodListBox = new HBox(); // HBox containing name of FoodListPane and add food button
            Text foodListName = new Text("Food List");
            Button addFoodButton = new Button();
            addFoodButton.setText("Add Food");
            
            Dialog dialogAddFood = new Dialog();
            addFood(dialogAddFood);

            addFoodButton.setOnAction((event -> {
                dialogAddFood.show();
            }));
            
            TextField searchContent = new TextField();
            Button nameFilterButton = new Button();
            nameFilterButton.setText("Search");
            nameFilterButton.setOnAction(event -> {
                try {
                    if (foodData.isEmpty()) throw new IllegalArgumentException();
                    nameFilter = searchContent.getText();
                    listAllFoodItem = foodData.globalFilter(nameFilter, queryStringList);
                    btnInvisibleFoodListRefresher.fire();
                } catch (IllegalArgumentException i) {
                    emptyFoodListAlert();
                }

            });

            HBox searchBarContainer = new HBox();
            searchBarContainer.getChildren().addAll(searchContent, nameFilterButton);
            searchBarContainer.setSpacing(5);
            HBox searchBarBiggerContainer = new HBox();
            Button btnAddToMeal = new Button("Add to Meal List");
            btnAddToMeal.setOnAction(event -> {
                // Renewing T/F values for foodList
                this.foodListSelectedList = new ArrayList<Boolean>();
                for (int i = 0; i < listAllFoodItem.size(); i++) {
                    foodListSelectedList.add(listAllFoodItem.get(i).getCheckBoxValue());
                }
                // Filter out all the ones to add to mealList
                for (int i = 0; i < foodListSelectedList.size(); i++) {
                    if (foodListSelectedList.get(i) == true) {
                        FoodItem temp = new FoodItem(listAllFoodItem.get(i).getID(), 
                            listAllFoodItem.get(i).getNutrients(), listAllFoodItem.get(i).getName(),
                            listAllFoodItem.get(i).getIndex());
                        mealList.addItem(temp);
                    }
                }
                this.listAllMealItem = this.mealList.getMealList();
                btnInvisibleMealListRefresher.fire();
            });
            searchBarBiggerContainer.getChildren().addAll(searchBarContainer, addFoodButton, btnAddToMeal);
            searchBarBiggerContainer.setSpacing(7);
            foodListBox.getChildren().addAll(foodListName, searchBarBiggerContainer);
            foodListBox.setSpacing(300); // Manually place this button to the right of HBox

            // Query list - GridPane setup
            filterGrid.add(filterTableHeader1, 1, 0);
            filterGrid.add(filterTableHeader2, 2, 0);
            filterGrid.add(filterTableHeader3, 3, 0);
            filterGrid.setHgap(15);

            // Foodlist - GridPane setup
            GridPane foodListGrid = new GridPane(); // GridPane for viewing foodList
            foodListGrid.setHgap(10);
            int foodListHeaderSize = 15;
            Text foodName = new Text(" Food Item Name      ");
            foodName.setFont(Font.font("Arial", foodListHeaderSize));
            Text calories = new Text(" Calories      ");
            calories.setFont(Font.font("Arial", foodListHeaderSize));
            Text fat = new Text(" Fat      ");
            fat.setFont(Font.font("Arial", foodListHeaderSize));
            Text carbohydrate = new Text(" Carbohydrate      ");
            carbohydrate.setFont(Font.font("Arial", foodListHeaderSize));
            Text fiber = new Text(" Fiber      ");
            fiber.setFont(Font.font("Arial", foodListHeaderSize));
            Text protein = new Text(" Protein      ");
            protein.setFont(Font.font("Arial", foodListHeaderSize));
            foodListGrid.setGridLinesVisible(true);


            btnInvisibleFoodListRefresher.setOnAction(event -> {
                try {
                    foodListGrid.getChildren().clear();
                    foodListGrid.add(foodName, 1, 0, 1, 1);
                    foodListGrid.add(calories, 2, 0, 1, 1);
                    foodListGrid.add(fat, 3, 0, 1, 1);
                    foodListGrid.add(carbohydrate, 4, 0, 1, 1);
                    foodListGrid.add(fiber, 5, 0, 1, 1);
                    foodListGrid.add(protein, 6, 0, 1, 1);

                    for (int i = 0; i < listAllFoodItem.size(); i++){
                        foodListGrid.add(listAllFoodItem.get(i).getCheckBox(), 0, i + 1, 1, 1);
                        foodListGrid.add(new Text(listAllFoodItem.get(i).getName()), 1, i + 1, 1, 1);
                        foodListGrid.add(new Text("" + listAllFoodItem.get(i).getNutrientValue("calories")), 2, i + 1, 1, 1);
                        foodListGrid.add(new Text("" + listAllFoodItem.get(i).getNutrientValue("fat")), 3, i + 1, 1, 1);
                        foodListGrid.add(new Text("" + listAllFoodItem.get(i).getNutrientValue("carbohydrate")), 4, i + 1, 1, 1);
                        foodListGrid.add(new Text("" + listAllFoodItem.get(i).getNutrientValue("fiber")), 5, i + 1, 1, 1);
                        foodListGrid.add(new Text("" + listAllFoodItem.get(i).getNutrientValue("protein")), 6, i + 1, 1, 1);
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            });

            // Setting up each part of the vBox at right
            // Including HBox for Query, GridPane for Query within ScrollPane, HBox for FoodList,
            // GridPane for Foodlist within ScrollPane

            // Meal List - HBox setup
            // TODO: implement the delete food button
            HBox upperRight = new HBox();
            Text mealLTitle = new Text("Meal List");
            Button clearBtn = new Button("Clear All");
            clearBtn.setOnAction(event -> {
                mealList.clearAll();
                this.listAllMealItem.clear();
                btnInvisibleMealListRefresher.fire();
            });
            Button btnDeleteFromMeal = new Button("Delete Food");
            btnDeleteFromMeal.setOnAction(event -> {
                // Renewing T/F values for foodList
                this.mealListSelectedList = new ArrayList<Boolean>();
                for (int i = 0; i < listAllMealItem.size(); i++) {
                    mealListSelectedList.add(listAllMealItem.get(i).getMealCheckBoxValue());
                }
                // Filter out all the ones to remove from mealList
                ArrayList<Integer> removeMealIndex = new ArrayList<Integer>();
                for (int i = 0; i < mealListSelectedList.size(); i++) {
                    if (mealListSelectedList.get(i)) {
                        removeMealIndex.add(i);
                    }
                }
                for (int j = removeMealIndex.size() - 1; j >= 0; j--) {
                    int index = removeMealIndex.get(j);
                    listAllMealItem.remove(index);
                }
                btnInvisibleMealListRefresher.fire();
            });
            upperRight.setSpacing(300);
            HBox upperRightButtonContainer = new HBox();
            upperRightButtonContainer.getChildren().addAll(btnDeleteFromMeal, clearBtn);
            upperRightButtonContainer.setSpacing(10);
            upperRight.getChildren().addAll(mealLTitle, upperRightButtonContainer);

            // Meal List - GridPane setup
            GridPane mealListGrid = new GridPane(); // GridPane for viewing foodList
            mealListGrid.setHgap(10);
            int mealListHeaderSize = 15;
            Text mealFoodName = new Text(" Food Item Name      ");
            mealFoodName.setFont(Font.font("Arial", mealListHeaderSize));
            Text mealCalories = new Text(" Calories      ");
            mealCalories.setFont(Font.font("Arial", mealListHeaderSize));
            Text mealFat = new Text(" Fat      ");
            mealFat.setFont(Font.font("Arial", mealListHeaderSize));
            Text mealCarbohydrate = new Text(" Carbohydrate      ");
            mealCarbohydrate.setFont(Font.font("Arial", mealListHeaderSize));
            Text mealFiber = new Text(" Fiber      ");
            mealFiber.setFont(Font.font("Arial", mealListHeaderSize));
            Text mealProtein = new Text(" Protein      ");
            mealProtein.setFont(Font.font("Arial", mealListHeaderSize));

            
            btnInvisibleMealListRefresher.setOnAction(event -> {
                try {
                    mealListGrid.getChildren().clear();
                    mealListGrid.add(mealFoodName, 1, 0, 1, 1);
                    mealListGrid.add(mealCalories, 2, 0, 1, 1);
                    mealListGrid.add(mealFat, 3, 0, 1, 1);
                    mealListGrid.add(mealCarbohydrate, 4, 0, 1, 1);
                    mealListGrid.add(mealFiber, 5, 0, 1, 1);
                    mealListGrid.add(mealProtein, 6, 0, 1, 1);

                    for (int i = 0; i < listAllMealItem.size(); i++){
                        mealListGrid.add(listAllMealItem.get(i).getMealCheckBox(), 0, i + 1, 1, 1);
                        mealListGrid.add(new Text(listAllMealItem.get(i).getName()), 1, i + 1, 1, 1);
                        mealListGrid.add(new Text("" + listAllMealItem.get(i).getNutrientValue("calories")), 2, i + 1, 1, 1);
                        mealListGrid.add(new Text("" + listAllMealItem.get(i).getNutrientValue("fat")), 3, i + 1, 1, 1);
                        mealListGrid.add(new Text("" + listAllMealItem.get(i).getNutrientValue("carbohydrate")), 4, i + 1, 1, 1);
                        mealListGrid.add(new Text("" + listAllMealItem.get(i).getNutrientValue("fiber")), 5, i + 1, 1, 1);
                        mealListGrid.add(new Text("" + listAllMealItem.get(i).getNutrientValue("protein")), 6, i + 1, 1, 1);
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            });
            //Meal Summary - HBox setup
            HBox sumTitleBox = new HBox();
            Text sumTitle = new Text("Meal Summary");
            Button sumBtn = new Button("Generate Summary");
            sumTitleBox.getChildren().addAll(sumTitle, sumBtn);
            sumTitleBox.setSpacing(100);


            // Following are wrapping up all components and put them together

            // Left side - wrap up
            vBox1.getChildren().addAll(queryBox, querySP, foodListBox, foodSP);
            vBox1.setPrefWidth(scene.getWidth() / 2);
            vBox1.setVgrow(foodSP, Priority.ALWAYS); // only expanding size of FoodList

            // Right side - wrap up
            vBox2.getChildren().addAll(upperRight, mealSP, sumTitleBox, sumSP);
            vBox2.setPrefWidth(scene.getWidth() / 2);

            // ScrollPanes - policies
            // ScrollBar policies
            querySP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            querySP.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
            foodSP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            foodSP.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
            sumSP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            sumSP.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
            // Size policies
            querySP.setPrefHeight(150);
            querySP.setMaxHeight(height / 3);
            querySP.setMinHeight(150);
            sumSP.setPrefSize(width / 2, height / 2);
            mealSP.setPrefSize(width / 2, height / 1.5);

            // Putting GridPanes into ScrollPanes
            foodSP.setContent(foodListGrid); // insert GridPane into FoodList scrollpane //FIXME:
            querySP.setContent(filterGrid); // insert GridPane into Query scrollpane
            mealSP.setContent(mealListGrid); // insert GridPane into Meal scrollPane
            foodListGrid.setGridLinesVisible(true);
            mealListGrid.setGridLinesVisible(true);// Showing Grid Lines

            // Scene - setup
            root.setTop(menuBar); // put the menuBar at top
            biggestBox.getChildren().addAll(vBox1, vBox2); // wrapping rest elements into one box
            root.setLeft(biggestBox); // Putting the box into BorderPane

            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Meal Planner");
            primaryStage.show();
            instruction.fire();

            //Query Part [filter]
            Dialog dialogFilter = new Dialog();
            dialogFilter.initOwner(primaryStage);
            filter(dialogFilter);
            
            btnEditQuery.setOnAction((event) -> dialogFilter.show());

            //TODO: after filter
            
            
            
          //Clear All button by Leon
            
            
            //Generate Summary button by Leon
            sumBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    GridPane summaryGP = new GridPane();
                    HashMap<String, Double> nutritionSummary = mealList.getNutritionSummary();

                    summaryGP.setHgap(70.0);
                    summaryGP.setVgap(20.0);
                    Text sum = new Text("                   " +
                            "                                         Summary");
                    Text sumCal = new Text(" Total Calories");
                    Text sumFat = new Text(" Total Fat");
                    Text sumCar = new Text(" Total Carbohydrate");
                    Text sumFiber = new Text(" Total Fiber");
                    Text sumPro = new Text(" Total Protein");

                    summaryGP.add(sum, 0, 0, 2, 1);
                    summaryGP.add(sumCal, 0, 1);
                    summaryGP.add(sumFat, 0, 2);
                    summaryGP.add(sumCar, 0, 3);
                    summaryGP.add(sumFiber, 0, 4);
                    summaryGP.add(sumPro, 0, 5);

                    mealList.analyzeMeal();
                    Text digitCal = new Text(nutritionSummary.get("calories").toString());
                    Text digitFat = new Text(nutritionSummary.get("fat").toString());
                    Text digitCar = new Text(nutritionSummary.get("carbohydrate").toString());
                    Text digitFiber = new Text(nutritionSummary.get("fiber").toString());
                    Text digitPro = new Text(nutritionSummary.get("protein").toString());

                    summaryGP.add(digitCal, 1, 1);
                    summaryGP.add(digitFat, 1, 2);
                    summaryGP.add(digitCar, 1, 3);
                    summaryGP.add(digitFiber, 1, 4);
                    summaryGP.add(digitPro, 1, 5);

                    sumSP.setContent(summaryGP);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showManual(Dialog dialogManual) {
        dialogManual.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeManualButton = dialogManual.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeManualButton.managedProperty().bind(closeManualButton.visibleProperty());
        closeManualButton.setVisible(false);
        
        Text instructions = new Text();
        instructions.setText("To use this program, you need to import a csv file from your "
            + "directory.\n        Load/ Save: File can be loaded and saved in the menubar\n"
            + "        Query: food queries can be edited by pressing the 'Edit Filter' button.\n"
            + "        Search Bar: Search bar can be used to search food with specific names.\n"
            + "        Add Food: Adding a new food to the current food list.\n"
            + "        Add to Meal: Adding the food selected to the meal list. To do this, first "
            + "select the corresponding checkBox. The 'Add To Meal List' button will add all foods "
            + "selected to meal list.\n"
            + "        Delete Food: Delete selected food from meal list. The 'Delete Food' button "
            + "will delete all foods selected in meal list.\n"
            + "        Generate Summary: Display the sum of each type of nutrients of foods in the "
            + "current meal list.\n\n"
            + "* This manual can be accessed from menu -> Help");
        //instructions.setTextAlignment(TextAlignment.CENTER);
        dialogManual.getDialogPane().setContent(instructions);
        
        dialogManual.setTitle("Manual");
        dialogManual.show();
    }
    
    private void addFood(Dialog dialogAddFood) {

        dialogAddFood.initModality(Modality.APPLICATION_MODAL);

        dialogAddFood.setTitle("Add Food");

        GridPane gridDialogAddFood = new GridPane();
        DialogPane dialogPaneAddFood = new DialogPane();
        dialogPaneAddFood.setContent(gridDialogAddFood);
        dialogAddFood.setDialogPane(dialogPaneAddFood);
        gridDialogAddFood.setHgap(10);
        gridDialogAddFood.setVgap(10);
        HBox cancelAndSave = new HBox();

        labAddFoodName.setText("Enter food name: ");
        labAddFoodCal.setText("Enter calories: ");
        labAddFoodFat.setText("Enter fat: ");
        labAddFoodFib.setText("Enter fiber: ");
        labAddFoodPro.setText("Enter protein: ");
        labAddFoodCarb.setText("Enter CarboHydrate: ");
        labAddFoodId.setText("Enter food id: ");

        gridDialogAddFood.add(labAddFoodId, 2, 0);
        gridDialogAddFood.add(txtAddFoodId, 3, 0);
        
        gridDialogAddFood.add(labAddFoodName, 2, 1);
        gridDialogAddFood.add(txtAddFoodName, 3, 1);

        gridDialogAddFood.add(labAddFoodCal, 2, 2);
        gridDialogAddFood.add(txtAddFoodCal, 3, 2);

        gridDialogAddFood.add(labAddFoodFat, 2, 3);
        gridDialogAddFood.add(txtAddFoodFat, 3, 3);

        gridDialogAddFood.add(labAddFoodCarb, 2, 4);
        gridDialogAddFood.add(txtAddFoodCarb, 3, 4);

        gridDialogAddFood.add(labAddFoodFib, 2, 5);
        gridDialogAddFood.add(txtAddFoodFib, 3, 5);

        gridDialogAddFood.add(labAddFoodPro, 2, 6);
        gridDialogAddFood.add(txtAddFoodPro, 3, 6);

        // "x" button setup
        dialogAddFood.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialogAddFood.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        // Cancel button
        Button btnCancelAddFood = new Button("Cancel");
        // gridDialogAddFood.add(btnCancelAddFood, 3, 8);
        btnCancelAddFood.setOnAction(event -> {
            dialogAddFood.close();
        });

        // Save Button
        Button btnSaveAddFood = new Button("Save");
        // gridDialogAddFood.add(btnSaveAddFood, 4, 8);
        btnSaveAddFood.setOnAction(event -> {
        });

        cancelAndSave.getChildren().addAll(btnCancelAddFood, btnSaveAddFood);
        gridDialogAddFood.add(cancelAndSave, 3, 8);

        // Save Button setup

        btnSaveAddFood.setOnAction(event -> {
            try {
                fromTextFieldToDesiredFormat();

                if (addFoodCal.equals("") || addFoodFat.equals("") || addFoodCarb.equals("") || 
                    addFoodFib.equals("") || addFoodName.equals("") || addFoodId.equals("") ||
                    addFoodCal == null || addFoodFat == null || addFoodCarb == null || 
                    addFoodFib == null || addFoodName == null || addFoodId == null) {
                    throw new IllegalArgumentException();
                }
                
                FoodItem newFoodAddedByUser = new FoodItem(addFoodId, addFoodName);
                
                newFoodAddedByUser.addNutrient("calories", addFoodCal);
                newFoodAddedByUser.addNutrient("fiber", addFoodFib);
                newFoodAddedByUser.addNutrient("protein", addFoodPro);
                newFoodAddedByUser.addNutrient("carbohydrate", addFoodCarb);
                newFoodAddedByUser.addNutrient("fat", addFoodFat);
                
                foodData.addFoodItem(newFoodAddedByUser);
                listAllFoodItem.add(newFoodAddedByUser);
                dialogAddFood.close();

            
            } catch (IllegalArgumentException i) {
                Alert numberFormatFilter = new Alert(Alert.AlertType.INFORMATION);
                numberFormatFilter.setTitle("ERROR");
                numberFormatFilter.setHeaderText("Illegal Filter Entry");
                numberFormatFilter.setContentText(
                        "Please enter legal filter entries. \n  " + 
                "Integer or decimal larger or equal to 0 ");
                numberFormatFilter.showAndWait();
                
            } catch (NullPointerException e) {
                Alert numberFormatFilter = new Alert(Alert.AlertType.INFORMATION);
                numberFormatFilter.setTitle("ERROR");
                numberFormatFilter.setHeaderText("Illegal Filter Entry");
                numberFormatFilter.setContentText(
                        "Please enter legal filter entries. \n  " + 
                "Integer or decimal larger or equal to 0 ");
                numberFormatFilter.showAndWait();
            }
            btnInvisibleFoodListRefresher.fire();
            
        });

    }
    private void filter(Dialog dialogFilter){

        setDefaultFilter();
        // Add Filter button
//        Dialog dialogFilter = new Dialog();
        dialogFilter.initModality(Modality.APPLICATION_MODAL);
//        dialogFilter.initOwner(primaryStage);
        dialogFilter.setTitle("Edit Filter");
        //dialogFilter.setHeight();

        // GridPane & DialogPane setup
        GridPane gridDialogFilter = new GridPane();
        DialogPane dialogPaneFilter = new DialogPane();
        dialogPaneFilter.setContent(gridDialogFilter);
        dialogFilter.setDialogPane(dialogPaneFilter);
        gridDialogFilter.setHgap(10);
        gridDialogFilter.setVgap(10);

        //Line Setup 1: calories
        Text textCaloriesFilter = new Text(" <=   Calories            <=");
        caloriesLowerLim.setText(calLowerLimHolder);    //load saved previous info to TextField
        caloriesUpperLim.setText(calUpperLimHolder);    //load saved previous info to TextField

        //Line Setup 2: fat
        Text textFatFilter = new Text(" <=   Fat                    <=");
        caloriesLowerLim.setText(fatLowerLimHolder);    //load saved previous info to TextField
        caloriesUpperLim.setText(fatUpperLimHolder);    //load saved previous info to TextField

        //Line Setup 3: Carbohydrate
        Text textCarboFilter = new Text(" <=   Carbohydrate   <=");
        caloriesLowerLim.setText(carboLowerLimHolder);    //load saved previous info to TextField
        caloriesUpperLim.setText(carboUpperLimHolder);    //load saved previous info to TextField

        //Line Setup 4: Fiber
        Text textFiberFilter = new Text(" <=   Fiber                 <=");
        caloriesLowerLim.setText(fiberLowerLimHolder);    //load saved previous info to TextField
        caloriesUpperLim.setText(fiberUpperLimHolder);    //load saved previous info to TextField

        //Line Setup 5: Protein
        Text textProteinFilter = new Text(" <=   Protein             <=");
        caloriesLowerLim.setText(proteinLowerLimHolder);    //load saved previous info to TextField
        caloriesUpperLim.setText(proteinUpperLimHolder);    //load saved previous info to TextField

        //BUTTON setup
        // "x" button setup
        dialogFilter.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialogFilter.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        // cancel button setup
        Button btnCancelFilter = new Button("Cancel");
        btnCancelFilter.setOnAction(event -> {
            dialogFilter.close();
            //FIXME: testing filter functionalities
            testFilter();
        });
        // reset button to clean the data
        Button btnResetFilterDialog = new Button("Reset Filter");
        btnResetFilterDialog.setOnAction(event -> {
            calLowerLimHolder = "";
            calUpperLimHolder = "";
            carboLowerLimHolder = "";
            carboUpperLimHolder = "";
            fatLowerLimHolder = "";
            fatUpperLimHolder = "";
            fiberLowerLimHolder = "";
            fiberUpperLimHolder = "";
            proteinLowerLimHolder = "";
            proteinUpperLimHolder = "";

            caloriesLowerLim.setText("");
            caloriesUpperLim.setText("");
            carboLowerLim.setText("");
            carboUpperLim.setText("");
            fatLowerLim.setText("");
            fatUpperLim.setText("");
            fiberLowerLim.setText("");
            fiberUpperLim.setText("");
            proteinLowerLim.setText("");
            proteinUpperLim.setText("");
            setDefaultFilter();

            //FIXME: testing filter functionalities
            testFilter();
        });
       
        // Save Button setup
        Button btnFilterSave = new Button("Save");
        HBox hBoxBtnFilterSave = new HBox();
        Text space = new Text("                        ");
        hBoxBtnFilterSave.getChildren().addAll(space, btnFilterSave);
        btnFilterSave.setAlignment(Pos.TOP_RIGHT);
        btnFilterSave.setOnAction(event -> {
            try {
                fromTextFieldToDouble();
                    if (calLower.compareTo(calUpper) > 0 || carboLower.compareTo(carboUpper) > 0 ||
                            fatLower.compareTo(fatUpper) > 0 || fiberLower.compareTo(fiberUpper) > 0 ||
                            proteinLower.compareTo(proteinUpper) > 0 || calLower.compareTo(LOWER_LIM) < 0 ||
                            carboLower.compareTo(LOWER_LIM) < 0 || fatLower.compareTo(LOWER_LIM) < 0 ||
                            fiberLower.compareTo(LOWER_LIM) < 0 || proteinLower.compareTo(LOWER_LIM) < 0 )
                        throw new IllegalArgumentException();

                filterEntryList = new ArrayList<>();
                inputFilterEntryList();
                updateFilterTable();

                queryStringList = new ArrayList<>();
                for (FilterEntry element: filterEntryList){
                    queryStringList.add(element.getNutrient() + " " + element.getFilterType() + " "
                            + element.getLimit());
                }
                if (foodData.isEmpty()) throw new IllegalAccessException();
                listAllFoodItem = foodData.globalFilter(nameFilter, queryStringList);
                btnInvisibleFoodListRefresher.fire();
                dialogFilter.close();

            } catch (IllegalAccessException e){
                emptyFoodListAlert();
            } catch (NumberFormatException n) {
                Alert numberFormatFilter = new Alert(Alert.AlertType.INFORMATION);
                numberFormatFilter.setTitle("ERROR");
                numberFormatFilter.setHeaderText("Entry Format Error");
                numberFormatFilter.setContentText("Filter entered cannot be processed.");
                numberFormatFilter.showAndWait();
                btnResetFilter.fire();
            } catch (IllegalArgumentException i){
                Alert numberFormatFilter = new Alert(Alert.AlertType.INFORMATION);
                numberFormatFilter.setTitle("ERROR");
                numberFormatFilter.setHeaderText("Illegal Filter Entry");
                numberFormatFilter.setContentText("Please enter legal filter entries. \n  " +
                        "1. Integer or decimal larger or equal to 0 \n  2. For [Entry1] <= Nutrient <= [Entry2]" +
                        "\n     Entry1 should be smaller than Entry2.");
                numberFormatFilter.showAndWait();
                btnResetFilter.fire();
            }
            //FIXME: testing filter functionalities
            testFilter();
        });
        
        // Reset Button on the primaryStage
        btnResetFilter.setOnAction(event -> {
            btnResetFilterDialog.fire();
            btnFilterSave.fire();
        });

        // filter prompt
        Text promptFilter1 = new Text("Entry is optional.");
        Text promptFilter2 = new Text("Please enter legal integer or decimal number(s).");
        //fiter gridPane integration
        int filTableLine = 2;
        gridDialogFilter.add(promptFilter1, 0, 0, 3, 1);
        gridDialogFilter.add(promptFilter2, 0, 1, 3, 1);

        gridDialogFilter.add(hBoxBtnFilterSave, 2, filTableLine + 5, 1, 1);
        gridDialogFilter.add(btnResetFilterDialog, 1, filTableLine + 5, 1, 1);
        gridDialogFilter.add(btnCancelFilter, 0, filTableLine + 5, 1, 1);

        gridDialogFilter.add(caloriesLowerLim, 0, filTableLine, 1, 1);
        gridDialogFilter.add(textCaloriesFilter, 1, filTableLine, 1, 1);
        gridDialogFilter.add(caloriesUpperLim, 2, filTableLine, 1, 1);

        gridDialogFilter.add(fatLowerLim, 0, filTableLine + 1, 1, 1);
        gridDialogFilter.add(textFatFilter, 1, filTableLine + 1, 1, 1);
        gridDialogFilter.add(fatUpperLim, 2, filTableLine + 1, 1, 1);

        gridDialogFilter.add(carboLowerLim, 0, filTableLine + 2, 1, 1);
        gridDialogFilter.add(textCarboFilter, 1, filTableLine + 2, 1, 1);
        gridDialogFilter.add(carboUpperLim, 2, filTableLine + 2, 1, 1);

        gridDialogFilter.add(fiberLowerLim, 0, filTableLine + 3, 1, 1);
        gridDialogFilter.add(textFiberFilter, 1, filTableLine + 3, 1, 1);
        gridDialogFilter.add(fiberUpperLim, 2, filTableLine + 3, 1, 1);

        gridDialogFilter.add(proteinLowerLim, 0, filTableLine + 4, 1, 1);
        gridDialogFilter.add(textProteinFilter, 1, filTableLine + 4, 1, 1);
        gridDialogFilter.add(proteinUpperLim, 2, filTableLine + 4, 1, 1);
    }

    private void setDefaultFilter() {
        calLower = LOWER_LIM;
        calUpper = UPPER_LIM;
        fatLower = LOWER_LIM;
        fatUpper = UPPER_LIM;
        carboLower = LOWER_LIM;
        carboUpper = UPPER_LIM;
        fiberLower = LOWER_LIM;
        fiberUpper = UPPER_LIM;
        proteinLower = LOWER_LIM;
        proteinUpper = UPPER_LIM;
    }

    private void fromTextFieldToDesiredFormat() {
        addFoodCaloriesHolder = txtAddFoodCal.getText();
        addFoodProteinHolder = txtAddFoodPro.getText();
        addFoodCarboHydrateHolder = txtAddFoodCarb.getText();
        addFoodFatHolder = txtAddFoodFat.getText();
        addFoodFiberHolder = txtAddFoodFib.getText();
        addFoodNameHolder = txtAddFoodName.getText();
        addFoodIdHolder = txtAddFoodId.getText();

        if (!addFoodIdHolder.equals(""))
            addFoodId = addFoodIdHolder;
        if (!addFoodCaloriesHolder.equals(""))
            addFoodCal = Double.parseDouble(addFoodCaloriesHolder);
        if (!addFoodFiberHolder.equals(""))
            addFoodFib = Double.parseDouble(addFoodFiberHolder);
        if (!addFoodFatHolder.equals(""))
            addFoodFat = Double.parseDouble(addFoodFatHolder);
        if (!addFoodCarboHydrateHolder.equals(""))
            addFoodCarb = Double.parseDouble(addFoodCarboHydrateHolder);
        if (!addFoodProteinHolder.equals(""))
            addFoodPro = Double.parseDouble(addFoodProteinHolder);
        if (!addFoodNameHolder.equals(""))
            addFoodName = addFoodNameHolder;

    }
    
    private void fromTextFieldToDouble() {

        calLowerLimHolder = caloriesLowerLim.getText();
        calUpperLimHolder = caloriesUpperLim.getText();
        carboLowerLimHolder = carboLowerLim.getText();
        carboUpperLimHolder = carboUpperLim.getText();
        fatLowerLimHolder = fatLowerLim.getText();
        fatUpperLimHolder = fatUpperLim.getText();
        fiberLowerLimHolder = fiberLowerLim.getText();
        fiberUpperLimHolder = fiberUpperLim.getText();
        proteinLowerLimHolder = proteinLowerLim.getText();
        proteinUpperLimHolder = proteinUpperLim.getText();

        if (!calLowerLimHolder.equals("")) calLower = Double.parseDouble(calLowerLimHolder);
        else calLower = LOWER_LIM;
        if (!calUpperLimHolder.equals("")) calUpper = Double.parseDouble(calUpperLimHolder);
        else calUpper = UPPER_LIM;
        if (!carboLowerLimHolder.equals("")) carboLower = Double.parseDouble(carboLowerLimHolder);
        else carboLower = LOWER_LIM;
        if (!carboUpperLimHolder.equals("")) carboUpper = Double.parseDouble(carboUpperLimHolder);
        else carboUpper = UPPER_LIM;
        if (!fatLowerLimHolder.equals("")) fatLower = Double.parseDouble(fatLowerLimHolder);
        else fatLower = LOWER_LIM;
        if (!fatUpperLimHolder.equals("")) fatUpper = Double.parseDouble(fatUpperLimHolder);
        else fatUpper = UPPER_LIM;
        if (!fiberLowerLimHolder.equals("")) fiberLower = Double.parseDouble(fiberLowerLimHolder);
        else fiberLower = LOWER_LIM;
        if (!fiberUpperLimHolder.equals("")) fiberUpper = Double.parseDouble(fiberUpperLimHolder);
        else fiberUpper = UPPER_LIM;
        if (!proteinLowerLimHolder.equals("")) proteinLower = Double.parseDouble(proteinLowerLimHolder);
        else proteinLower = LOWER_LIM;
        if (!proteinUpperLimHolder.equals("")) proteinUpper = Double.parseDouble(proteinUpperLimHolder);
        else proteinUpper = UPPER_LIM;
    }

    private void inputFilterEntryList(){

        if (calLower.equals(calUpper)) {
            filterEntryList.add(new FilterEntry("calories", EQUAL, calUpper));
        } else {
            if (!calLower.equals(LOWER_LIM))
                filterEntryList.add(new FilterEntry("calories", LARGER_EQUAL, calLower));
            if (!calUpper.equals(UPPER_LIM))
                filterEntryList.add(new FilterEntry("calories", SMALLER_EQUAL, calUpper));
        }

        if (carboLower.equals(carboUpper)) {
            filterEntryList.add(new FilterEntry("carbohydrate", EQUAL, carboUpper));
        } else {
            if (!carboLower.equals(LOWER_LIM))
                filterEntryList.add(new FilterEntry("carbohydrate", LARGER_EQUAL, carboLower));
            if (!carboUpper.equals(UPPER_LIM))
                filterEntryList.add(new FilterEntry("carbohydrate", SMALLER_EQUAL, carboUpper));
        }

        if (fatLower.equals(fatUpper)) {
            filterEntryList.add(new FilterEntry("fat", EQUAL, fatUpper));
        } else {
            if (!fatLower.equals(LOWER_LIM))
                filterEntryList.add(new FilterEntry("fat", LARGER_EQUAL, fatLower));
            if (!fatUpper.equals(UPPER_LIM))
                filterEntryList.add(new FilterEntry("fat", SMALLER_EQUAL, fatUpper));
        }

        if (fiberLower.equals(fiberUpper)) {
            filterEntryList.add(new FilterEntry("fiber", EQUAL, fiberUpper));
        } else {
            if (!fiberLower.equals(LOWER_LIM))
                filterEntryList.add(new FilterEntry("fiber", LARGER_EQUAL, fiberLower));
            if (!fiberUpper.equals(UPPER_LIM))
                filterEntryList.add(new FilterEntry("fiber", SMALLER_EQUAL, fiberUpper));
        }

        if (proteinLower.equals(proteinUpper)) {
            filterEntryList.add(new FilterEntry("protein", EQUAL, proteinUpper));
        } else {
            if (!proteinLower.equals(LOWER_LIM))
                filterEntryList.add(new FilterEntry("protein", LARGER_EQUAL, proteinLower));
            if (!proteinUpper.equals(UPPER_LIM))
                filterEntryList.add(new FilterEntry("protein", SMALLER_EQUAL, proteinUpper));
        }
    }

    private void updateFilterTable() {
        try {
            filterGrid.getChildren().clear();
            filterGrid.add(filterTableHeader1, 1, 0);
            filterGrid.add(filterTableHeader2, 2, 0);
            filterGrid.add(filterTableHeader3, 3, 0);
            for (int i = 0; i < filterEntryList.size(); i++) {
                filterGrid.add(filterEntryList.get(i).getCheckBox(), 0, i + 1, 1, 1);
                filterGrid.add(new Text(filterEntryList.get(i).getNutrient()), 1, i + 1, 1, 1);
                filterGrid.add(new Text(filterEntryList.get(i).getFilterType()), 2, i + 1, 1, 1);
                filterGrid.add(new Text(filterEntryList.get(i).getLimit().toString()), 3, i + 1, 1, 1);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void emptyFoodListAlert() {
        Alert emptyFoodList = new Alert(Alert.AlertType.INFORMATION);
        emptyFoodList.setTitle("ERROR");
        emptyFoodList.setHeaderText("ERROR: Empty Food List");
        emptyFoodList.setContentText("Food data has not yet loaded from file.");
        emptyFoodList.showAndWait();
    }

    //FIXME: testing only
    public void testFilter() {
        //FIXME: testing filter functionalities
        System.out.println("Cal: " + calLower + " " + calUpper);
        System.out.println("Carbo: " + carboLower + " " + carboUpper);
        System.out.println("Fat: " + fatLower + " " + fatUpper);
        System.out.println("Fiber: " + fiberLower + " " + fiberUpper);
        System.out.println("Protein: " + proteinLower + " " + proteinUpper);
        System.out.println((calLower.compareTo(calUpper) >= 0) + " " + (carboLower.compareTo(carboUpper) >= 0) + " " +
                (fatLower.compareTo(fatUpper) >= 0) + " " + (fiberLower.compareTo(fiberUpper) >= 0) + " " +
                (proteinLower.compareTo(proteinUpper) >= 0));
    }
}




class FilterEntry {
    private String nutrient;
    private String filterType;
    private CheckBox checkDeleteFilter;
    private Double limit;
    FilterEntry(){
        System.out.println("Initialization problem");
    }
    FilterEntry(String nutrient, String filterType, Double limit){
        this.nutrient = nutrient;
        this.filterType = filterType;
        this.checkDeleteFilter = new CheckBox();
        this.limit = limit;
    }

    public String getNutrient() {
        return nutrient;
    }
    public String getFilterType() {
        return filterType;
    }
    public boolean getCheckBoxValue() {
        return checkDeleteFilter.isSelected();
    }
    public CheckBox getCheckBox (){
        return checkDeleteFilter;
    }
    public Double getLimit(){
        return limit;
    }

}


