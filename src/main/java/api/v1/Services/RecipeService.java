package api.v1.Services;
import api.v1.Models.Recipe;
import net.minidev.json.JSONObject;
import java.util.List;
import java.util.Set;

public interface RecipeService {
    List<JSONObject> listRecipes(Integer initial_row, Integer rows);
    JSONObject getRecipeByUuid(String uuid);
    JSONObject getRecipePreview( String uuid);
    Set<JSONObject> searchRecipesBySentence(String sentence, Integer intial_row, Integer rows);
    Integer addRecipe(Recipe recipe, Set images, Set ingeredients, List steps);
}