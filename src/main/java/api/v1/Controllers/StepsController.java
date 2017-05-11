package api.v1.Controllers;

import api.v1.Services.StepsService;
import api.v1.Services.StepsServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StepsController {
    private StepsService stepsService = new StepsServiceImpl();

    @RequestMapping("/api/v1/steps/{recipe_uid}")
    public ResponseEntity<?> show(@PathVariable("recipe_uid") String uid) {

        List<JSONObject> steps= this.stepsService.getStepsByUuid(uid);
        JSONObject response = new JSONObject();

        if ( steps == null ) {
            response.appendField("steps", new ArrayList<>());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.appendField("steps",steps);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}