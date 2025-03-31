package com.example.week_11.Iterators;

import com.example.week_11.Entities.Match;
import com.example.week_11.Entities.Player;
import com.example.week_11.Interfaces.CustomIterator;

import java.util.List;

public class MatchIterator implements CustomIterator<Match> {
    private List<Match> matches;
    private int position = 0;

    public MatchIterator(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public boolean hasNext() {
        return position < matches.size();
    }

    @Override
    public Match next() {
        return hasNext() ? matches.get(position++) : null;
    }

    public static MatchIterator getCustomIterator(List<Match> players) {
        return new MatchIterator(players);
    }

}
