package com.foofinc.MeCS.repository.cfb_api;

import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.RankingsDTO;
import com.foofinc.MeCS.repository.models.SchoolDTO;

import java.util.List;

public record SeasonRecord(List<SchoolDTO> teams,
                           List<List<GameDTO>> weeks,
                           List<RankingsDTO> preseasonRankings) {}
