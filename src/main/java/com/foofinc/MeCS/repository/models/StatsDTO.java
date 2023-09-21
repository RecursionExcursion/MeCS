package com.foofinc.MeCS.repository.models;

public class StatsDTO {
    private String category;
    private String stat;

    public StatsDTO(String category, String stat) {
        this.category = category;
        this.stat = stat;
    }

    public StatsDTO() {}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
