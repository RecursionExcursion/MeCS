package com.foofinc.MeCS.repository.cfb_api;

import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.SchoolDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

class DataResolverTest {

    @Test
    void getTeams() {
        List<SchoolDTO> teams = DataResolver.getTeams(2022);
    }

    @Test
    void getGames() {
        List<List<GameDTO>> games = DataResolver.getGames(2022);
    }
}