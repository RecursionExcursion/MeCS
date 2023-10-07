package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.service.stats.TeamStats;

import java.util.ArrayList;
import java.util.List;

public class RankedSeasonStats {

    private final List< List<TeamStats>> weeksInSeason = new ArrayList<>();

    public List<List<TeamStats>> getWeeksInSeason() {
        return weeksInSeason;
    }
}
