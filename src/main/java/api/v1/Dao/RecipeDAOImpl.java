package api.v1.Dao;

import api.v1.Models.Recipe;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
public class RecipeDAOImpl implements RecipeDAO{

    private final String hibernateConfig = "hibernate.cfg.xml";
    private final Configuration configuration = new Configuration().configure(hibernateConfig);
    private final SessionFactory sessionFactory;
    private final String[] resources = {"Recipe.hbm.xml","Image.hbm.xml","Step.hbm.xml","Ingredient.hbm.xml"};

    {
        try{
            sessionFactory = addResources(configuration,resources).buildSessionFactory();

        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public List listRecipes(Integer initial_row, Integer rows) {

        Session session = this.sessionFactory.openSession();

        List recipes = session.
                createQuery("select name, difficulty, duration, icon_image, uuid from Recipe ")
                .setFirstResult(initial_row)
                .setMaxResults(rows).list();

        session.close();

        return recipes;

    }

    @Override
    public Recipe getRecipeByUuid(String uid) {

        Session session = this.sessionFactory.openSession();

        List list = session.createQuery("from Recipe R "+
                "join fetch R.images" +
                " join fetch R.ingredients where R.uuid = :uid")
                .setParameter("uid",uid).setMaxResults(1).list();

        session.close();

        return list.size() > 0 ? (Recipe) list.get(0) : null;
    }

    @Override
    public Recipe getRecipePreview(String uid) {

        Session session = this.sessionFactory.openSession();

        List list = session.createQuery(" from Recipe R where R.uuid = :uid")
                .setParameter("uid",uid).setMaxResults(1).list();

        session.close();

        return list.size() > 0 ? (Recipe) list.get(0) : null;
    }

    @Override
    public  List searchRecipesByKeyword( String sentence, Integer initial_row, Integer rows){

        Session session = this.sessionFactory.openSession();

        List recipes = session.createQuery(
                "select distinct name, difficulty, duration, icon_image, uuid from Recipe "+
                        "where fts('english',lower(trim(name)), :sentence) = true")
                .setParameter("sentence",sentence.trim().toLowerCase())
                .setFirstResult(initial_row)
                .setMaxResults(rows).list();

        session.close();

        return recipes;
    }

    @Override
    public Integer addRecipe(Recipe recipe, Set images, Set ingredients, Set steps){

        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        Integer recipeID = null;

        try{
            tx = session.beginTransaction();

            recipe.setImages(images);
            recipe.setIngredients(ingredients);
            recipe.setSteps(steps);
            recipeID = (Integer) session.save(recipe);

            tx.commit();
        }catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }

        return recipeID;
    }

    private Configuration addResources(Configuration configuration, String[] resources){
        for( String resource : resources ){
            configuration = configuration.addResource(resource);
        }
        return configuration;
    }
}