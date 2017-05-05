package api.v1.Dao;

import api.v1.Models.Step;
import net.minidev.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristi on 5/5/17.
 */
public class StepsDAOImpl implements StepsDAO {

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
    public List<JSONObject> getStepByUuid(String uid){
        Session session = this.sessionFactory.openSession();
        List<Step> list = session.createQuery(" select steps from Recipe R where R.uuid = :uid")
                .setParameter("uid",uid).list();
        return list.size() > 0 ? jsonify(list) : null;
    }

    private JSONObject jsonify(Step s){
        JSONObject res = new JSONObject();
        res.appendField("task",s.getName());
        res.appendField("description", s.getDescription());
        return res;
    }

    private List<JSONObject> jsonify(List<Step> steps){
        List<JSONObject> jsonSteps = new ArrayList<>();
        for( Step step : steps) jsonSteps.add(jsonify(step));
        return jsonSteps;
    }
}
