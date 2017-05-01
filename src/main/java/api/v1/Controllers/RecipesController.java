package api.v1.Controllers;

import api.v1.Models.Recipe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipesController {

    @RequestMapping("/api/v1/recipes")
    public Recipe index() {
        return null;
    }

    @RequestMapping("/api/v1/recipes/{uid}")
    public Recipe show() {
        return null;
    }
}