package api.v1.Controllers;

import api.v1.Controllers.StepsController;
import api.v1.Utils.JSONStepValidator;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.Assert.assertEquals;



/**
 * Created by Vlad Stefan on 5/17/2017.
 */

public class StepsControllerTest {

    private  StepsController stepsControllerTest;

    @Before
    public void setUP(){

       stepsControllerTest = new StepsController();

    }

    @Test
    public void show(){

        succesRequestShow();
        failRequestShow();
    }


    /**
     * Tests show method of StepsController class
     * with a valid parameter.
     * It tests the respond's status and the format
     * of steps list
     */

    private void succesRequestShow(){

        String validUid = "90a731f7-a60e-4a51-bdb8-8aa46764eace";

        ResponseEntity result = stepsControllerTest.show(validUid);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assert(result.hasBody());
        JSONObject body = (JSONObject) result.getBody();
        assert(body.containsKey("steps"));
        List<JSONObject> stepsList =  (List<JSONObject>) body.get("steps");
        assert(stepsList.size() > 0);

        JSONStepValidator stepValidator = new JSONStepValidator();
        try {
            for(JSONObject step : stepsList){
                assert(stepValidator.isValid(step));
            }
        }
        catch(Exception e){
            System.out.println("couldn't iterate through recipes in index method");
        }

    }

    /**
     * Tests show method of StepsController class
     * with a wrong parameter.
     * It tests the respond's status and its body size.
     */
    private void failRequestShow(){

        String validUid = "-90a731f7-a60e-4a51-bdb8-8aa46764eace";

        ResponseEntity result = stepsControllerTest.show(validUid);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assert(result.hasBody());
        JSONObject body = (JSONObject)result.getBody();
        assert(body.containsKey("steps"));
        List<JSONObject> stepsList =  (List<JSONObject>) body.get("steps");
        assertEquals(0, stepsList.size());
    }

}

