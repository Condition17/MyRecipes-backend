package api.v1.Dao;

import api.v1.Models.Step;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class StepsDAOImpl implements StepsDAO {

    private final String[] resources = { "Step.hbm.xml", "Recipe.hbm.xml" };
    private final String hibernateConfig = "hibernate.cfg.xml";
    private final Configuration configuration = new Configuration().configure(hibernateConfig);
    private final SessionFactory sessionFactory;

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

        List steps = session
                .createQuery(" select S from Recipe R inner join R.steps S where R.uuid = :uid order by S.id")
                .setParameter("uid",uid).list();

        return steps.size() > 0 ? steps : null;
    }

    private Configuration addResources(Configuration configuration, String[] resources){

        for( String resource : resources ){
            configuration = configuration.addResource(resource);
        }

        return configuration;
    }



}
