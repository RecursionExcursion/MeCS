package com.foofinc.MeCS.repository.cfb_api;

public class SeasonBuilder {

    public static Season buildSeason(int year) {
       return new Season(DataResolver.getTeams(year), DataResolver.getGames(year));
    }
}
