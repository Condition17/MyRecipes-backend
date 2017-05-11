package api.v1.Services;

import api.v1.Dao.StepsDAO;
import api.v1.Dao.StepsDAOImpl;
import api.v1.Models.Step;
import net.minidev.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class StepsServiceImpl implements StepsService {
    private StepsDAO stepsDAO = new StepsDAOImpl();

    @Override
    public List<JSONObject> getStepsByUuid(String uuid){
        return jsonify( stepsDAO.getStepsByUuid(uuid) );
    }

    private JSONObject jsonify(Step s){
        JSONObject res = new JSONObject();
        res.appendField("task",s.getName());
        res.appendField("description", s.getDescription());
        return res;
    }

    private List<JSONObject> jsonify(List<Step> steps){
        List<JSONObject> jsonSteps = new ArrayList<>();
        if( steps != null ) for( Step step : steps) jsonSteps.add(jsonify(step));
        return jsonSteps;
    }

}
