package com.foofinc.MeCS.service.stats;

import java.util.ArrayList;
import java.util.List;

public class SeasonStats {

    private final List<List<TeamStats>> weeklyStats = new ArrayList<>();

    public List<List<TeamStats>> getWeeklyStats() {
        return weeklyStats;
    }

    void addWeek(List<TeamStats> week) {
        weeklyStats.add(week);
    }

    public static List<TeamStats> copyWeek(List<TeamStats> week) {
        return week.stream()
                   .map(TeamStats::new)
                   .toList();
    }
}
