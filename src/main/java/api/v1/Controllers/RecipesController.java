package api.v1.Controllers;

import api.v1.Models.Recipe;

import api.v1.Services.RecipeService;
import api.v1.Services.RecipeServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @RequestMapping(value = "/api/v1/recipes", method = RequestMethod.POST)
    public ResponseEntity<?> search( @RequestBody Map<String,Object> req ){
        Integer page_number = 0;
        Integer initial_row = 0;
        JSONObject res = new JSONObject();
        res.appendField("recipes", new ArrayList<>());

        try{
            page_number = (Integer) req.get("page");
            initial_row = (page_number-1)*page_size;
            if ( initial_row < 0 ) throw new Exception();
        }catch( Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }

        String search_sentence = (String) req.get("keywords");
        Set<JSONObject> results = this.recipeService.searchRecipesBySentence( search_sentence, initial_row, page_size);
        res.put("recipes", results);
        return results.size() > 0 ? ResponseEntity.status(HttpStatus.OK).body(res) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
}

