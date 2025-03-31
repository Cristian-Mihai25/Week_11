package com.example.week_11.Iterators;

import com.example.week_11.Entities.Player;
import com.example.week_11.Entities.Team;
import com.example.week_11.Interfaces.CustomIterator;

import java.util.List;

public class PlayerIterator implements CustomIterator<Player> {
    private List<Player> players;
    private int position = 0;

    public PlayerIterator(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean hasNext() {
        return position < players.size();
    }

    @Override
    public Player next() {
        return hasNext() ? players.get(position++) : null;
    }

    public static PlayerIterator getCustomIterator(List<Player> players) {
        return new PlayerIterator(players);
    }
}
