package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class preserves information of all FoodItems added to meal plan. It uses a list to preserve
 * FoodItems added to it, and a HashMap to preserve the sum of each kind of nutrients. Getters and
 * Setters are provided for other classes which uses it.
 * @author Leon Zhang
 */
public class MealList {

    private List<FoodItem> mealList; // The list containing all FoodItems in the meal plan
    private HashMap<String, Double> nutritionSummary; // The HashMap containing nutrition sums

    /**
     * This constructor construct a new MealList with an ArrayList and a HashMap.
     */
    public MealList() {
        mealList = new ArrayList<>();
        nutritionSummary = new HashMap<>();
    }

    /**
     * This method adds a foodItem to the MealList.
     * @param foodItem the foodItem to be added to the MealList
     */
    public void addItem(FoodItem foodItem) {
        if (foodItem == null) throw new NullPointerException();
        mealList.add(foodItem);
    }

    /**
     * This method removes a foodItem from the MealList.
     * @param foodItem the foodItem to be removed from the MealList
     */
    public void removeItem(FoodItem foodItem) {
        if (foodItem == null) throw new NullPointerException();
        if (mealList.isEmpty()) return;
        mealList.remove(mealList.indexOf(foodItem));
    }

    /**
     * This method traverses through all the MealList and adds up all the nutrition.
     */
    public void analyzeMeal() {
        nutritionSummary.put("calories", sumNutrition("calories"));
        nutritionSummary.put("fat", sumNutrition("fat"));
        nutritionSummary.put("carbohydrate", sumNutrition("carbohydrate"));
        nutritionSummary.put("fiber", sumNutrition("fiber"));
        nutritionSummary.put("protein", sumNutrition("protein"));
    }

    /**
     * This method clear the entire MealList.
     */
    public void clearAll() {
        mealList = new ArrayList<>();
    }

    /**
     * This method helps to calculate the sum of one nutrition.
     * @param nutrition the nutrition to be calculated
     * @return the sum of this nutrition
     */
    private double sumNutrition(String nutrition) {
        if (mealList.isEmpty()) return 0;

        double sumNutrition = 0;
        for (FoodItem curItem : mealList) {
            double curNutrition = curItem.getNutrientValue(nutrition);
            sumNutrition += curNutrition;
        }

        return sumNutrition;
    }

    /**
     * This method give the caller the whole mealList.
     * @return the entire mealList
     */
    public List<FoodItem> getMealList() {
        return mealList;
    }

    /**
     * This method give the caller the whole nutritionSummary HashMap.
     * @return the entire nutritionSummary HashMap
     */
    public HashMap<String, Double> getNutritionSummary() {
        return nutritionSummary;
    }
}