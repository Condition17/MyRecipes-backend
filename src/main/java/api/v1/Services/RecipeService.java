package api.v1.Services;
import api.v1.Models.Recipe;
import net.minidev.json.JSONObject;

import java.util.List;

public interface RecipeService {
    List<JSONObject> listRecipes(Integer initial_row, Integer rows);
    JSONObject getRecipeByUuid(String uuid);
    JSONObject getRecipePreview( String uuid);
}
