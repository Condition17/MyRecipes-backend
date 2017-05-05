package api.v1.Controllers;

import api.v1.Models.Recipe;

import api.v1.Services.RecipeService;
import api.v1.Services.RecipeServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecipesController {
    private RecipeService recipeService = new RecipeServiceImpl();
    private Integer page_size = 5;

    @RequestMapping("/api/v1/recipes")
    public ResponseEntity<?> index(@RequestParam(value="page")Integer page_number){

        JSONObject res = new JSONObject();
        Integer initial_row = (page_number-1)*page_size;

        if( initial_row >= 0) {
            List<JSONObject> list = this.recipeService.listRecipes(initial_row, page_size);
            res.appendField("recipes", list);
            return (list.size() > 0) ? ResponseEntity.status(HttpStatus.OK).body(res) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

        res.appendField("recipes", new ArrayList<>());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

    }

    @RequestMapping("/api/v1/recipes/{uid}")
    public ResponseEntity<?> show(@PathVariable("uid")	String uid) {
        JSONObject recipe = this.recipeService.getRecipeByUuid(uid);
        return (recipe != null) ? ResponseEntity.status(HttpStatus.OK).body(recipe) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JSONObject());
    }

    @RequestMapping("/api/v1/preview/{uid}")
    public ResponseEntity<?> preview(@PathVariable("uid")	String uid) {
        JSONObject recipe = this.recipeService.getRecipePreview(uid);
        return (recipe != null) ? ResponseEntity.status(HttpStatus.OK).body(recipe) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JSONObject());
    }
}