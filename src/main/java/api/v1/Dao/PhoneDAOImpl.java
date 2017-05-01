package api.v1.Dao;

import api.v1.Models.Phone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PhoneDAOImpl implements PhoneDAO {
    @Autowired
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
    public void addPhone(Phone p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);

    }

    @Override
    public void updatePhone(Phone p) {
        Session session = this.sessionFactory.openSession();
        session.update(p);
    }
    @SuppressWarnings("unchecked")

    @Override
    public List<Phone> listPhones() {

        Session session = this.sessionFactory.openSession();
        List<Phone> phonesList = session.createQuery("FROM Phone").list();
        return phonesList;
    }

    @Override
    public Phone getPhoneById(int id) {
        Session session = this.sessionFactory.openSession();
        Phone p = session.load(Phone.class, new Integer(id));
        return p;
    }
    @Override
    public void removePhone(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Phone p = session.load(Phone.class, new Integer(id));
        if (null != p) {
            session.delete(p);
        }
    }

}