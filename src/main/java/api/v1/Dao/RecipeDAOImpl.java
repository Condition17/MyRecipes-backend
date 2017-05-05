package api.v1.Dao;

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
        CriteriaQuery<Recipe> crit_query = session.getCriteriaBuilder().createQuery(Recipe.class);
        Root<Recipe> recipes = crit_query.from(Recipe.class);
        crit_query.multiselect(recipes.get("name"));
        crit_query.where(session.getCriteriaBuilder().equal(recipes.get("uuid"), uid));
        List<Recipe> list = session.createQuery(crit_query).setMaxResults(1).list();
        return list.size() > 0 ? proper_jsonify(list.get(0)) : null;
    }

    private JSONObject proper_jsonify( Object[] r){
        JSONObject recipe = new JSONObject();
        recipe.appendField("name", r[0]);
        recipe.appendField("difficulty", r[1]);
        recipe.appendField("duration", r[2]);
        recipe.appendField("icon_image", r[3]);
        recipe.appendField("uuid", r[4]);
        return recipe;
    }

    private JSONObject proper_jsonify( Recipe r){
        JSONObject recipe = new JSONObject();
        recipe.appendField("name", r.getName());
        recipe.appendField("images", r.getImages());
        recipe.appendField("difficulty", r.getDifficulty());
        recipe.appendField("duration", r.getDuration());
        recipe.appendField("icon_image", r.getIcon_image());
        //should add ingredients
        //       recipe.appendField("steps")
        return recipe;
    }

    private List<JSONObject> showable(List<Object[]> recipes){
        List<JSONObject> objects = new ArrayList<>();
        for( Object[] r : recipes){
            JSONObject recipe = proper_jsonify(r);
            objects.add( recipe );
        }
        return objects;
    }
}
