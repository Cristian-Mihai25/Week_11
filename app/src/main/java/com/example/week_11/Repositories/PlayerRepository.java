package com.example.week_11.Repositories;

import com.example.week_11.Entities.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerRepository extends Repository<Player> {
    public List<Player> filterByTeam(String team) {
        return items.stream().filter(player -> player.getName().equalsIgnoreCase(team)).collect(Collectors.toList());
    }
}