package api.v1.Dao;
import api.v1.Models.Recipe;
import java.util.List;

public interface RecipeDAO {
    List<Recipe> listRecipes();
    Recipe getRecipeById(int id);
}
