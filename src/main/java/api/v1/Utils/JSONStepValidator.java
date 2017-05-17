package api.v1.Utils;

import net.minidev.json.JSONObject;

/**
 * Created by Vlad Stefan on 5/17/2017.
 */
public class JSONStepValidator implements Validator {


    /**
     * Validate if a  JsonObject respects the step structure
     * (name, description)
     *
     * @return a boolean, the validation result
     * @recipe the JSONObject to validate
     */
    public boolean isValid(Object toValidate) {

        JSONObject step;
        try {
            step = (JSONObject) toValidate;
        } catch (Exception e) {
            return false;
        }

        String taskName;
        String description;
        try {
            taskName = step.getAsString("task");
            description = step.getAsString("description");
        } catch (Exception e) {
            System.out.println("Cast");

            return false;
        }
        // invalid taskName

        if (taskName.trim() == "") {
            return false;
        }

        // invalid description
        if (description.trim() == "") {
            return false;
        }

        return true;
    }
}