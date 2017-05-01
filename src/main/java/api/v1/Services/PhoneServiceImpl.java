package api.v1.Services;

import java.util.List;

import api.v1.Dao.PhoneDAO;
import api.v1.Dao.PhoneDAOImpl;
import api.v1.Models.Phone;

import javax.transaction.Transactional;


public class PhoneServiceImpl implements PhoneService {
    private PhoneDAO phoneDAO = new PhoneDAOImpl();

    @Override
    @Transactional
    public void addPhone(Phone p) {
        this.phoneDAO.addPhone(p);
    }
    @Override
    @Transactional
    public void updatePhone(Phone p) {
        this.phoneDAO.updatePhone(p);
    }
    @Override
    @Transactional
    public List<Phone> listPhones() {
        return this.phoneDAO.listPhones();
    }

    @Override
    @Transactional
    public Phone getPhoneById(int id) {
        return this.phoneDAO.getPhoneById(id);
    }
    @Override
    @Transactional
    public void removePhone(int id) {
        this.phoneDAO.removePhone(id);
    }
}