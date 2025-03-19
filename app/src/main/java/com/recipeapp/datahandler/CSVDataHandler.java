package com.recipeapp.datahandler;

import java.io.IOException;
import java.util.ArrayList;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler{
    private String filePath;

    public CSVDataHandler(){
        this.filePath = "app/src/main/resources/recipes.csv";
    }
    
    public CSVDataHandler(String filePath){
        this.filePath = filePath;
    }

    public String getMode(){
        return "CSV";
    }

    public ArrayList<Recipe> readData() throws IOException{
        return null;
    }

    public void writeData(Recipe recipe) throws IOException{

    }

    public ArrayList<Recipe> searchData(String keyword) throws IOException{
        return null;
    }
}