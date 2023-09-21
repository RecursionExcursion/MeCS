package com.foofinc.MeCS.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDTO {

    private long schoolId;
    private String school;
    private String homeAway;
    private int points;
    private StatsDTO[] stats;

    public TeamDTO(long schoolId, String school, String homeAway, int points, StatsDTO[] stats) {
        this.schoolId = schoolId;
        this.school = school;
        this.homeAway = homeAway;
        this.points = points;
        this.stats = stats;
    }

    public TeamDTO() {}

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHomeAway() {
        return homeAway;
    }

    public void setHomeAway(String homeAway) {
        this.homeAway = homeAway;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public StatsDTO[] getStats() {
        return stats;
    }

    public void setStats(StatsDTO[] stats) {
        this.stats = stats;
    }
}
