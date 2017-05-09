package api.v1.Dao;

import api.v1.Models.Step;
import net.minidev.json.JSONObject;

import java.util.List;

/**
 * Created by cristi on 5/5/17.
 */
public interface StepsDAO {
    List<Step> getStepsByUuid(String uuid);
}
