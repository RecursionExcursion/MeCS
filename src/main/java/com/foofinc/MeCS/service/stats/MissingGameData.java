package com.foofinc.MeCS.service.stats;

import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.TeamDTO;

public class MissingGameData {

    private final static long AkronVsBuffalo2022GameId = 401506450;

    public static GameStats getGameData(GameDTO game, TeamDTO currentTeam) {

        if (game.getId() == AkronVsBuffalo2022GameId) {
            return AkronVsBuffalo2022(game, currentTeam);
        }

        throw new RuntimeException(String.format("No data for game - %s", game.getId()));
    }

    private static GameStats AkronVsBuffalo2022(GameDTO game, TeamDTO currentTeam) {
        //Away
        int akronId = 2006;
        int akronYards = 306;
        int akronScore = 22;
        //Home
        int buffaloId = 2084;
        int buffaloYards = 291;
        int buffaloScore = 23;

        MissingGame missingGame = MissingGame.Builder.builder()
                                                     .withAwayStats(akronScore, akronYards)
                                                     .withHomeStats(buffaloScore, buffaloYards).build();

        if (currentTeam.getSchoolId() == buffaloId) {
            return missingGame.getHomeGameStats();
        }
        if (currentTeam.getSchoolId() == akronId) {
            return missingGame.getAwayGameStats();
        }
        throw new RuntimeException(String.format("No data for %s id- %s",
                                                 currentTeam.getSchool(), currentTeam.getSchoolId()));
    }

    private static class MissingGame {

        private final int awayYards;
        private final int awayScore;
        private final int homeYards;
        private final int homeScore;

        private MissingGame(Builder builder) {
            awayYards = builder.awayYards;
            awayScore = builder.awayScore;
            homeYards = builder.homeYards;
            homeScore = builder.homeScore;
        }

        GameStats getHomeGameStats() {
            return new GameStats(homeYards,
                                 awayYards,
                                 homeScore,
                                 awayScore,
                                 homeScore > awayScore);
        }

        GameStats getAwayGameStats() {
            return new GameStats(awayYards,
                                 homeYards,
                                 awayScore,
                                 homeScore,
                                 awayScore > homeScore);
        }

        public static final class Builder {

            private int awayYards;
            private int awayScore;
            private int homeYards;
            private int homeScore;

            private Builder() {}

            public static Builder builder() {
                return new Builder();
            }

            public Builder withAwayStats(int awayScore, int awayYards) {
                this.awayScore = awayScore;
                this.awayYards = awayYards;
                return this;
            }

            public Builder withHomeStats(int homeScore, int homeYards) {
                this.homeScore = homeScore;
                this.homeYards = homeYards;
                return this;
            }

            public MissingGame build() {
                return new MissingGame(this);
            }
        }
    }
}
