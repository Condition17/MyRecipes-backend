package api.v1.Services;

import api.v1.Dao.RecipeDAO;
import api.v1.Dao.RecipeDAOImpl;
import api.v1.Models.Recipe;

import javax.transaction.Transactional;
import java.util.List;

public class RecipeServiceImpl implements RecipeService {
    private RecipeDAO recipeDAO = new RecipeDAOImpl();

    @Override
    @Transactional
    public List<Recipe> listRecipes() {
        return this.recipeDAO.listRecipes();
    }

    @Override
    @Transactional
    public Recipe getRecipeById(int id) {
        return null;
    }
}
