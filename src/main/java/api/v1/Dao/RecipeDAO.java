package api.v1.Dao;
import api.v1.Models.Recipe;
import net.minidev.json.JSONObject;

import java.util.List;
import java.util.Set;

public interface RecipeDAO {
    List<JSONObject> listRecipes(Integer initial_row, Integer rows);
    JSONObject getRecipeByUuid(String uuid);
    JSONObject getRecipePreview(String uuid);
    List<JSONObject> searchRecipesByKeyword( String keyword, Integer initial_row, Integer rows );
    Integer addRecipe(Recipe recipe, Set images, Set ingredients, Set steps);
}