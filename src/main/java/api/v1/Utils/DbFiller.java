package api.v1.Utils;

import api.v1.Models.Image;
import api.v1.Models.Ingredient;
import api.v1.Models.Recipe;
import api.v1.Models.Step;
import api.v1.Services.RecipeService;
import api.v1.Services.RecipeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by cristi on 5/7/17.
 */
public class DbFiller {

    static final String app_id = "347a3693";
    static final String app_key = "51e7b7d3123ab9fe568d44adf966e51b";
    static final String mock_text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore " +
            "magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
            "ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";

    static final String[] keywords = {"chicken","ham","lamb","soup"};
//    "pizza","hamburger","porc","egg","salad",
//            "cocktail","italian", "greek", "american", "chocolate","desert","cherry","gordon","indian",
//            "pineapple", "pie", "turkey", "fish", "tomato", "squid", "herbs", "spaghetti", "baked", "fried"};
    static final RecipeService recipeService = new RecipeServiceImpl();
    public static void main(String[] args) throws IOException {


        for( String keyword : keywords ) {
            ArrayList<JSONObject> recipesJSON = apiCallResultsWith(keyword);
            for (Map recipe : recipesJSON) {
                insertIntoDb((Map) recipe.get("recipe"));
            }
        }

    }

    private static ArrayList<JSONObject> apiCallResultsWith(String keyword) throws IOException {
        String url = "https://api.edamam.com/search?q="+keyword+"&app_id="+app_id+"&app_key="+app_key+"&from=0&to=100";
        String response = responseFromGet( url );
        if( response.equals("not found") ) return new ArrayList<>();
        JSONObject response_json = new ObjectMapper().readValue(response, JSONObject.class);
        return (ArrayList<JSONObject>) response_json.get("hits");
    }

    private static String responseFromGet( String url ) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        int responseCode = con.getResponseCode();
        if( responseCode == 404 ) return "not found";
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        con.disconnect();
        return response.toString();
    }

    private static void insertIntoDb(Map recipe){

        String difficulties[] = {"Easy", "Medium", "Hard"};
        String name = (String) recipe.get("label");
        String icon_image = (String) recipe.get("image");
        String description = mock_text;
        String difficulty = difficulties[generateRandomBetween(0,3)];
        String uuid = (UUID.randomUUID()).toString();
        Integer duration = generateRandomBetween(1200,10800);
        Recipe rec = new Recipe(name,difficulty,description,duration,icon_image);
        rec.setUuid(uuid);
        recipeService.addRecipe(rec,getImages(name,icon_image),getIngredients(recipe),getSteps(name));
    }

    private static Set getIngredients(Map recipe){
        HashSet ingredients = new HashSet();
        ArrayList<JSONObject> initial_ingredients = (ArrayList<JSONObject>) recipe.get("ingredients");
        for( Map component : initial_ingredients ){
            ingredients.add( new Ingredient( (String) component.get("text"), (Double) component.get("weight"), "g"));
        }
        return ingredients;
    }

    private static Set getSteps(String recipe){

        HashSet tasks = new HashSet();
        Integer step_number = 7+ generateRandomBetween(1,9);

        for(int i = 1; i<= step_number; i++){
            String name = "Task number "+i;
            String description = "This is the task with the number "+i+" of the recipe \\\""+recipe+
                                " preparation. So, you should do "+mock_text;
            tasks.add(new Step(name,description));
        }
        return tasks;

    }

    private static Integer generateRandomBetween(Integer left, Integer right){
        Random rand = new Random();
        Integer result;
        do{
            result = left + rand.nextInt()%(right-left);
        }while(result < left || result > right);

        return result;
    }
    private static Set getImages(String recipe, String icon_image){
        //here should be another api call for getting images associated with recipe
        //this thing MAYBE will be developed
        //..but I don't think so
        HashSet images = new HashSet();
        images.add(new Image(icon_image));
        return images;
    }
}
