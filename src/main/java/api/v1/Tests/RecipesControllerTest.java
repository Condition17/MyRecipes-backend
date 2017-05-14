package api.v1.Tests;

import api.v1.Utils.JSONRecipeValidator;
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

    @Test
    public void index() throws Exception {

        succesRequestIndex();
        failRequestIndex();

    }

    @Test
    public void show() throws Exception {
    }

    @Test
    public void preview() throws Exception {
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

        RecipesController recipesControllerTest = new RecipesController();

        ResponseEntity result = recipesControllerTest.index(1);

        assertEquals(HttpStatus.OK, result.getStatusCode());

        assertEquals(true,result.hasBody());

        if(!result.hasBody())
            System.out.println("Body");

        JSONObject body = (JSONObject) result.getBody();

        assertEquals(true,body.containsKey("recipes"));

        List<JSONObject> recipesList =  (List<JSONObject>) body.get("recipes");

        assertEquals(5, recipesList.size());

        JSONRecipeValidator recipeValidator = new JSONRecipeValidator();

        try {
            for (JSONObject recipe : recipesList) {

                assertEquals(true,recipeValidator.isValid(recipe));
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

        RecipesController recipesControllerTest = new RecipesController();

        ResponseEntity resultNegativeInt = recipesControllerTest.index(-1);
        assertEquals(HttpStatus.BAD_REQUEST, resultNegativeInt.getStatusCode());

    }
}