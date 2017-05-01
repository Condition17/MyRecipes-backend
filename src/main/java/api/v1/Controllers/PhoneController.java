package api.v1.Controllers;
import api.v1.Models.Phone;
import api.v1.Services.PhoneService;
import api.v1.Services.PhoneServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneController {
    private PhoneService phoneService = new PhoneServiceImpl();

    @RequestMapping(value = "/phones", method = RequestMethod.GET)
    public ResponseEntity<?> listPhones(){
        JSONObject res = new JSONObject();
        List<Phone> list = this.phoneService.listPhones();
        res.appendField("recipes",list);
        return list.size() == 0  ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res) : ResponseEntity.status(HttpStatus.OK).body(res);
    }

//    @RequestMapping("/remove/{id}")
//    public String removePhone(@PathVariable("id") int id) {
//        this.phoneService.removePhone(id);
//        return "redirect:/phones";
//    }
    @RequestMapping("/phones/{id}")
    public Phone show(@PathVariable("id") int id) {
        Phone p = this.phoneService.getPhoneById(id);
        return p;
    }
}