package api.v1.Controllers;

import api.v1.Models.Recipe;

import api.v1.Services.RecipeService;
import api.v1.Services.RecipeServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<Recipe> list = this.recipeService.listRecipes();
        int left_limit = (page_number-1)*page_size;
        int right_limit = page_number*page_size;

        if( left_limit > list.size() || left_limit < 0) {
            res.appendField("recipes", new ArrayList<Recipe>());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }else{
            res.appendField("recipes",list.subList( left_limit , right_limit > list.size() ? list.size() : right_limit ) );
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }

    }

    @RequestMapping("/api/v1/recipes/{uid}")
    public Recipe show() {
        return null;
    }
}