package api.v1.Dao;

import net.minidev.json.JSONObject;

import java.util.List;

/**
 * Created by cristi on 5/5/17.
 */
public interface StepsDAO {
    List<JSONObject> getStepByUuid(String uuid);
}
