package api.v1.Services;

import api.v1.Dao.StepsDAO;
import api.v1.Dao.StepsDAOImpl;
import api.v1.Utils.DataFormatter;
import net.minidev.json.JSONObject;
import java.util.List;

public class StepsServiceImpl implements StepsService {
    private final StepsDAO stepsDAO = new StepsDAOImpl();
    private final DataFormatter formatter = new DataFormatter();
    @Override
    public List<JSONObject> getStepsByUuid(String uuid){
        return formatter.jsonify( stepsDAO.getStepsByUuid(uuid) );
    }

}
