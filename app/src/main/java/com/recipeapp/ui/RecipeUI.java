package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes(){
        try {
            ArrayList<Recipe> recipes = dataHandler.readData();
            if(recipes.isEmpty()){
                System.out.println();
                System.out.println("No recipes available.");
                return;
            }

            System.out.println("Recipes:");
            System.out.println("-----------------------------------");
            for(Recipe recipe : recipes){
                //nameを受け取る
                System.out.println("Recipe Name:" + recipe.getName());
                //ingredientを受け取る
                String text = "";
                ArrayList<Ingredient> ingredients = recipe.getIngredients();
                for(int i = 0; i < ingredients.size(); i++){
                    text += ingredients.get(i).getName();
                    if(i < ingredients.size() - 1){
                        text += ",";
                    }
                }
                System.out.println("Main Ingredients:" + text);
                System.out.println("-----------------------------------");
    
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
    }

    private void addNewRecipe(){
        try {
            //レシピ名入力
            System.out.println("Adding a new recipe.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter recipe name:");
            String RecipeName = reader.readLine();

            //材料名入力
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Enter ingredients (type 'done' when finished):");
            while(true){
                System.out.print("Ingredient:");
                String ingredient = reader.readLine();

                if(ingredient.equals("done")){
                    System.out.println("Recipe added successfully.");
                    break;
                }
                ingredients.add(new Ingredient(ingredient));
            }

            //インスタンス化
            Recipe recipe = new Recipe(RecipeName, ingredients);
            //writeData()メソッド使用
            dataHandler.writeData(recipe);

        } catch (IOException e) {
            System.out.println("Failed to add new recipe:" + e.getMessage());
        }




    }
}
