package api.v1.Services;

import api.v1.Dao.RecipeDAO;
import api.v1.Dao.RecipeDAOImpl;
import api.v1.Models.Recipe;
import api.v1.Utils.DataFormatter;
import net.minidev.json.JSONObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeServiceImpl implements RecipeService {

    private final RecipeDAO recipeDAO = new RecipeDAOImpl();
    private final DataFormatter formatter = new DataFormatter();

    @Override
    public List<JSONObject> listRecipes(Integer initial_row, Integer rows) {
        return formatter.jsonifyList(this.recipeDAO.listRecipes(initial_row,rows) );
    }

    @Override
    public JSONObject getRecipeByUuid(String uuid) {

        Recipe result = this.recipeDAO.getRecipeByUuid(uuid);
        return result == null ? null : formatter.jsonify(result);

    }

    @Override
    public JSONObject getRecipePreview(String uuid){

        Recipe result = this.recipeDAO.getRecipePreview(uuid);
        return result == null ? null : formatter.preview(result);

    }

    @Override
    public Set<JSONObject> searchRecipesBySentence(String sentence, Integer initial_row, Integer rows ){

        List foundRecipes = this.recipeDAO.searchRecipesByKeyword(sentence.trim(), initial_row, rows);
        List<JSONObject> recipes = formatter.jsonifyList( foundRecipes );

        Set<JSONObject> results = new HashSet<>();
        for( JSONObject recipe : recipes) results.add(recipe);

        return results;

    }

    @Override
    public Integer addRecipe(Recipe recipe, Set images, Set ingredients, Set steps) {
        return this.recipeDAO.addRecipe(recipe,images,ingredients,steps);
    }

}