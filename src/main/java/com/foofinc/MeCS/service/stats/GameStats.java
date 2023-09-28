package com.foofinc.MeCS.service.stats;

public record GameStats(int totalOffense,
                        int totalDefense,
                        int pointsFor,
                        int pointsAllowed,
                        boolean win) {}
