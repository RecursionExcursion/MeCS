package com.foofinc.MeCS.repository.cfb_api;

public class SeasonBuilder {

    public static SeasonRecord buildSeason(int year) {
       return new SeasonRecord(DataResolver.getTeams(year), DataResolver.getGames(year));
    }
}
