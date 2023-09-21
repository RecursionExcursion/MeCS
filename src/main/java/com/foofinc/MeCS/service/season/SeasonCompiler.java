package com.foofinc.MeCS.service.season;

import com.foofinc.MeCS.repository.cfb_api.Season;
import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.TeamDTO;
import com.foofinc.MeCS.service.season.model.WeeklyGame;
import com.foofinc.MeCS.service.season.model.CompleteSchool;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SeasonCompiler {

    public static SeasonManager compileSeason(Season season) {

        SeasonManager seasonManager = new SeasonManager();

        /* Order of next 2 methods must be maintained */
        compileSchools(season, seasonManager);
        compileGames(season.weeks(), seasonManager);

        return seasonManager;
    }

    private static void compileSchools(Season season, SeasonManager seasonManager) {
        season.teams().forEach(team -> seasonManager.getSchoolMap().put(team.getId(), new CompleteSchool(team)));
    }

    private static void compileGames(List<List<GameDTO>> weeks, SeasonManager seasonManager) {

        for (int i = 0; i < weeks.size(); i++) {
            for (GameDTO game : weeks.get(i)) {
                TeamDTO[] teams = game.getTeams();

                /* may be null if not fbs team */
                Optional<CompleteSchool> homeSchoolOptional =
                        Optional.ofNullable(seasonManager.getSchoolMap().get(getHomeTeam(teams).getSchoolId()));

                Optional<CompleteSchool> awaySchoolOptional =
                        Optional.ofNullable(seasonManager.getSchoolMap().get(getAwayTeam(teams).getSchoolId()));

                /* Week # is equal to list index + 1 */
                WeeklyGame weeklyGame = new WeeklyGame(game.getId(), game.getTeams(), i + 1);

                homeSchoolOptional.ifPresent(school -> addGame(school, weeklyGame));
                awaySchoolOptional.ifPresent(school -> addGame(school, weeklyGame));
            }
        }
    }

    private static TeamDTO getHomeTeam(TeamDTO[] teams) {
        return Arrays.stream(teams)
                     .filter(team -> team.getHomeAway().equals("home"))
                     .findFirst()
                     .orElseThrow();
    }

    private static TeamDTO getAwayTeam(TeamDTO[] teams) {
        return Arrays.stream(teams)
                     .filter(team -> team.getHomeAway().equals("away"))
                     .findFirst()
                     .orElseThrow();
    }

    private static void addGame(CompleteSchool school, WeeklyGame game) {
        school.getSchedule().add(game);
    }
}
