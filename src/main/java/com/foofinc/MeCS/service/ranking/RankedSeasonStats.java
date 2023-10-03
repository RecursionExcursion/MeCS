package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.service.stats.TeamStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RankedSeasonStats {

    private final List<Map<RankingCategory, List<TeamStats>>> weeksInSeason = new ArrayList<>();

    public void addWeekMap(Map<RankingCategory, List<TeamStats>> rankedWeekMap){
        weeksInSeason.add(rankedWeekMap);
    }

    public List<Map<RankingCategory, List<TeamStats>>> getWeeksInSeason() {
        return weeksInSeason;
    }
}
