package com.foofinc.MeCS.service.season.model;

import com.foofinc.MeCS.repository.models.SchoolDTO;

import java.util.ArrayList;
import java.util.List;

public class CompleteSchool {

    private final SchoolDTO schoolDTO;
    private final List<WeeklyGame> schedule = new ArrayList<>();

    public CompleteSchool(SchoolDTO schoolDTO) {
        this.schoolDTO = schoolDTO;
    }

    public SchoolDTO getSchoolDTO() {
        return schoolDTO;
    }

    public List<WeeklyGame> getSchedule() {
        return schedule;
    }
}
