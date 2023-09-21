package com.foofinc.MeCS.service.season;

import com.foofinc.MeCS.service.season.model.CompleteSchool;

import java.util.HashMap;
import java.util.Map;

public class SeasonManager {

    private final Map<Long, CompleteSchool> schoolMap = new HashMap<>();

    public Map<Long, CompleteSchool> getSchoolMap() {
        return schoolMap;
    }
}
