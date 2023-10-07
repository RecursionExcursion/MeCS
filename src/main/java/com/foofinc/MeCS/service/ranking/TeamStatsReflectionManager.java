package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.service.stats.TeamStats;

import java.lang.reflect.Method;

class TeamStatsReflectionManager {

    //Method Signature strings are tightly coupled to method names in the TeamStat Class
    //TODO rework with Custom Annotation?
    private static final String OFF_METHOD_SIGNATURE = "getOffensePerGame";
    private static final String DEF_METHOD_SIGNATURE = "getDefensePerGame";

    private static final String PFPG_METHOD_SIGNATURE = "getPointsForPerGame";
    private static final String PAPG_METHOD_SIGNATURE = "getPointsAllowedPerGame";

    private static final String WINS_METHOD_SIGNATURE = "getWins";
    private static final String LOSSES_METHOD_SIGNATURE = "getLosses";

    static Method getOffensePerGameMethod() {
        return getMethod(OFF_METHOD_SIGNATURE);
    }

    static Method getDefensePerGameMethod() {
        return getMethod(DEF_METHOD_SIGNATURE);
    }

    static Method getPointsForPerGameMethod() {
        return getMethod(PFPG_METHOD_SIGNATURE);
    }

    static Method getPointsAllowedPerGameMethod() {
        return getMethod(PAPG_METHOD_SIGNATURE);
    }

    static Method getWinsMethod() {
        return getMethod(WINS_METHOD_SIGNATURE);
    }

    static Method getLossesMethod() {
        return getMethod(LOSSES_METHOD_SIGNATURE);
    }

    private static Method getMethod(String methodSignature) {
        try {
            return TeamStats.class.getMethod(methodSignature);
        } catch (NoSuchMethodException e) {
            String message = String.format("Could not find method signature \"%s\" in TeamStat object- %s",
                                           methodSignature, e);
            throw new RuntimeException(message);
        }
    }
}
