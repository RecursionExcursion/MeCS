package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.service.stats.SeasonStats;
import com.foofinc.MeCS.service.stats.TeamStats;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class WeightCalculator {

    private final SeasonStats seasonStats;
    private final RankingParams rankingParams;

    WeightCalculator(SeasonStats seasonStats, RankingParams rankingParams) {
        this.seasonStats = seasonStats;
        this.rankingParams = rankingParams;
    }

    /*
    This method calculates the non rank dependent stats, Wins, Losses. Off, Etc
    Forgoes poll inertia and Strength of Schedule
     */
    List<List<TeamStats>> calculateBaseWeights() {

        calculateWeight(TeamStatsReflectionManager.getOffensePerGameMethod(), RankingCategory.OFFENSE_PG);

        calculateWeight(TeamStatsReflectionManager.getDefensePerGameMethod(), RankingCategory.DEFENSE_PG);

        calculateWeight(TeamStatsReflectionManager.getPointsForPerGameMethod(), RankingCategory.POINTS_FOR_PG);

        calculateWeight(TeamStatsReflectionManager.getPointsAllowedPerGameMethod(), RankingCategory.POINTS_ALLOWED_PG);

        calculateWeight(TeamStatsReflectionManager.getWinsMethod(), RankingCategory.WINS);

        calculateWeight(TeamStatsReflectionManager.getLossesMethod(), RankingCategory.LOSSES);

        List<List<TeamStats>> weightedSeason = new ArrayList<>();

        for (List<TeamStats> weeklyStat : seasonStats.getWeeklyStats()) {
            List<TeamStats> sortedWeeklyStat = new ArrayList<>(weeklyStat);
            sortedWeeklyStat.sort(Comparator.comparingDouble(TeamStats::getWeight));
            weightedSeason.add(sortedWeeklyStat);
        }
        return weightedSeason;
    }

    public void calculatePollInertiaWeight() {

        seasonStats.getWeeklyStats()


    }

    public void calculateStrengthOfSchedule() {

    }

    private void calculateWeight(Method method, RankingCategory rankingCategory) {

        int paramWeight = getParamWeight(rankingCategory);

        for (List<TeamStats> weeklyStat : seasonStats.getWeeklyStats()) {

            int currentWeight = 1;
            double statStandard = -1;
            List<TeamStats> sortedStats = StatRanker.sortTeamsAgainstStat(weeklyStat, rankingCategory);

            for (TeamStats team : sortedStats) {

                double stats = getStatStandard(method, team);

                if (statStandard == -1) {
                    statStandard = stats;
                } else if (team.getOffensePerGame() != statStandard) {
                    currentWeight++;
                    statStandard = stats;
                }
                team.addWeight(currentWeight * paramWeight);
            }
        }
    }

    private static double getStatStandard(Method method, TeamStats team) {
        try {
            Object result = method.invoke(team);

            if (result instanceof Integer) {
                // If the result is an Integer, cast it to int and convert to double
                return (double) ((int) result);
            } else if (result instanceof Double) {
                // If the result is a Double, cast it to double
                return (double) result;
            } else {
                // Handle other types or throw an exception if needed
                throw new RuntimeException("Unsupported return type: " + result.getClass().getName());
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private int getParamWeight(RankingCategory rankingCategory) {
        int weightMultiplier;
        switch (rankingCategory) {
            case WINS -> weightMultiplier =  rankingParams.getWinWeight();
            case LOSSES -> weightMultiplier = rankingParams.getLossWeight();
            case OFFENSE_PG -> weightMultiplier = rankingParams.getOffWeight();
            case DEFENSE_PG -> weightMultiplier = rankingParams.getDefWeight();
            case POINTS_ALLOWED_PG -> weightMultiplier = rankingParams.getPointsAllowedWeight();
            case POINTS_FOR_PG -> weightMultiplier = rankingParams.getPointsForWeight();
            case POLL_INERTIA -> weightMultiplier = rankingParams.getPollInertiaWeight();
            case STRENGTH_OF_SCHEDULE -> weightMultiplier = rankingParams.getScheduleStrengthWeight();
            default -> throw new NullPointerException();
        }
        return weightMultiplier;
    }


}
