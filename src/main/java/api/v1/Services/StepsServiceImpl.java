package api.v1.Services;

import api.v1.Dao.StepsDAO;
import api.v1.Dao.StepsDAOImpl;
import net.minidev.json.JSONObject;

import java.util.List;

/**
 * Created by cristi on 5/5/17.
 */
public class StepsServiceImpl implements StepsService {
    private StepsDAO stepsDAO = new StepsDAOImpl();

    @Override
    public List<JSONObject> getStepByUuid(String uuid){
        return stepsDAO.getStepByUuid(uuid);
    }

}
