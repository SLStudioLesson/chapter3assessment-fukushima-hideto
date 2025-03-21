package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler{
    private String filePath;

    public CSVDataHandler(){
        this.filePath = "app/src/main/resources/recipes.csv";
    }
    
    public CSVDataHandler(String filePath){
        this.filePath = filePath;
    }

    @Override
    public String getMode(){
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException{
        ArrayList<Recipe> recipes = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath));){
            String line;
            String name = "";
            //1行ごと受け取る
            while((line = reader.readLine()) != null ){
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                //,で区切りはじめはnameに残りはingredientsに追加する
                String[] pairs = line.split(",");
                for(int i = 0; i <pairs.length; i++){
                    if(i == 0){
                        name = pairs[i];
                    } else {
                        ingredients.add(new Ingredient(pairs[i]));
                    }
                }
                //ArrayList<Recipe>に代入する
                recipes.add(new Recipe(name, ingredients));
            }
        }catch(IOException e){
            System.out.println("Error reading file:" + e.getMessage());
        }

        return recipes;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException{
        String text = recipe.getName();
        String ingredient = "";
        for(int i = 0; i < recipe.getIngredients().size(); i++){
            ingredient += "," + recipe.getIngredients().get(i).getName();
        }
        text += ingredient;
        BufferedWriter writer = null;
        try {
            writer =new BufferedWriter(new FileWriter(filePath, true));
            writer .write(text);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("入力に誤りがありました。");
        }finally{
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException{
        return null;
    }
}