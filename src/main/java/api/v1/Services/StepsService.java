package api.v1.Services;

import net.minidev.json.JSONObject;

import java.util.List;

public interface StepsService {
    List<JSONObject> getStepsByUuid(String uuid);
}
