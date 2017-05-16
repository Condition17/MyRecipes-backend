package api.v1.Utils;

import api.v1.Models.Image;
import api.v1.Models.Ingredient;
import api.v1.Models.Recipe;
import api.v1.Models.Step;
import net.minidev.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataFormatter {

    public List<JSONObject> jsonify(List<Step> steps){

        List<JSONObject> jsonSteps = new ArrayList<>();

        if( steps != null ) for( Step step : steps) jsonSteps.add(jsonify(step));
        return jsonSteps;

    }

    public List<JSONObject> jsonifyList(List<Object[]> recipes) {

        List<JSONObject> objects = new ArrayList<>();

        for (Object[] r : recipes) {
            JSONObject recipe = toRecipe(r);
            objects.add(recipe);
        }

        return objects;

    }

    private JSONObject jsonify(Step s){

        JSONObject res = new JSONObject();

        res.appendField("task",s.getName());
        res.appendField("description", s.getDescription());

        return res;

    }

    private JSONObject toRecipe( Object[] r){

        JSONObject recipe = new JSONObject();

        recipe.appendField("name", r[0]);
        recipe.appendField("difficulty", r[1]);
        recipe.appendField("duration", r[2]);
        recipe.appendField("icon_image", r[3]);
        recipe.appendField("uid", r[4]);

        return recipe;

    }

    public JSONObject jsonify( Recipe r){

        JSONObject recipe = new JSONObject();

        recipe.appendField("name", r.getName());
        recipe.appendField("images", imagesOf(r));
        recipe.appendField("difficulty", r.getDifficulty());
        recipe.appendField("description", r.getDescription());
        recipe.appendField("duration", r.getDuration());
        recipe.appendField("icon_image", r.getIcon_image());
        recipe.appendField("ingredients", ingredientsOf(r) );

        return recipe;

    }

    public JSONObject preview( Recipe r){

        JSONObject preview = new JSONObject();

        preview.appendField("name", r.getName());
        preview.appendField("difficulty", r.getDifficulty());
        preview.appendField("duration", r.getDuration());
        preview.appendField("icon_image", r.getIcon_image());
        preview.appendField("uid", r.getUuid());

        return preview;

    }

    private List<String> imagesOf( Recipe r){

        List<String> urls = new ArrayList<>();
        Set<Image> images = (Set<Image>) r.getImages();

        for( Image im: images) urls.add(im.getUrl());

        return urls;

    }

    private List<JSONObject> ingredientsOf( Recipe r){

        List<JSONObject> jsonIngredients = new ArrayList<>();
        Set<Ingredient> ingredients = (Set<Ingredient>) r.getIngredients();

        for( Ingredient ing : ingredients){

            JSONObject ingredient = new JSONObject();
            ingredient.appendField("name",ing.getName());
            ingredient.appendField("quantity", ing.getQuantity());
            ingredient.appendField("unit", ing.getUnit());
            jsonIngredients.add(ingredient);

        }

        return jsonIngredients;
    }


}
