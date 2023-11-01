package com.foofinc.MeCS.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PollsDTO {

    protected String poll;
    protected PreseasonSchoolDTO[] ranks;

    public PollsDTO(String poll, PreseasonSchoolDTO[] ranks) {
        this.poll = poll;
        this.ranks = ranks;
    }

    public PollsDTO() {}

    public String getPoll() {
        return poll;
    }

    public void setPoll(String poll) {
        this.poll = poll;
    }

    public PreseasonSchoolDTO[] getRanks() {
        return ranks;
    }

    public void setRanks(PreseasonSchoolDTO[] ranks) {
        this.ranks = ranks;
    }
}
