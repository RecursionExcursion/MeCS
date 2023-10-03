package com.foofinc.MeCS.service.ranking;

public class RankingParams {

    private int year;

    private int winWeight;
    private int lossWeight;

    private int OffWeight;
    private int DefWeight;

    private int pointsForWeight;
    private int pointsAllowedWeight;

    private int pollInertiaWeight;
    private int scheduleStrengthWeight;

    public RankingParams() {}

    public RankingParams(int year,
                         int winWeight,
                         int lossWeight,
                         int offWeight,
                         int defWeight,
                         int pointsForWeight,
                         int pointsAllowedWeight,
                         int pollInertiaWeight,
                         int scheduleStrengthWeight) {
        this.year = year;
        this.winWeight = winWeight;
        this.lossWeight = lossWeight;
        OffWeight = offWeight;
        DefWeight = defWeight;
        this.pointsForWeight = pointsForWeight;
        this.pointsAllowedWeight = pointsAllowedWeight;
        this.pollInertiaWeight = pollInertiaWeight;
        this.scheduleStrengthWeight = scheduleStrengthWeight;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWinWeight() {
        return winWeight;
    }

    public void setWinWeight(int winWeight) {
        this.winWeight = winWeight;
    }

    public int getLossWeight() {
        return lossWeight;
    }

    public void setLossWeight(int lossWeight) {
        this.lossWeight = lossWeight;
    }

    public int getOffWeight() {
        return OffWeight;
    }

    public void setOffWeight(int offWeight) {
        OffWeight = offWeight;
    }

    public int getDefWeight() {
        return DefWeight;
    }

    public void setDefWeight(int defWeight) {
        DefWeight = defWeight;
    }

    public int getPointsForWeight() {
        return pointsForWeight;
    }

    public void setPointsForWeight(int pointsForWeight) {
        this.pointsForWeight = pointsForWeight;
    }

    public int getPointsAllowedWeight() {
        return pointsAllowedWeight;
    }

    public void setPointsAllowedWeight(int pointsAllowedWeight) {
        this.pointsAllowedWeight = pointsAllowedWeight;
    }

    public int getPollInertiaWeight() {
        return pollInertiaWeight;
    }

    public void setPollInertiaWeight(int pollInertiaWeight) {
        this.pollInertiaWeight = pollInertiaWeight;
    }

    public int getScheduleStrengthWeight() {
        return scheduleStrengthWeight;
    }

    public void setScheduleStrengthWeight(int scheduleStrengthWeight) {
        this.scheduleStrengthWeight = scheduleStrengthWeight;
    }
}
