package com.foofinc.MeCS.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RankingsDTO {

    protected int season;
    protected int week;
    protected PollsDTO[] polls;

    public RankingsDTO(int season, int week, PollsDTO[] polls) {
        this.season = season;
        this.week = week;
        this.polls = polls;
    }

    public RankingsDTO() {}

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public PollsDTO[] getPolls() {
        return polls;
    }

    public void setPolls(PollsDTO[] polls) {
        this.polls = polls;
    }
}
