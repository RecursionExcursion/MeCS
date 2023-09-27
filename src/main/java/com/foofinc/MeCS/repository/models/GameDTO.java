package com.foofinc.MeCS.repository.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDTO {

    protected long id;
    protected TeamDTO[] teams;

    public GameDTO(long id, TeamDTO[] teams) {
        this.id = id;
        this.teams = teams;
    }

    public GameDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TeamDTO[] getTeams() {
        return teams;
    }

    public void setTeams(TeamDTO[] teams) {
        this.teams = teams;
    }
}
