package com.foofinc.MeCS.service;

import com.foofinc.MeCS.repository.TeamsRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamsService {

    private final TeamsRepository repository;

    public TeamsService(TeamsRepository repository) {
        this.repository = repository;
    }

    public String getTeams(){
        return "Teams";
    }
}
