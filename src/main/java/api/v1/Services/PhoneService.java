package api.v1.Services;
import api.v1.Models.Phone;
import java.util.List;

public interface PhoneService {
    void addPhone(Phone p);
    void updatePhone(Phone p);
    List<Phone> listPhones();
    Phone getPhoneById(int id);
    void removePhone(int id);
}