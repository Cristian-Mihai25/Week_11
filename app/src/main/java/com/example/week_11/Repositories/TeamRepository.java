package com.example.week_11.Repositories;

import com.example.week_11.Entities.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamRepository extends Repository<Team> {
    public List<Team> filterByLeague(String league) {
        return items.stream().filter(team -> team.getLeague().equalsIgnoreCase(league)).collect(Collectors.toList());
    }
}