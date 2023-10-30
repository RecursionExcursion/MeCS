package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.service.stats.SeasonStats;
import com.foofinc.MeCS.service.stats.TeamStats;

import java.util.ArrayList;
import java.util.List;

public class RankingFacade {

    private final SeasonStats seasonStats;
    private final RankingParams rankingParams;

    public RankingFacade(SeasonStats seasonStats, RankingParams rankingParams) {
        this.seasonStats = seasonStats;
        this.rankingParams = rankingParams;
    }

    public SeasonRankings rankSeason() {
        WeightCalculator weightCalculator = new WeightCalculator(seasonStats, rankingParams);

        //Teams are now being weighted off base stats
        List<List<TeamStats>> initialWeights = weightCalculator.calculateBaseWeights();

        //Teams are now being ranked off base stats
        List<List<TeamStats>> baseWeightRankings = rankBaseWeights(initialWeights);

        //Calc poll inertia weight
        weightCalculator.calculatePollInertiaWeight();

        //Calc Strength of Schedule
        weightCalculator.calculateStrengthOfSchedule();
        return null;
    }

    private static List<List<TeamStats>> rankBaseWeights(List<List<TeamStats>> season) {
        List<List<TeamStats>> copy = new ArrayList<>();

//        for (List<TeamStats> week : season) {
//            List<TeamStats> rankedWeek = StatRanker.sortTeamsAgainstWeight(week);
//            copy.add(rankedWeek);
//        }

        season.forEach(week -> copy.add(StatRanker.sortTeamsAgainstWeight(week)));
        return copy;
    }
}
