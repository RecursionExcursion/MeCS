package com.foofinc.MeCS.service.stats;

import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.SchoolDTO;

import java.util.ArrayList;
import java.util.List;

public class TeamStats {

    private final SchoolDTO school;

    private int rank;
    private final Schedule schedule;
    private Double weight;

    private int totalOffense;
    private int totalDefense;

    private int pointsAllowed;
    private int pointsFor;

    private int strengthOfSchedule;

    //TODO May need to be rewritten if ranking algorithm is altered

    public TeamStats(SchoolDTO school) {
        this.school = school;
        rank = 0;
        schedule = new Schedule();
        totalOffense = 0;
        totalDefense = 0;
        pointsAllowed = 0;
        pointsFor = 0;
        strengthOfSchedule = 0;
        weight = 0.0;
    }

    public TeamStats(TeamStats oldTeam) {
        this.school = oldTeam.school;
        this.rank = 0;
        this.schedule = new Schedule(oldTeam.schedule);
        this.totalOffense = oldTeam.getTotalOffense();
        this.totalDefense = oldTeam.getTotalDefense();
        this.pointsAllowed = oldTeam.getPointsAllowed();
        this.pointsFor = oldTeam.getPointsFor();
        this.strengthOfSchedule = -1;
        this.weight = 0.0;
    }

    public SchoolDTO getSchool() {
        return school;
    }

    public int getRank() {
        return rank;
    }

    public Double getWeight() {
        return weight;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public int getWins() {
        return schedule.getWins();
    }

    public int getLosses() {
        return schedule.getLosses();
    }

    public int getTotalOffense() {
        return totalOffense;
    }

    public int getTotalDefense() {
        return totalDefense;
    }

    public int getPointsAllowed() {
        return pointsAllowed;
    }

    public int getPointsFor() {
        return pointsFor;
    }

    public int getNumberOfGamesPlayed() {
        return schedule.gamesPlayed.size();
    }

    public int getStrengthOfSchedule() {
        return strengthOfSchedule;
    }

    /*Adder Methods*/
    public void addToTotalOffense(int offense) {
        this.totalOffense += offense;
    }

    public void addToTotalDefense(int defense) {
        this.totalDefense += defense;
    }

    public void addToPointsAllowed(int pointsAllowed) {
        this.pointsAllowed += pointsAllowed;
    }

    public void addToPointsFor(int pointsFor) {
        this.pointsFor += pointsFor;
    }

    public void addWeight(int addedWeight) {
        weight += addedWeight;
    }

    /*
    PG Getter Methods
     */
    public double getPointsForPerGame() {
        return (double) pointsFor / schedule.gamesPlayed.size();
    }

    public double getPointsAllowedPerGame() {
        return (double) pointsAllowed / schedule.gamesPlayed.size();
    }

    public double getOffensePerGame() {
        return (double) totalOffense / schedule.gamesPlayed.size();
    }

    public double getDefensePerGame() {
        return (double) totalDefense / schedule.gamesPlayed.size();
    }

    public double getStrengthOfSchedulePerGame() {
        if (strengthOfSchedule == -1.0) {
            throw new IllegalStateException("Strength of Schedule has not been calculated");
        }
        return (double) strengthOfSchedule / getNumberOfGamesPlayed();
    }

    /*
    Setter Methods
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setStrengthOfSchedule(int strengthOfSchedule) {
        this.strengthOfSchedule = strengthOfSchedule;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public static class Schedule {

        private final List<GameDTO> gamesPlayed;
        private int wins;
        private int losses;

        private Schedule() {
            wins = 0;
            losses = 0;
            gamesPlayed = new ArrayList<>();
        }

        private Schedule(Schedule schedule) {
            this.wins = schedule.wins;
            this.losses = schedule.losses;
            this.gamesPlayed = new ArrayList<>(schedule.gamesPlayed);
        }

        public List<GameDTO> getGamesPlayed() {
            return gamesPlayed;
        }

        public void addGame(GameDTO game) {
            gamesPlayed.add(game);
        }

        public int getWins() {
            return wins;
        }

        public int getLosses() {
            return losses;
        }

        public double getWinRate() {

            if (gamesPlayed.size() != wins + losses) {
                String message = String.format("Schedule has %s present, but Wins + Losses = %s",
                                         gamesPlayed.size(), wins + losses);
                throw new RuntimeException(message);
            }

            return ((double) wins / (wins + losses));
        }

        public void incWins() {
            wins++;
        }

        public void incLoses() {
            losses++;
        }
    }
}
