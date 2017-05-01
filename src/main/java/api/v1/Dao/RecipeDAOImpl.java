package api.v1.Dao;

import api.v1.Models.Recipe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO{

    SessionFactory sessionFactory;
    {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public List<Recipe> listRecipes() {
        Session session = this.sessionFactory.openSession();
        List<Recipe> recipes = session.createQuery("SELECT name, difficulty, description, duration, icon_image, uuid FROM Recipe").list();
        return recipes;
    }

    @Override
    public Recipe getRecipeById(int id) {
        return null;
    }
}
