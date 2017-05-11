package api.v1.Dao;

import api.v1.Models.Step;

import java.util.List;

public interface StepsDAO {
    List<Step> getStepsByUuid(String uuid);
}
