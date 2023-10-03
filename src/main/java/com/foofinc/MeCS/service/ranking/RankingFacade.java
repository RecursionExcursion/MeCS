package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.service.stats.SeasonStats;
import com.foofinc.MeCS.service.stats.TeamStats;

import java.util.List;
import java.util.Map;

public class RankingFacade {


    public SeasonRankings rankSeason(SeasonStats seasonStats, RankingParams rankingParams) {

        RankedSeasonStats rankedSeasonStats = StatRanker.calculateStatRanksForEachWeek(seasonStats);

        calculateWeight(rankedSeasonStats, rankingParams.getWinWeight());
        return null;
    }

    private void applyWeight(RankedSeasonStats rankedSeasonStats, RankingParams rankingParams) {

    }

    private void calculateWeight(RankedSeasonStats rankedSeasonStats, int paramWeight) {

        List<Map<RankingCategory, List<TeamStats>>> weeksInSeason = rankedSeasonStats.getWeeksInSeason();

        for (Map<RankingCategory, List<TeamStats>> statMap : weeksInSeason) {
            int currentWeight = 1;
            int statStandard = -1;
            for (TeamStats team : statMap.get(RankingCategory.OFFENSE_PG)) {
                if (statStandard == -1) {
                    statStandard = team.getWins();
                }
                if (team.getWins() == statStandard) {
                    currentWeight++;
                }
                team.addWeight(currentWeight * paramWeight);
            }
        }
    }
}
