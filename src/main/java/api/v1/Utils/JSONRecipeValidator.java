package api.v1.Utils;

import net.minidev.json.JSONObject;

/**
 * Created by Vlad Stefan on 5/14/2017.
 */


public class JSONRecipeValidator implements Validator {


    /**
     * Validate if a  JsonObject respects the recipe structure
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

        } catch (Exception e) {
            System.out.println("Cast");

            return false;
        }


        // invalid name
        if(name.trim() == "") {
            System.out.println(name);

            return false;
        }

        // invalid difficulty
        if(!difficulty.trim().equals("medium") &&
                !difficulty.equals("easy") &&
                !difficulty.trim().equals("hard")) {

            System.out.println(difficulty);

            return false;
        }

        //invalid duration
        NumberValidator numberValidator = new NumberValidator();

        if(!numberValidator.isValid(duration)){
            System.out.println(duration);

            return false;
        }

        // invalid image url
        URLValidator urlValidator = new URLValidator();

        if(!urlValidator.isValid(image_url)) {
            System.out.println(image_url);

            return false;
        }

        // null uid
        if(uid.trim() == "" ) {
            System.out.println(uid);

            return false;
        }


        return true;
    }
}
