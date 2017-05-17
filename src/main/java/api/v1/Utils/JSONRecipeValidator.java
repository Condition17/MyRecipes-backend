package api.v1.Utils;

import net.minidev.json.JSONObject;

import java.util.List;


/**
 * Created by Vlad Stefan on 5/14/2017.
 */


public class JSONRecipeValidator implements Validator {


    /**
     * Validate if a  JsonObject respects the recipe structure
     * (name, difficulty, duration, image_url and uid)
     * @recipe the JSONObject to validate
     * @return a boolean, the validation result
     */
    public boolean isValid(Object toValidate)
    {

        JSONObject recipe;
        try {
            recipe = (JSONObject) toValidate;
        }
        catch (Exception e)
        {
            System.out.println("Json");
            return false;
        }

        String name;
        String difficulty;
        String duration;
        String image_url;
        String uid;

        try {
            name = recipe.getAsString("name");
            difficulty = recipe.getAsString("difficulty").toLowerCase();
            duration = recipe.getAsString("duration");
            image_url = recipe.getAsString("icon_image");
            uid = recipe.getAsString("uid");

        }
        catch (Exception e) {
            System.out.println("Cast");

            return false;
        }
        // invalid name

        if(name.trim() == "") {
            return false;
        }

        // invalid difficulty
        if(!difficulty.trim().equals("medium") && !difficulty.equals("easy") && !difficulty.trim().equals("hard")) {
            return false;
        }

        //invalid duration
        NumberValidator numberValidator = new NumberValidator();

        if(!numberValidator.isValid(duration)){
            return false;
        }

        // invalid image url
        URLValidator urlValidator = new URLValidator();

        if(!urlValidator.isValid(image_url)) {
            return false;
        }

        // null uid
        if(uid.trim() == "" ) {
            return false;
        }

        return true;
    }

    /**
     * Validate if a  JsonObject respects the recipe structure
     * (name, difficulty, duration, image_url, description, ingredients)
     * @recipe the JSONObject to validate
     * @return a boolean, the validation result
     */
    public boolean isValidFull(Object toValidate){

        JSONObject recipe;
        try {
            recipe = (JSONObject) toValidate;
        }
        catch (Exception e)
        {
            System.out.println("Json");
            return false;
        }

        String name;
        String difficulty;
        String duration;
        String description;

        List<String> images;
        List<JSONObject> ingredientsList;

        try {
            name = recipe.getAsString("name");
            difficulty = recipe.getAsString("difficulty").toLowerCase();
            duration = recipe.getAsString("duration");
            images = (List<String>) recipe.get("images");
            description = recipe.getAsString("description");
            ingredientsList =  (List<JSONObject>) recipe.get("ingredients");
        }
        catch (Exception e) {
            System.out.println("Cast");
            return false;
        }

        // invalid name
        if(name.trim() == "") {
            return false;
        }

        // invalid difficulty
        if(!difficulty.trim().equals("medium") && !difficulty.equals("easy") && !difficulty.trim().equals("hard")) {
            return false;
        }

        //invalid duration
        NumberValidator numberValidator = new NumberValidator();

        if(!numberValidator.isValid(duration)){
            return false;
        }

        // invalid image url
        URLValidator urlValidator = new URLValidator();

        for(String url: images)
        if(!urlValidator.isValid(url)) {
            return false;
        }

        //invalid description
        if(description.trim() == ""){
            return false;
        }

        try {
            for (JSONObject ingredient : ingredientsList) {

                if(!validIngredient(ingredient)){
                    return false;
                }
            }
        }
        catch (Exception e) {
            System.out.println("couldn't iterate through recipes in index method");
        }

        return true;
    }


    /**
     * Internal method to check if an object represents an
     * ingredient JSON
     * @param toValidate object to be validated
     * @return a boolean, validation result
     */

    private boolean validIngredient(Object toValidate){

        JSONObject ingredient;
        try {
            ingredient = (JSONObject) toValidate;
        }
        catch (Exception e)
        {
            System.out.println("Json");
            return false;
        }

        String unit;
        String quantity;
        String name;

        try{
            unit = ingredient.getAsString("unit");
            quantity = ingredient.getAsString("quantity");
            name = ingredient.getAsString("name");
        }
        catch (Exception e)
        {
            System.out.println("cast");
            return false;
        }

        //invalid name
        if(name.trim() == ""){
            return false;
        }

        //invalid unit
        if(unit.trim() == ""){
            return false;
        }

        //invalid quantity
        NumberValidator validator = new NumberValidator();

        if(!validator.isValid(quantity)){
           
            return false;
        }

        return true;
    }


}
