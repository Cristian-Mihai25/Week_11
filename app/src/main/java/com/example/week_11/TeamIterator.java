package com.example.week_11;

import com.example.week_11.Entities.Team;
import com.example.week_11.Interfaces.CustomIterator;

import java.util.List;

public class TeamIterator implements CustomIterator<Team> {
    private List<Team> teams;
    private int position = 0;

    public TeamIterator(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean hasNext() {
        return position < teams.size();
    }

    @Override
    public Team next() {
        return hasNext() ? teams.get(position++) : null;
    }
}
