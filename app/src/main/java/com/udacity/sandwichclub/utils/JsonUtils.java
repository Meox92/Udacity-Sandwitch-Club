package com.udacity.sandwichclub.utils;

import android.content.Context;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        final String NAME_LIST = "name";
        final String MAIN_NAME = "mainName";
        final String ALSO_KNOWN_AS = "alsoKnownAs";

        final String PLACE_OF_ORIGIN = "placeOfOrigin";
        final String DESCRIPTION = "description";
        final String INGREDIENTS = "ingredients";
        final String IMAGE = "image";


        List<String> alsoKnownAsList = new ArrayList<String>();
        List<String> ingredientsList = new ArrayList<String>();

        Sandwich sandwich = null;

        try{
            JSONObject sandwitchJson = new JSONObject(json);

            JSONObject nameArray = sandwitchJson.getJSONObject(NAME_LIST);
            String mainName = nameArray.getString(MAIN_NAME);
            JSONArray alsoKnownAs = nameArray.getJSONArray(ALSO_KNOWN_AS);
            if (alsoKnownAs != null) {
                for (int i = 0; i < alsoKnownAs.length(); i++) {
                    alsoKnownAsList.add(alsoKnownAs.getString(i));
                }
            }
            String placeOfOrigin = sandwitchJson.getString(PLACE_OF_ORIGIN);
            String description = sandwitchJson.getString(DESCRIPTION);
            String image = sandwitchJson.getString(IMAGE);
            JSONArray ingredients = sandwitchJson.getJSONArray(INGREDIENTS);
            if (ingredients != null) {
                for (int i = 0; i < ingredients.length(); i++) {
                    ingredientsList.add(ingredients.getString(i));
                }
            }

            sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);

        }catch (JSONException e){
            e.printStackTrace();
            Log.i("JSONException", e.toString());
        }

        return sandwich;
    }


}
