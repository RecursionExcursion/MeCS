package com.foofinc.MeCS.service.ranking;

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
        this.schedule = new Schedule(
                new ArrayList<>(oldTeam.getSchedule().getGamesPlayed()),
                oldTeam.getSchedule().getWins(),
                oldTeam.getSchedule().getLosses()
        );
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

    public Schedule getSchedule() {
        return schedule;
    }

    public Double getWeight() {
        return weight;
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


    /*
    Getter Methods- Method names are tightly coupled to Ranking Algo Strings
     */
    public int getWins() {
        return schedule.getWins();
    }

    public int getLosses() {
        return schedule.getLosses();
    }

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

    public int getGamesPlayed() {
        return schedule.gamesPlayed.size();
    }

    public double getStrengthOfSchedulePerGame() {
        if (strengthOfSchedule == -1.0) {
            throw new IllegalStateException("Strength of Schedule has not been calculated");
        }
        return (double) strengthOfSchedule / getGamesPlayed();
    }

    public int getStrengthOfSchedule() {
        return strengthOfSchedule;
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

        private List<GameDTO> gamesPlayed = new ArrayList<>();
        private int wins;
        private int losses;

        Schedule() {
            wins = 0;
            losses = 0;
        }

        public Schedule(List<GameDTO> gamesPlayed, int wins, int loses) {
            this.wins = wins;
            this.losses = loses;
            this.gamesPlayed = gamesPlayed;
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

        public void incWins() {
            wins++;
        }

        public void incLoses() {
            losses++;
        }
    }
}
