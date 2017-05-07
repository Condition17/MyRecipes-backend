package api.v1.Services;

import api.v1.Dao.RecipeDAO;
import api.v1.Dao.RecipeDAOImpl;
import api.v1.Models.Recipe;
import net.minidev.json.JSONObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeServiceImpl implements RecipeService {
    private RecipeDAO recipeDAO = new RecipeDAOImpl();

    @Override
    public List<JSONObject> listRecipes(Integer initial_row, Integer rows) {
        return this.recipeDAO.listRecipes(initial_row,rows);
    }

    @Override
    public JSONObject getRecipeByUuid(String uuid) {
        return this.recipeDAO.getRecipeByUuid(uuid);
    }

    @Override
    public JSONObject getRecipePreview(String uuid){
        return this.recipeDAO.getRecipePreview(uuid);
    }

    @Override
    public Set<JSONObject> searchRecipesBySentence(String sentence, Integer initial_row, Integer rows ){
        sentence = sentence.trim();
        Set<JSONObject> results = new HashSet<>();

            List<JSONObject> recipes = this.recipeDAO.searchRecipesByKeyword(sentence, initial_row, rows);
            for( JSONObject recipe : recipes) results.add(recipe);
        return results;
    }

    @Override
    public Integer addRecipe(Recipe recipe, Set images, Set ingredients, Set steps) {
        return this.recipeDAO.addRecipe(recipe,images,ingredients,steps);
    }

}
