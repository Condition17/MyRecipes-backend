package api.v1.Controllers;

import api.v1.Models.Recipe;

import api.v1.Services.RecipeService;
import api.v1.Services.RecipeServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipesController {
    private RecipeService recipeService = new RecipeServiceImpl();

    @RequestMapping("/api/v1/recipes")
    public ResponseEntity<?> index(){
        JSONObject res = new JSONObject();
        List<Recipe> list = this.recipeService.listRecipes();
        res.appendField("recipes",list);
        return list.size() == 0  ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res) : ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @RequestMapping("/api/v1/recipes/{uid}")
    public Recipe show() {
        return null;
    }
}