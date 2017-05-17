package api.v1.Controllers;

import api.v1.Services.RecipeService;
import api.v1.Services.RecipeServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RecipesController {

    private final RecipeService recipeService = new RecipeServiceImpl();
    private final Integer pageSize = 5;

    @RequestMapping("/api/v1/recipes")
    public ResponseEntity<?> index(@RequestParam(value="page")Integer page_number){

        List<JSONObject> recipesList;
        JSONObject resBody = new JSONObject().appendField("recipes",new ArrayList<>());
        final Integer initialRow = (page_number-1)*pageSize;

        if( initialRow < 0 )
            return new ResponseEntity<>(resBody,HttpStatus.NOT_FOUND);

        recipesList = this.recipeService.listRecipes(initialRow, pageSize);
        resBody.put("recipes", recipesList);

        return new ResponseEntity<>(resBody, recipesList.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND );

    }

    @RequestMapping("/api/v1/recipes/{uid}")
    public ResponseEntity<?> show(@PathVariable("uid")	String uid) {

        JSONObject recipe = this.recipeService.getRecipeByUuid(uid);

        return  (recipe != null) ?
                new ResponseEntity<>(recipe, HttpStatus.OK) :
                new ResponseEntity<>(new JSONObject(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/api/v1/previews/{uid}")
    public ResponseEntity<?> preview(@PathVariable("uid")	String uid) {

        JSONObject recipe = this.recipeService.getRecipePreview(uid);

        return (recipe != null) ?
                new ResponseEntity<>(recipe, HttpStatus.OK) :
                new ResponseEntity<>(new JSONObject(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/api/v1/recipes", method = RequestMethod.POST)
    public ResponseEntity<?> search( @RequestBody Map<String,Object> reqBody ){

        Integer pageNumber, initialRow;
        JSONObject resBody = new JSONObject().appendField("recipes", new HashSet<>());
        String searchSentence;


        try{
            validateReqFields(reqBody);
            pageNumber = (Integer) reqBody.get("page");
            initialRow = ( pageNumber - 1) * pageSize;
            searchSentence = (String) reqBody.get("keywords");
            if ( initialRow < 0 ) throw new Exception();

        }catch( Exception e){
            return new ResponseEntity<>(resBody, HttpStatus.BAD_REQUEST);
        }

        Set<JSONObject> results = this.recipeService.searchRecipesBySentence( searchSentence, initialRow, pageSize);
        resBody.put("recipes", results);

        return new ResponseEntity<>(resBody, results.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);

    }

    private void validateReqFields(Map<String, Object> reqBody) throws Exception {
            if( reqBody.size() != 2 || !(reqBody.containsKey("page") && reqBody.containsKey("keywords")) )
                throw new Exception();
    }
}

