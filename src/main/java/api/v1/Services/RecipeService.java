package api.v1.Services;
import api.v1.Models.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> listRecipes();
    Recipe getRecipeById(int id);
}
