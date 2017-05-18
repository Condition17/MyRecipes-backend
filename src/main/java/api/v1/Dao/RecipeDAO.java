package api.v1.Dao;
import api.v1.Models.Recipe;
import java.util.List;
import java.util.Set;

public interface RecipeDAO {
    List listRecipes(Integer initial_row, Integer rows);
    Recipe getRecipeByUuid(String uuid);
    Recipe getRecipePreview(String uuid);
    List searchRecipesByKeyword( String keyword, Integer initial_row, Integer rows );
    Integer addRecipe(Recipe recipe, Set images, Set ingredients, List steps);
}