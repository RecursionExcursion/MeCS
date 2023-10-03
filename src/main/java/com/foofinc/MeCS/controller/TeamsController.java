package com.foofinc.MeCS.controller;

import com.foofinc.MeCS.service.TeamsService;
import com.foofinc.MeCS.service.ranking.RankingParams;
import com.foofinc.MeCS.service.ranking.SeasonRankings;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TeamsController {

    private final TeamsService service;

    public TeamsController(TeamsService service) {
        this.service = service;
    }

    @GetMapping("/teams")
    public ResponseEntity<String> getTeams(){
        return new ResponseEntity<>("teams", HttpStatusCode.valueOf(200));
    }

    @GetMapping(value = "/rankings", consumes = "application/json")
    public ResponseEntity<SeasonRankings> getRankedTeams(@RequestBody RankingParams rankingParams) {

        SeasonRankings rankings = service.getRankings(rankingParams);

        return new ResponseEntity<>(rankings, HttpStatusCode.valueOf(200));
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
