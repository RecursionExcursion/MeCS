package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.service.stats.TeamStats;

import java.util.ArrayList;
import java.util.List;

public class SeasonRankings {

    private List<List<TeamStats>> rankedWeeks = new ArrayList<>();

    public List<List<TeamStats>> getRankedWeeks() {
        return rankedWeeks;
    }
}
