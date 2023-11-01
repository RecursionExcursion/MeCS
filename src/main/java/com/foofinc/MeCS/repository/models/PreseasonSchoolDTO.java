package com.foofinc.MeCS.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PreseasonSchoolDTO {

    protected int rank;
    protected String school;

    public PreseasonSchoolDTO(int rank, String school) {
        this.rank = rank;
        this.school = school;
    }

    public PreseasonSchoolDTO() {}

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
