package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.StatsDTO;
import com.foofinc.MeCS.repository.models.TeamDTO;
import com.foofinc.MeCS.service.season.SeasonData;

import java.util.List;
import java.util.Optional;

public class SeasonWeekCompiler {

    //TODO Data copy works but needs to be tested


    public static RankedSeason compileSeason(SeasonData seasonData) {

        List<TeamStats> teamStats = seasonData.getSchools().stream()
                                              .map(TeamStats::new)
                                              .toList();

        RankedSeason rankedSeason = new RankedSeason();

        //TODO Here to view in debugging, delete
//        List<List<TeamStats>> weeklyStats = RankedSeason.getWeeklyTeams();

//        for (List<GameDTO> games : regularSeason) {
//            List<TeamStats> weekStats = compileWeek(games, teamStats);
//            rankedSeason.addWeek(weekStats);
//            //TODO Why is this here? To carry over information?
//            teamStats = new ArrayList<>(weekStats);
//        }
        //Regular Season
        for (List<GameDTO> games : seasonData.getRegularSeason()) {
            teamStats = compileWeek(games, teamStats);
            rankedSeason.addWeek(teamStats);
        }

        //Post Season
        teamStats = compileWeek(seasonData.getPostSeason(), teamStats);
        rankedSeason.addWeek(teamStats);

        return rankedSeason;
    }

    private static List<TeamStats> compileWeek(List<GameDTO> games, List<TeamStats> teamStats) {

        //Creates copy of week
        List<TeamStats> newTeamStats = RankedSeason.copyWeek(teamStats);

        for (GameDTO game : games) {

            TeamDTO[] teams = game.getTeams();

            Optional<TeamStats> school1 = newTeamStats.stream()
                                                      .filter(ts -> ts.getSchool().getId() == teams[0].getSchoolId())
                                                      .findFirst();

            Optional<TeamStats> school2 = newTeamStats.stream()
                                                      .filter(ts -> ts.getSchool().getId() == teams[1].getSchoolId())
                                                      .findFirst();

            school1.ifPresent(school -> compileGame(school, game));
            school2.ifPresent(school -> compileGame(school, game));
        }
        return newTeamStats;
    }

    private static void compileGame(TeamStats team, GameDTO game) {

        TeamDTO[] teams = game.getTeams();

        TeamDTO currentTeam = teams[0].getSchoolId() == team.getSchool().getId() ? teams[0] : teams[1];
        TeamDTO oppTeam = currentTeam == teams[0] ? teams[1] : teams[0];

        GameStats gameStats = getGameStats(game, currentTeam, oppTeam);

        addStats(team, gameStats);
        team.getSchedule().addGame(game);
    }

    private static GameStats getGameStats(GameDTO game, TeamDTO currentTeam, TeamDTO oppTeam) {
        try {
            return new GameStats(getTotalYards(currentTeam),
                                 getTotalYards(oppTeam),
                                 currentTeam.getPoints(),
                                 oppTeam.getPoints(),
                                 currentTeam.getPoints() > oppTeam.getPoints()
            );
        } catch (RuntimeException e) {
            String errorMessage = String.format("%s, game id- %s", e, game.getId());
            throw new RuntimeException(errorMessage);
        }
    }

    private static int getTotalYards(TeamDTO team) {
        for (StatsDTO stat : team.getStats()) {
            if (stat.getCategory().equals(StatConstants.TOTAL_YARDS)) {
                return Integer.parseInt(stat.getStat());
            }
        }
        String errorMessage = String.format("Yards could not be found for %s (id- %s)",
                                            team.getSchool(), team.getSchoolId());
        throw new RuntimeException(errorMessage);
    }

    private record GameStats(int totalOffense,
                             int totalDefense,
                             int pointsFor,
                             int pointsAllowed,
                             boolean win) {}

    private static void addStats(TeamStats teamStats, GameStats gameStats) {

        teamStats.addToTotalOffense(gameStats.totalOffense);
        teamStats.addToTotalDefense(gameStats.totalDefense);
        teamStats.addToPointsAllowed(gameStats.pointsAllowed);
        teamStats.addToPointsFor(gameStats.pointsFor);

        if (gameStats.win()) {
            teamStats.getSchedule().incWins();
        } else {
            teamStats.getSchedule().incLoses();
        }
    }
}
