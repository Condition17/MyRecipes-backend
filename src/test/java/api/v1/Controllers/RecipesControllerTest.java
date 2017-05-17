package api.v1.Controllers;

import api.v1.Controllers.RecipesController;
import api.v1.Utils.JSONRecipeValidator;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Vlad Stefan on 5/14/2017.
 */
public class RecipesControllerTest {

    private  RecipesController recipesControllerTest;

    @Before
    public void setUP(){

        recipesControllerTest = new RecipesController();

    }
    @Test
    public void index() {

        succesRequestIndex();
        failRequestIndex();
    }

    @Test
    public void show(){

        succesRequestShow();
        failRequestShow();
    }

    @Test
    public void preview(){

        succesRequestPreview();
        failRequestPreview();
    }

    @Test
    public void search(){

        succesRequestSearch();
        failRequestSearch();
    }

    /**
     * Tests index method of RecipesController class
     * with a valid parameter.
     * It tests the respond's status and the format
     * of recipes list
     */
    private void succesRequestIndex() {

        ResponseEntity result = recipesControllerTest.index(1);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assert(result.hasBody());

        JSONObject body = (JSONObject) result.getBody();
        assert(body.containsKey("recipes"));

        List<JSONObject> recipesList =  (List<JSONObject>) body.get("recipes");
        assertEquals(5, recipesList.size());

        JSONRecipeValidator recipeValidator = new JSONRecipeValidator();
        try {
            for (JSONObject recipe : recipesList) {
                assert(recipeValidator.isValid(recipe));
            }
         }
        catch (Exception e) {
           fail("couldn't iterate through recipes in index method");
        }
    }

    /**
     * Tests index method of RecipesController class
     * with an invalid parameter
     */
    private void failRequestIndex() {

        ResponseEntity result = recipesControllerTest.index(-1);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assert(result.hasBody());
        JSONObject body = (JSONObject) result.getBody();
        assert(body.containsKey("recipes"));
        List<JSONObject> recipesList =  (List<JSONObject>) body.get("recipes");
        assertEquals(0, recipesList.size());

    }

    /**
     * Tests show method of RecipesController class
     * with a valid parameter
     */

    private void succesRequestShow(){

        String validUid = "90a731f7-a60e-4a51-bdb8-8aa46764eace";

        ResponseEntity result = recipesControllerTest.show(validUid);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assert(result.hasBody());

        JSONObject recipe= (JSONObject) result.getBody();
        JSONRecipeValidator validator = new JSONRecipeValidator();
        assert(validator.isValidFull(recipe));
    }

    /**
     * Tests show method of RecipesController class
     * with a wrong parameter
     */
    private void failRequestShow(){

        String validUid = "-90a731f7-a60e-4a51-bdb8-8aa46764eace";

        ResponseEntity result = recipesControllerTest.show(validUid);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assert(result.hasBody());

        JSONObject body = (JSONObject)result.getBody();
        assertEquals(0, body.size());
    }

    /**
     * Tests preview method of RecipesController class
     * with a valid parameter
     */
    private void succesRequestPreview(){

        String validUid = "90a731f7-a60e-4a51-bdb8-8aa46764eace";

        ResponseEntity result = recipesControllerTest.preview(validUid);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assert(result.hasBody());

        JSONObject recipe = (JSONObject) result.getBody();
        JSONRecipeValidator validator = new JSONRecipeValidator();
        assert(validator.isValid(recipe));
    }

    /**
     * Tests preview method of RecipesController class
     * with a wrong parameter
     */
    private void failRequestPreview(){

        String validUid = "-90a731f7-a60e-4a51-bdb8-8aa46764eace";

        ResponseEntity result = recipesControllerTest.preview(validUid);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assert(result.hasBody());

        JSONObject body = (JSONObject)result.getBody();
        assertEquals(0, body.size());
    }

    /**
     * Tests search method of RecipesController class
     * with a valid parameter
     */
    private void succesRequestSearch(){

        HashMap<String,Object> request = new HashMap<String,Object>();
        request.put("keywords", "grill");
        request.put("page", new Integer(1));
        int page = 1;
        while(true) {

            ResponseEntity result = recipesControllerTest.search(request);

            if(result.getStatusCode().equals(HttpStatus.NOT_FOUND))
                break;

            succesResponse(result);

            JSONObject body = (JSONObject) result.getBody();
            Set<JSONObject> recipesList = (Set<JSONObject>) body.get("recipes");
            assert (recipesList.size() > 0);

            JSONRecipeValidator recipeValidator = new JSONRecipeValidator();
            try {
                for (JSONObject recipe : recipesList) {
                    assert (recipe.getAsString("name").toLowerCase().contains("grill"));
                }
            } catch (Exception e) {
               fail("couldn't iterate through recipes in index method");
            }
        page ++;
            request.put("page", page);

        }
    }

    /**
     * Tests search method of RecipesController class
     * with a wrong parameter
     */
    private void failRequestSearch(){

        HashMap<String,Object> request1 = new HashMap<String,Object>();
        request1.put("page1",new Integer(1));
        request1.put("keywords","vegetable grill");
        ResponseEntity result = recipesControllerTest.search(request1);
        failResponse(result, HttpStatus.BAD_REQUEST);

        request1.put("something", new Double(5));
        result = recipesControllerTest.search(request1);
        failResponse(result, HttpStatus.BAD_REQUEST);

        HashMap<String,Object> request2 = new HashMap<String,Object>();
        request2.put("page",new Integer(1));
        request2.put("keyworrds","grill");
        result = recipesControllerTest.search(request2);
        failResponse(result, HttpStatus.BAD_REQUEST);

        HashMap<String,Object> request3 = new HashMap<String,Object>();
        request3.put("page",new Integer(-1));
        request3.put("keywords","grill");
        result = recipesControllerTest.search(request3);
        failResponse(result, HttpStatus.BAD_REQUEST);

        HashMap<String,Object> request4 = new HashMap<String,Object>();
        request4.put("page",new Integer(1000000));
        request4.put("keywords","grill");
        result = recipesControllerTest.search(request4);
        failResponse(result, HttpStatus.NOT_FOUND);

        HashMap<String,Object> request5 = new HashMap<String,Object>();
        request5.put("page",new Integer(1));
        request5.put("keywords","I just wanna go home, :'( *sad face*");
        result = recipesControllerTest.search(request5);
        failResponse(result, HttpStatus.NOT_FOUND);


    }

    private void succesResponse( ResponseEntity result){

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assert(result.hasBody());

        JSONObject body = (JSONObject) result.getBody();
        assert(body.containsKey("recipes"));

        Set<JSONObject> recipesList =  (Set<JSONObject>) body.get("recipes");
        assert(recipesList.size() > 0);

        JSONRecipeValidator recipeValidator = new JSONRecipeValidator();
        try {
            for (JSONObject recipe : recipesList) {
                assert(recipeValidator.isValid(recipe));
            }
        }
        catch (Exception e) {
            fail("couldn't iterate through recipes in index method");
        }
    }

    private void failResponse(ResponseEntity result, HttpStatus status){

        assertEquals(status, result.getStatusCode());

        JSONObject body = (JSONObject) result.getBody();
        Set<JSONObject> recipesList =  (Set<JSONObject>) body.get("recipes");
        assertEquals(0, recipesList.size());
    }

}