package com.foofinc.MeCS.service.ranking;

import java.util.ArrayList;
import java.util.List;

public class RankedSeason {

    private final List<List<TeamStats>> weeklyStats = new ArrayList<>();


    public List<List<TeamStats>> getWeeklyStats() {
        return weeklyStats;
    }

    public void addWeek(List<TeamStats> week) {
        weeklyStats.add(week);
    }

    public static List<TeamStats> copyWeek(List<TeamStats> week) {
        return week.stream()
                   .map(TeamStats::new)
                   .toList();
    }
}
