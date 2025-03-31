package com.example.week_11.Repositories;

import com.example.week_11.Entities.Match;

import java.util.List;
import java.util.stream.Collectors;

public class MatchRepository extends Repository<Match> {
    public List<Match> filterByTeam(String team) {
        return items.stream().filter(match -> match.getName().contains(team)).collect(Collectors.toList());
    }
}
