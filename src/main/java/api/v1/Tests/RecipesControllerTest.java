package api.v1.Tests;

import api.v1.Utils.JSONRecipeValidator;
import nyla.solutions.global.json.JSON;
import org.apache.http.protocol.HTTP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.v1.Controllers.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import java.util.List;

import  net.minidev.json.JSONObject;

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
    public void preview() throws Exception {

        succesRequestPreview();
        failRequestPreview();
    }

    @Test
    public void search() throws Exception {
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
        assertEquals(true,result.hasBody());

        JSONObject body = (JSONObject) result.getBody();
        assertEquals(true,body.containsKey("recipes"));

        List<JSONObject> recipesList =  (List<JSONObject>) body.get("recipes");
        assertEquals(5, recipesList.size());

        JSONRecipeValidator recipeValidator = new JSONRecipeValidator();
        try {
            for (JSONObject recipe : recipesList) {

                assertEquals(true, recipeValidator.isValid(recipe));
            }
        }
        catch (Exception e) {

            System.out.println("couldn't iterate through recipes in index method");
        }
    }

    /**
     * Tests index method of RecipesController class
     * with an invalid parameter
     */
    private void failRequestIndex() {

        ResponseEntity resultNegativeInt = recipesControllerTest.index(-1);
        assertEquals(HttpStatus.BAD_REQUEST, resultNegativeInt.getStatusCode());

        JSONObject body = (JSONObject) resultNegativeInt.getBody();
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
        assertEquals(true, result.hasBody());

        JSONObject recipe= (JSONObject) result.getBody();
        JSONRecipeValidator validator = new JSONRecipeValidator();
        assertEquals(true,validator.isValidFull(recipe));

    }


    /**
     * Tests show method of RecipesController class
     * with a wrong parameter
     */

    private void failRequestShow(){

        String validUid = "-90a731f7-a60e-4a51-bdb8-8aa46764eace";

        ResponseEntity result = recipesControllerTest.show(validUid);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(true, result.hasBody());

        JSONObject body = (JSONObject)result.getBody();
        assertEquals(0, body.size());
    }

    private void succesRequestPreview(){

        String validUid = "90a731f7-a60e-4a51-bdb8-8aa46764eace";

        ResponseEntity result = recipesControllerTest.preview(validUid);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.hasBody());

        JSONObject recipe = (JSONObject) result.getBody();
        JSONRecipeValidator validator = new JSONRecipeValidator();
        assertEquals(true,validator.isValid(recipe));

    }

    private void failRequestPreview(){

        String validUid = "-90a731f7-a60e-4a51-bdb8-8aa46764eace";

        ResponseEntity result = recipesControllerTest.preview(validUid);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(true, result.hasBody());

        JSONObject body = (JSONObject)result.getBody();
        assertEquals(0, body.size());

    }
}