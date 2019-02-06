package com.example.vipin.inclass08;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Json {
    static public class RecipeJSONParser {
        static ArrayList<Recipe> parseRecipes(String in) throws JSONException {
            ArrayList<Recipe> personList = new ArrayList<Recipe>();
            JSONObject root = new JSONObject(in);
            JSONArray personJsonArray = root.getJSONArray("results");
            for (int i = 0; i < personJsonArray.length(); i++) {
                JSONObject personsJSONObject = personJsonArray.getJSONObject(i);
                Recipe person = new Recipe();
                person.setTitle(personsJSONObject.getString("title"));
                person.setIngredients(personsJSONObject.getString("ingredients"));
                person.setImage(personsJSONObject.getString("thumbnail"));
                person.setUrl(personsJSONObject.getString("href"));
                person.setIndex(i);
                personList.add(person);

            }
            return personList;
        }
    }
}
