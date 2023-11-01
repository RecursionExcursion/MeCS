package com.foofinc.MeCS.repository.cfb_api;

class UrlBuilder {

    static String getSchoolsUrl(int year) {
        String pathVars = String.format("?year=%s", year);
        return UrlConstants.schoolsUrlString + pathVars;
    }

    static String getGameStatsUrl(int year, int week, String season) {
        String pathVars = String.format("?year=%s&week=%s&seasonType=%s&classification=fbs", year, week, season);
        return UrlConstants.gameStatsUrlString + pathVars;
    }

    static String getPreseasonRankings(int year) {
        String pathVars = String.format("?year=%s&week=1&seasonType=%s", year, UrlConstants.regularSeason);
        return UrlConstants.preseasonRankings + pathVars;
    }
}