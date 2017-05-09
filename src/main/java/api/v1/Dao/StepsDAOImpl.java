package api.v1.Dao;

import api.v1.Models.Step;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 * Created by cristi on 5/5/17.
 */
public class StepsDAOImpl implements StepsDAO {
    private String[] resources = { "Step.hbm.xml", "Recipe.hbm.xml" };
    private String hibernateConfig = "hibernate.cfg.xml";
    private Configuration configuration = new Configuration().configure(hibernateConfig);
    private SessionFactory sessionFactory;

    {
        try{
            sessionFactory = addResources(configuration,resources)
                            .buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public List<Step> getStepsByUuid(String uid){
        Session session = this.sessionFactory.openSession();
        List<Step> list = session.createQuery(" select steps from Recipe R where R.uuid = :uid")
                .setParameter("uid",uid).list();
        return list.size() > 0 ? list : null;
    }

    private Configuration addResources(Configuration configuration, String[] resources){
        for( String resource : resources ){
            configuration = configuration.addResource(resource);
        }
        return configuration;
    }



}
