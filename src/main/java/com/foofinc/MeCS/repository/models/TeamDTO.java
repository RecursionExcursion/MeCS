package com.foofinc.MeCS.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDTO {

    public long schoolId;
    public String school;
    public String homeAway;
    public int points;
    public StatsDTO[] stats;
}
