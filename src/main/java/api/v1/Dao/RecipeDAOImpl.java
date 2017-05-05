package api.v1.Dao;

import api.v1.Models.Image;
import api.v1.Models.Ingredient;
import api.v1.Models.Recipe;
import com.sun.corba.se.spi.ior.ObjectKey;
import com.sun.org.apache.regexp.internal.RE;
import net.minidev.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecipeDAOImpl implements RecipeDAO{

    private SessionFactory sessionFactory;
    {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public List<JSONObject> listRecipes(Integer initial_row, Integer rows) {
        Session session = this.sessionFactory.openSession();
        List<Object[]> recipes = session.createQuery("select name, difficulty, duration, icon_image, uuid from Recipe ")
                .setFirstResult(initial_row)
                .setMaxResults(rows).list();
      return showable(recipes);
    }

    @Override
    public JSONObject getRecipeByUuid(String uid) {

        Session session = this.sessionFactory.openSession();
        List<Recipe> list = session.createQuery(" from Recipe R where R.uuid = :uid")
                            .setParameter("uid",uid).setMaxResults(1).list();
        return list.size() > 0 ? jsonify(list.get(0)) : null;

    }

    @Override
    public JSONObject getRecipePreview(String uid) {

        Session session = this.sessionFactory.openSession();
        List<Recipe> list = session.createQuery(" from Recipe R where R.uuid = :uid")
                .setParameter("uid",uid).setMaxResults(1).list();
        return list.size() > 0 ? preview(list.get(0)) : null;

    }

    private JSONObject preview( Recipe r){
        JSONObject preview = new JSONObject();
        preview.appendField("name", r.getName());
        preview.appendField("difficulty", r.getDifficulty());
        preview.appendField("duration", r.getDuration());
        preview.appendField("icon_image", r.getIcon_image());
        preview.appendField("uid", r.getIcon_image());
        return preview;
    }

    private JSONObject jsonifyList( Object[] r){
        JSONObject recipe = new JSONObject();
        recipe.appendField("name", r[0]);
        recipe.appendField("difficulty", r[1]);
        recipe.appendField("duration", r[2]);
        recipe.appendField("icon_image", r[3]);
        recipe.appendField("uid", r[4]);
        return recipe;
    }

    private JSONObject jsonify( Recipe r){
        JSONObject recipe = new JSONObject();
        recipe.appendField("name", r.getName());
        recipe.appendField("images", imagesOf(r));
        recipe.appendField("difficulty", r.getDifficulty());
        recipe.appendField("duration", r.getDuration());
        recipe.appendField("icon_image", r.getIcon_image());
        recipe.appendField("ingredients", ingredientsOf(r) );
        return recipe;
    }

    private List<JSONObject> showable(List<Object[]> recipes){
        List<JSONObject> objects = new ArrayList<>();
        for( Object[] r : recipes){
            JSONObject recipe = jsonifyList(r);
            objects.add( recipe );
        }
        return objects;
    }

    private List<String> imagesOf( Recipe r){
        List<String> urls = new ArrayList<>();
        Set<Image> images = r.getImages();
        for( Image im: images) urls.add(im.getUrl());
        return urls;
    }

    private List<JSONObject> ingredientsOf( Recipe r){

        List<JSONObject> jsonIngredients = new ArrayList<>();
        Set<Ingredient> ingredients = r.getIngredients();

        for( Ingredient ing : ingredients){

            JSONObject ingredient = new JSONObject();
            ingredient.appendField("name",ing.getName());
            ingredient.appendField("quantity", ing.getQuantity());
            ingredient.appendField("unit", ing.getUnit());
            jsonIngredients.add(ingredient);

        }
        return jsonIngredients;
    }
}
