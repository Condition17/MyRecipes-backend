package api.v1.Services;

import api.v1.Dao.RecipeDAO;
import api.v1.Dao.RecipeDAOImpl;
import api.v1.Models.Recipe;
import net.minidev.json.JSONObject;

import javax.transaction.Transactional;
import java.util.List;

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
}
