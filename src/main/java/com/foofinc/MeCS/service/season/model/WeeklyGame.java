package com.foofinc.MeCS.service.season.model;

import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.TeamDTO;

public class WeeklyGame extends GameDTO {

    private int week;

    public WeeklyGame(long id, TeamDTO[] teams, int week) {
        super(id, teams);
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
