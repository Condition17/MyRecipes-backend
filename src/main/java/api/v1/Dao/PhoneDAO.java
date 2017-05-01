package api.v1.Dao;

import java.util.List;
import api.v1.Models.Phone;

public interface PhoneDAO {
    void addPhone(Phone p);
    void updatePhone(Phone p);
    List<Phone> listPhones();
    Phone getPhoneById(int id);
    void removePhone(int id);
}
